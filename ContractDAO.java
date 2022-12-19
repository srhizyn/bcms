/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Bean.UserBean;
import Bean.ContractBean;
import Bean.ContractorBean;
import Utility.ConnectionManager;
import Utility.StringUtil;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.apache.commons.lang3.text.WordUtils;
import java.math.*;

/**
 *
 * @author munawwarah.mahmod
 */
public class ContractDAO {
    
    //private Connection currentCon = null;  

 public ContractDAO() {
  //currentCon = ConnectionManager.getConnection();
  
 }

    //ResultSet rs = null;
public List<ContractBean> getWpc() throws SQLException {

        List<ContractBean> Contracts = new ArrayList<ContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("SELECT contractor.contractor_id, company_name from bcms_contractor as contractor\n" +
            "where contractor.is_active='Y' and contractor.contractortype_id = 1 order by contractor_id");){

            while (rs.next()) {

                ContractBean Contract = new ContractBean();
                Contract.setWpcID(rs.getInt("contractor_id"));
                Contract.setWpcName(rs.getString("company_name"));
                Contracts.add(Contract);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contracts;
    }
 
public List<ContractBean> getInsertedRevisedContractAmount(ContractBean contract) throws SQLException, ParseException {

        List<ContractBean> revisedContractAmounts = new ArrayList<ContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection(); 
             PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select revisedamount.date_create,revised_contract_amount from bcms_contract as contract\n" +
             "inner join bcms_revisedcontractamount as revisedamount on contract.contract_id = revisedamount.contract_id\n" +
             "where contract.contract_id=? and revisedamount.is_active='Y'");){
            
            preparedStatement2.setInt(1, contract.getContractID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                final String FORMAT_DATETIME = "yyyy-MM-dd";
                String RCACreateDate = "";
                String dateString = rs.getString("date_create"); 
                if (dateString != null && !dateString.isEmpty()){
                SimpleDateFormat sdf3 =
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d3 = sdf3.parse(dateString);
                sdf3.applyPattern(FORMAT_DATETIME);
                RCACreateDate = sdf3.format(d3);
                }
       
                ContractBean revisedContractAmount = new ContractBean();
                revisedContractAmount.setRevisedContractAmount(rs.getString("revised_contract_amount"));
                revisedContractAmount.setRCACreateDate((RCACreateDate));
                               
                revisedContractAmounts.add(revisedContractAmount);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revisedContractAmounts;
    }    

public void addContractReg(ContractBean contract) throws SQLException {
        try (Connection currentCon = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = currentCon.prepareStatement
                ("insert into bcms_contract(date_create,user_create,emp_ID, wpc_id, package, package_code, contract_num,contract_amount, contract_period_from, contract_period_to, jv_bumi_participation_sum) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);){
    
   
   // Parameters start with 1
   try{
   
   final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

   Date now = new Date();
   String newDateStringNow = "";
   String dateString = now.toString();  
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringNow = sdf3.format(d3);
   }
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, contract.getEmpAD());
   preparedStatement.setDouble(3, contract.getEmpID());
   preparedStatement.setInt(4, contract.getWpcID());
   preparedStatement.setString(5, contract.getContractPackage());
   preparedStatement.setString(6, contract.getContractPackageCode());
   preparedStatement.setString(7, contract.getContractNo());
   preparedStatement.setString(8, contract.getContractAmount());
  
   
   if("".equals(contract.getContractPeriodFrom())){
   preparedStatement.setNull(9, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(9, contract.getContractPeriodFrom());
   }
   
   if("".equals(contract.getContractPeriodTo())){
   preparedStatement.setNull(10, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(10, contract.getContractPeriodTo());
   }
    preparedStatement.setBigDecimal(11, contract.getJvBumiParticipationSum());
//   preparedStatement.setString(8, contract.getDateLoa());
//   preparedStatement.setString(9, contract.getDateCommencement());
//   preparedStatement.setString(10, contract.getDateProgressAudit());
//   preparedStatement.setDouble(11, contract.getRetentionSum());
//   preparedStatement.setDouble(12, contract.getVoAmount());
//   preparedStatement.setDouble(13, contract.getFinalContractSum());
//   preparedStatement.setString(14, contract.getDateCompletion());
//   preparedStatement.setString(15, contract.getDLPFrom());
//   preparedStatement.setString(16, contract.getDLPTo());
   
   preparedStatement.executeUpdate();
   ResultSet rs = preparedStatement.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        contract.setContractID(rs.getInt(1));
        //int key = rs.getInt(1);
    }
    
     try (
        PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_revisedcontractamount(date_create,user_create,contract_id,revised_contract_amount) "
                           + "values (?,?,?,?)");
        ){
       
       
        if (contract.getRevisedContractAmounts() != null){
        for (int i= 0; i < contract.getRevisedContractAmounts().length; i++){
           
           String revisedContractAmount = (String) Array.get(contract.getRevisedContractAmounts(), i);
           if (revisedContractAmount != null && !revisedContractAmount.isEmpty()){
           
                
                preparedStatement2.setString(1, newDateStringNow);
                preparedStatement2.setString(2, contract.getEmpAD());
                preparedStatement2.setInt(3, contract.getContractID());
                preparedStatement2.setString(4, revisedContractAmount);
                preparedStatement2.executeUpdate();
           } else {
               //Do nothing
                }
           
            }
        }
     }
        try (
        PreparedStatement preparedStatement3 = currentCon.prepareStatement
                   ("insert into bcms_contractvalue(date_create,user_create,contract_id,"
                           + "g1_req_nos,g1_req_amt,g1_proposal_nos,g1_proposal_amt,"
                           + "g2_req_nos,g2_req_amt,g2_proposal_nos,g2_proposal_amt,"
                           + "g3_req_nos,g3_req_amt,g3_proposal_nos,g3_proposal_amt,"
                           + "g4_req_nos,g4_req_amt,g4_proposal_nos,g4_proposal_amt,"
                           + "g5_req_nos,g5_req_amt,g5_proposal_nos,g5_proposal_amt,"
                           + "g6_req_nos,g6_req_amt,g6_proposal_nos,g6_proposal_amt,"
                           + "g1g4_req_nos,g1g4_req_amt,g1g4_proposal_nos,g1g4_proposal_amt,"
                           + "g5g6_req_nos,g5g6_req_amt,g5g6_proposal_nos,g5g6_proposal_amt,"
                           + "g1g6_req_nos,g1g6_req_amt,g1g6_proposal_nos,g1g6_proposal_amt) "
                           + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ){
       
                
                preparedStatement3.setString(1, newDateStringNow);
                preparedStatement3.setString(2, contract.getEmpAD());
                preparedStatement3.setInt(3, contract.getContractID());
                
                preparedStatement3.setInt(4, contract.getG1ReqNos());
                preparedStatement3.setBigDecimal(5, contract.getG1ReqAmt());
                preparedStatement3.setInt(6, contract.getG1ProposalNos());
                preparedStatement3.setBigDecimal(7, contract.getG1ProposalAmt());
                
                preparedStatement3.setInt(8, contract.getG2ReqNos());
                preparedStatement3.setBigDecimal(9, contract.getG2ReqAmt());
                preparedStatement3.setInt(10, contract.getG2ProposalNos());
                preparedStatement3.setBigDecimal(11, contract.getG2ProposalAmt());
                
                preparedStatement3.setInt(12, contract.getG3ReqNos());
                preparedStatement3.setBigDecimal(13, contract.getG3ReqAmt());
                preparedStatement3.setInt(14, contract.getG3ProposalNos());
                preparedStatement3.setBigDecimal(15, contract.getG3ProposalAmt());
                
                preparedStatement3.setInt(16, contract.getG4ReqNos());
                preparedStatement3.setBigDecimal(17, contract.getG4ReqAmt());
                preparedStatement3.setInt(18, contract.getG4ProposalNos());
                preparedStatement3.setBigDecimal(19, contract.getG4ProposalAmt());
                
                preparedStatement3.setInt(20, contract.getG5ReqNos());
                preparedStatement3.setBigDecimal(21, contract.getG5ReqAmt());
                preparedStatement3.setInt(22, contract.getG5ProposalNos());
                preparedStatement3.setBigDecimal(23, contract.getG5ProposalAmt());
                
                preparedStatement3.setInt(24, contract.getG6ReqNos());
                preparedStatement3.setBigDecimal(25, contract.getG6ReqAmt());
                preparedStatement3.setInt(26, contract.getG6ProposalNos());
                preparedStatement3.setBigDecimal(27, contract.getG6ProposalAmt());
                
                preparedStatement3.setInt(28, contract.getG1G4ReqNos());
                preparedStatement3.setBigDecimal(29, contract.getG1G4ReqAmt());
                preparedStatement3.setInt(30, contract.getG1G4ProposalNos());
                preparedStatement3.setBigDecimal(31, contract.getG1G4ProposalAmt());
                
                preparedStatement3.setInt(32, contract.getG5G6ReqNos());
                preparedStatement3.setBigDecimal(33, contract.getG5G6ReqAmt());
                preparedStatement3.setInt(34, contract.getG5G6ProposalNos());
                preparedStatement3.setBigDecimal(35, contract.getG5G6ProposalAmt());
                
                preparedStatement3.setInt(36, contract.getG1G6ReqNos());
                preparedStatement3.setBigDecimal(37, contract.getG1G6ReqAmt());
                preparedStatement3.setInt(38, contract.getG1G6ProposalNos());
                preparedStatement3.setBigDecimal(39, contract.getG1G6ProposalAmt());
                
                preparedStatement3.executeUpdate();
          
           
            
       
   }  
    
    catch (SQLException e) {
        e.printStackTrace();
        //currentCon.rollback();
    }
     }
   
    
    catch (SQLException e) {
        e.printStackTrace();
        //currentCon.rollback();
    }
   }
   catch(ParseException pe) {
        pe.printStackTrace();
       // currentCon.rollback();
        }
    } 
 
 public List<ContractBean> getAllContract(int jtStartIndex, int jtPageSize, String jtSorting, ContractBean contract) throws ParseException, SQLException{
 List<ContractBean> contracts = new ArrayList<ContractBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = contract.getAction();
  if (null != contract.getAction()) switch (contract.getAction()) {
          case "contractreg_list":
            if ("".equals(contract.getSearchContractNo())){ 
              query="SELECT *,ROW_NUMBER() OVER (ORDER BY contract_id desc) AS RowNum\n" +
                    "FROM bcms_contract as contract\n" +
                    "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                    "where contract.is_active='Y' " + 
                    "and company_name like '%"+contract.getSearchWpc()+"%' and package like '%"+contract.getSearchPackage()+"%' ORDER BY contract_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
               }
            else if ("".equals(contract.getSearchWpc())){
                 query="SELECT *,ROW_NUMBER() OVER (ORDER BY contract_id desc) AS RowNum\n" +
                    "FROM bcms_contract as contract\n" +
                    "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                    "where contract.is_active='Y' " + 
                    "and contract_num like '%"+contract.getSearchContractNo()+"%' and package like '%"+contract.getSearchPackage()+"%' ORDER BY contract_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
            
            }
            else if ("".equals(contract.getSearchPackage())){
                 query="SELECT *,ROW_NUMBER() OVER (ORDER BY contract_id desc) AS RowNum\n" +
                    "FROM bcms_contract as contract\n" +
                    "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                    "where contract.is_active='Y' " + 
                    "and wpc like '%"+contract.getSearchWpc()+"%' and contract_num like '%"+contract.getSearchContractNo()+"% ORDER BY contract_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
            
            }
               else {
               query="SELECT *,ROW_NUMBER() OVER (ORDER BY contract_id desc) AS RowNum\n" +
                    "FROM bcms_contract as contract\n" +
                    "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                    "where contract.is_active='Y' " + 
                    "and wpc like '%"+contract.getSearchWpc()+"%' and contract_num like '%"+contract.getSearchContractNo()+"%' and package like '%"+contract.getSearchPackage()+"%' ORDER BY contract_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
               }
                break;
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();){
   
   
   while (rs.next()) {
    contract = new ContractBean();
    
   
   final String FORMAT_DATETIME = "dd-MM-yyyy";
   String newDateStringDateCreate = "";
   String dateString = rs.getString("date_create");   
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringDateCreate = sdf3.format(d3);
   }
    contract.setContractID(rs.getInt("contract_id"));
    //contract.setCompanyName(rs.getString("company_name"));
    contract.setDateCreate(newDateStringDateCreate);
    contract.setRowNumber(rs.getInt("RowNum"));
    contract.setWpcName(rs.getString("company_name"));
    contract.setContractPackage(rs.getString("package"));
    contract.setContractPackageCode(rs.getString("package_code"));
    contract.setContractNo(rs.getString("contract_num"));
    contract.setContractAmount(rs.getString("contract_amount"));
    contract.setTotalBumiParticipationSum(rs.getBigDecimal("total_bumi_participation_sum"));
    contract.setTotalBumiParticipationPercent(rs.getBigDecimal("total_bumi_participation_percent"));
    
    //String user_create = rs.getString("emp_name");
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //contract.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    contracts.add(contract);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return contracts;
 }
 
 public int getContractCount(ContractBean contract) throws SQLException{
 int count=0;
 try (Connection currentCon = ConnectionManager.getConnection();
      Statement statement = currentCon.createStatement();){
                
                ResultSet rs;
                if (null != contract.getAction()) switch (contract.getAction()) {
                    
                case "contractreg_list":
                    if ("".equals(contract.getSearchContractNo())){ 
                    rs = 
                             statement.executeQuery("SELECT count(*) as count\n" +
                             "FROM bcms_contract as contract\n" +
                             "inner join v_active_user as actv on actv.emp_id = contract.emp_id\n" +
                             "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                             "where contract.is_active='Y'\n" +
                             "and company_name like '%"+contract.getSearchWpc()+"%' and package like '%"+contract.getSearchPackage()+"%' ");
                         while (rs.next()) {
                         count=rs.getInt("count");
                       }
               }
                else if ("".equals(contract.getSearchWpc())){
                      rs = 
                            statement.executeQuery("SELECT count(*) as count\n" +
                            "FROM bcms_contract as contract\n" +
                            "inner join v_active_user as actv on actv.emp_id = contract.emp_id\n" +
                            "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                            "where contract.is_active='Y'\n" +
                            "and contract_num like '%"+contract.getSearchContractNo()+"%' and package like '%"+contract.getSearchPackage()+"%' ");
                        while (rs.next()) {
                        count=rs.getInt("count");
                      }

                }
                else if ("".equals(contract.getSearchPackage())){
                      rs = 
                            statement.executeQuery("SELECT count(*) as count\n" +
                            "FROM bcms_contract as contract\n" +
                            "inner join v_active_user as actv on actv.emp_id = contract.emp_id\n" +
                            "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                            "where contract.is_active='Y'\n" +
                            "and wpc like '%"+contract.getSearchWpc()+"%' and contract_num like '%"+contract.getSearchContractNo()+"% ");
                        while (rs.next()) {
                        count=rs.getInt("count");
                      }

                }
                else {
                 rs = 
                         statement.executeQuery("SELECT count(*) as count\n" +
                         "FROM bcms_contract as contract\n" +
                         "inner join v_active_user as actv on actv.emp_id = contract.emp_id\n" +
                         "inner join bcms_contractor as c on c.contractor_id = contract.wpc_id\n" +
                         "where contract.is_active='Y'\n" +
                         "and wpc like '%"+contract.getSearchWpc()+"%' and contract_num like '%"+contract.getSearchContractNo()+"%' and package like '%"+contract.getSearchPackage()+"%' ");
                     while (rs.next()) {
                     count=rs.getInt("count");
                   }
                }
                break;
                    
                }
                //currentCon.commit();
  
   
 } catch (SQLException e) {
  e.printStackTrace();
 }
 return count;
} 
 
 public ContractBean getContractById(ContractBean contract) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement("select * from bcms_contract as contract inner join bcms_contractor as c on c.contractor_id = contract.wpc_id where contract_id=?");
           PreparedStatement preparedStatement2 = currentCon.prepareStatement("select * from bcms_contractvalue as contractval where contract_id=? and is_active='Y'");){
   
   preparedStatement.setInt(1, contract.getContractID());
   ResultSet rs = preparedStatement.executeQuery();
   //currentCon.commit();
   
   if (rs.next()) {
       
       final String FORMAT_DATETIME = "yyyy-MM-dd";
       String ContractPeriodFromDate = "";
       String dateString = rs.getString("contract_period_from");   
       if (dateString != null && !dateString.isEmpty()){
       SimpleDateFormat sdf3 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d3 = sdf3.parse(dateString);
       sdf3.applyPattern(FORMAT_DATETIME);
       ContractPeriodFromDate = sdf3.format(d3);
       }
       
       String ContractPeriodToDate = "";
       String dateString2 = rs.getString("contract_period_to"); 
       if (dateString2 != null && !dateString2.isEmpty()){
       SimpleDateFormat sdf4 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d4 = sdf4.parse(dateString2);
       sdf4.applyPattern(FORMAT_DATETIME);
       ContractPeriodToDate = sdf4.format(d4);
       }
   
       
    contract = new ContractBean();
    contract.setContractID(rs.getInt("contract_id"));
    contract.setWpcID(rs.getInt("wpc_id"));
    contract.setWpcName(rs.getString("company_name"));
    contract.setContractPackage(rs.getString("package"));
    contract.setContractPackageCode(rs.getString("package_code"));
    //contract.setScopeWork(rs.getString("scope_of_work"));
    contract.setContractNo(rs.getString("contract_num"));
    contract.setContractAmount(rs.getString("contract_amount"));
    contract.setContractPeriodFrom(ContractPeriodFromDate);
    contract.setContractPeriodTo(ContractPeriodToDate);
    
     
    
//    contract.setJvBumiParticipationSum(rs.getBigDecimal("jv_bumi_participation_sum"));
//    contract.setJvBumiParticipationPercent(rs.getBigDecimal("jv_bumi_participation_percent"));
//    contract.setG1g4BumiParticipationSum(rs.getBigDecimal("g1g4_bumi_participation_sum"));
//    contract.setG1g4BumiParticipationPercent(rs.getBigDecimal("g1g4_bumi_participation_percent"));
//    contract.setG5g6BumiParticipationSum(rs.getBigDecimal("g5g6_bumi_participation_sum"));
//    contract.setG5g6BumiParticipationPercent(rs.getBigDecimal("g5g6_bumi_participation_percent"));
//    contract.setTotalBumiParticipationSum(rs.getBigDecimal("total_bumi_participation_sum"));
//    contract.setTotalBumiParticipationPercent(rs.getBigDecimal("total_bumi_participation_percent"));
//    contract.setRetentionSum(rs.getDouble("retention_sum"));
//    contract.setVoAmount(rs.getDouble("VO_amount"));
//    contract.setFinalContractSum(rs.getDouble("final_contract_sum"));
//    contract.setDateLoa(LOADate);
//    contract.setDateCommencement(CommencementDate);
//    contract.setDateProgressAudit(ProgressAuditDate);
//    contract.setDateCompletion(CompletionDate);
//    contract.setDLPFrom(DLPFromDate);
//    contract.setDLPTo(DLPToDate);
    
    }
   
   preparedStatement2.setInt(1, contract.getContractID());
   ResultSet rs2 = preparedStatement2.executeQuery();
   if (rs2.next()) {
     
    contract.setG1ReqNos(rs2.getInt("g1_req_nos"));
    contract.setG1ReqAmt(rs2.getBigDecimal("g1_req_amt"));
    contract.setG1ProposalNos(rs2.getInt("g1_proposal_nos"));
    contract.setG1ProposalAmt(rs2.getBigDecimal("g1_proposal_amt"));
    
    contract.setG2ReqNos(rs2.getInt("g2_req_nos"));
    contract.setG2ReqAmt(rs2.getBigDecimal("g2_req_amt"));
    contract.setG2ProposalNos(rs2.getInt("g2_proposal_nos"));
    contract.setG2ProposalAmt(rs2.getBigDecimal("g2_proposal_amt"));    
    
    contract.setG3ReqNos(rs2.getInt("g3_req_nos"));
    contract.setG3ReqAmt(rs2.getBigDecimal("g3_req_amt"));
    contract.setG3ProposalNos(rs2.getInt("g3_proposal_nos"));
    contract.setG3ProposalAmt(rs2.getBigDecimal("g3_proposal_amt"));
    
    contract.setG4ReqNos(rs2.getInt("g4_req_nos"));
    contract.setG4ReqAmt(rs2.getBigDecimal("g4_req_amt"));
    contract.setG4ProposalNos(rs2.getInt("g4_proposal_nos"));
    contract.setG4ProposalAmt(rs2.getBigDecimal("g4_proposal_amt"));
    
    contract.setG5ReqNos(rs2.getInt("g5_req_nos"));
    contract.setG5ReqAmt(rs2.getBigDecimal("g5_req_amt"));
    contract.setG5ProposalNos(rs2.getInt("g5_proposal_nos"));
    contract.setG5ProposalAmt(rs2.getBigDecimal("g5_proposal_amt")); 
    
    contract.setG6ReqNos(rs2.getInt("g6_req_nos"));
    contract.setG6ReqAmt(rs2.getBigDecimal("g6_req_amt"));
    contract.setG6ProposalNos(rs2.getInt("g6_proposal_nos"));
    contract.setG6ProposalAmt(rs2.getBigDecimal("g6_proposal_amt"));   
    
    contract.setG1G4ReqNos(rs2.getInt("g1g4_req_nos"));
    contract.setG1G4ReqAmt(rs2.getBigDecimal("g1g4_req_amt"));
    contract.setG1G4ProposalNos(rs2.getInt("g1g4_proposal_nos"));
    contract.setG1G4ProposalAmt(rs2.getBigDecimal("g1g4_proposal_amt"));
    
    contract.setG5G6ReqNos(rs2.getInt("g5g6_req_nos"));
    contract.setG5G6ReqAmt(rs2.getBigDecimal("g5g6_req_amt"));
    contract.setG5G6ProposalNos(rs2.getInt("g5g6_proposal_nos"));
    contract.setG5G6ProposalAmt(rs2.getBigDecimal("g5g6_proposal_amt"));  
    
    contract.setG1G6ReqNos(rs2.getInt("g1g6_req_nos"));
    contract.setG1G6ReqAmt(rs2.getBigDecimal("g1g6_req_amt"));
    contract.setG1G6ProposalNos(rs2.getInt("g1g6_proposal_nos"));
    contract.setG1G6ProposalAmt(rs2.getBigDecimal("g1g6_proposal_amt"));  
   }
   
  //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return contract;
 }
 
 public void updateContract(ContractBean contract) throws ParseException, SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();){
    
    final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

   Date now = new Date();
   String newDateStringNow = "";
   String dateString = now.toString(); 
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringNow = sdf3.format(d3);
   }
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_contract set date_update=?, user_update=?, wpc_id=?, package=?, package_code=?, contract_num=?, "
                + "contract_amount=?, contract_period_from=?, contract_period_to=?"
                + " where contract_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, contract.getEmpAD());
   preparedStatement.setInt(3, contract.getWpcID());   
   preparedStatement.setString(4, contract.getContractPackage()); 
   preparedStatement.setString(5, contract.getContractPackageCode()); 
   //preparedStatement.setString(5, contract.getScopeWork());
   preparedStatement.setString(6, contract.getContractNo());   
   preparedStatement.setString(7, contract.getContractAmount());
   preparedStatement.setString(8, contract.getContractPeriodFrom());
   preparedStatement.setString(9, contract.getContractPeriodTo());
   preparedStatement.setInt(10, contract.getContractID());
   
   
   preparedStatement.executeUpdate();
   
   PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("update bcms_revisedcontractamount set is_active='N' where contract_id=?");
   // Parameters start with 1
   preparedStatement3.setDouble(1, contract.getContractID());
   preparedStatement3.executeUpdate();
   
   PreparedStatement preparedStatement4 = currentCon.prepareStatement
        ("update bcms_contractvalue set is_active='N' where contract_id=?");
   // Parameters start with 1
   preparedStatement4.setDouble(1, contract.getContractID());
   preparedStatement4.executeUpdate();
   
   try (
        PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_revisedcontractamount(date_create,user_create,contract_id,revised_contract_amount) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement5 = currentCon.prepareStatement
                   ("insert into bcms_contractvalue(date_create,user_create,contract_id,"
                           + "g1_req_nos,g1_req_amt,g1_proposal_nos,g1_proposal_amt,"
                           + "g2_req_nos,g2_req_amt,g2_proposal_nos,g2_proposal_amt,"
                           + "g3_req_nos,g3_req_amt,g3_proposal_nos,g3_proposal_amt,"
                           + "g4_req_nos,g4_req_amt,g4_proposal_nos,g4_proposal_amt,"
                           + "g5_req_nos,g5_req_amt,g5_proposal_nos,g5_proposal_amt,"
                           + "g6_req_nos,g6_req_amt,g6_proposal_nos,g6_proposal_amt,"
                           + "g1g4_req_nos,g1g4_req_amt,g1g4_proposal_nos,g1g4_proposal_amt,"
                           + "g5g6_req_nos,g5g6_req_amt,g5g6_proposal_nos,g5g6_proposal_amt,"
                           + "g1g6_req_nos,g1g6_req_amt,g1g6_proposal_nos,g1g6_proposal_amt) "
                           + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ){
       
       
        if (contract.getRevisedContractAmounts() != null){
        for (int i= 0; i < contract.getRevisedContractAmounts().length; i++){
           
           String revisedContractAmount = (String) Array.get(contract.getRevisedContractAmounts(), i);
           if (revisedContractAmount != null && !revisedContractAmount.isEmpty()){
           
                
                preparedStatement2.setString(1, newDateStringNow);
                preparedStatement2.setString(2, contract.getEmpAD());
                preparedStatement2.setInt(3, contract.getContractID());
                preparedStatement2.setString(4, revisedContractAmount);
                preparedStatement2.executeUpdate();
           } else {
               //Do nothing
                }
           
            }
        
                preparedStatement5.setString(1, newDateStringNow);
                preparedStatement5.setString(2, contract.getEmpAD());
                preparedStatement5.setInt(3, contract.getContractID());
                
                preparedStatement5.setInt(4, contract.getG1ReqNos());
                preparedStatement5.setBigDecimal(5, contract.getG1ReqAmt());
                preparedStatement5.setInt(6, contract.getG1ProposalNos());
                preparedStatement5.setBigDecimal(7, contract.getG1ProposalAmt());
                
                preparedStatement5.setInt(8, contract.getG2ReqNos());
                preparedStatement5.setBigDecimal(9, contract.getG2ReqAmt());
                preparedStatement5.setInt(10, contract.getG2ProposalNos());
                preparedStatement5.setBigDecimal(11, contract.getG2ProposalAmt());
                
                preparedStatement5.setInt(12, contract.getG3ReqNos());
                preparedStatement5.setBigDecimal(13, contract.getG3ReqAmt());
                preparedStatement5.setInt(14, contract.getG3ProposalNos());
                preparedStatement5.setBigDecimal(15, contract.getG3ProposalAmt());
                
                preparedStatement5.setInt(16, contract.getG4ReqNos());
                preparedStatement5.setBigDecimal(17, contract.getG4ReqAmt());
                preparedStatement5.setInt(18, contract.getG4ProposalNos());
                preparedStatement5.setBigDecimal(19, contract.getG4ProposalAmt());
                
                preparedStatement5.setInt(20, contract.getG5ReqNos());
                preparedStatement5.setBigDecimal(21, contract.getG5ReqAmt());
                preparedStatement5.setInt(22, contract.getG5ProposalNos());
                preparedStatement5.setBigDecimal(23, contract.getG5ProposalAmt());
                
                preparedStatement5.setInt(24, contract.getG6ReqNos());
                preparedStatement5.setBigDecimal(25, contract.getG6ReqAmt());
                preparedStatement5.setInt(26, contract.getG6ProposalNos());
                preparedStatement5.setBigDecimal(27, contract.getG6ProposalAmt());
                
                preparedStatement5.setInt(28, contract.getG1G4ReqNos());
                preparedStatement5.setBigDecimal(29, contract.getG1G4ReqAmt());
                preparedStatement5.setInt(30, contract.getG1G4ProposalNos());
                preparedStatement5.setBigDecimal(31, contract.getG1G4ProposalAmt());
                
                preparedStatement5.setInt(32, contract.getG5G6ReqNos());
                preparedStatement5.setBigDecimal(33, contract.getG5G6ReqAmt());
                preparedStatement5.setInt(34, contract.getG5G6ProposalNos());
                preparedStatement5.setBigDecimal(35, contract.getG5G6ProposalAmt());
                
                preparedStatement5.setInt(36, contract.getG1G6ReqNos());
                preparedStatement5.setBigDecimal(37, contract.getG1G6ReqAmt());
                preparedStatement5.setInt(38, contract.getG1G6ProposalNos());
                preparedStatement5.setBigDecimal(39, contract.getG1G6ProposalAmt());
                
                preparedStatement5.executeUpdate();
        
        
        }
       
       
       
   }  
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
 public void deleteContract(double contractID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();){
   
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_contract set is_active='N' where contract_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, contractID);
   preparedStatement.executeUpdate();
   
   PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("update bcms_revisedcontractamount set is_active='N' where contract_id=?");
   // Parameters start with 1
   preparedStatement3.setDouble(1, contractID);
   preparedStatement3.executeUpdate();
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 

}


