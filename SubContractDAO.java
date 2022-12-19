/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author munawwarah.mahmod
 */

import Bean.ContractorBean;
import Bean.UserBean;
import Bean.SubContractBean;
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
import java.util.Objects;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.text.WordUtils;

public class SubContractDAO {
    
public List<SubContractBean> getSpecialisation() throws SQLException {

        List<SubContractBean> Specialisations = new ArrayList<SubContractBean>();
        String query = "SELECT specialisationmaster_id, speccidb_id, specialisation_code, specialisation_desc, concat(specialisation_code, ' - ', specialisation_desc) as speccombine from bcms_specialisationmaster where is_active='Y' and speccidb_id = 1 order by specialisation_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
             

            while (rs.next()) {

                SubContractBean Specialisation = new SubContractBean();
                //Specialisation.setValue(rs.getInt("specialisationmaster_id"));
                //Specialisation.setDisplayText(rs.getString("specialisation_code"));
                Specialisation.setSpecialisationID(rs.getInt("specialisationmaster_id"));
                Specialisation.setSpecialisationCode(rs.getString("speccombine"));
                Specialisation.setSpecialisationDesc(rs.getString("specialisation_desc"));
                //Specialisation.setGroupingB(rs.getInt("speccidb_id"));
                               
                Specialisations.add(Specialisation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Specialisations;
    }

public List<SubContractBean> getContract() throws SQLException {

        List<SubContractBean> Contracts = new ArrayList<SubContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("  SELECT contract_id, company_name, package, package_code, contract_num, contract_amount from bcms_contract as contract inner join bcms_contractor as c on c.contractor_id = contract.wpc_id where contract.is_active='Y' order by contract_id");){
            

            while (rs.next()) {

                SubContractBean Contract = new SubContractBean();
                Contract.setContractID(rs.getInt("contract_id"));
                Contract.setContractPackageCode(rs.getString("package_code"));  
                Contracts.add(Contract);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contracts;
    }

public List<SubContractBean> getDateCategory() throws SQLException {

        List<SubContractBean> Contracts = new ArrayList<SubContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("select datecatmaster_id, datecatmaster_desc from bcms_datecategorymaster where is_active ='Y' ");){
            

            while (rs.next()) {

                SubContractBean Contract = new SubContractBean();
                Contract.setDateCategoryID(rs.getInt("datecatmaster_id"));
                Contract.setDateCategoryDesc(rs.getString("datecatmaster_desc"));  
                Contracts.add(Contract);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contracts;
    }

public List<ContractorBean> getCIDBGrade() throws SQLException {

        List<ContractorBean> CIDBgrades = new ArrayList<ContractorBean>();
        String query = "SELECT grade_id, grade_code,speccidb_id from bcms_CIDBgrademaster where is_active='Y' and grade_id not in (7) order by grade_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                ContractorBean CIDBgrade = new ContractorBean();
                CIDBgrade.setValue(rs.getInt("grade_id"));
                CIDBgrade.setDisplayText(rs.getString("grade_code"));
                CIDBgrade.setGradeID(rs.getInt("grade_id"));
                CIDBgrade.setGradeCode(rs.getString("grade_code"));
                CIDBgrade.setGroupingA(rs.getInt("speccidb_id"));
                               
                CIDBgrades.add(CIDBgrade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CIDBgrades;
    }

public List<SubContractBean> getInsertedRevisedContractAmount(SubContractBean subContract) throws SQLException, ParseException {

        List<SubContractBean> revisedSubContractAmounts = new ArrayList<SubContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection(); 
             PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select revisedamount.date_create,revised_subcontract_amount from bcms_subcontract as subcontract\n" +
             "inner join bcms_revisedsubcontractamount as revisedamount on subcontract.subcontract_id = revisedamount.subcontract_id\n" +
             "where subcontract.subcontract_id=? and revisedamount.is_active='Y'");){
            
            preparedStatement2.setInt(1, subContract.getSubContractID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                final String FORMAT_DATETIME = "yyyy-MM-dd";
                String RSCACreateDate = "";
                String dateString = rs.getString("date_create"); 
                if (dateString != null && !dateString.isEmpty()){
                SimpleDateFormat sdf3 =
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d3 = sdf3.parse(dateString);
                sdf3.applyPattern(FORMAT_DATETIME);
                RSCACreateDate = sdf3.format(d3);
                }
       
                SubContractBean revisedSubContractAmount = new SubContractBean();
                revisedSubContractAmount.setRevisedSubContractAmount(rs.getString("revised_subcontract_amount"));
                revisedSubContractAmount.setRSCACreateDate((RSCACreateDate));
                               
                revisedSubContractAmounts.add(revisedSubContractAmount);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revisedSubContractAmounts;
    }    

public void addSubContractReg(SubContractBean subContract) throws SQLException {
        try (Connection currentCon = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = currentCon.prepareStatement
                ("insert into bcms_subcontract(date_create,user_create,emp_ID, contract_id, subcontract_num, subcontract_amount, subcontract_period_from, subcontract_period_to, scope_of_work, subcontract_date, datecatmaster_id, grade_id) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);){
    
   
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
   preparedStatement.setString(2, subContract.getEmpAD());
   preparedStatement.setDouble(3, subContract.getEmpID());
   preparedStatement.setInt(4, subContract.getContractID());
   preparedStatement.setString(5, subContract.getSubContractNo());
   preparedStatement.setString(6, subContract.getSubContractAmount());
   preparedStatement.setString(7, subContract.getSubContractPeriodFrom());
   preparedStatement.setString(8, subContract.getSubContractPeriodTo());
   preparedStatement.setString(9, subContract.getScopeWork());
   preparedStatement.setString(10, subContract.getSubContractDate());
   preparedStatement.setInt(11, subContract.getDateCategoryID());
   preparedStatement.setInt(12, subContract.getGradeID());
   
   preparedStatement.executeUpdate(); 
   ResultSet rs = preparedStatement.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        subContract.setSubContractID(rs.getInt(1));
        //int key = rs.getInt(1);
    }
   
//   try (PreparedStatement preparedStatement3 = currentCon.prepareStatement
//        ("insert into bcms_subcontractspec(date_create,user_create,subcontract_id,specialisationmaster_id, scope_of_work) "
//        + "values (?,?,?,?,?)");
//        ){
////       ResultSet rs = preparedStatement2.executeQuery();
////       while (rs.next()) {
////       ContractorBean newcontractor = new ContractorBean();
////       newcontractor.setContractorID(rs.getDouble("currentcontractorid"));
//       
//       if (subContract.getSpecialisationIDs() != null){
//       for (int i= 0; i < subContract.getSpecialisationIDs().length; i++){
//           String specialisationID = (String) Array.get(subContract.getSpecialisationIDs(), i);
//           String scopeWork = (String) Array.get(subContract.getScopeWorks(), i);
//           if (specialisationID != null && !specialisationID.isEmpty() && specialisationID != ""){
//           preparedStatement3.setString(1, newDateStringNow);
//           preparedStatement3.setString(2, subContract.getEmpAD());
//           preparedStatement3.setInt(3, subContract.getSubContractID());
//           preparedStatement3.setString(4, specialisationID);
//           preparedStatement3.setString(5, scopeWork);
//           preparedStatement3.executeUpdate();
//           }
//       }
//       }
//       
//      
//       
//   }  
   
   try (PreparedStatement preparedStatement4 = currentCon.prepareStatement
        ("insert into bcms_revisedsubcontractamount(date_create,user_create,subcontract_id, revised_subcontract_amount) "
        + "values (?,?,?,?)");
        ){
//       ResultSet rs = preparedStatement2.executeQuery();
//       while (rs.next()) {
//       ContractorBean newcontractor = new ContractorBean();
//       newcontractor.setContractorID(rs.getDouble("currentcontractorid"));
       
       if (subContract.getRevisedSubContractAmounts()!= null){
       for (int i= 0; i < subContract.getRevisedSubContractAmounts().length; i++){
           String revisedSubContractAmount = (String) Array.get(subContract.getRevisedSubContractAmounts(), i);
           //String scopeWork = (String) Array.get(subContract.getScopeWorks(), i);
           if (revisedSubContractAmount != null && !revisedSubContractAmount.isEmpty() && revisedSubContractAmount != ""){
           preparedStatement4.setString(1, newDateStringNow);
           preparedStatement4.setString(2, subContract.getEmpAD());
           preparedStatement4.setInt(3, subContract.getSubContractID());
           preparedStatement4.setString(4, revisedSubContractAmount);
           preparedStatement4.executeUpdate();
           }
       }
       }
       
      
       
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

public List<SubContractBean> getAllSubContract(int jtStartIndex, int jtPageSize, String jtSorting, SubContractBean subContract) throws ParseException, SQLException{
 List<SubContractBean> subContracts = new ArrayList<SubContractBean>();

    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = subContract.getAction();
  //if (null != subContract.getAction()) switch (subContract.getAction()) {
          //case "subcontractreg_list":
              
              String SPsql = "EXEC sp_searchsubcontractlist ?,?,?,?,?";  
              
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
                preparedStatement.setString(1, subContract.getSearchPackageCode());
                preparedStatement.setString(2, subContract.getSearchCidbGrade());
                preparedStatement.setString(3, subContract.getSearchScopeWork());
                preparedStatement.setString(4, startIndex);
                preparedStatement.setString(5, pageSize);
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs = preparedStatement.executeQuery();
                        ){
                while (rs.next()) {
    subContract = new SubContractBean();
    
   
   subContract.setRowNumber(rs.getInt("RowNum"));
    subContract.setSubContractID(rs.getInt("subcontract_id"));
    subContract.setContractPackageCode(rs.getString("package_code"));
    subContract.setScopeWork(rs.getString("scope_of_work"));
    subContract.setSubContractNo(rs.getString("subcontract_num"));
    subContract.setSubContractAmount(rs.getString("subcontract_amount"));
    subContract.setGradeCode(rs.getString("grade_code"));

    subContracts.add(subContract);
    
   }
                
   
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
  }catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
//}
  return subContracts;
 }


public int getSubContractCount(SubContractBean subContract) throws SQLException{
 int count=0;
 String SPsql = "EXEC sp_searchsubcontractlist_count ?,?,?";  
              
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
                preparedStatement.setString(1, subContract.getSearchPackageCode());
                preparedStatement.setString(2, subContract.getSearchCidbGrade());
                preparedStatement.setString(3, subContract.getSearchScopeWork());
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs = preparedStatement.executeQuery();
                        ){
                while (rs.next()) {
                    count=rs.getInt("count");
                    System.out.print(count);
                    }
              
                //}
                //currentCon.commit();
  
                }
                
catch (SQLException e) {
  e.printStackTrace();
    }
 } catch (SQLException e) {
  e.printStackTrace();
 }
 return count;
 
}

public SubContractBean getSubContractById(SubContractBean subContract) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement("select subcontract_id, package_code, sc.contract_id, subcontract_num, subcontract_amount, subcontract_period_from, subcontract_period_to, scope_of_work, subcontract_date, datecatmaster_id, grade_id from bcms_subcontract as sc\n" +
                                                "inner join bcms_contract as c on c.contract_id = sc.contract_id\n" +
                                                "where subcontract_id = ?");){
   
   preparedStatement.setInt(1, subContract.getSubContractID());
   ResultSet rs = preparedStatement.executeQuery();
   //currentCon.commit();
   
   if (rs.next()) {
       
     final String FORMAT_DATETIME = "yyyy-MM-dd";
       String SubContractPeriodFromDate = "";
       String dateString = rs.getString("subcontract_period_from");   
       if (dateString != null && !dateString.isEmpty()){
       SimpleDateFormat sdf3 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d3 = sdf3.parse(dateString);
       sdf3.applyPattern(FORMAT_DATETIME);
       SubContractPeriodFromDate = sdf3.format(d3);
       }
       
       String SubContractPeriodToDate = "";
       String dateString2 = rs.getString("subcontract_period_to"); 
       if (dateString2 != null && !dateString2.isEmpty()){
       SimpleDateFormat sdf4 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d4 = sdf4.parse(dateString2);
       sdf4.applyPattern(FORMAT_DATETIME);
       SubContractPeriodToDate = sdf4.format(d4);
       }
       
    subContract = new SubContractBean();
    subContract.setContractID(rs.getInt("contract_id"));
    subContract.setSubContractID(rs.getInt("subcontract_id"));
    //subContract.setScopeWork(rs.getString("scope_of_works"));
    subContract.setContractPackageCode(rs.getString("package_code"));
    subContract.setSubContractNo(rs.getString("subcontract_num"));
    subContract.setSubContractAmount(rs.getString("subcontract_amount"));
    subContract.setSubContractPeriodFrom(SubContractPeriodFromDate);
    subContract.setSubContractPeriodTo(SubContractPeriodToDate);
    subContract.setScopeWork(rs.getString("scope_of_work"));
    subContract.setSubContractDate(rs.getString("subcontract_date"));
    subContract.setDateCategoryID(rs.getInt("datecatmaster_id"));
    subContract.setGradeID(rs.getInt("grade_id"));
    
    }
   
  //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return subContract;
 }

public List<SubContractBean> getScopeWork(SubContractBean subContract) throws SQLException {

        List<SubContractBean> scopeWorks = new ArrayList<SubContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
             PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select c.subcontract_id, sc.subcontractspec_id, specialisation_code, specialisation_desc, concat(specialisation_code, ' - ', specialisation_desc) as speccombine, sc.scope_of_work, sc.specialisationmaster_id  from bcms_subcontractspec as sc\n" +
            "inner join bcms_subcontract as c on c.subcontract_id = sc.subcontract_id\n" +
            "inner join bcms_specialisationmaster as spec on sc.specialisationmaster_id = spec.specialisationmaster_id\n" +
            "where c.subcontract_id = ? and sc.is_active = 'Y'");){
            
            preparedStatement2.setInt(1, subContract.getSubContractID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                SubContractBean scopeWork = new SubContractBean();
                scopeWork.setSubContractSpecID(rs.getInt("subcontractspec_id"));
                scopeWork.setSpecialisationID(rs.getInt("specialisationmaster_id"));
                scopeWork.setSpecialisationCode(rs.getString("speccombine"));  
                scopeWork.setSpecialisationDesc(rs.getString("specialisation_desc"));  
                scopeWork.setScopeWork(rs.getString("scope_of_work"));          
                scopeWorks.add(scopeWork);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scopeWorks;
    }

public void updateSubContract(SubContractBean subContract) throws ParseException, SQLException {
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
        ("update bcms_subcontract set date_update=?, user_update=?, contract_id=?, subcontract_num=?, subcontract_amount=?, subcontract_period_from=?, subcontract_period_to=?, scope_of_work=?, subcontract_date=?, datecatmaster_id=?, grade_id=?"
                + " where subcontract_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, subContract.getEmpAD());
   preparedStatement.setInt(3, subContract.getContractID());
   preparedStatement.setString(4, subContract.getSubContractNo());
   preparedStatement.setString(5, subContract.getSubContractAmount());
   preparedStatement.setString(6, subContract.getSubContractPeriodFrom());
   preparedStatement.setString(7, subContract.getSubContractPeriodTo());
   preparedStatement.setString(8, subContract.getScopeWork());
   preparedStatement.setString(9, subContract.getSubContractDate());
   preparedStatement.setInt(10, subContract.getDateCategoryID());
   preparedStatement.setInt(11, subContract.getGradeID());
   preparedStatement.setInt(12, subContract.getSubContractID());
   
   
   preparedStatement.executeUpdate();
   
//   PreparedStatement preparedStatement4 = currentCon.prepareStatement
//        ("update bcms_subcontractspec set is_active='N' where subcontract_id=?");
//   // Parameters start with 1
//   preparedStatement4.setDouble(1, subContract.getSubContractID());
//   preparedStatement4.executeUpdate();
//   
//   try (PreparedStatement preparedStatement3 = currentCon.prepareStatement
//        ("insert into bcms_subcontractspec(date_create,user_create,subcontract_id,specialisationmaster_id, scope_of_work) "
//        + "values (?,?,?,?,?)");
//        ){
//       
//       if (subContract.getSpecialisationIDs() != null){
//       for (int i= 0; i < subContract.getSpecialisationIDs().length; i++){
//           String specialisationID = (String) Array.get(subContract.getSpecialisationIDs(), i);
//           String scopeWork = (String) Array.get(subContract.getScopeWorks(), i);
//           if (specialisationID != null && !specialisationID.isEmpty() && specialisationID != ""){
//           preparedStatement3.setString(1, newDateStringNow);
//           preparedStatement3.setString(2, subContract.getEmpAD());
//           preparedStatement3.setInt(3, subContract.getSubContractID());
//           preparedStatement3.setString(4, specialisationID);
//           preparedStatement3.setString(5, scopeWork);
//           preparedStatement3.executeUpdate();
//           }
//       }
//       }
//   } 
   
   PreparedStatement preparedStatement5 = currentCon.prepareStatement
        ("update bcms_revisedsubcontractamount set is_active='N' where subcontract_id=?");
   // Parameters start with 1
   preparedStatement5.setDouble(1, subContract.getSubContractID());
   preparedStatement5.executeUpdate();
   
   try (PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("insert into bcms_revisedsubcontractamount(date_create,user_create,subcontract_id,revised_subcontract_amount) "
        + "values (?,?,?,?)");
        ){
       
       if (subContract.getRevisedSubContractAmounts() != null){
       for (int i= 0; i < subContract.getRevisedSubContractAmounts().length; i++){
           String revisedSubContractAmountID = (String) Array.get(subContract.getRevisedSubContractAmounts(), i);
           if (revisedSubContractAmountID != null && !revisedSubContractAmountID.isEmpty() && revisedSubContractAmountID != ""){
           preparedStatement3.setString(1, newDateStringNow);
           preparedStatement3.setString(2, subContract.getEmpAD());
           preparedStatement3.setInt(3, subContract.getSubContractID());
           preparedStatement3.setString(4, revisedSubContractAmountID);
           preparedStatement3.executeUpdate();
           }
       }
       }
   }
    
    catch (SQLException e) {
        e.printStackTrace();
        //currentCon.rollback();
    }
   }  
 }

public void deleteSubContract(double subContractID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();){
   
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_subcontract set is_active='N' where subcontract_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, subContractID);
   preparedStatement.executeUpdate();
   
   PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("update bcms_subcontractspec set is_active='N' where subcontract_id=?");
   // Parameters start with 1
   preparedStatement3.setDouble(1, subContractID);
   preparedStatement3.executeUpdate();
   
   PreparedStatement preparedStatement4 = currentCon.prepareStatement
        ("update bcms_revisedsubcontractamount set is_active='N' where subcontract_id=?");
   // Parameters start with 1
   preparedStatement4.setDouble(1, subContractID);
   preparedStatement4.executeUpdate();
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}
