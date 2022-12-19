/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Bean.BallotBean;
import Bean.UserBean;
import Bean.ContractBean;
import Bean.ContractorBean;
import Bean.ContractAwardBean;
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
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author munawwarah.mahmod
 */
public class ContractAwardDAO {
    
    //private Connection currentCon = null;  

 public ContractAwardDAO() {
  //currentCon = ConnectionManager.getConnection();
  
 }

    //ResultSet rs = null;
public List<ContractBean> getContract() throws SQLException {

        List<ContractBean> Contracts = new ArrayList<ContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("SELECT contract_id, company_name, package, package_code, contract_num, contract_amount from bcms_contract as contract\n" +
            "inner join bcms_contractor as c on contract.wpc_id = c.contractor_id where contract.is_active='Y' order by contract_id");){
            

            while (rs.next()) {

                ContractBean Contract = new ContractBean();
                Contract.setValue(rs.getDouble("contract_id"));
                Contract.setDisplayText(rs.getString("package"));
                Contract.setContractID(rs.getInt("contract_id"));
                Contract.setContractNo(rs.getString("contract_num"));
                Contract.setWpc(rs.getString("company_name"));
                Contract.setContractPackage(rs.getString("package"));  
                Contract.setContractPackageCode(rs.getString("package_code")); 
                Contract.setContractAmount(rs.getString("contract_amount"));  
                Contracts.add(Contract);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contracts;
    }

public List<ContractBean> getSelectedContractList(ContractAwardBean contractAward) throws SQLException {

        List<ContractBean> Contracts = new ArrayList<ContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("SELECT contract_id, company_name, package, package_code, contract_num, contract_amount from bcms_contract as contract\n" +
            "inner join bcms_contractor as c on contract.wpc_id = c.contractor_id where contract.is_active='Y' and contract_id = "+contractAward.getContractID()+" order by contract_id");){
            

            while (rs.next()) {

                ContractBean Contract = new ContractBean();
                Contract.setValue(rs.getDouble("contract_id"));
                Contract.setDisplayText(rs.getString("package"));
                Contract.setContractID(rs.getInt("contract_id"));
                Contract.setContractNo(rs.getString("contract_num"));
                Contract.setWpc(rs.getString("company_name"));
                Contract.setContractPackage(rs.getString("package"));  
                Contract.setContractPackageCode(rs.getString("package_code")); 
                Contract.setContractAmount(rs.getString("contract_amount"));  
                Contracts.add(Contract);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contracts;
    }

public List<BallotBean> getContractor() throws SQLException {

        List<BallotBean> Ballots = new ArrayList<BallotBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT contractor.contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id, CONVERT(VARCHAR(10),bm.date_ballot,105) as date_ballot,  ballotstatus_desc from bcms_contractor as contractor\n" +
//            "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
//            "inner join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
//            "inner join bcms_ballotstatusmaster as ballotstatus on ballot.ballotstatusmaster_id = ballotstatus.ballotstatusmaster_id\n" +
//            "inner join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id \n" +
//            "where contractor.is_active='Y' and ballot.is_active = 'Y' and ballot.ballotstatusmaster_id = 1 order by contractor_id");){
            ResultSet rs = statement.executeQuery("SELECT contractor.contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id, speccidb_id, COALESCE(NULLIF(CONVERT(VARCHAR(10),bm.date_ballot,105),'-'), '-')  as date_ballot,  COALESCE(NULLIF(ballotstatus_desc,'-'), '-') as ballotstatus_desc, ballot.ballotstatusmaster_id,\n" +
                                                    "CASE contractor.grade_id \n" +
                                                    "WHEN 1 THEN 1\n" +
                                                    "WHEN 2 THEN 1 \n" +
                                                    "WHEN 3 THEN 1\n" +
                                                    "WHEN 4 THEN 1\n" +
                                                    "WHEN 5 THEN 2 \n" +
                                                    "WHEN 6 THEN 2\n" +
                                                    "ELSE 0 \n" +
                                                    "END as cidbgradecategory from bcms_contractor as contractor\n" +
                                                    "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                                    "left join bcms_ballotstatusmaster as ballotstatus on ballot.ballotstatusmaster_id = ballotstatus.ballotstatusmaster_id\n" +
                                                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                                                    "where contractor.is_active='Y' \n" +
                                                    "and ballot.is_active = 'Y' \n" +
                                                    "and (contractor.grade_id in (1,2,3,4) and ballot.ballotstatusmaster_id in (1))\n" +
                                                    "and contractor.grade_id not in (7)  \n" +
                                                    "or (contractor.grade_id in (5,6) and (ballot.ballotstatusmaster_id is null or ballot.ballotstatusmaster_id in (1)))  \n" +
                                                    "and application_status = 'PASSED'\n" +
                                                    "order by grade_id,company_name");){

            while (rs.next()) {

                BallotBean Ballot = new BallotBean();
                Ballot.setValue(rs.getDouble("contractor_id"));
                Ballot.setDisplayText(rs.getString("company_name"));
                Ballot.setContractorID(rs.getInt("contractor_id"));
                Ballot.setCompanyName(rs.getString("company_name"));
                Ballot.setSsmCertNo(rs.getString("SSM_cert_no"));
                Ballot.setGradeCode(rs.getString("grade_code"));
                Ballot.setCidbGradeCategoryID(rs.getInt("cidbgradecategory"));
                Ballot.setGradeID(rs.getInt("grade_id"));
                Ballot.setDateBallot(rs.getString("date_ballot"));
                Ballot.setBallotStatusDesc(rs.getString("ballotstatus_desc"));
                               
                Ballots.add(Ballot);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Ballots;
    }  

public List<BallotBean> getSelectedContractorList(ContractAwardBean contractAward) throws SQLException {

        List<BallotBean> Ballots = new ArrayList<BallotBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT contractor.contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id, CONVERT(VARCHAR(10),bm.date_ballot,105) as date_ballot,  ballotstatus_desc from bcms_contractor as contractor\n" +
//            "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
//            "inner join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
//            "inner join bcms_ballotstatusmaster as ballotstatus on ballot.ballotstatusmaster_id = ballotstatus.ballotstatusmaster_id\n" +
//            "inner join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id \n" +
//            "where contractor.is_active='Y' and ballot.is_active = 'Y' and ballot.ballotstatusmaster_id = 1 order by contractor_id");){
            ResultSet rs = statement.executeQuery("select * from (SELECT contractor.contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id, speccidb_id, COALESCE(NULLIF(CONVERT(VARCHAR(10),bm.date_ballot,105),'-'), '-')  as date_ballot,  COALESCE(NULLIF(ballotstatus_desc,'-'), '-') as ballotstatus_desc, ballot.ballotstatusmaster_id, \n" +
                                                    "CASE contractor.grade_id  \n" +
                                                    "WHEN 1 THEN 1 \n" +
                                                    "WHEN 2 THEN 1  \n" +
                                                    "WHEN 3 THEN 1 \n" +
                                                    "WHEN 4 THEN 1 \n" +
                                                    "WHEN 5 THEN 2  \n" +
                                                    "WHEN 6 THEN 2 \n" +
                                                    "ELSE 0  \n" +
                                                    "END as cidbgradecategory from bcms_contractor as contractor \n" +
                                                    "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id \n" +
                                                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id \n" +
                                                    "left join bcms_ballotstatusmaster as ballotstatus on ballot.ballotstatusmaster_id = ballotstatus.ballotstatusmaster_id \n" +
                                                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id where contractor.is_active='Y'  \n" +
                                                    "and ballot.is_active = 'Y'  \n" +
                                                    "and (contractor.grade_id in (1,2,3,4) and ballot.ballotstatusmaster_id in (1)) \n" +
                                                    "and contractor.grade_id not in (7)   \n" +
                                                    "or (contractor.grade_id in (5,6) and (ballot.ballotstatusmaster_id is null or ballot.ballotstatusmaster_id in (1)))   \n" +
                                                    "and application_status = 'PASSED' ) as c\n" +
                                                    "where  cidbgradecategory = "+contractAward.getCidbGradeCategoryID()+"\n" +
                                                    "order by grade_id,company_name ");){

            while (rs.next()) {

                BallotBean Ballot = new BallotBean();
                Ballot.setValue(rs.getDouble("contractor_id"));
                Ballot.setDisplayText(rs.getString("company_name"));
                Ballot.setContractorID(rs.getInt("contractor_id"));
                Ballot.setCompanyName(rs.getString("company_name"));
                Ballot.setSsmCertNo(rs.getString("SSM_cert_no"));
                Ballot.setGradeCode(rs.getString("grade_code"));
                Ballot.setCidbGradeCategoryID(rs.getInt("cidbgradecategory"));
                Ballot.setGradeID(rs.getInt("grade_id"));
                Ballot.setDateBallot(rs.getString("date_ballot"));
                Ballot.setBallotStatusDesc(rs.getString("ballotstatus_desc"));
                               
                Ballots.add(Ballot);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Ballots;
    } 
    
public List<BallotBean> getSow() throws SQLException {

        List<BallotBean> specSows = new ArrayList<BallotBean>();
        String query = "SELECT * from bcms_subcontract as subcontract \n" +
                        "inner join bcms_contract as contract on contract.contract_id = subcontract.contract_id where subcontract.is_active='Y' order by subcontract_id";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean specSow = new BallotBean();
                specSow.setContractID(rs.getInt("contract_id"));
                specSow.setSubContractID(rs.getInt("subcontract_id"));
                specSow.setScopeWork(rs.getString("scope_of_work"));               
                specSows.add(specSow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specSows;
    }

public List<BallotBean> getSelectedSowList(ContractAwardBean contractAward) throws SQLException {

        List<BallotBean> specSows = new ArrayList<BallotBean>();
        String query = "SELECT * from bcms_subcontract as subcontract \n" +
                        "inner join bcms_contract as contract on contract.contract_id = subcontract.contract_id where subcontract.is_active='Y' and subcontract.contract_id = "+contractAward.getContractID()+" order by subcontract_id";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean specSow = new BallotBean();
                specSow.setContractID(rs.getInt("contract_id"));
                specSow.setSubContractID(rs.getInt("subcontract_id"));
                specSow.setScopeWork(rs.getString("scope_of_work"));               
                specSows.add(specSow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specSows;
    }

public List<ContractAwardBean> getCidbGradeCategory() throws SQLException {

        List<ContractAwardBean> ContractAwards = new ArrayList<ContractAwardBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * from bcms_cidbgradecategory where is_active = 'Y'");){
            

            while (rs.next()) {

                ContractAwardBean ContractAward = new ContractAwardBean();
                ContractAward.setCidbGradeCategoryID(rs.getInt("cidbgradecategory_id"));
                ContractAward.setCidbGradeCategoryDesc(rs.getString("cidbgradecategory_desc"));
                ContractAwards.add(ContractAward);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return ContractAwards;
    }

public void addContractAwardReg(ContractAwardBean contractAward, ContractBean contract, ContractorBean contractor) throws SQLException {
        try (Connection currentCon = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = currentCon.prepareStatement
                ("insert into bcms_contractaward(date_create,user_create,emp_ID, contractor_id, contract_id, subcontract_id, cidbgradecategory_id, performa_accepted_date, loa_date, commencement_date,completion_date, progress_audit_date, actual_completion_date ,final_account_date) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");){
    
   
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
   preparedStatement.setString(2, contractAward.getEmpAD());
   preparedStatement.setDouble(3, contractAward.getEmpID());
   preparedStatement.setInt(4, contractor.getContractorID());
   preparedStatement.setInt(5, contract.getContractID());
   preparedStatement.setInt(6, contractAward.getSubContractID());
   preparedStatement.setInt(7, contractAward.getCidbGradeCategoryID());
   if("".equals(contractAward.getDatePerformaAccepted())){
   preparedStatement.setNull(8, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(8, contractAward.getDatePerformaAccepted());
   }
   if("".equals(contractAward.getDateLoa())){
   preparedStatement.setNull(9, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(9, contractAward.getDateLoa());
   }
   if("".equals(contractAward.getDateCommencement())){
   preparedStatement.setNull(10, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(10, contractAward.getDateCommencement());
   }
   if("".equals(contractAward.getDateCompletion())){
   preparedStatement.setNull(11, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(11, contractAward.getDateCompletion());
   }
   if("".equals(contractAward.getDateProgressAudit())){
   preparedStatement.setNull(12, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(12, contractAward.getDateProgressAudit());
   }
   if("".equals(contractAward.getDateActualCompletion())){
   preparedStatement.setNull(13, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(13, contractAward.getDateActualCompletion());
   }
  
   if("".equals(contractAward.getDateFinalAccount())){
   preparedStatement.setNull(14, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(14, contractAward.getDateFinalAccount());
   }
   preparedStatement.executeUpdate(); 
        
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
 
 public List<ContractAwardBean> getAllContractAward(int jtStartIndex, int jtPageSize, String jtSorting, ContractAwardBean contractAward) throws ParseException, SQLException{
 List<ContractAwardBean> contractAwards = new ArrayList<ContractAwardBean>();

    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = contractAward.getAction();
  if (null != contractAward.getAction()) switch (contractAward.getAction()) {
          case "contractawardreg_list":
              
            String SPsql = "EXEC sp_searchcontractawardlist ?,?,?,?,?";  
   //PreparedStatement ps2 = null;
  
   try (Connection currentCon = ConnectionManager.getConnection();
//             Statement statement = currentCon.createStatement();
//             ResultSet rs = statement.executeQuery(query);
               //PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql); 
               //preparedStatement.setString(1, contractor.getSearchName());
               //ResultSet rs2 = preparedStatement.executeQuery();  
                ){
                //String combinedSpec = StringUtils.join(contractor.getSearchSpecialisationCodes(),",");
                PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql);
                preparedStatement.setEscapeProcessing(true);
                preparedStatement.setQueryTimeout(100);
                preparedStatement.setString(1, contractAward.getSearchName());
                preparedStatement.setString(2, contractAward.getSearchPackageCode());
                preparedStatement.setString(3, contractAward.getSearchScopeWork());
                preparedStatement.setString(4, startIndex);
                preparedStatement.setString(5, pageSize);
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs = preparedStatement.executeQuery();
                        ){
   
   
   while (rs.next()) {
    contractAward = new ContractAwardBean();
    
   
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
   
   String newDateStringLOADate = "";
   String dateString2 = rs.getString("LOA_date"); 
   if (dateString2 != null && !dateString2.isEmpty() && !"".equals(dateString2)){
   SimpleDateFormat sdf4 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d4 = sdf4.parse(dateString2);
   sdf4.applyPattern(FORMAT_DATETIME);
   newDateStringLOADate = sdf4.format(d4);
   }
   else if ("".equals(dateString2)){
   newDateStringLOADate = rs.getString("LOA_date");  
           }
   
   String newDateStringCommencementDate = "";
   String dateString3 = rs.getString("commencement_date"); 
   if (dateString3 != null && !dateString3.isEmpty() && !"".equals(dateString3)){
   SimpleDateFormat sdf5 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d5 = sdf5.parse(dateString3);
   sdf5.applyPattern(FORMAT_DATETIME);
   newDateStringCommencementDate = sdf5.format(d5);
   }
   else if ("".equals(dateString3)){
   newDateStringCommencementDate = rs.getString("commencement_date");  
           }
   
   String newDateStringCompletionDate = "";
   String dateString4 = rs.getString("completion_date"); 
   if (dateString4 != null && !dateString4.isEmpty() && !"".equals(dateString2)){
   SimpleDateFormat sdf6 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d6 = sdf6.parse(dateString4);
   sdf6.applyPattern(FORMAT_DATETIME);
   newDateStringCompletionDate = sdf6.format(d6);
   }
   else if ("".equals(dateString2)){
   newDateStringCompletionDate = rs.getString("completion_date");  
           }
   
    contractAward.setContractAwardID(rs.getInt("contractaward_id"));
    //contractAward
    contractAward.setCompanyName(rs.getString("company_name"));
    contractAward.setScopeWork(rs.getString("scope_of_work"));
    contractAward.setContractPackage(rs.getString("package"));
    contractAward.setContractPackageCode(rs.getString("package_code"));
    contractAward.setDateCreate(newDateStringDateCreate);
    contractAward.setRowNumber(rs.getInt("RowNum"));
    contractAward.setDateLoa(newDateStringLOADate);
    contractAward.setDateCommencement(newDateStringCommencementDate);
    contractAward.setDateCompletion(newDateStringCompletionDate);
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //contractAward.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    contractAwards.add(contractAward);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
                } catch (SQLException e) {
   e.printStackTrace();
  }
  }
  return contractAwards;
 }
 
 public int getContractAwardCount(ContractAwardBean contractAward) throws SQLException{
 int count=0;

                if (null != contractAward.getAction()) switch (contractAward.getAction()) {
                    
                case "contractawardreg_list":
              
            String SPsql = "EXEC sp_searchcontractawardlist_count ?,?,?";  
   //PreparedStatement ps2 = null;
  
   try (Connection currentCon = ConnectionManager.getConnection();
//             Statement statement = currentCon.createStatement();
//             ResultSet rs = statement.executeQuery(query);
               //PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql); 
               //preparedStatement.setString(1, contractor.getSearchName());
               //ResultSet rs2 = preparedStatement.executeQuery();  
                ){
                //String combinedSpec = StringUtils.join(contractor.getSearchSpecialisationCodes(),",");
                PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql);
                preparedStatement.setEscapeProcessing(true);
                preparedStatement.setQueryTimeout(100);
                preparedStatement.setString(1, contractAward.getSearchName());
                preparedStatement.setString(2, contractAward.getSearchPackageCode());
                preparedStatement.setString(3, contractAward.getSearchScopeWork());
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs2 = preparedStatement.executeQuery();
                        ){
                while (rs2.next()) {
                count = rs2.getInt("count");
    
   }
  
   
 } catch (SQLException e) {
  e.printStackTrace();
 }
 } catch (SQLException e) {
  e.printStackTrace();
 }
                }
 return count;
 
} 
 
 public ContractAwardBean getContractAwardById(ContractAwardBean contractAward) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement("select *, bm.date_ballot as date_ballot2 from bcms_contractaward as contractaward \n" +
        "inner join bcms_contractor as contractor on contractaward.contractor_id = contractor.contractor_id\n" +
        "inner join bcms_contract as contract on contractaward.contract_id = contract.contract_id \n" +
        "inner join bcms_subcontract as sc on sc.subcontract_id = contractaward.subcontract_id \n" +
        "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id \n" +
        "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
        "where contractaward_id=?");){
   
   preparedStatement.setInt(1, contractAward.getContractAwardID());
   ResultSet rs = preparedStatement.executeQuery();
   //currentCon.commit();
   
   if (rs.next()) {
       
       final String FORMAT_DATETIME = "yyyy-MM-dd";
       String LOADate = "";
       String dateString = rs.getString("LOA_date");   
       if (dateString != null && !dateString.isEmpty()){
       SimpleDateFormat sdf3 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d3 = sdf3.parse(dateString);
       sdf3.applyPattern(FORMAT_DATETIME);
       LOADate = sdf3.format(d3);
       }
       
       String CommencementDate = "";
       String dateString2 = rs.getString("commencement_date"); 
       if (dateString2 != null && !dateString2.isEmpty()){
       SimpleDateFormat sdf4 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d4 = sdf4.parse(dateString2);
       sdf4.applyPattern(FORMAT_DATETIME);
       CommencementDate = sdf4.format(d4);
       }
       
       String ProgressAuditDate ="";
       String dateString6 = rs.getString("progress_audit_date");
       if (dateString6 != null && !dateString6.isEmpty()){
       SimpleDateFormat sdf8 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d8 = sdf8.parse(dateString6);
       sdf8.applyPattern(FORMAT_DATETIME);
       ProgressAuditDate = sdf8.format(d8);
       }
       
       String CompletionDate = "";
       String dateString3 = rs.getString("completion_date"); 
       if (dateString3 != null && !dateString3.isEmpty()){
       SimpleDateFormat sdf5 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d5 = sdf5.parse(dateString3);
       sdf5.applyPattern(FORMAT_DATETIME);
       CompletionDate = sdf5.format(d5);
       }
       
       String ActualCompletionDate = "";
       String dateString9 = rs.getString("actual_completion_date"); 
       if (dateString9 != null && !dateString9.isEmpty()){
       SimpleDateFormat sdf10 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d5 = sdf10.parse(dateString9);
       sdf10.applyPattern(FORMAT_DATETIME);
       ActualCompletionDate = sdf10.format(d5);
       }
       
       String BallotDate ="";
       String dateString7 = rs.getString("date_ballot2");
       if (dateString7 != null && !dateString7.isEmpty()){
       SimpleDateFormat sdf9 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d9 = sdf9.parse(dateString7);
       sdf9.applyPattern(FORMAT_DATETIME);
       BallotDate = sdf9.format(d9);
       }
       
       String PerformaAcceptedDate = "";
       String dateString8 = rs.getString("performa_accepted_date"); 
       if (dateString8 != null && !dateString8.isEmpty()){
       SimpleDateFormat sdf10 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d10 = sdf10.parse(dateString8);
       sdf10.applyPattern(FORMAT_DATETIME);
       PerformaAcceptedDate = sdf10.format(d10);
       }
       
       String FinalAccountDate = "";
       String dateString10 = rs.getString("final_account_date"); 
       if (dateString10 != null && !dateString10.isEmpty()){
       SimpleDateFormat sdf11 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d11 = sdf11.parse(dateString10);
       sdf11.applyPattern(FORMAT_DATETIME);
       FinalAccountDate = sdf11.format(d11);
       }
       
       
    contractAward = new ContractAwardBean();
    contractAward.setContractAwardID(rs.getInt("contractaward_id"));
    contractAward.setContractID(rs.getInt("contract_id"));
    contractAward.setContractorID(rs.getInt("contractor_id"));
    contractAward.setSubContractID(rs.getInt("subcontract_id"));
    contractAward.setCidbGradeCategoryID(rs.getInt("cidbgradecategory_id"));
    contractAward.setWpc(rs.getString("company_name"));
    contractAward.setContractPackage(rs.getString("package"));
    contractAward.setContractPackageCode(rs.getString("package_code"));
    contractAward.setScopeWork(rs.getString("scope_of_work"));
    contractAward.setContractAmount(rs.getString("contract_amount"));
    contractAward.setCompanyName(rs.getString("company_name"));
    contractAward.setDateBallot(BallotDate);
    contractAward.setDatePerformaAccepted(PerformaAcceptedDate);
//    contractAward.setBallotStatusID(rs.getString("ballotstatusmaster_id"));
//    contractAward.setBallotStatusDesc(rs.getString("ballotstatus_desc"));
    contractAward.setDateLoa(LOADate);
    contractAward.setDateCommencement(CommencementDate);
    contractAward.setDateProgressAudit(ProgressAuditDate);
    contractAward.setDateCompletion(CompletionDate);
//    contractAward.setContractAwardRemarks(rs.getString("contract_award_remarks"));
    contractAward.setDateActualCompletion(ActualCompletionDate);
//    contractAward.setEotDays(rs.getString("eot_days"));
//    contractAward.setDocRefNo(rs.getString("doc_ref_no"));
//    contractAward.setSubConContractSum(rs.getString("subcon_contract_sum"));
//    contractAward.setSubConRevisedContractSum(rs.getString("subcon_revised_contract_sum"));
    contractAward.setDateFinalAccount(FinalAccountDate);
//    contractAward.setSubContractSpecID(rs.getInt("subcontractspec_id"));
    }
   
  //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return contractAward;
 }
 
 public void updateContractAward(ContractAwardBean contractAward, ContractBean contract, ContractorBean contractor) throws ParseException, SQLException {
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
        ("update bcms_contractaward set date_update=?, user_update=?, contract_id=?, contractor_id=?, performa_accepted_date=?, loa_date=?, commencement_date=?, completion_date=?,"
                + "progress_audit_date=?, actual_completion_date=?,  final_account_date=?, subcontract_id=?, cidbgradecategory_id=?"
                + " where contractaward_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, contractAward.getEmpAD());
   preparedStatement.setInt(3, contract.getContractID());   
   preparedStatement.setInt(4, contractor.getContractorID()); 
   if("".equals(contractAward.getDatePerformaAccepted())){
   preparedStatement.setNull(5, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(5, contractAward.getDatePerformaAccepted());
   }
   if("".equals(contractAward.getDateLoa())){
   preparedStatement.setNull(6, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(6, contractAward.getDateLoa());
   }
   if("".equals(contractAward.getDateCommencement())){
   preparedStatement.setNull(7, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(7, contractAward.getDateCommencement());
   }
   if("".equals(contractAward.getDateCompletion())){
   preparedStatement.setNull(8, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(8, contractAward.getDateCompletion());
   }
   if("".equals(contractAward.getDateProgressAudit())){
   preparedStatement.setNull(9, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(9, contractAward.getDateProgressAudit());
   }
//   preparedStatement.setString(10, contractAward.getContractAwardRemarks());
   preparedStatement.setString(10, contractAward.getDateActualCompletion());
//   preparedStatement.setString(12, contractAward.getEotDays());
//   preparedStatement.setString(13, contractAward.getDocRefNo());
//   preparedStatement.setString(14, contractAward.getScopeWork());
//   preparedStatement.setString(15, contractAward.getSubConContractSum());
//   preparedStatement.setString(16, contractAward.getSubConRevisedContractSum());
   if("".equals(contractAward.getDateFinalAccount())){
   preparedStatement.setNull(11, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(11, contractAward.getDateFinalAccount());
   }
   preparedStatement.setInt(12, contractAward.getSubContractID());
   preparedStatement.setInt(13, contractAward.getCidbGradeCategoryID());
   preparedStatement.setInt(14, contractAward.getContractAwardID());
   
   
   preparedStatement.executeUpdate();
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
 public void deleteContractAward(double contractAwardID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();){
   
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_contractaward set is_active='N' where contractaward_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, contractAwardID);
   preparedStatement.executeUpdate();
   
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
public void addDeclineContractAwardReg(ContractAwardBean contractAward, ContractBean contract, ContractorBean contractor) throws SQLException {
        try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = currentCon.prepareStatement
                ("insert into bcms_declinecontractaward(date_create,user_create,emp_ID, contractor_id, contract_id, subcontract_id, cidbgradecategory_id, decline_reason) "
                + "values (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
                
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_attachmentdecline(date_create,user_create,declinecontractaward_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
                           + "values (?,?,?,?,?,?,?)");){
    
   
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
   preparedStatement.setString(2, contractAward.getEmpAD());
   preparedStatement.setDouble(3, contractAward.getEmpID());
   preparedStatement.setInt(4, contractor.getContractorID());
   preparedStatement.setInt(5, contract.getContractID());
   preparedStatement.setInt(6, contractAward.getSubContractID());
   preparedStatement.setInt(7, contractAward.getCidbGradeCategoryID());
   preparedStatement.setString(8, contractAward.getReasonDecline());
   preparedStatement.execute();
   
   ResultSet rs = preparedStatement.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        contractAward.setDeclineContractAwardID(rs.getInt(1));
        //int key = rs.getInt(1);
    }
   if (contractAward.getAttachDeclineLinks() != null){
       for (int i= 0; i < contractAward.getAttachDeclineLinks().length; i++) {
           
           String attachDeclineLink = (String) Array.get(contractAward.getAttachDeclineLinks(), i);
           String attachDeclineFileName = (String) Array.get(contractAward.getAttachDeclineFileNames(), i);
           String oriAttachDeclineFileName = (String) Array.get(contractAward.getOriAttachDeclineFileNames(), i);
           if (attachDeclineLink != null && !attachDeclineLink.isEmpty()){
           
           preparedStatement2.setString(1, newDateStringNow);
           preparedStatement2.setString(2, contractAward.getEmpAD());
           preparedStatement2.setInt(3, contractAward.getDeclineContractAwardID());
           preparedStatement2.setInt(4, 11);
           preparedStatement2.setString(5, attachDeclineLink);
           preparedStatement2.setString(6, attachDeclineFileName);
           preparedStatement2.setString(7, oriAttachDeclineFileName);
           preparedStatement2.executeUpdate();
       }
       }
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

public void updateDeclineContractAward(ContractAwardBean contractAward, ContractBean contract, ContractorBean contractor) throws ParseException, SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement2 = currentCon.prepareStatement
        ("insert into bcms_attachmentdecline(date_create,user_create,declinecontractaward_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
        + "values (?,?,?,?,?,?,?)");){
    
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
        ("update bcms_declinecontractaward set date_update=?, user_update=?, contract_id=?, contractor_id=?, "
                + "subcontract_id=?, cidbgradecategory_id=?, decline_reason=?"
                + " where declinecontractaward_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, contractAward.getEmpAD());
   preparedStatement.setInt(3, contract.getContractID());   
   preparedStatement.setInt(4, contractor.getContractorID()); 
   
   preparedStatement.setInt(5, contractAward.getSubContractID());
   preparedStatement.setInt(6, contractAward.getCidbGradeCategoryID());
   preparedStatement.setString(7, contractAward.getReasonDecline());
   preparedStatement.setInt(8, contractAward.getDeclineContractAwardID());
   
   preparedStatement.executeUpdate();
   
   if (contractAward.getAttachDeclineLinks() != null){
       for (int i= 0; i < contractAward.getAttachDeclineLinks().length; i++) {
           
           String attachDeclineLink = (String) Array.get(contractAward.getAttachDeclineLinks(), i);
           String attachDeclineFileName = (String) Array.get(contractAward.getAttachDeclineFileNames(), i);
           String oriAttachDeclineFileName = (String) Array.get(contractAward.getOriAttachDeclineFileNames(), i);
           if (attachDeclineLink != null && !attachDeclineLink.isEmpty()){
           
           preparedStatement2.setString(1, newDateStringNow);
           preparedStatement2.setString(2, contractAward.getEmpAD());
           preparedStatement2.setInt(3, contractAward.getDeclineContractAwardID());
           preparedStatement2.setInt(4, 11);
           preparedStatement2.setString(5, attachDeclineLink);
           preparedStatement2.setString(6, attachDeclineFileName);
           preparedStatement2.setString(7, oriAttachDeclineFileName);
           preparedStatement2.executeUpdate();
       }
       }
        }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public void deleteDeclineContractAward(double declineContractAwardID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();){
   
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_declinecontractaward set is_active='N' where declinecontractaward_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, declineContractAwardID);
   preparedStatement.executeUpdate();
   
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public List<ContractAwardBean> getAllDeclineContractAward2(int jtStartIndex, int jtPageSize, String jtSorting, ContractAwardBean contractAward) throws ParseException, SQLException{
 List<ContractAwardBean> contractAwards = new ArrayList<ContractAwardBean>();

    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = contractAward.getAction();
  if (null != contractAward.getAction()) switch (contractAward.getAction()) {
          case "declinecontractawardreg_list":
              
            if ("".equals(contractAward.getSearchName())){ 
              query="SELECT *,ROW_NUMBER() OVER (ORDER BY declinecontractaward_id desc) AS RowNum from bcms_declinecontractaward as decline\n" +
                "inner join bcms_contractor as contractor on decline.contractor_id = contractor.contractor_id\n" +
                "inner join bcms_contract as contract on decline.contract_id = contract.contract_id\n" +
                "where decline.is_active='Y' and \n" +
                "(package_code like '%"+contractAward.getSearchPackageCode()+"%')ORDER BY declinecontractaward_id desc\n" +
                "OFFSET "+startIndex+" ROWS\n" +
                "FETCH NEXT "+pageSize+" ROWS ONLY";
               }
            else if ("".equals(contractAward.getSearchPackageCode())){
                query="SELECT *,ROW_NUMBER() OVER (ORDER BY declinecontractaward_id desc) AS RowNum from bcms_declinecontractaward as decline\n" +
                "inner join bcms_contractor as contractor on decline.contractor_id = contractor.contractor_id\n" +
                "inner join bcms_contract as contract on decline.contract_id = contract.contract_id\n" +
                "where decline.is_active='Y' and \n" +
                "(company_name like '%"+contractAward.getSearchName()+"%')ORDER BY declinecontractaward_id desc\n" +
                "OFFSET "+startIndex+" ROWS\n" +
                "FETCH NEXT "+pageSize+" ROWS ONLY";
            }
            
                break;
          
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();){
   
   
   while (rs.next()) {
    contractAward = new ContractAwardBean();
    
   
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
   
   
    contractAward.setDeclineContractAwardID(rs.getInt("declinecontractaward_id"));
    //contractAward
    contractAward.setCompanyName(rs.getString("company_name"));
    //contractAward.setScopeWork(rs.getString("scope_of_work"));
    //contractAward.setContractPackage(rs.getString("package"));
    contractAward.setContractPackageCode(rs.getString("package_code"));
    contractAward.setReasonDecline(rs.getString("decline_reason"));
    contractAward.setDateCreate(newDateStringDateCreate);
    contractAward.setRowNumber(rs.getInt("RowNum"));
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //contractAward.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    contractAwards.add(contractAward);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return contractAwards;
 }

public List<ContractAwardBean> getAllDeclineContractAward(int jtStartIndex, int jtPageSize, String jtSorting, ContractAwardBean contractAward) throws ParseException, SQLException{
 List<ContractAwardBean> contractAwards = new ArrayList<ContractAwardBean>();

    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = contractAward.getAction();
  if (null != contractAward.getAction()) switch (contractAward.getAction()) {
          case "declinecontractawardreg_list":
              
            String SPsql = "EXEC sp_searchdeclinecontractawardlist ?,?,?,?";  
   //PreparedStatement ps2 = null;
  
   try (Connection currentCon = ConnectionManager.getConnection();
//             Statement statement = currentCon.createStatement();
//             ResultSet rs = statement.executeQuery(query);
               //PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql); 
               //preparedStatement.setString(1, contractor.getSearchName());
               //ResultSet rs2 = preparedStatement.executeQuery();  
                ){
                //String combinedSpec = StringUtils.join(contractor.getSearchSpecialisationCodes(),",");
                PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql);
                preparedStatement.setEscapeProcessing(true);
                preparedStatement.setQueryTimeout(100);
                preparedStatement.setString(1, contractAward.getSearchName());
                preparedStatement.setString(2, contractAward.getSearchPackageCode());
                preparedStatement.setString(3, startIndex);
                preparedStatement.setString(4, pageSize);
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs = preparedStatement.executeQuery();
                        ){
   
   
   while (rs.next()) {
    contractAward = new ContractAwardBean();
    
   
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
   
   
    contractAward.setDeclineContractAwardID(rs.getInt("declinecontractaward_id"));
    //contractAward
    contractAward.setCompanyName(rs.getString("company_name"));
    //contractAward.setScopeWork(rs.getString("scope_of_work"));
    //contractAward.setContractPackage(rs.getString("package"));
    contractAward.setContractPackageCode(rs.getString("package_code"));
    contractAward.setReasonDecline(rs.getString("decline_reason"));
    contractAward.setDateCreate(newDateStringDateCreate);
    contractAward.setRowNumber(rs.getInt("RowNum"));
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //contractAward.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    contractAwards.add(contractAward);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
                } catch (SQLException e) {
   e.printStackTrace();
  }
  }
  return contractAwards;
 }

public int getDeclineContractAwardCount(ContractAwardBean contractAward) throws SQLException{
 int count=0;

                if (null != contractAward.getAction()) switch (contractAward.getAction()) {
                    
                case "declinecontractawardreg_list":
              
            String SPsql = "EXEC sp_searchdeclinecontractawardlist_count ?,?";  
   //PreparedStatement ps2 = null;
  
   try (Connection currentCon = ConnectionManager.getConnection();
//             Statement statement = currentCon.createStatement();
//             ResultSet rs = statement.executeQuery(query);
               //PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql); 
               //preparedStatement.setString(1, contractor.getSearchName());
               //ResultSet rs2 = preparedStatement.executeQuery();  
                ){
                //String combinedSpec = StringUtils.join(contractor.getSearchSpecialisationCodes(),",");
                PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql);
                preparedStatement.setEscapeProcessing(true);
                preparedStatement.setQueryTimeout(100);
                preparedStatement.setString(1, contractAward.getSearchName());
                preparedStatement.setString(2, contractAward.getSearchPackageCode());
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs2 = preparedStatement.executeQuery();
                        ){
                while (rs2.next()) {
                count = rs2.getInt("count");
    
   }
  
   
 } catch (SQLException e) {
  e.printStackTrace();
 }
 } catch (SQLException e) {
  e.printStackTrace();
 }
                }
 return count;
 
} 

public ContractAwardBean getDeclineContractAwardById(ContractAwardBean contractAward) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement("select *, bm.date_ballot as date_ballot2 from bcms_declinecontractaward as decline\n" +
        "inner join bcms_contractor as contractor on decline.contractor_id = contractor.contractor_id\n" +
        "inner join bcms_contract as contract on decline.contract_id = contract.contract_id \n" +
        "inner join bcms_subcontract as sc on sc.subcontract_id = decline.subcontract_id \n" +
        "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id \n" +
        "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
        "where declinecontractaward_id=?");){
   
   preparedStatement.setInt(1, contractAward.getDeclineContractAwardID());
   ResultSet rs = preparedStatement.executeQuery();
   //currentCon.commit();
   
   if (rs.next()) {
       
       final String FORMAT_DATETIME = "yyyy-MM-dd";
       
       String BallotDate ="";
       String dateString7 = rs.getString("date_ballot2");
       if (dateString7 != null && !dateString7.isEmpty()){
       SimpleDateFormat sdf9 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d9 = sdf9.parse(dateString7);
       sdf9.applyPattern(FORMAT_DATETIME);
       BallotDate = sdf9.format(d9);
       }
       
    contractAward = new ContractAwardBean();
    contractAward.setDeclineContractAwardID(rs.getInt("declinecontractaward_id"));
    contractAward.setContractID(rs.getInt("contract_id"));
    contractAward.setContractorID(rs.getInt("contractor_id"));
    contractAward.setSubContractID(rs.getInt("subcontract_id"));
    contractAward.setCidbGradeCategoryID(rs.getInt("cidbgradecategory_id"));
    contractAward.setWpc(rs.getString("company_name"));
    contractAward.setContractPackage(rs.getString("package"));
    contractAward.setContractPackageCode(rs.getString("package_code"));
    contractAward.setScopeWork(rs.getString("scope_of_work"));
    contractAward.setContractAmount(rs.getString("contract_amount"));
    contractAward.setCompanyName(rs.getString("company_name"));
    contractAward.setReasonDecline(rs.getString("decline_reason"));
    contractAward.setDateBallot(BallotDate);
    }
   
  //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return contractAward;
 }

public List<ContractAwardBean> getInsertedAttachment(ContractAwardBean contractAward, String attachmentType) throws SQLException {
        
        List<ContractAwardBean> Attachments = new ArrayList<ContractAwardBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select decline_attachment_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentdecline as attachment\n" +
             "inner join bcms_declinecontractaward as decline on attachment.declinecontractaward_id = decline.declinecontractaward_id\n" +
             "where attachment.declinecontractaward_id=? and attachmentmaster_id=11 and attachment.is_active='Y'");
               
            ){
                
        if (null != attachmentType) switch (attachmentType) {
                   
                case "declineContractAward":
                preparedStatement2.setInt(1, contractAward.getDeclineContractAwardID());
                try (ResultSet rs = preparedStatement2.executeQuery();){

                while (rs.next()) {

                    ContractAwardBean Attachment = new ContractAwardBean();
                    Attachment.setAttachDeclineID(rs.getInt("decline_attachment_id"));
                    Attachment.setAttachDeclineMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachDeclineLink(rs.getString("attachment_link"));
                    Attachment.setAttachDeclineFileName(rs.getString("file_name"));
                    Attachment.setOriAttachDeclineFileName(rs.getString("ori_file_name"));
                    Attachments.add(Attachment);
                }
                }
                break;
                  
                    
                }
                

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Attachments;
    }

public void deleteAttachment(ContractAwardBean contractAward) throws SQLException, ParseException {
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_attachmentdecline set is_active='N', date_update=?, user_update=?  where decline_attachment_id=?");
        ){
       
   final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
   Date now = new Date();
   String newDateStringUpdate = "";
   String dateString = now.toString(); 
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringUpdate = sdf3.format(d3);
   }
   
   preparedStatement.setString(1, newDateStringUpdate);
   preparedStatement.setString(2, contractAward.getEmpAD());
   preparedStatement.setDouble(3, contractAward.getAttachDeclineID());
   preparedStatement.executeUpdate();
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}





