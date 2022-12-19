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
import Bean.MattersTrackingBean;
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
public class MattersTrackingDAO {
 
public MattersTrackingDAO() {
  //currentCon = ConnectionManager.getConnection();
  
 }

public List<ContractAwardBean> getContractAward() throws SQLException {

        List<ContractAwardBean> ContractAwards = new ArrayList<ContractAwardBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("select contractaward_id,contractor.contractor_id, company_name,SSM_cert_no,contract.contract_id, scope_of_work, wpc, package from bcms_contractaward as contractaward \n" +
            "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
            "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
            "where contractaward.is_active = 'Y' and is_selected_matterstracking = 'N' order by company_name");){
            

            while (rs.next()) {

                ContractAwardBean ContractAward = new ContractAwardBean();
                ContractAward.setValue(rs.getDouble("contractaward_id"));
                ContractAward.setDisplayText(rs.getString("scope_of_work")+"-"+rs.getString("company_name"));
                ContractAward.setContractAwardID(rs.getInt("contractaward_id"));
                ContractAward.setCompanyName(rs.getString("company_name"));
                ContractAward.setSsmCertNo(rs.getString("SSM_cert_no"));
                ContractAward.setContractID(rs.getInt("contract_id"));
                ContractAward.setScopeWork(rs.getString("scope_of_work"));
                ContractAward.setWpc(rs.getString("wpc"));
                ContractAward.setContractPackage(rs.getString("package"));  
                ContractAwards.add(ContractAward);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return ContractAwards;
    }

public List<MattersTrackingBean> getMattersCategory() throws SQLException {

        List<MattersTrackingBean> MattersTrackings = new ArrayList<MattersTrackingBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("select * from bcms_matterscategorymaster \n" +
            "where is_active = 'Y'");){
            

            while (rs.next()) {

                MattersTrackingBean MattersTracking = new MattersTrackingBean();
                MattersTracking.setValue(rs.getInt("matters_id"));
                MattersTracking.setDisplayText(rs.getString("matters_category"));
                MattersTracking.setMattersID(rs.getInt("matters_id"));
                MattersTracking.setMattersCategory(rs.getString("matters_category"));
                MattersTrackings.add(MattersTracking);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return MattersTrackings;
    }

public List<ContractorBean> getCIDBGrade() throws SQLException {

        List<ContractorBean> CIDBgrades = new ArrayList<ContractorBean>();
        String query = "SELECT grade_id, grade_code,speccidb_id from bcms_CIDBgrademaster where is_active='Y' order by grade_code";
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

public List<MattersTrackingBean> getSubKeyIssueStatus() throws SQLException {

        List<MattersTrackingBean> MattersTrackings = new ArrayList<MattersTrackingBean>();
        //Map<Integer, String> myMap = new HashMap<Integer, String>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("select * from bcms_statussubkeyissuemaster \n" +
            "where is_active = 'Y'");){
            

            while (rs.next()) {

                MattersTrackingBean MattersTracking = new MattersTrackingBean();
                MattersTracking.setValue(rs.getInt("status_subkeyissue_master_id"));
                MattersTracking.setDisplayText(rs.getString("status_desc"));
                MattersTracking.setMattersID(rs.getInt("status_subkeyissue_master_id"));
                MattersTracking.setMattersCategory(rs.getString("status_desc"));
                MattersTrackings.add(MattersTracking);
                //myMap.put(rs.getInt("status_subkeyissue_master_id"), rs.getString("status_desc"));
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return MattersTrackings;
    }

public List<ContractAwardBean> getAllContractAward() throws SQLException {

        List<ContractAwardBean> ContractAwards = new ArrayList<ContractAwardBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("select contractaward_id,contractor.contractor_id, company_name,SSM_cert_no,contract.contract_id, scope_of_work, wpc, package from bcms_contractaward as contractaward \n" +
            "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
            "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
            "where contractaward.is_active = 'Y' order by company_name");){
            

            while (rs.next()) {

                ContractAwardBean ContractAward = new ContractAwardBean();
                ContractAward.setValue(rs.getDouble("contractaward_id"));
                ContractAward.setDisplayText(rs.getString("scope_of_work")+"-"+rs.getString("company_name"));
                ContractAward.setContractAwardID(rs.getInt("contractaward_id"));
                ContractAward.setCompanyName(rs.getString("company_name"));
                ContractAward.setSsmCertNo(rs.getString("SSM_cert_no"));
                ContractAward.setContractID(rs.getInt("contract_id"));
                ContractAward.setScopeWork(rs.getString("scope_of_work"));
                ContractAward.setWpc(rs.getString("wpc"));
                ContractAward.setContractPackage(rs.getString("package"));  
                ContractAwards.add(ContractAward);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return ContractAwards;
    }

public void addMattersTracking(MattersTrackingBean mattersTracking) throws SQLException {
    
        //String query = ("update bcms_contractaward set is_selected_matterstracking='Y' where contractaward_id=?");
        
        try (Connection currentCon = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = currentCon.prepareStatement
                ("insert into bcms_matterstracking(date_create,user_create,contractaward_id,matters_id, date_actual,description,remarks,matters_status) "
                + "values (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_attachmentmatterstracking(date_create,user_create,matterstracking_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
                           + "values (?,?,?,?,?,?,?)");
             
              //PreparedStatement preparedStatement4 = currentCon.prepareStatement(query);
             
                ){
    
   
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
   preparedStatement.setString(2, mattersTracking.getEmpAD());
   preparedStatement.setInt(3, mattersTracking.getContractAwardID());
   preparedStatement.setString(4,mattersTracking.getMattersCategory());
   if("".equals(mattersTracking.getDateActual())){
   preparedStatement.setNull(5, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(5, mattersTracking.getDateActual());
   }
   preparedStatement.setString(6, mattersTracking.getDescription());
   preparedStatement.setString(7, mattersTracking.getRemarks());
   preparedStatement.setString(8, mattersTracking.getMattersStatus());
   
   
   preparedStatement.executeUpdate(); 
   ResultSet rs = preparedStatement.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        mattersTracking.setMattersTrackingID(rs.getInt(1));
        //int key = rs.getInt(1);
    }
    
    if (mattersTracking.getAttachMattersTrackingLinks() != null){
       for (int i= 0; i < mattersTracking.getAttachMattersTrackingLinks().length; i++) {
           
           String attachMattersTrackingLink = (String) Array.get(mattersTracking.getAttachMattersTrackingLinks(), i);
           String attachMattersTrackingFileName = (String) Array.get(mattersTracking.getAttachMattersTrackingFileNames(), i);
           String oriAttachMattersTrackingFileName = (String) Array.get(mattersTracking.getOriAttachMattersTrackingFileNames(), i);
           if (attachMattersTrackingLink != null && !attachMattersTrackingLink.isEmpty()){
           
           preparedStatement2.setString(1, newDateStringNow);
           preparedStatement2.setString(2, mattersTracking.getEmpAD());
           preparedStatement2.setInt(3, mattersTracking.getMattersTrackingID());
           preparedStatement2.setInt(4, 10);
           preparedStatement2.setString(5, attachMattersTrackingLink);
           preparedStatement2.setString(6, attachMattersTrackingFileName);
           preparedStatement2.setString(7, oriAttachMattersTrackingFileName);
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

public List<MattersTrackingBean> getAllMattersTracking2(int jtStartIndex, int jtPageSize, String jtSorting, MattersTrackingBean mattersTracking) throws ParseException, SQLException{
 List<MattersTrackingBean> mattersTrackings = new ArrayList<MattersTrackingBean>();

    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = mattersTracking.getAction();
  if (null != mattersTracking.getAction()) switch (mattersTracking.getAction()) {
          case "matterstracking_list":
              
              query="SELECT *,ROW_NUMBER() OVER (ORDER BY contractor.contractor_id desc) AS RowNum\n" +
                "FROM  bcms_contractaward as contractaward \n" +
                "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
                "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
                "inner join bcms_subcontract as sc on contractaward.contractaward_id = sc.subcontract_id\n" +
                "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                "where contractaward.is_active='Y' and sc.is_active = 'Y' and \n" +
                "(company_name like '%"+mattersTracking.getSearchName()+"%' and scope_of_work like '%"+mattersTracking.getSearchScopeWork()+"%' and contractor.grade_id like '%"+mattersTracking.getSearchCidbGrade()+"%')ORDER BY contractor.contractor_id desc \n" +
                "OFFSET "+startIndex+" ROWS\n" +
                "FETCH NEXT "+pageSize+" ROWS ONLY";
              
                break;
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();){
   
   
   while (rs.next()) {
    mattersTracking = new MattersTrackingBean();
    
   
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
    mattersTracking.setContractAwardID(rs.getInt("contractaward_id"));
    //contractAward
    mattersTracking.setCompanyName(rs.getString("company_name"));
    mattersTracking.setScopeWork(rs.getString("scope_of_work"));
    mattersTracking.setGradeCode(rs.getString("grade_code"));
    mattersTracking.setDateCreate(newDateStringDateCreate);
    mattersTracking.setRowNumber(rs.getInt("RowNum"));
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //mattersTracking.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    mattersTrackings.add(mattersTracking);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return mattersTrackings;
 }

public List<MattersTrackingBean> getAllMattersTracking(int jtStartIndex, int jtPageSize, String jtSorting, MattersTrackingBean mattersTracking) throws ParseException, SQLException{
 List<MattersTrackingBean> mattersTrackings = new ArrayList<MattersTrackingBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
    String SearchCidbGrade = "";
    if ("".equals(mattersTracking.getSearchCidbGrade())){
    }
    else {
    Double d = new Double(mattersTracking.getSearchCidbGrade());
    int aa = d.intValue();
    SearchCidbGrade = String.valueOf(aa);
    }
    //String combinedCidbGrade = String.join(",", contractor.getSearchCidbGrades());
//    System.out.println("f"+combinedCidbGrade);
  
    String query="";
    String action = mattersTracking.getAction();
   
   
   String SPsql = "EXEC sp_searchmatterstrackinglist ?,?,?,?,?";  
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
                preparedStatement.setString(1, mattersTracking.getSearchName());
                preparedStatement.setString(2, mattersTracking.getSearchCidbGrade());
                preparedStatement.setString(3, mattersTracking.getSearchScopeWork());
                preparedStatement.setString(4, startIndex);
                preparedStatement.setString(5, pageSize);
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs = preparedStatement.executeQuery();
                        ){
                while (rs.next()) {
    mattersTracking = new MattersTrackingBean();
    
   
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
    mattersTracking.setContractAwardID(rs.getInt("contractaward_id"));
    //contractAward
    mattersTracking.setCompanyName(rs.getString("company_name"));
    mattersTracking.setScopeWork(rs.getString("scope_of_work"));
    mattersTracking.setGradeCode(rs.getString("grade_code"));
    mattersTracking.setDateCreate(newDateStringDateCreate);
    mattersTracking.setRowNumber(rs.getInt("RowNum"));
    mattersTracking.setMattersStatus(rs.getString("matters_status"));
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //mattersTracking.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    mattersTrackings.add(mattersTracking);
    
   
   }
                
   
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
  }catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
   

  return mattersTrackings;
 }

 public int getMattersTrackingViewCount(MattersTrackingBean mattersTracking) throws SQLException{
 int count=0;
 try (Connection currentCon = ConnectionManager.getConnection();
      Statement statement = currentCon.createStatement();){
                
                ResultSet rs;
                if (null != mattersTracking.getAction()) switch (mattersTracking.getAction()) {
                    
                case "matterstracking_list":
                    
                    rs = 
                    statement.executeQuery("SELECT count(*) as count\n" +
                    "FROM  bcms_contractaward as contractaward\n" +
                    "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
                    "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
                    "inner join bcms_subcontract as sc on contractaward.contractaward_id = sc.subcontract_id\n" +
                    "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "where contractaward.is_active='Y' and sc.is_active = 'Y' and\n" +
                    "(company_name like '%"+mattersTracking.getSearchName()+"%' and scope_of_work like '%"+mattersTracking.getSearchScopeWork()+"%' and contractor.grade_id like '%"+mattersTracking.getSearchCidbGrade()+"%')");
                    while (rs.next()) {
                    count=rs.getInt("count");
                  
                    }
                    break;
                
                case "viewmatterstracking_list":
                    
                     rs = 
                    statement.executeQuery("SELECT count(*) as count\n" +
                    "FROM bcms_matterstracking as matters inner join\n" +
                    "bcms_contractaward as contractaward on matters.contractaward_id = contractaward.contractaward_id\n" +
                    "inner join bcms_matterscategorymaster as mattcat on mattcat.matters_id = matters.matters_id\n" +
                    "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
                    "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
                    "inner join bcms_subcontract as sc on contractaward.contractaward_id = sc.subcontract_id\n" +
                    "where contractaward.is_active='Y' and sc.is_active = 'Y' and contractaward.contractaward_id = "+mattersTracking.getContractAwardID()+"");
                    while (rs.next()) {
                    count=rs.getInt("count");
                  
                    }
                    break;
                
                }
                //currentCon.commit();
  
   
 } catch (SQLException e) {
  e.printStackTrace();
 }
 return count;
}
 
 public int getMattersTrackingCount(MattersTrackingBean mattersTracking) throws SQLException{
 int count=0;
 String SearchCidbGrade = "";
 if ("".equals(mattersTracking.getSearchCidbGrade())){
    }
    else {
    Double d = new Double(mattersTracking.getSearchCidbGrade());
    int aa = d.intValue();
    SearchCidbGrade = String.valueOf(aa);
 }
  String SPsql = "EXEC sp_searchmatterstrackinglist_count ?,?,?";  
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
                preparedStatement.setString(1, mattersTracking.getSearchName());
                preparedStatement.setString(2, mattersTracking.getSearchCidbGrade());
                preparedStatement.setString(3, mattersTracking.getSearchScopeWork());
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
   System.out.println(e.getMessage());
  }
  }catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
 return count;
} 

 public MattersTrackingBean getMattersTrackingById(MattersTrackingBean mattersTracking) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
         PreparedStatement preparedStatement = currentCon.prepareStatement
        ("SELECT * FROM bcms_contractaward as contractaward \n" +
        "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
        "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
        "inner join bcms_subcontract as sc on contractaward.contractaward_id = sc.subcontract_id\n" +
        "where contractaward.is_active='Y' and sc.is_active = 'Y' and contractaward_id = ?");
       ){
   
        preparedStatement.setInt(1, mattersTracking.getContractAwardID());
        try (ResultSet rs = preparedStatement.executeQuery();){
   //currentCon.commit();
   
   if (rs.next()) {
       
       
    mattersTracking = new MattersTrackingBean();
    //mattersTracking.setMattersTrackingID(rs.getInt("matterstracking_id"));
    mattersTracking.setContractAwardID(rs.getInt("contractaward_id"));
    mattersTracking.setCompanyName(rs.getString("company_name"));
    mattersTracking.setScopeWork(rs.getString("scope_of_work"));
   }
        }
   
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return mattersTracking;
 }
 
 public List<MattersTrackingBean> getAllMattersTrackingView(int jtStartIndex, int jtPageSize, String jtSorting, MattersTrackingBean mattersTracking) throws ParseException, SQLException{
 List<MattersTrackingBean> mattersTrackings = new ArrayList<MattersTrackingBean>();

    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = mattersTracking.getAction();
  if (null != mattersTracking.getAction()) switch (mattersTracking.getAction()) {
          case "viewmatterstracking_list":
              
              query="SELECT *,ROW_NUMBER() OVER (ORDER BY matters.date_create) AS RowNum\n" +
                "FROM bcms_matterstracking as matters inner join\n" +
                "bcms_contractaward as contractaward on matters.contractaward_id = contractaward.contractaward_id\n" +
                "inner join bcms_matterscategorymaster as mattcat on mattcat.matters_id = matters.matters_id\n" +
                "inner join bcms_contract as contract on contract.contract_id = contractaward.contract_id\n" +
                "inner join bcms_contractor as contractor on contractor.contractor_id = contractaward.contractor_id\n" +
                "where contractaward.is_active='Y' and contractaward.contractaward_id = ?  ORDER BY matters.date_create \n" +
                "OFFSET "+startIndex+" ROWS\n" +
                "FETCH NEXT "+pageSize+" ROWS ONLY";
              
                break;
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ){
        
        preparedStatement.setInt(1, mattersTracking.getContractAwardID());  
        ResultSet rs = preparedStatement.executeQuery();
   while (rs.next()) {
    mattersTracking = new MattersTrackingBean();
    
   
   final String FORMAT_DATETIME = "dd-MM-yyyy";
   String newDateStringDateActual = "";
   String dateString = rs.getString("date_actual");   
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringDateActual = sdf3.format(d3);
   }
    mattersTracking.setRowNumber(rs.getInt("rowNum"));
    mattersTracking.setMattersTrackingID(rs.getInt("matterstracking_id"));
    mattersTracking.setContractAwardID(rs.getInt("contractaward_id"));
    mattersTracking.setMattersCategory(rs.getString("matters_category"));
    mattersTracking.setDateActual(newDateStringDateActual);
    mattersTracking.setDescription(rs.getString("description"));
    mattersTracking.setRemarks(rs.getString("remarks"));
    mattersTracking.setMattersStatus(rs.getString("matters_status"));
    //WordUtils.capitalize(emp_name);
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //mattersTracking.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    mattersTrackings.add(mattersTracking);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return mattersTrackings;
 }
 
 public void updateMattersTracking(MattersTrackingBean mattersTracking) throws ParseException, SQLException {
    
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
     
  
   
   try (PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_pdpremarks(date_create,user_create,matterstracking_id,pdp_remarks) "
                           + "values (?,?,?,?)");
         
        PreparedStatement preparedStatement3 = currentCon.prepareStatement
                   ("insert into bcms_mrtremarks(date_create,user_create,matterstracking_id,mrt_remarks) "
                           + "values (?,?,?,?)");
        
        
       
        ){

   
       
   }  
   catch (SQLException e) {
   e.printStackTrace();
  }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
 public List<MattersTrackingBean> getAllAttachmentView(MattersTrackingBean mattersTracking) throws ParseException, SQLException{
 List<MattersTrackingBean> mattersTrackings = new ArrayList<MattersTrackingBean>();

//    String startIndex=Integer.toString(jtStartIndex);
//    String pageSize=Integer.toString(jtPageSize);
//    String defaultSorting = jtSorting;
  
    String query="";
    String action = mattersTracking.getAction();
  if (null != mattersTracking.getAction()) switch (mattersTracking.getAction()) {
          case "attachment_list":
              
              query="select *,ROW_NUMBER() OVER (ORDER BY matters.date_create) AS RowNum\n" +
                "FROM bcms_matterstracking as matters inner join bcms_attachmentmatterstracking as m on matters.matterstracking_id = m.matterstracking_id\n" +
                "where matters.matterstracking_id = ? ORDER BY m.date_create  \n";
//                "OFFSET "+startIndex+" ROWS\n" +
//                "FETCH NEXT "+pageSize+" ROWS ONLY";
              
                break;
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ){
        
        preparedStatement.setInt(1, mattersTracking.getMattersTrackingID());  
        ResultSet rs = preparedStatement.executeQuery();
   while (rs.next()) {
    mattersTracking = new MattersTrackingBean();
    
   
   final String FORMAT_DATETIME = "dd-MM-yyyy";
   String newDateStringDateActual = "";
   String dateString = rs.getString("date_actual");   
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringDateActual = sdf3.format(d3);
   }
    mattersTracking.setRowNumber(rs.getInt("rowNum"));
    mattersTracking.setMattersTrackingID(rs.getInt("matterstracking_id"));
    mattersTracking.setAttachMattersTrackingID(rs.getInt("matterstracking_attachment_id"));
    mattersTracking.setAttachMattersTrackingMasterID(rs.getInt("attachmentmaster_id"));
    mattersTracking.setAttachMattersTrackingLink(rs.getString("attachment_link"));
    mattersTracking.setAttachMattersTrackingFileName(rs.getString("file_name"));
    mattersTracking.setOriAttachMattersTrackingFileName(rs.getString("ori_file_name"));
  
    
    mattersTrackings.add(mattersTracking);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return mattersTrackings;
 }
}



