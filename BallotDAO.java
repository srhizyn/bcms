/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.BallotBean;
import Bean.ContractBean;
import Bean.ContractorBean;
import Utility.ConnectionManager;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author munawwarah.mahmod
 */
public class BallotDAO {
    
    //private Connection currentCon = null;  

    //ResultSet rs = null;
    
    
public List<ContractBean> getContract() throws SQLException {

        List<ContractBean> Contracts = new ArrayList<ContractBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("SELECT contract_id, package from bcms_contract where is_active='Y' order by contract_id");){
            

            while (rs.next()) {

                ContractBean Contract = new ContractBean();
                Contract.setValue(rs.getDouble("contract_id"));
                Contract.setDisplayText(rs.getString("package"));
                Contract.setContractID(rs.getInt("contract_id"));
                Contract.setContractPackage(rs.getString("package"));
                               
                Contracts.add(Contract);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contracts;
    }
    
public List<ContractorBean> getContractor() throws SQLException {

        List<ContractorBean> Contractors = new ArrayList<ContractorBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
            ResultSet rs = statement.executeQuery("SELECT contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id from bcms_contractor as contractor\n" +
                                        "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                        "where contractor.is_active='Y' order by contractor_id");){
            

            while (rs.next()) {

                ContractorBean Contractor = new ContractorBean();
                Contractor.setValue(rs.getInt("contractor_id"));
                Contractor.setDisplayText(rs.getString("company_name"));
                Contractor.setContractorID(rs.getInt("contractor_id"));
                Contractor.setCompanyName(rs.getString("company_name"));
                Contractor.setSsmCertNo(rs.getString("SSM_cert_no"));
                Contractor.setGradeCode(rs.getString("grade_code"));
                Contractor.setGradeID(rs.getInt("grade_id"));
                               
                Contractors.add(Contractor);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Contractors;
    }
    
public List<BallotBean> getSelectedContractor(BallotBean ballot) throws SQLException {

        List<BallotBean> Ballots = new ArrayList<BallotBean>();
       
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id from bcms_contractor as contractor\n" +
//                                        "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
//                                        "where contractor.is_active='Y' and contractor.grade_id in ("+ballot.getGradeCode()+") order by contractor.grade_id,company_name");
              ResultSet rs = statement.executeQuery("select *,\n" +
                                "ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                                "SELECT distinct ssm_cert_no, contractor.contractor_id as contractor_id, COALESCE(NULLIF(contractor.company_name,''),'') as company_name, \n" +
                                "cidb.grade_code, cidb.grade_id\n" +
                                "FROM dbo.bcms_ballotworkpackage as bwp\n" +
                                "inner join bcms_ballotmaster as bm on bm.ballotmaster_id = bwp.ballotmaster_id\n" +
                                "inner join bcms_ballotcidbgrade as bcg on bcg.ballotworkpackage_id = bwp.ballotworkpackage_id\n" +
                                "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = bcg.grade_id\n" +
                                "inner join bcms_contractor as contractor on contractor.grade_id = bcg.grade_id\n" +
                                "where bwp.ballotworkpackage_id = "+ballot.getBallotWorkPackageID()+"\n" +
                                "and bwp.is_active = 'Y')  as c\n" +
                                "ORDER BY grade_code,company_name");  
                ){
            

            while (rs.next()) {

                BallotBean Ballot = new BallotBean();
                Ballot.setValue(rs.getDouble("contractor_id"));
                Ballot.setDisplayText(rs.getString("company_name"));
                Ballot.setContractorID(rs.getInt("contractor_id"));
                Ballot.setCompanyName(rs.getString("company_name"));
                Ballot.setSsmCertNo(rs.getString("SSM_cert_no"));
                Ballot.setGradeCode(rs.getString("grade_code"));
                Ballot.setGradeID(rs.getInt("grade_id"));
                               
                Ballots.add(Ballot);
            }
            
        
        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }
        

        return Ballots;
    }
   
public List<BallotBean> getSelectedWorkPackage(BallotBean ballot) throws SQLException {

        List<BallotBean> Ballots = new ArrayList<BallotBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            Statement statement = currentCon.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT contractor_id, company_name,SSM_cert_no, grade_code, contractor.grade_id from bcms_contractor as contractor\n" +
//                                        "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
//                                        "where contractor.is_active='Y' and contractor.grade_id in ("+ballot.getGradeCode()+") order by contractor.grade_id,company_name");
              ResultSet rs = statement.executeQuery("select *,CONCAT(grade_code, '; ', specidcombine, '; ', spkk_sub_category) as workpackage,ROW_NUMBER() OVER (ORDER BY ballotmaster_id desc) AS RowNum from (\n" +
                                "SELECT distinct wp.ballotmaster_id, wp.ballotworkpackage_id, cg.grade_id, cgm.grade_code, \n" +
                                "specidcombine = STUFF((\n" +
                                "SELECT ', ' + ta.specialisation_code \n" +
                                "FROM dbo.bcms_ballotworkpackage as c\n" +
                                "left join bcms_ballotspecialisation as l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                                "left JOIN dbo.bcms_specialisationmaster AS ta ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                "WHERE l.ballotworkpackage_id = bs.ballotworkpackage_id and l.is_active = 'Y' \n" +
                                "FOR XML PATH ('')),1,1,''),\n" +
                                "spkk_sub_category = STUFF(\n" +
                                "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                "FROM dbo.bcms_ballotworkpackage as c\n" +
                                "left join dbo.bcms_ballotspkkspec AS l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                                "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                "WHERE  l.ballotworkpackage_id = bss.ballotworkpackage_id and l.is_active = 'Y'\n" +
                                "FOR XML PATH ('')),1,1,'')\n" +
                                "from bcms_ballotmaster as bm\n" +
                                "inner join bcms_ballotworkpackage as wp on bm.ballotmaster_id = wp.ballotmaster_id\n" +
                                "inner join bcms_ballotcidbgrade as cg on wp.ballotworkpackage_id = cg.ballotworkpackage_id\n" +
                                "inner join bcms_cidbgrademaster as cgm on cg.grade_id = cgm.grade_id\n" +
                                "left join bcms_ballotspecialisation as bs on bs.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                                "left join bcms_ballotspkkspec as bss on bss.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                                "inner join bcms_spkkspecialisationmaster as ssm on ssm.spkkspecmaster_id = bss.spkkspecmaster_id\n" +
                                "where wp.is_active='Y' and bm.ballotmaster_id="+ballot.getBallotMasterID()+") as c\n" +
                                "order by ballotmaster_id desc");  
                ){
            

            while (rs.next()) {

                BallotBean Ballot = new BallotBean();
                //Ballot.setValue(rs.getDouble("contractor_id"));
                //Ballot.setDisplayText(rs.getString("company_name"));
                Ballot.setBallotMasterID(rs.getInt("ballotmaster_id"));
                Ballot.setBallotWorkPackageID(rs.getInt("ballotworkpackage_id"));
                Ballot.setGradeCode(rs.getString("grade_code"));
                Ballot.setGradeID(rs.getInt("grade_id"));
                Ballot.setSpecialisationCode(rs.getString("specidcombine"));
                Ballot.setSpkkSpecCode(rs.getString("spkk_sub_category"));
                Ballot.setBallotWorkPackage(rs.getString("workpackage"));
                               
                Ballots.add(Ballot);
            }
            
        
        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }
        

        return Ballots;
    }

public List<BallotBean> getBallotStatus() throws SQLException {

        List<BallotBean> ballots = new ArrayList<BallotBean>();
        String query = "SELECT ballotstatusmaster_id, ballotstatus_code,ballotstatus_desc from bcms_ballotstatusmaster where is_active='Y' order by ballotstatusmaster_id";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean ballot = new BallotBean();
                ballot.setValue(rs.getDouble("ballotstatusmaster_id"));
                //ballot.setDisplayText(rs.getString("program_code") +"-"+ rs.getString("program_desc"));
                ballot.setBallotStatusID(rs.getString("ballotstatusmaster_id"));
                ballot.setBallotStatusCode(rs.getString("ballotstatus_code"));
                ballot.setBallotStatusDesc(rs.getString("ballotstatus_desc"));
                               
                ballots.add(ballot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ballots;
    }
   
public List<BallotBean> getCIDBGrade() throws SQLException {

        List<BallotBean> CIDBgrades = new ArrayList<BallotBean>();
        String query = "SELECT grade_id, grade_code,speccidb_id from bcms_CIDBgrademaster where is_active='Y' order by grade_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean CIDBgrade = new BallotBean();
                CIDBgrade.setValue(rs.getDouble("grade_id"));
                CIDBgrade.setDisplayText(rs.getString("grade_code"));
                CIDBgrade.setGradeID(rs.getInt("grade_id"));
                CIDBgrade.setGradeCode(rs.getString("grade_code"));
                               
                CIDBgrades.add(CIDBgrade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CIDBgrades;
    }
    
public List<BallotBean> getCIDBGradeBallot() throws SQLException {

        List<BallotBean> CIDBgrades = new ArrayList<BallotBean>();
        String query = "SELECT grade_id, grade_code,speccidb_id from bcms_CIDBgrademaster where is_active='Y' and grade_id in (1,2,3,4) order by grade_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean CIDBgrade = new BallotBean();
                CIDBgrade.setValue(rs.getDouble("grade_id"));
                CIDBgrade.setDisplayText(rs.getString("grade_code"));
                CIDBgrade.setGradeID(rs.getInt("grade_id"));
                CIDBgrade.setGradeCode(rs.getString("grade_code"));
                               
                CIDBgrades.add(CIDBgrade);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CIDBgrades;
    }
    
public List<ContractorBean> getSpecialisation() throws SQLException {

        List<ContractorBean> Specialisations = new ArrayList<ContractorBean>();
        String query = "SELECT specialisationmaster_id, speccidb_id, specialisation_code, specialisation_desc from bcms_specialisationmaster where is_active='Y' or specialisation_code = 'N/A' order by specialisation_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
             

            while (rs.next()) {

                ContractorBean Specialisation = new ContractorBean();
                Specialisation.setValue(rs.getInt("specialisationmaster_id"));
                Specialisation.setDisplayText(rs.getString("specialisation_code"));
                Specialisation.setSpecialisationID(rs.getInt("specialisationmaster_id"));
                Specialisation.setSpecialisationCode(rs.getString("specialisation_code"));
                Specialisation.setSpecialisationDesc(rs.getString("specialisation_desc"));
                Specialisation.setGroupingB(rs.getInt("speccidb_id"));
                               
                Specialisations.add(Specialisation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Specialisations;
    }
    
public List<BallotBean> getSpecSow() throws SQLException {

        List<BallotBean> specSows = new ArrayList<BallotBean>();
        String query = "SELECT * from bcms_subcontractspec as sc \n" +
                        "inner join bcms_specialisationmaster as specm on sc.specialisationmaster_id = specm.specialisationmaster_id\n" +
                        "inner join bcms_subcontract as subcontract on sc.subcontract_id = subcontract.subcontract_id\n" +
                        "inner join bcms_contract as contract on contract.contract_id = subcontract.contract_id where sc.is_active='Y' order by subcontractspec_id";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean specSow = new BallotBean();
                specSow.setSubContractSpecID(rs.getInt("subcontractspec_id"));
                specSow.setSpecialisationCode(rs.getString("specialisation_code"));
                specSow.setScopeWork(rs.getString("scope_of_work"));               
                specSows.add(specSow);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specSows;
    }
    
public List<BallotBean> getSPKKSpecCode() throws SQLException {

        List<BallotBean> SPKKs = new ArrayList<BallotBean>();
        String query = "SELECT spkkspecmaster_id, spkkspecmaster_code, spkkspecmaster_desc, spkkcat_id from bcms_spkkspecialisationmaster where is_active='Y' or spkkspecmaster_code = 'N/A' order by spkkspecmaster_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            

            while (rs.next()) {

                BallotBean SPKK = new BallotBean();
                SPKK.setSpkkSpecID(rs.getInt("spkkspecmaster_id"));
                SPKK.setSpkkSpecCode(rs.getString("spkkspecmaster_code"));
                SPKK.setSpkkSpecDesc(rs.getString("spkkspecmaster_desc"));
                SPKK.setSpkkCatID(rs.getInt("spkkcat_id"));
                               
                SPKKs.add(SPKK);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SPKKs;
    }

public List<BallotBean> getBallotSession() throws SQLException, ParseException {

        List<BallotBean> ballotSessions = new ArrayList<BallotBean>();
        String query = "SELECT distinct c.ballotmaster_id, title_ballot,date_ballot, venue_ballot\n" +
                       "FROM dbo.bcms_ballotmaster as c\n" +
                       "where c.is_active='Y' order by date_ballot";
        
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                BallotBean ballotSession = new BallotBean();
                
                final String FORMAT_DATETIME = "dd-MM-yyyy";
                String BallotDate = "";
                String dateString = rs.getString("date_ballot"); 
                if (dateString != null && !dateString.isEmpty()){
                SimpleDateFormat sdf3 =
                         new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d3 = sdf3.parse(dateString);
                sdf3.applyPattern(FORMAT_DATETIME);
                BallotDate = sdf3.format(d3);
                }
       
       
                ballotSession.setValue(rs.getDouble("ballotmaster_id"));
                ballotSession.setDisplayText(BallotDate);
                ballotSession.setBallotMasterID(rs.getInt("ballotmaster_id"));
                ballotSession.setTitleBallot(rs.getString("title_ballot"));
                ballotSession.setDateBallot(BallotDate);
                ballotSession.setVenueBallot(rs.getString("venue_ballot")); 
                ballotSessions.add(ballotSession);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ballotSessions;
    }
    
public void addBallot(BallotBean ballot) throws SQLException {
       try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = currentCon.prepareStatement
           ("insert into bcms_ballot(date_create, user_create, emp_id, contractor_id, ballotmaster_id,ballotstatusmaster_id,ballotworkpackage_id) "
                + "values (?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_attachmentballot(date_create,user_create,ballot_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
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
   preparedStatement.setString(2, ballot.getEmpAD());
   preparedStatement.setDouble(3, ballot.getEmpID());
   preparedStatement.setInt(4, ballot.getContractorID());
   preparedStatement.setInt(5, ballot.getBallotMasterID());
   preparedStatement.setString(6, ballot.getBallotStatusID());
   preparedStatement.setInt(7, ballot.getBallotWorkPackageID());
   preparedStatement.executeUpdate(); 
   
    ResultSet rs = preparedStatement.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        ballot.setBallotID(rs.getInt(1));
        //int key = rs.getInt(1);
    }
   if (ballot.getAttachBallotLinks() != null){
       for (int i= 0; i < ballot.getAttachBallotLinks().length; i++) {
           
           String attachBallotLink = (String) Array.get(ballot.getAttachBallotLinks(), i);
           String attachBallotFileName = (String) Array.get(ballot.getAttachBallotFileNames(), i);
           String oriAttachBallotFileName = (String) Array.get(ballot.getOriAttachBallotFileNames(), i);
           if (attachBallotLink != null && !attachBallotLink.isEmpty()){
           
           preparedStatement2.setString(1, newDateStringNow);
           preparedStatement2.setString(2, ballot.getEmpAD());
           preparedStatement2.setInt(3, ballot.getBallotID());
           preparedStatement2.setInt(4, 8);
           preparedStatement2.setString(5, attachBallotLink);
           preparedStatement2.setString(6, attachBallotFileName);
           preparedStatement2.setString(7, oriAttachBallotFileName);
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

public void addBallotMaster(BallotBean ballot) throws SQLException {
       try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = currentCon.prepareStatement
           ("insert into bcms_ballotmaster(date_create, user_create, title_ballot, date_ballot, venue_ballot) "
                + "values (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
//            PreparedStatement preparedStatement2 = currentCon.prepareStatement
//                   ("insert into bcms_ballotcidbgrade(date_create,user_create,ballotmaster_id, grade_id) "
//                           + "values (?,?,?,?)");
//            PreparedStatement preparedStatement6 = currentCon.prepareStatement
//                   ("insert into bcms_ballotworkpackage(date_create,user_create,ballotmaster_id, grade_id) "
//                           + "values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
//            PreparedStatement preparedStatement3 = currentCon.prepareStatement
//                   ("insert into bcms_ballotspecialisation(date_create,user_create,ballotmaster_id, ballotworkpackage_id, specialisationmaster_id) "
//                           + "values (?,?,?,?,?)");
//            PreparedStatement preparedStatement5 = currentCon.prepareStatement
//                   ("insert into bcms_ballotspkkspec(date_create,user_create,ballotmaster_id, ballotworkpackage_id, spkkspecmaster_id) "
//                           + "values (?,?,?,?,?)");
            PreparedStatement preparedStatement4 = currentCon.prepareStatement
                   ("insert into bcms_attachmentballotsession(date_create,user_create,ballotmaster_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
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
   preparedStatement.setString(2, ballot.getEmpAD());
   preparedStatement.setString(3, ballot.getTitleBallot());
   preparedStatement.setString(4, ballot.getDateBallot());
   preparedStatement.setString(5, ballot.getVenueBallot());
   preparedStatement.executeUpdate(); 
   
//    ResultSet rs = preparedStatement.getGeneratedKeys();
//    if ( rs.next()) {
//        // Retrieve the auto generated key(s).
//        //ContractorBean newcontractor = new ContractorBean();
//        ballot.setBallotMasterID(rs.getInt(1));
//        //int key = rs.getInt(1);
//    }
    
//    if (ballot.getCidbGrades() != null){
//       for (int i= 0; i < ballot.getCidbGrades().length; i++){
//           
//           String cidbGrade = (String) Array.get(ballot.getCidbGrades(), i);
//            if (cidbGrade != null && !cidbGrade.isEmpty()){
//           preparedStatement6.setString(1, newDateStringNow);
//           preparedStatement6.setString(2, ballot.getEmpAD());
//           preparedStatement6.setInt(3, ballot.getBallotMasterID());
//           preparedStatement6.setString(4, cidbGrade);
//           preparedStatement6.executeUpdate();
//           
//            ResultSet rs2 = preparedStatement6.getGeneratedKeys();
//            if ( rs2.next()) {
//                // Retrieve the auto generated key(s).
//                //ContractorBean newcontractor = new ContractorBean();
//                ballot.setBallotWorkPackageID(rs2.getInt(1));
//                //int key = rs.getInt(1);
//                
//            if (ballot.getSpecialisationIDs()!= null){
//               for (int j= 0; j < ballot.getSpecialisationIDs().length; j++){
//
//                   String specialisation = (String) Array.get(ballot.getSpecialisationIDs(), j);
//                    if (specialisation != null && !specialisation.isEmpty()){
//                   preparedStatement3.setString(1, newDateStringNow);
//                   preparedStatement3.setString(2, ballot.getEmpAD());
//                   preparedStatement3.setInt(3, ballot.getBallotMasterID());
//                   preparedStatement3.setInt(4, ballot.getBallotWorkPackageID());
//                   preparedStatement3.setString(5, specialisation);
//                   preparedStatement3.executeUpdate();
//                    }
//               }
//              }
//
//            if (ballot.getSpkkSpecIDs()!= null){
//               for (int k= 0; k < ballot.getSpkkSpecIDs().length; k++){
//
//                   String spkkspec = (String) Array.get(ballot.getSpkkSpecIDs(), k);
//                    if (spkkspec != null && !spkkspec.isEmpty()){
//                   preparedStatement5.setString(1, newDateStringNow);
//                   preparedStatement5.setString(2, ballot.getEmpAD());
//                   preparedStatement5.setInt(3, ballot.getBallotMasterID());
//                   preparedStatement5.setInt(4, ballot.getBallotWorkPackageID());
//                   preparedStatement5.setString(5, spkkspec);
//                   preparedStatement5.executeUpdate();
//                    }
//               }
//              }
//            }
//            }
//       }
//      }
    
   
    
//   if (ballot.getCidbGrades() != null){
//       for (int i= 0; i < ballot.getCidbGrades().length; i++){
//           
//           String cidbGrade = (String) Array.get(ballot.getCidbGrades(), i);
//            if (cidbGrade != null && !cidbGrade.isEmpty()){
//           preparedStatement2.setString(1, newDateStringNow);
//           preparedStatement2.setString(2, ballot.getEmpAD());
//           preparedStatement2.setInt(3, ballot.getBallotMasterID());
//           preparedStatement2.setString(4, cidbGrade);
//           preparedStatement2.executeUpdate();
//            }
//       }
//      }
   
    
    
    if (ballot.getAttachBallotSessionLinks() != null){
       for (int i= 0; i < ballot.getAttachBallotSessionLinks().length; i++) {
           
           String attachBallotSessionLink = (String) Array.get(ballot.getAttachBallotSessionLinks(), i);
           String attachBallotSessionFileName = (String) Array.get(ballot.getAttachBallotSessionFileNames(), i);
           String oriAttachBallotSessionFileName = (String) Array.get(ballot.getOriAttachBallotSessionFileNames(), i);
           if (attachBallotSessionLink != null && !attachBallotSessionLink.isEmpty()){
           
           preparedStatement4.setString(1, newDateStringNow);
           preparedStatement4.setString(2, ballot.getEmpAD());
           preparedStatement4.setInt(3, ballot.getBallotMasterID());
           preparedStatement4.setInt(4, 9);
           preparedStatement4.setString(5, attachBallotSessionLink);
           preparedStatement4.setString(6, attachBallotSessionFileName);
           preparedStatement4.setString(7, oriAttachBallotSessionFileName);
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
   catch(ParseException pe) {
        pe.printStackTrace();
       // currentCon.rollback();
        }
    } 

public void addBallotMasterDetails(BallotBean ballot) throws SQLException {
       try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement4 = currentCon.prepareStatement
           ("insert into bcms_ballotworkpackage(date_create, user_create, ballotmaster_id) "
                + "values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatement = currentCon.prepareStatement
                   ("insert into bcms_ballotcidbgrade(date_create,user_create,ballotworkpackage_id, grade_id) "
                           + "values (?,?,?,?)");
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
                   ("insert into bcms_ballotspecialisation(date_create,user_create,ballotworkpackage_id, specialisationmaster_id) "
                           + "values (?,?,?,?)");
            PreparedStatement preparedStatement3 = currentCon.prepareStatement
                   ("insert into bcms_ballotspkkspec(date_create,user_create,ballotworkpackage_id, spkkspecmaster_id) "
                           + "values (?,?,?,?)");
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
   
   preparedStatement4.setString(1, newDateStringNow);
   preparedStatement4.setString(2, ballot.getEmpAD());
   preparedStatement4.setInt(3, ballot.getBallotMasterID());
   preparedStatement4.executeUpdate(); 
   
    ResultSet rs = preparedStatement4.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        ballot.setBallotWorkPackageID(rs.getInt(1));
        //int key = rs.getInt(1);
    }

   if (ballot.getCidbGrades() != null){
       for (int i= 0; i < ballot.getCidbGrades().length; i++){
           
           String cidbGrade = (String) Array.get(ballot.getCidbGrades(), i);
            if (cidbGrade != null && !cidbGrade.isEmpty()){
           preparedStatement.setString(1, newDateStringNow);
           preparedStatement.setString(2, ballot.getEmpAD());
           preparedStatement.setInt(3, ballot.getBallotWorkPackageID());
           preparedStatement.setString(4, cidbGrade);
           preparedStatement.executeUpdate();
            }
       }
      }
   
   if (ballot.getSpecialisationIDs() != null){
       for (int i= 0; i < ballot.getSpecialisationIDs().length; i++){
           
           String specialisation = (String) Array.get(ballot.getSpecialisationIDs(), i);
            if (specialisation != null && !specialisation.isEmpty()){
           preparedStatement2.setString(1, newDateStringNow);
           preparedStatement2.setString(2, ballot.getEmpAD());
           preparedStatement2.setInt(3, ballot.getBallotWorkPackageID());
           preparedStatement2.setString(4, specialisation);
           preparedStatement2.executeUpdate();
            }
       }
      }
   
   if (ballot.getSpkkSpecIDs() != null){
       for (int i= 0; i < ballot.getSpkkSpecIDs().length; i++){
           
           String spkkSpec = (String) Array.get(ballot.getSpkkSpecIDs(), i);
            if (spkkSpec != null && !spkkSpec.isEmpty()){
           preparedStatement3.setString(1, newDateStringNow);
           preparedStatement3.setString(2, ballot.getEmpAD());
           preparedStatement3.setInt(3, ballot.getBallotWorkPackageID());
           preparedStatement3.setString(4, spkkSpec);
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
   catch(ParseException pe) {
        pe.printStackTrace();
       // currentCon.rollback();
        }
    } 

public List<BallotBean> getAllBallot(int jtStartIndex, int jtPageSize, String jtSorting, BallotBean ballot) throws ParseException, SQLException{
    List<BallotBean> ballots = new ArrayList<BallotBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = ballot.getAction();
  if (null != ballot.getAction()) switch (ballot.getAction()) {
          case "ballot_list":
                //query="SELECT *, ROW_NUMBER() OVER(ORDER BY contractor_id) AS Row from bcms_contractor";
              
              query="select *,CONCAT(grade_code, '; ', specidcombine, '; ', spkk_sub_category) as workpackage,ROW_NUMBER() OVER (ORDER BY ballotmaster_id desc) AS RowNum from (\n" +
                    "SELECT distinct ballot.*, bm.title_ballot, bm.venue_ballot, bsm.ballotstatus_desc, cidb.grade_code, contractor.company_name, \n" +
                    "COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'No Data') as cidb_exp_date,\n" +
                    "specidcombine = STUFF((\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_ballotworkpackage as c\n" +
                    "left join bcms_ballotspecialisation as l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.ballotworkpackage_id = bs.ballotworkpackage_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_ballotworkpackage as c\n" +
                    "inner join dbo.bcms_ballotspkkspec AS l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                    "inner JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE  l.ballotworkpackage_id = bss.ballotworkpackage_id and l.is_active = 'Y'\n" +
                    "FOR XML PATH ('')),1,1,'') FROM bcms_ballot as ballot\n" +
                    "inner join bcms_contractor as contractor on contractor.contractor_id = ballot.contractor_id\n" +
                    "inner join bcms_ballotstatusmaster as bsm on ballot.ballotstatusmaster_id = bsm.ballotstatusmaster_id\n" +
                    "inner join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "inner join bcms_ballotworkpackage as wp on bm.ballotmaster_id = wp.ballotmaster_id and ballot.ballotworkpackage_id = wp.ballotworkpackage_id \n" +
                    "inner join bcms_ballotcidbgrade as cg on wp.ballotworkpackage_id = cg.ballotworkpackage_id\n" +
                    "inner join bcms_cidbgrademaster as cidb on cidb.grade_id = cg.grade_id "+
                    "inner join bcms_ballotspecialisation as bs on bs.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                    "inner join bcms_ballotspkkspec as bss on bss.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                    "where ballot.is_active ='Y' and company_name like '%"+ballot.getSearchName()+"%') as c ORDER BY ballot_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
              
                      
                break;
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();){
   
   
    while (rs.next()) {
    ballot = new BallotBean();
    
   
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
   
   String newDateStringCIDBExpiryDate = "";
   String dateString2 = rs.getString("CIDB_exp_date"); 
   if (dateString2 != null && !dateString2.isEmpty() && !"No Data".equals(dateString2)){
   SimpleDateFormat sdf4 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d4 = sdf4.parse(dateString2);
   sdf4.applyPattern(FORMAT_DATETIME);
   newDateStringCIDBExpiryDate = sdf4.format(d4);
   }
   else if ("No Data".equals(dateString2)){
   newDateStringCIDBExpiryDate = rs.getString("CIDB_exp_date"); 
   }
   
    ballot.setBallotID(rs.getInt("ballot_id"));
    ballot.setContractorID(rs.getInt("contractor_id"));
    ballot.setCompanyName(rs.getString("company_name"));
    ballot.setBallotMasterID(rs.getInt("ballotmaster_id"));
    ballot.setBallotStatusID(rs.getString("ballotstatusmaster_id"));
    ballot.setBallotStatusDesc(rs.getString("ballotstatus_desc"));
    ballot.setGradeCode(rs.getString("grade_code"));
    ballot.setBallotWorkPackage(rs.getString("workpackage"));
    ballot.setCidbExpiryDate(newDateStringCIDBExpiryDate);
    ballot.setDateCreate(newDateStringDateCreate);
    ballot.setRowNumber(rs.getInt("RowNum"));
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //ballot.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    ballots.add(ballot);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return ballots;
 }

public int getBallotCount(BallotBean ballot) throws SQLException{
 int count=0;
 try (Connection currentCon = ConnectionManager.getConnection();
      Statement statement = currentCon.createStatement();
      ){
                
                if (null != ballot.getAction()) switch (ballot.getAction()) {
                    
                case "ballot_list":
                     try (ResultSet rs = statement.executeQuery("select count(*) as count from bcms_ballot as ballot inner join "
                             + "bcms_contractor as contractor on ballot.contractor_id = contractor.contractor_id inner join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id where ballot.is_active='Y' and company_name like '%"+ballot.getSearchName()+"%'");){
                    
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

public void deleteBallot(double ballotID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement = currentCon.prepareStatement("update bcms_ballot set is_active='N' where ballot_id=?");){
   
   // Parameters start with 1
   preparedStatement.setDouble(1, ballotID);
   preparedStatement.executeUpdate();
   
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
public BallotBean getBallotById(BallotBean ballot) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement("select ballot.*, bm.date_ballot, bm.title_ballot, bm.venue_ballot, SSM_cert_no, grade_code, company_name  from bcms_ballot as ballot \n" +
                                        "inner join bcms_contractor as contractor on ballot.contractor_id = contractor.contractor_id\n" +
                                        "inner join bcms_cidbgrademaster as cidb on contractor.grade_id = cidb.grade_id\n" +
                                        "inner join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                                        "where ballot_id=?");){
   
   preparedStatement.setInt(1, ballot.getBallotID());
   ResultSet rs = preparedStatement.executeQuery();
   //currentCon.commit();
   
   if (rs.next()) {
       
      final String FORMAT_DATETIME = "yyyy-MM-dd";
       String BallotDate = "";
       String dateString = rs.getString("date_ballot");   
       if (dateString != null && !dateString.isEmpty()){
       SimpleDateFormat sdf3 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d3 = sdf3.parse(dateString);
       sdf3.applyPattern(FORMAT_DATETIME);
       BallotDate = sdf3.format(d3);
       }
       
       
       
    ballot = new BallotBean();
    ballot.setBallotID(rs.getInt("ballot_id"));
    ballot.setContractorID(rs.getInt("contractor_id"));
    ballot.setCompanyName(rs.getString("company_name"));
    ballot.setSsmCertNo(rs.getString("SSM_cert_no"));
    ballot.setGradeCode(rs.getString("grade_code"));
    ballot.setBallotMasterID(rs.getInt("ballotmaster_id"));
    ballot.setBallotStatusID(rs.getString("ballotstatusmaster_id"));
    ballot.setBallotWorkPackageID(rs.getInt("ballotworkpackage_id"));
    ballot.setDateBallot(BallotDate);
    
    
    }
   
  //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return ballot;
 }
 
public void updateBallot(BallotBean ballot) throws ParseException, SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_ballot set date_update=?, user_update=?, contractor_id=?, date_ballot=?, ballotstatusmaster_id=?, ballotworkpackage_id=?  where ballot_id=?");
       PreparedStatement preparedStatement2 = currentCon.prepareStatement
         ("insert into bcms_attachmentballot(date_create,user_create,contractor_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
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
   
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, ballot.getEmpAD());
   preparedStatement.setInt(3, ballot.getContractorID()); 
   preparedStatement.setString(4, ballot.getDateBallot());
   preparedStatement.setString(5, ballot.getBallotStatusID());
   preparedStatement.setInt(6, ballot.getBallotWorkPackageID());
   preparedStatement.setInt(7, ballot.getBallotID());
   preparedStatement.executeUpdate();
   
   if (ballot.getAttachBallotLinks() != null){
       for (int i= 0; i < ballot.getAttachBallotLinks().length; i++) {
           
           String attachBallotLink = (String) Array.get(ballot.getAttachBallotLinks(), i);
           String attachBallotFileName = (String) Array.get(ballot.getAttachBallotFileNames(), i);
           String oriAttachBallotFileName = (String) Array.get(ballot.getOriAttachBallotFileNames(), i);
           if (attachBallotLink != null && !attachBallotLink.isEmpty()){
           
           preparedStatement2.setString(1, newDateStringNow);
           preparedStatement2.setString(2, ballot.getEmpAD());
           preparedStatement2.setInt(3, ballot.getContractorID());
           preparedStatement2.setInt(4, 8);
           preparedStatement2.setString(5, attachBallotLink);
           preparedStatement2.setString(6, attachBallotFileName);
           preparedStatement2.setString(7, oriAttachBallotFileName);
           preparedStatement2.executeUpdate();
       }
       }
        }
   
   
   
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
 
public ContractorBean getContractorById(ContractorBean contractor) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
         PreparedStatement preparedStatement = currentCon.prepareStatement
        ("select contractor.*, contractor.grade_id, speccidb_id from bcms_contractor as contractor \n" +
        "inner join bcms_cidbgrademaster as cidbmaster on cidbmaster.grade_id = contractor.grade_id\n" +
        "where contractor_id=?");
        PreparedStatement preparedStatement2 = currentCon.prepareStatement
        ("select spkkcatmaster_id from bcms_contractor as contractor\n" +
        "inner join bcms_SPKKcategory as spkk on contractor.contractor_id = spkk.contractor_id\n" +
        "where contractor.contractor_id=?");){
   
        preparedStatement.setInt(1, contractor.getContractorID());
        try (ResultSet rs = preparedStatement.executeQuery();){
   //currentCon.commit();
   
   if (rs.next()) {
       
       
       
       
    contractor = new ContractorBean();
    contractor.setContractorID(rs.getInt("contractor_id"));
    contractor.setSsmCertNo(rs.getString("SSM_cert_no"));
    contractor.setGradeID(rs.getInt("grade_id"));
    
    
    
   preparedStatement2.setInt(1, contractor.getContractorID());
   ResultSet rs2 = preparedStatement2.executeQuery();

   List rowValues = new ArrayList();
    while (rs2.next()) {
        rowValues.add(rs2.getString(1));
    }   
    // You can then put this back into an array if necessary
    String[] contactListNames;
    contactListNames = (String[]) rowValues.toArray(new String[rowValues.size()]);
    
    contractor.setSpkkCatIDs(contactListNames);
    }
        }
   
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return contractor;
 }

public List<BallotBean> getInsertedAttachment(BallotBean ballot, String attachmentType) throws SQLException {
        
        List<BallotBean> Attachments = new ArrayList<BallotBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select ballot_attachment_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentballot as attachment\n" +
            "inner join bcms_ballot as ballot on attachment.ballot_id = ballot.ballot_id\n" +
            "where attachment.ballot_id=? and attachmentmaster_id=8 and attachment.is_active='Y'");
               
            PreparedStatement preparedStatement3 = currentCon.prepareStatement
            ("select ballotsession_attachment_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentballotsession as attachment\n" +
            "inner join bcms_ballotmaster as ballot on attachment.ballotmaster_id = ballot.ballotmaster_id\n" +
            "where attachment.ballotmaster_id=? and attachmentmaster_id=9 and attachment.is_active='Y'");
            
            ){
                
        if (null != attachmentType) switch (attachmentType) {
                   
                case "ballot":
                preparedStatement2.setInt(1, ballot.getBallotID());
                try (ResultSet rs = preparedStatement2.executeQuery();){

                while (rs.next()) {

                    BallotBean Attachment = new BallotBean();
                    Attachment.setAttachBallotID(rs.getInt("ballot_attachment_id"));
                    Attachment.setAttachBallotMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachBallotLink(rs.getString("attachment_link"));
                    Attachment.setAttachBallotFileName(rs.getString("file_name"));
                    Attachment.setOriAttachBallotFileName(rs.getString("ori_file_name"));
                    Attachments.add(Attachment);
                }
                }
                break;
                    
                case "ballotsession":
                preparedStatement3.setInt(1, ballot.getBallotMasterID());
                try (ResultSet rs = preparedStatement3.executeQuery();){

                while (rs.next()) {

                    BallotBean Attachment = new BallotBean();
                    Attachment.setAttachBallotSessionID(rs.getInt("ballotsession_attachment_id"));
                    Attachment.setAttachBallotSessionMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachBallotSessionLink(rs.getString("attachment_link"));
                    Attachment.setAttachBallotSessionFileName(rs.getString("file_name"));
                    Attachment.setOriAttachBallotSessionFileName(rs.getString("ori_file_name"));
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

public void deleteAttachment(BallotBean ballot) throws SQLException, ParseException {
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_attachmentballot set is_active='N', date_update=?, user_update=?  where ballot_attachment_id=?");
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
   preparedStatement.setString(2, ballot.getEmpAD());
   preparedStatement.setDouble(3, ballot.getAttachBallotID());
   preparedStatement.executeUpdate();
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public void deleteAttachmentBallotSession(BallotBean ballot) throws SQLException, ParseException {
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_attachmentballotsession set is_active='N', date_update=?, user_update=?  where ballotsession_attachment_id=?");
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
   preparedStatement.setString(2, ballot.getEmpAD());
   preparedStatement.setDouble(3, ballot.getAttachBallotSessionID());
   preparedStatement.executeUpdate();
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public List<BallotBean> getAllBallotMaster(int jtStartIndex, int jtPageSize, String jtSorting, BallotBean ballot) throws ParseException, SQLException{
    List<BallotBean> ballots = new ArrayList<BallotBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = ballot.getAction();
  if (null != ballot.getAction()) switch (ballot.getAction()) {
          case "ballotmaster_list":
                //query="SELECT *, ROW_NUMBER() OVER(ORDER BY contractor_id) AS Row from bcms_contractor";
              
              query="select *,ROW_NUMBER() OVER (ORDER BY ballotmaster_id desc) AS RowNum from (\n" +
                    "SELECT distinct bm.ballotmaster_id, title_ballot,date_ballot, venue_ballot\n" +
                    "from bcms_ballotmaster as bm  where bm.is_active='Y' and\n" +
                    "title_ballot like '%"+ballot.getSearchBallotSession()+"%') as c\n" +
                    "order by ballotmaster_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
              
                      
                break;
          
      }
  
  try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();){
   
   
    while (rs.next()) {
    ballot = new BallotBean();
    
   
   final String FORMAT_DATETIME = "dd-MM-yyyy";
//   String newDateStringDateCreate = "";
//   String dateString = rs.getString("date_create");  
//   if (dateString != null && !dateString.isEmpty()){
//   SimpleDateFormat sdf3 =
//            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//   Date d3 = sdf3.parse(dateString);
//   sdf3.applyPattern(FORMAT_DATETIME);
//   newDateStringDateCreate = sdf3.format(d3);
//   }
   
   String newDateStringBallotDate = "";
   String dateString2 = rs.getString("date_ballot"); 
   if (dateString2 != null && !dateString2.isEmpty() && !"No Data".equals(dateString2)){
   SimpleDateFormat sdf4 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d4 = sdf4.parse(dateString2);
   sdf4.applyPattern(FORMAT_DATETIME);
   newDateStringBallotDate = sdf4.format(d4);
   }
   else if ("No Data".equals(dateString2)){
   newDateStringBallotDate = rs.getString("date_ballot"); 
   }
   
    
    ballot.setBallotMasterID(rs.getInt("ballotmaster_id"));
    ballot.setTitleBallot(rs.getString("title_ballot"));
    ballot.setVenueBallot(rs.getString("venue_ballot"));
    ballot.setDateBallot(newDateStringBallotDate);
    ballot.setRowNumber(rs.getInt("RowNum"));
    //String user_create = rs.getString("emp_name");
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    //ballot.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    ballots.add(ballot);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return ballots;
 }

public List<BallotBean> getAllBallotMasterDetails(int jtStartIndex, int jtPageSize, String jtSorting, BallotBean ballot) throws ParseException, SQLException{
    List<BallotBean> ballots = new ArrayList<BallotBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
  
    String query="";
    String action = ballot.getAction();
  if (null != ballot.getAction()) switch (ballot.getAction()) {
          case "ballotmasterdetails_list":
                //query="SELECT *, ROW_NUMBER() OVER(ORDER BY contractor_id) AS Row from bcms_contractor";
              
              query="select *,ROW_NUMBER() OVER (ORDER BY ballotmaster_id desc) AS RowNum from (\n" +
                    "SELECT distinct wp.ballotmaster_id, wp.ballotworkpackage_id, cg.grade_id, cgm.grade_code, \n" +
                    "specidcombine = STUFF((\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_ballotworkpackage as c\n" +
                    "left join bcms_ballotspecialisation as l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.ballotworkpackage_id = bs.ballotworkpackage_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_ballotworkpackage as c\n" +
                    "left join dbo.bcms_ballotspkkspec AS l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE  l.ballotworkpackage_id = bss.ballotworkpackage_id and l.is_active = 'Y'\n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "from bcms_ballotmaster as bm\n" +
                    "inner join bcms_ballotworkpackage as wp on bm.ballotmaster_id = wp.ballotmaster_id\n" +
                    "inner join bcms_ballotcidbgrade as cg on wp.ballotworkpackage_id = cg.ballotworkpackage_id\n" +
                    "inner join bcms_cidbgrademaster as cgm on cg.grade_id = cgm.grade_id\n" +
                    "left join bcms_ballotspecialisation as bs on bs.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                    "left join bcms_ballotspkkspec as bss on bss.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                    "inner join bcms_spkkspecialisationmaster as ssm on ssm.spkkspecmaster_id = bss.spkkspecmaster_id\n" +
                    "where wp.is_active='Y' and bm.ballotmaster_id=?) as c\n" +
                    "order by ballotmaster_id desc\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
              
                      
                break;
          
      }
  
 try (Connection currentCon = ConnectionManager.getConnection();
        
        PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ){
        
        preparedStatement.setInt(1, ballot.getBallotMasterID());  
        ResultSet rs = preparedStatement.executeQuery();
   while (rs.next()) {
       ballot = new BallotBean();
       
    
    ballot.setBallotMasterID(rs.getInt("ballotmaster_id"));
    ballot.setBallotWorkPackageID(rs.getInt("ballotworkpackage_id"));
    ballot.setGradeCode(rs.getString("grade_code"));
    ballot.setSpecialisationCode(rs.getString("specidcombine"));
    ballot.setSpkkSpecCode(rs.getString("spkk_sub_category"));
    ballot.setRowNumber(rs.getInt("RowNum"));;
  
    
    ballots.add(ballot);
    
   }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }

  return ballots;
 }

public int getBallotMasterCount(BallotBean ballot) throws SQLException{
 int count=0;
 try (Connection currentCon = ConnectionManager.getConnection();
      Statement statement = currentCon.createStatement();
      ){
                
                if (null != ballot.getAction()) switch (ballot.getAction()) {
                    
                case "ballotmaster_list":
                     try (ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                        "SELECT distinct bm.ballotmaster_id, title_ballot,date_ballot, venue_ballot\n" +
                        "from bcms_ballotmaster as bm  where bm.is_active='Y' and\n" +
                        "title_ballot like '%"+ballot.getSearchBallotSession()+"%') as c");){
                    
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

public int getBallotMasterDetailsCount(BallotBean ballot) throws SQLException{
 int count=0;
 String query="";
 query = "select count(*) as count from (\n" +
                    "SELECT distinct wp.ballotmaster_id, wp.ballotworkpackage_id, cg.grade_id, cgm.grade_code, \n" +
                    "specidcombine = STUFF((\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_ballotworkpackage as c\n" +
                    "left join bcms_ballotspecialisation as l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.ballotworkpackage_id = bs.ballotworkpackage_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_ballotworkpackage as c\n" +
                    "left join dbo.bcms_ballotspkkspec AS l on l.ballotworkpackage_id = c.ballotworkpackage_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE  l.ballotworkpackage_id = bss.ballotworkpackage_id and l.is_active = 'Y'\n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "from bcms_ballotmaster as bm\n" +
                    "inner join bcms_ballotworkpackage as wp on bm.ballotmaster_id = wp.ballotmaster_id\n" +
                    "inner join bcms_ballotcidbgrade as cg on wp.ballotworkpackage_id = cg.ballotworkpackage_id\n" +
                    "inner join bcms_cidbgrademaster as cgm on cg.grade_id = cgm.grade_id\n" +
                    "left join bcms_ballotspecialisation as bs on bs.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                    "left join bcms_ballotspkkspec as bss on bss.ballotworkpackage_id = wp.ballotworkpackage_id\n" +
                    "inner join bcms_spkkspecialisationmaster as ssm on ssm.spkkspecmaster_id = bss.spkkspecmaster_id\n" +
                    "where wp.is_active='Y' and bm.ballotmaster_id=?) as c";
 
 
 try (Connection currentCon = ConnectionManager.getConnection();
      
      ){
                
                if (null != ballot.getAction()) switch (ballot.getAction()) {
                    
                case "ballotmasterdetails_list":
                     try (PreparedStatement preparedStatement = currentCon.prepareStatement(query);
        ){
        
                    preparedStatement.setInt(1, ballot.getBallotMasterID());  
                    ResultSet rs = preparedStatement.executeQuery();
                    
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

public BallotBean getBallotMasterById(BallotBean ballot) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement("select bm.ballotmaster_id, bm.date_ballot, bm.title_ballot, bm.venue_ballot from bcms_ballotmaster as bm\n" +
                                            "where ballotmaster_id=?");){
   
   preparedStatement.setInt(1, ballot.getBallotMasterID());
   ResultSet rs = preparedStatement.executeQuery();
   //currentCon.commit();
   
   if (rs.next()) {
       
      final String FORMAT_DATETIME = "yyyy-MM-dd";
       String BallotDate = "";
       String dateString = rs.getString("date_ballot");   
       if (dateString != null && !dateString.isEmpty()){
       SimpleDateFormat sdf3 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d3 = sdf3.parse(dateString);
       sdf3.applyPattern(FORMAT_DATETIME);
       BallotDate = sdf3.format(d3);
       }
       
       
       
    ballot = new BallotBean();
    ballot.setBallotMasterID(rs.getInt("ballotmaster_id"));
    ballot.setTitleBallot(rs.getString("title_ballot"));
    ballot.setDateBallot(BallotDate);
    ballot.setVenueBallot(rs.getString("venue_ballot"));
    
    }
   
  //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return ballot;
 }

public List<BallotBean> getSelectedCidbGrade(BallotBean ballot) throws SQLException {

        List<BallotBean> cidbGrades = new ArrayList<BallotBean>();
         try (Connection currentCon = ConnectionManager.getConnection();
              PreparedStatement preparedStatement2 = currentCon.prepareStatement
               ("select bm.ballotmaster_id, l.grade_id, grade_code from bcms_ballotmaster as bm\n" +
                "inner join bcms_ballotcidbgrade AS l on bm.ballotmaster_id = l.ballotmaster_id\n" +
                "inner join bcms_cidbgrademaster as c on c.grade_id= l.grade_id\n" +
                "where bm.ballotmaster_id=? and l.is_active = 'Y'");){
            
            preparedStatement2.setInt(1, ballot.getBallotMasterID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                BallotBean cidbGrade = new BallotBean();
                cidbGrade.setGradeID(rs.getInt("grade_id"));
                cidbGrade.setGradeCode(rs.getString("grade_code"));
                cidbGrades.add(cidbGrade);
            }
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidbGrades;
    }

public List<BallotBean> getSelectedSpecSow(BallotBean ballot) throws SQLException {

        List<BallotBean> specSows = new ArrayList<BallotBean>();
         try (Connection currentCon = ConnectionManager.getConnection();
              PreparedStatement preparedStatement2 = currentCon.prepareStatement
               ("select * from bcms_ballotspecsow as ss \n" +
                "inner join bcms_subcontractspec as cs on cs.subcontractspec_id = ss.subcontractspec_id\n" +
                "inner join bcms_subcontract as subcontract on cs.subcontract_id = subcontract.subcontract_id\n" +
                "inner join bcms_contract as contract on contract.contract_id = subcontract.contract_id \n" +
                "inner join bcms_specialisationmaster as specm on cs.specialisationmaster_id = specm.specialisationmaster_id\n" +
                "where ss.ballotmaster_id=? and ss.is_active = 'Y'");){
            
            preparedStatement2.setInt(1, ballot.getBallotMasterID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                BallotBean specSow = new BallotBean();
                specSow.setSubContractSpecID(rs.getInt("subcontractspec_id"));
                specSow.setSpecialisationCode(rs.getString("specialisation_code"));
                specSow.setScopeWork(rs.getString("scope_of_work"));
                specSows.add(specSow);
            }
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specSows;
    }

public void updateBallotMaster(BallotBean ballot) throws ParseException, SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_ballotmaster set date_update=?, user_update=?, title_ballot=?, date_ballot=?, venue_ballot=?  where ballotmaster_id=?");
         PreparedStatement preparedStatement6 = currentCon.prepareStatement
                   ("insert into bcms_attachmentballotsession(date_create,user_create,ballotmaster_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
                           + "values (?,?,?,?,?,?,?)");
       ){
    
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
   
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, ballot.getEmpAD());
   preparedStatement.setString(3, ballot.getTitleBallot()); 
   preparedStatement.setString(4, ballot.getDateBallot());
   preparedStatement.setString(5, ballot.getVenueBallot());
   preparedStatement.setInt(6, ballot.getBallotMasterID());
   preparedStatement.executeUpdate();
   
   if (ballot.getAttachBallotSessionLinks() != null){
       for (int i= 0; i < ballot.getAttachBallotSessionLinks().length; i++) {
           
           String attachBallotSessionLink = (String) Array.get(ballot.getAttachBallotSessionLinks(), i);
           String attachBallotSessionFileName = (String) Array.get(ballot.getAttachBallotSessionFileNames(), i);
           String oriAttachBallotSessionFileName = (String) Array.get(ballot.getOriAttachBallotSessionFileNames(), i);
           if (attachBallotSessionLink != null && !attachBallotSessionLink.isEmpty()){
           
           preparedStatement6.setString(1, newDateStringNow);
           preparedStatement6.setString(2, ballot.getEmpAD());
           preparedStatement6.setInt(3, ballot.getBallotMasterID());
           preparedStatement6.setInt(4, 9);
           preparedStatement6.setString(5, attachBallotSessionLink);
           preparedStatement6.setString(6, attachBallotSessionFileName);
           preparedStatement6.setString(7, oriAttachBallotSessionFileName);
           preparedStatement6.executeUpdate();
       }
       }
        }
   
//   PreparedStatement preparedStatement2 = currentCon.prepareStatement
//        ("update bcms_ballotcidbgrade set is_active='N' where ballotmaster_id=?");
//   // Parameters start with 1
//   preparedStatement2.setDouble(1, ballot.getBallotMasterID());
//   preparedStatement2.executeUpdate();
//   
//   PreparedStatement preparedStatement4 = currentCon.prepareStatement
//        ("update bcms_ballotspecsow set is_active='N' where ballotmaster_id=?");
//   // Parameters start with 1
//   preparedStatement4.setDouble(1, ballot.getBallotMasterID());
//   preparedStatement4.executeUpdate();
//   
//     try (PreparedStatement preparedStatement3 = currentCon.prepareStatement
//        ("insert into bcms_ballotcidbgrade(date_create,user_create,ballotmaster_id,grade_id) "
//        + "values (?,?,?,?)");
//             ){
//         
//        if (ballot.getCidbGrades() != null){
//        for (int i= 0; i < ballot.getCidbGrades().length; i++){
//           
//           String cidbGrade = (String) Array.get(ballot.getCidbGrades(), i);
//            if (cidbGrade != null && !cidbGrade.isEmpty()){
//           preparedStatement3.setString(1, newDateStringNow);
//           preparedStatement3.setString(2, ballot.getEmpAD());
//           preparedStatement3.setInt(3, ballot.getBallotMasterID());
//           preparedStatement3.setString(4, cidbGrade);
//           preparedStatement3.executeUpdate();
//            }
//        }
//      } 
//         }  
//   catch (SQLException e) {
//   e.printStackTrace();
//  }
     
//     try (PreparedStatement preparedStatement5 = currentCon.prepareStatement
//        ("insert into bcms_ballotspecsow(date_create,user_create,ballotmaster_id,subcontractspec_id) "
//        + "values (?,?,?,?)");
//             ){
//         
//         if (ballot.getSubContractSpecIDs()!= null){
//       for (int i= 0; i < ballot.getSubContractSpecIDs().length; i++){
//           
//           String subContractSpec = (String) Array.get(ballot.getSubContractSpecIDs(), i);
//            if (subContractSpec != null && !subContractSpec.isEmpty()){
//           preparedStatement5.setString(1, newDateStringNow);
//           preparedStatement5.setString(2, ballot.getEmpAD());
//           preparedStatement5.setInt(3, ballot.getBallotMasterID());
//           preparedStatement5.setString(4, subContractSpec);
//           preparedStatement5.executeUpdate();
//            }
//       }
//      } 
//         }  
//   catch (SQLException e) {
//   e.printStackTrace();
//  }
     
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public void deleteBallotMaster(double ballotMasterID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement = currentCon.prepareStatement("update bcms_ballotmaster set is_active='N' where ballotmaster_id=?");){
   
   // Parameters start with 1
   preparedStatement.setDouble(1, ballotMasterID);
   preparedStatement.executeUpdate();
   
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public void deleteBallotMasterDetails(double ballotWorkPackageID) throws SQLException {
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement = currentCon.prepareStatement("update bcms_ballotworkpackage set is_active='N' where ballotworkpackage_id=?");){
   
   // Parameters start with 1
   preparedStatement.setDouble(1, ballotWorkPackageID);
   preparedStatement.executeUpdate();
   
  
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }
}


