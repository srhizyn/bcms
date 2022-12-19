/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Bean.UserBean;
import Bean.ContractorBean;
import Utility.ConnectionManager;
import Utility.StringUtil;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author munawwarah.mahmod
 */
public class ContractorDAO {
    
    //private Connection currentCon = null;  

// public ContractorDAO() {
//  currentCon = ConnectionManager.getConnection();
//  
// }

    //ResultSet rs = null;
    
    
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

public List<ContractorBean> getSPKKCategoryCode() throws SQLException {

        List<ContractorBean> SPKKs = new ArrayList<ContractorBean>();
        String query = "SELECT spkkcatmaster_id, spkkcatmaster_code, spkkcatmaster_desc from bcms_spkkcategorymaster where is_active='Y' order by spkkcatmaster_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
           
            while (rs.next()) {

                ContractorBean SPKK = new ContractorBean();
                SPKK.setValue(rs.getInt("spkkcatmaster_id"));
                SPKK.setDisplayText(rs.getString("spkkcatmaster_code"));
                SPKK.setSpkkCatID(rs.getInt("spkkcatmaster_id"));
                SPKK.setSpkkCatCode(rs.getString("spkkcatmaster_code"));
                SPKK.setSpkkCatDesc(rs.getString("spkkcatmaster_desc"));
                               
                SPKKs.add(SPKK);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return SPKKs;
    }

public List<ContractorBean> getSPKKSpecCode() throws SQLException {

        List<ContractorBean> SPKKs = new ArrayList<ContractorBean>();
        String query = "SELECT spkkspecmaster_id, spkkspecmaster_code, spkkspecmaster_desc, spkkcat_id from bcms_spkkspecialisationmaster where is_active='Y' order by spkkspecmaster_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            

            while (rs.next()) {

                ContractorBean SPKK = new ContractorBean();
                SPKK.setValue(rs.getInt("spkkspecmaster_id"));
                SPKK.setDisplayText(rs.getString("spkkspecmaster_code"));
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

public List<ContractorBean> getSpecialisation() throws SQLException {

        List<ContractorBean> Specialisations = new ArrayList<ContractorBean>();
        String query = "SELECT specialisationmaster_id, speccidb_id, specialisation_code, specialisation_desc from bcms_specialisationmaster where is_active='Y' order by specialisation_code";
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

public List<ContractorBean> getMof() throws SQLException {

        List<ContractorBean> Mofs = new ArrayList<ContractorBean>();
        String query = "SELECT * from v_combined_mof order by combined_mofcode";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
             

            while (rs.next()) {

                ContractorBean Mof = new ContractorBean();
                Mof.setValue(rs.getInt("mofsub2master_id"));
                Mof.setDisplayText(rs.getString("combined_mofcode"));
                Mof.setMofCodeID(rs.getInt("mofsub2master_id"));
                Mof.setMofCode(rs.getString("combined_mofcode"));
                Mof.setMofDesc(rs.getString("mofsub2_desc"));
                               
                Mofs.add(Mof);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Mofs;
    }

public List<ContractorBean> getCountries() throws SQLException {

        List<ContractorBean> Countries = new ArrayList<ContractorBean>();
        String query = "SELECT country_id, country_name from bcms_country where is_active='Y' order by country_name";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){

            while (rs.next()) {

                ContractorBean Country = new ContractorBean();
                Country.setValue(rs.getInt("country_id"));
                Country.setDisplayText(rs.getString("country_name"));
                Country.setCountryID(rs.getInt("country_id"));
                Country.setCountryName(rs.getString("country_name"));
                               
                Countries.add(Country);
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            //currentCon.rollback();
        }

        return Countries;
    }

public List<ContractorBean> getStates() throws SQLException {

        List<ContractorBean> States = new ArrayList<ContractorBean>();
        String query = "SELECT state_id, state_name from bcms_state where is_active='Y' order by state_name";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                ContractorBean State = new ContractorBean();
                State.setValue(rs.getInt("state_id"));
                State.setDisplayText(rs.getString("state_name"));
                State.setStateID(rs.getInt("state_id"));
                State.setStateName(rs.getString("state_name"));
                               
                States.add(State);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return States;
    }

public List<ContractorBean> getProgram() throws SQLException {

        List<ContractorBean> programs = new ArrayList<ContractorBean>();
        String query = "SELECT programmaster_id, program_code,program_desc from bcms_programmaster where is_active='Y' and programmaster_id not in (1) order by program_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                ContractorBean program = new ContractorBean();
                program.setValue(rs.getInt("programmaster_id"));
                program.setDisplayText(rs.getString("program_code") +"-"+ rs.getString("program_desc"));
                program.setProgramID(rs.getInt("programmaster_id"));
                program.setProgramCode(rs.getString("program_code"));
                program.setProgramDesc(rs.getString("program_desc"));
                               
                programs.add(program);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

public List<ContractorBean> getAllProgram() throws SQLException {

        List<ContractorBean> programs = new ArrayList<ContractorBean>();
        String query = "SELECT programmaster_id, program_code,program_desc from bcms_programmaster where is_active='Y' order by program_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                ContractorBean program = new ContractorBean();
                program.setValue(rs.getInt("programmaster_id"));
                program.setDisplayText(rs.getString("program_code") +"-"+ rs.getString("program_desc"));
                program.setProgramID(rs.getInt("programmaster_id"));
                program.setProgramCode(rs.getString("program_code"));
                program.setProgramDesc(rs.getString("program_desc"));
                               
                programs.add(program);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programs;
    }

public List<ContractorBean> getFormType() throws SQLException {

        List<ContractorBean> formTypes = new ArrayList<ContractorBean>();
        String query = "SELECT formtypemaster_id, formtype_code,formtype_desc from bcms_formtypemaster where is_active='Y' order by formtype_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                ContractorBean formType = new ContractorBean();
                formType.setValue(rs.getInt("formtypemaster_id"));
                //program.setDisplayText(rs.getString("program_code") +"-"+ rs.getString("program_desc"));
                formType.setFormTypeID(rs.getString("formtypemaster_id"));
                formType.setFormTypeCode(rs.getString("formtype_code"));
                formType.setFormTypeDesc(rs.getString("formtype_desc"));
                               
                formTypes.add(formType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formTypes;
    }

public List<ContractorBean> getContractorType() throws SQLException {

        List<ContractorBean> contractorTypes = new ArrayList<ContractorBean>();
        String query = "SELECT contractortypemaster_id, contractortype_code,contractortype_desc from bcms_contractortypemaster where is_active='Y' order by contractortype_code";
        try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);){
            
            while (rs.next()) {

                ContractorBean contractorType = new ContractorBean();
                contractorType.setValue(rs.getInt("contractortypemaster_id"));
                contractorType.setDisplayText(rs.getString("contractortype_code") +"-"+ rs.getString("contractortype_desc"));
                contractorType.setContractorTypeID(rs.getInt("contractortypemaster_id"));
                contractorType.setContractorTypeCode(rs.getString("contractortype_code"));
                contractorType.setContractorTypeDesc(rs.getString("contractortype_desc"));
                               
                contractorTypes.add(contractorType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractorTypes;
}

public List<ContractorBean> getAllContractor2(int jtStartIndex, int jtPageSize, String jtSorting, ContractorBean contractor) throws ParseException, SQLException{
 List<ContractorBean> contractors = new ArrayList<ContractorBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
    String SearchCidbGrade = "";
    if ("".equals(contractor.getSearchCidbGrade())){
    }
    else {
    Double d = new Double(contractor.getSearchCidbGrade());
    int aa = d.intValue();
    SearchCidbGrade = String.valueOf(aa);
    }
//    String combinedCidbGrade = String.join(",", contractor.getSearchCidbGrades());
//    System.out.println("f"+combinedCidbGrade);
  
    String query="";
    String action = contractor.getAction();
   if (null != contractor.getAction()) switch (contractor.getAction()) {
          case "contractor_list":
              
              if ("".equals(contractor.getSearchSpecialisationCode())){ 
                  if ("".equals(contractor.getSearchCidbGrade())){ 
                     if ("".equals(contractor.getSearchSpkkSubCat())){ 
                        if ("".equals(contractor.getSearchAwardStatus())){
                          if ("".equals(contractor.getSearchContractorType())){  
                    query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +        
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "where contractor.is_active = 'Y' \n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'\n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                          } 
                          // else for contractortype
                          else {
                          query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +        
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "where contractor.is_active = 'Y' \n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                          }
                    // else for awardstatus
                    } else {
                    if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){         
                    query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "where contractor.is_active = 'Y' and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y') \n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'  \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                        } else {
                    
                    query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +        
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "where contractor.is_active = 'Y' and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y') \n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'  \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                    }
                        }
                // else for spkksubcat
              } else {
                     if ("".equals(contractor.getSearchAwardStatus())){ 
                    query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id " + 
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+")\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";  
                     }
                     else {
                     if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){    
                     query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";  
                     } else {
                     query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +         
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") and ballot.is_active = 'Y' and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";  
                     }
                     }
                     }
                  }
                  //else for cidb grade
                  else {
                   if ("".equals(contractor.getSearchAwardStatus())){ 
                   query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +       
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y'\n" +
                    //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+")\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                   } 
                   else {
                    if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){ 
                   query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +       
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y'\n" +
                    //"and ta.spkkspecmaster_id in ('"+contractor.getSearchSpkkSubCat()+"') " +
                    "and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%' and c.grade_id like '%"+SearchCidbGrade+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                   }else {
                    query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +        
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' "
                    //+ "and ta.spkkspecmaster_id in ('"+contractor.getSearchSpkkSubCat()+"') "
                    + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                    }
                   }
                  }
              }
              //else for spec code
              else {
                  if ("".equals(contractor.getSearchAwardStatus())){ 
              query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name,  bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +  
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS se ON t.specialisationmaster_id = se.specialisationmaster_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +  
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' and se.specialisationmaster_id in ("+contractor.getSearchSpecialisationCode()+")\n" +
                    //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+")\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%' and c.grade_id like '%"+SearchCidbGrade+"%'   and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY";
                  }
                  else {
                     if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){
                     query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name,  bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +  
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS se ON t.specialisationmaster_id = se.specialisationmaster_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +         
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' and se.specialisationmaster_id in ("+contractor.getSearchSpecialisationCode()+")\n" +
                    //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+")" +
                    "and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and c.ispecidcombine like '%"+contractor.getSearchSpecialisationCode()+"%' and  isnull(c.spkk_sub_category,'') like '%"+contractor.getSearchSpkkSubCat()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY"; 
                  } else {
                     query="select *,COALESCE(NULLIF(ispecidcombine,''), '') as specidcombine, ROW_NUMBER() OVER (ORDER BY grade_code,company_name ) AS RowNum from (\n" +
                    "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name,  bs.ballotstatus_desc, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date,COALESCE(CAST(NULLIF(STB_expiry_date,'')as varchar),'') as stb_exp_date, ispecidcombine = STUFF(\n" +
                    "(\n" +
                    "SELECT ', ' + ta.specialisation_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                    "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,''),\n" +
                    "spkk_sub_category = STUFF(\n" +
                    "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                    "FROM dbo.bcms_contractor as c\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                    "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                    "FOR XML PATH ('')),1,1,'')\n" +  
                    "FROM dbo.bcms_contractor AS contractor\n" +
                    "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                    "left JOIN dbo.bcms_specialisationmaster AS se ON t.specialisationmaster_id = se.specialisationmaster_id\n" +
                    "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                    "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                    "left join bcms_ballotstatusmaster as bs on ballot.ballotstatusmaster_id = bs.ballotstatusmaster_id\n" +
                    "left join bcms_ballotmaster as bm on bm.ballotmaster_id = ballot.ballotmaster_id\n" +
                    "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id\n" +         
                    "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                    "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
		    "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                    "where contractor.is_active = 'Y' and se.specialisationmaster_id in ("+contractor.getSearchSpecialisationCode()+")\n" +
                    //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") "+
                    "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                    ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and isnull(c.application_status,'')  like '%"+contractor.getSearchAppStatus()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and c.ispecidcombine like '%"+contractor.getSearchSpecialisationCode()+"%' and  isnull(c.spkk_sub_category,'') like '%"+contractor.getSearchSpkkSubCat()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%' \n" +
                    "ORDER BY grade_code,company_name\n" +
                    "OFFSET "+startIndex+" ROWS\n" +
                    "FETCH NEXT "+pageSize+" ROWS ONLY"; 
                     
                     }
                  }
              }
                break;
             
          
      }
   
  // String SPsql = "EXEC sp_searchcontractorlist ?,?,?,?,?,?,?,?,?";  
   //PreparedStatement ps2 = null;
  
   try (Connection currentCon = ConnectionManager.getConnection();
             Statement statement = currentCon.createStatement();
             ResultSet rs = statement.executeQuery(query);
               //PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql); 
               //preparedStatement.setString(1, contractor.getSearchName());
               //ResultSet rs2 = preparedStatement.executeQuery();  
                ){
//                PreparedStatement preparedStatement = currentCon.prepareStatement(SPsql);
                //preparedStatement.setEscapeProcessing(true);
                //preparedStatement.setQueryTimeout(100);
//                preparedStatement.setString(1, contractor.getSearchName());
//                preparedStatement.setString(2, contractor.getSearchContractorType());
//                preparedStatement.setString(3, contractor.getSearchCidbGrade());
//                preparedStatement.setString(4, contractor.getSearchSpecialisationCode());
//                preparedStatement.setString(5, contractor.getSearchSpkkSubCat());
//                preparedStatement.setString(6, contractor.getSearchAppStatus());
//                preparedStatement.setString(7, contractor.getSearchAwardStatus());
//                preparedStatement.setString(8, startIndex);
//                preparedStatement.setString(9, pageSize);
////                
////                ResultSet rs2 = preparedStatement.executeQuery();  
////                System.out.println(preparedStatement.executeQuery()); 
//                
//                try (ResultSet rs2 = preparedStatement.executeQuery();){
//                    System.out.println(rs2); 
//                while (rs2.next()) {
//    contractor = new ContractorBean();
//    
//   
//   final String FORMAT_DATETIME = "dd-MM-yyyy";
//   String newDateStringDateCreate = "";
//   String dateString = rs2.getString("date_create"); 
//   if (dateString != null && !dateString.isEmpty()){
//   SimpleDateFormat sdf3 =
//            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//   Date d3 = sdf3.parse(dateString);
//   sdf3.applyPattern(FORMAT_DATETIME);
//   newDateStringDateCreate = sdf3.format(d3);
//   }
//   
////   String newDateStringCIDBExpiryDate = "";
////   String dateString2 = rs.getString("CIDB_exp_date"); 
////   if (dateString2 != null && !dateString2.isEmpty() && !"".equals(dateString2)){
////   SimpleDateFormat sdf4 =
////            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////   Date d4 = sdf4.parse(dateString2);
////   sdf4.applyPattern(FORMAT_DATETIME);
////   newDateStringCIDBExpiryDate = sdf4.format(d4);
////   }
////   else if ("".equals(dateString2)){
////   newDateStringCIDBExpiryDate = rs.getString("CIDB_exp_date");  
////           }
//   
//   String newDateStringSTBExpiryDate = "";
//   String dateString3 = rs2.getString("STB_exp_date"); 
//   if (dateString3 != null && !dateString3.isEmpty() && !"".equals(dateString3)){
//   SimpleDateFormat sdf5 =
//            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//   Date d5 = sdf5.parse(dateString3);
//   sdf5.applyPattern(FORMAT_DATETIME);
//   newDateStringSTBExpiryDate = sdf5.format(d5);
//   }
//   else if ("".equals(dateString3)){
//   newDateStringSTBExpiryDate = rs2.getString("STB_exp_date");  
//           }
//   
//    contractor.setContractorID(rs2.getInt("contractor_id"));
//    contractor.setCompanyName(rs2.getString("company_name"));
//    contractor.setGradeCode(rs2.getString("grade_code"));
//    //contractor.setCidbExpiryDate(newDateStringCIDBExpiryDate);
//    contractor.setAttachCompanyLogoLink(rs2.getString("company_logo_link"));
//    contractor.setAttachCompanyLogoFileName(rs2.getString("company_logo_file_name"));
//    contractor.setOriAttachCompanyLogoFileName(rs2.getString("company_logo_ori_file_name"));
//    contractor.setDateCreate(newDateStringDateCreate);
//    contractor.setBallotStatus(rs2.getString("ballotstatus_desc"));
//    contractor.setSpecialisationCode(rs2.getString("specidcombine"));
//    contractor.setSpkkSpecCode(rs2.getString("spkk_sub_category"));
//    contractor.setAppStatus(rs2.getString("application_status"));
//    contractor.setRowNumber(rs2.getInt("RowNum"));
//    contractor.setStbExpiryDate(newDateStringSTBExpiryDate);
//    contractor.setContractorTypeDesc(rs2.getString("contractortype_desc"));
//    String user_create = rs2.getString("emp_name");
//    //WordUtils.capitalize(emp_name);
//    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
//           
//    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
//    contractor.setUserCreate(WordUtils.capitalizeFully(user_create));
//  
//    
//    contractors.add(contractor);
//    
//   }
                
   while (rs.next()) {
    contractor = new ContractorBean();
    
   
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
  
   String newDateStringSTBExpiryDate = "";
   String dateString3 = rs.getString("STB_exp_date"); 
   if (dateString3 != null && !dateString3.isEmpty() && !"".equals(dateString3)){
   SimpleDateFormat sdf5 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d5 = sdf5.parse(dateString3);
   sdf5.applyPattern(FORMAT_DATETIME);
   newDateStringSTBExpiryDate = sdf5.format(d5);
   }
   else if ("".equals(dateString3)){
   newDateStringSTBExpiryDate = rs.getString("STB_exp_date");  
           }
   
    contractor.setContractorID(rs.getInt("contractor_id"));
    contractor.setCompanyName(rs.getString("company_name"));
    contractor.setGradeCode(rs.getString("grade_code"));
    //contractor.setCidbExpiryDate(newDateStringCIDBExpiryDate);
    contractor.setAttachCompanyLogoLink(rs.getString("company_logo_link"));
    contractor.setAttachCompanyLogoFileName(rs.getString("company_logo_file_name"));
    contractor.setOriAttachCompanyLogoFileName(rs.getString("company_logo_ori_file_name"));
    contractor.setDateCreate(newDateStringDateCreate);
    contractor.setBallotStatus(rs.getString("ballotstatus_desc"));
    contractor.setSpecialisationCode(rs.getString("specidcombine"));
    contractor.setSpkkSpecCode(rs.getString("spkk_sub_category"));
    contractor.setAppStatus(rs.getString("application_status"));
    contractor.setRowNumber(rs.getInt("RowNum"));
    contractor.setStbExpiryDate(newDateStringSTBExpiryDate);
    contractor.setContractorTypeDesc(rs.getString("contractortype_desc"));
    String user_create = rs.getString("emp_name");
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    contractor.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    contractors.add(contractor);
    
   }
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e);
  }
   

  return contractors;
 }

public List<ContractorBean> getAllContractor(int jtStartIndex, int jtPageSize, String jtSorting, ContractorBean contractor) throws ParseException, SQLException{
 List<ContractorBean> contractors = new ArrayList<ContractorBean>();
    String startIndex=Integer.toString(jtStartIndex);
    String pageSize=Integer.toString(jtPageSize);
    String defaultSorting = jtSorting;
    String SearchCidbGrade = "";
    if ("".equals(contractor.getSearchCidbGrade())){
    }
    else {
    Double d = new Double(contractor.getSearchCidbGrade());
    int aa = d.intValue();
    SearchCidbGrade = String.valueOf(aa);
    }
//    String combinedCidbGrade = String.join(",", contractor.getSearchCidbGrades());
//    System.out.println("f"+combinedCidbGrade);
  
    String query="";
    String action = contractor.getAction();
   
   
   String SPsql = "EXEC sp_searchcontractorlist ?,?,?,?,?,?,?,?,?";  
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
                preparedStatement.setString(1, contractor.getSearchName());
                preparedStatement.setString(2, contractor.getSearchContractorType());
                preparedStatement.setString(3, contractor.getSearchCidbGrade());
                preparedStatement.setString(4, contractor.getSearchSpecialisationCode());
                preparedStatement.setString(5, contractor.getSearchSpkkSubCat());
                preparedStatement.setString(6, contractor.getSearchAppStatus());
                preparedStatement.setString(7, contractor.getSearchAwardStatus());
                preparedStatement.setString(8, startIndex);
                preparedStatement.setString(9, pageSize);
//                
//                ResultSet rs2 = preparedStatement.executeQuery();  
//                System.out.println(preparedStatement.executeQuery()); 
                
                try (ResultSet rs2 = preparedStatement.executeQuery();
                        ){
                while (rs2.next()) {
    contractor = new ContractorBean();
    
   
   final String FORMAT_DATETIME = "dd-MM-yyyy";
   String newDateStringDateCreate = "";
   String dateString = rs2.getString("date_create"); 
   if (dateString != null && !dateString.isEmpty()){
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringDateCreate = sdf3.format(d3);
   }
   
//   String newDateStringCIDBExpiryDate = "";
//   String dateString2 = rs.getString("CIDB_exp_date"); 
//   if (dateString2 != null && !dateString2.isEmpty() && !"".equals(dateString2)){
//   SimpleDateFormat sdf4 =
//            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//   Date d4 = sdf4.parse(dateString2);
//   sdf4.applyPattern(FORMAT_DATETIME);
//   newDateStringCIDBExpiryDate = sdf4.format(d4);
//   }
//   else if ("".equals(dateString2)){
//   newDateStringCIDBExpiryDate = rs.getString("CIDB_exp_date");  
//           }
   
   String newDateStringSTBExpiryDate = "";
   String dateString3 = rs2.getString("STB_exp_date"); 
   if (dateString3 != null && !dateString3.isEmpty() && !"".equals(dateString3)){
   SimpleDateFormat sdf5 =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   Date d5 = sdf5.parse(dateString3);
   sdf5.applyPattern(FORMAT_DATETIME);
   newDateStringSTBExpiryDate = sdf5.format(d5);
   }
   else if ("".equals(dateString3)){
   newDateStringSTBExpiryDate = rs2.getString("STB_exp_date");  
           }
   
    contractor.setContractorID(rs2.getInt("contractor_id"));
    contractor.setCompanyName(rs2.getString("company_name"));
    contractor.setGradeCode(rs2.getString("grade_code"));
    //contractor.setCidbExpiryDate(newDateStringCIDBExpiryDate);
    contractor.setAttachCompanyLogoLink(rs2.getString("company_logo_link"));
    contractor.setAttachCompanyLogoFileName(rs2.getString("company_logo_file_name"));
    contractor.setOriAttachCompanyLogoFileName(rs2.getString("company_logo_ori_file_name"));
    contractor.setDateCreate(newDateStringDateCreate);
    contractor.setBallotStatus(rs2.getString("ballotstatus_desc"));
    contractor.setSpecialisationCode(rs2.getString("specidcombine"));
    contractor.setSpkkSpecCode(rs2.getString("spkk_sub_category"));
    contractor.setAppStatus(rs2.getString("application_status"));
    contractor.setRowNumber(rs2.getInt("RowNum"));
    contractor.setStbExpiryDate(newDateStringSTBExpiryDate);
    contractor.setContractorTypeDesc(rs2.getString("contractortype_desc"));
    String user_create = rs2.getString("emp_name");
    //WordUtils.capitalize(emp_name);
    //String convert_emp_name = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, emp_name);
           
    //form.setEmpName(WordUtils.capitalizeFully(emp_name));
    contractor.setUserCreate(WordUtils.capitalizeFully(user_create));
  
    
    contractors.add(contractor);
    
   }
                
   
  } catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
  }catch (SQLException e) {
   e.printStackTrace();
   System.out.println(e.getMessage());
  }
   

  return contractors;
 }

public ContractorBean getContractorById(ContractorBean contractor) throws SQLException, ParseException {
  //ContractorBean contractor = new ContractorBean();
   try (Connection currentCon = ConnectionManager.getConnection();
         PreparedStatement preparedStatement = currentCon.prepareStatement
        ("select contractor.*, contractor.grade_id, speccidb_id from bcms_contractor as contractor \n" +
        "left join bcms_cidbgrademaster as cidbmaster on cidbmaster.grade_id = contractor.grade_id\n" +
        "where contractor_id=?");
        PreparedStatement preparedStatement2 = currentCon.prepareStatement
        ("select spkkcatmaster_id from bcms_contractor as contractor\n" +
        "inner join bcms_SPKKcategory as spkk on contractor.contractor_id = spkk.contractor_id\n" +
        "where contractor.contractor_id=?");
        PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("select specialisation_others from bcms_contractor as contractor\n" +
        "inner join bcms_specialisation as specialisation on contractor.contractor_id = specialisation.contractor_id\n" +
        "where contractor.contractor_id=? and specialisationmaster_id = 21 and specialisation.is_active = 'Y'");   
           ){
   
        preparedStatement.setInt(1, contractor.getContractorID());
        try (ResultSet rs = preparedStatement.executeQuery();){
   //currentCon.commit();
   
   if (rs.next()) {
       
       final String FORMAT_DATETIME = "yyyy-MM-dd";
//       String SSMExpiryDate = "";
//       String dateString = rs.getString("SSM_expiry_date"); 
//       if (dateString != null && !dateString.isEmpty()){
//       SimpleDateFormat sdf3 =
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       Date d3 = sdf3.parse(dateString);
//       sdf3.applyPattern(FORMAT_DATETIME);
//       SSMExpiryDate = sdf3.format(d3);
//       }
       
       String SubmissionDate = "";
       String dateString2 = rs.getString("date_submission"); 
       if (dateString2 != null && !dateString2.isEmpty()){
       SimpleDateFormat sdf4 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d4 = sdf4.parse(dateString2);
       sdf4.applyPattern(FORMAT_DATETIME);
       SubmissionDate = sdf4.format(d4);
       }
       
       String CidbExpiryDate = "";
       String dateString3 = rs.getString("CIDB_expiry_date"); 
       if (dateString3 != null && !dateString3.isEmpty()){
       SimpleDateFormat sdf5 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d5 = sdf5.parse(dateString3);
       sdf5.applyPattern(FORMAT_DATETIME);
       CidbExpiryDate = sdf5.format(d5);
       }
       
       String StbExpiryDate = "";
       String dateString4 = rs.getString("STB_expiry_date"); 
       if (dateString4 != null && !dateString4.isEmpty()){
       SimpleDateFormat sdf6 =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date d6 = sdf6.parse(dateString4);
       sdf6.applyPattern(FORMAT_DATETIME);
       StbExpiryDate = sdf6.format(d6);
       }
       
       
    contractor = new ContractorBean();
    contractor.setContractorID(rs.getInt("contractor_id"));
    contractor.setCompanyName(rs.getString("company_name"));
    contractor.setProgramID(rs.getInt("programmaster_id"));
    contractor.setSsmCertNo(rs.getString("SSM_cert_no"));
    //contractor.setSsmExpiryDate(SSMExpiryDate);
    contractor.setCorrespondenceAddress1(rs.getString("correspondence_address_1"));
    contractor.setCorrespondenceAddress2(rs.getString("correspondence_address_2"));
    contractor.setCorrespondenceCity(rs.getString("correspondence_city"));
    contractor.setCorrespondencePostCode(rs.getString("correspondence_postcode"));
    contractor.setCorrespondenceStateID(rs.getInt("correspondence_stateid"));
    //contractor.setCompSsmAddress1(rs.getString("company_ssm_address_1"));
    //contractor.setCompSsmAddress2(rs.getString("company_ssm_address_2"));
    //contractor.setCompSsmCity(rs.getString("company_ssm_city"));
    //contractor.setCompSsmPostCode(rs.getString("company_ssm_postcode"));
    //contractor.setCompSsmStateID(rs.getInt("company_ssm_stateid"));
    contractor.setOfficePhoneNo(rs.getString("office_tel_no"));
    contractor.setOfficeFaxNo(rs.getString("office_fax_no"));
    contractor.setPicMobileNo(rs.getString("PIC_mobile_no"));
    contractor.setEmailAddress1(rs.getString("email_address_1"));
    //contractor.setEmailAddress2(rs.getString("email_address_2"));
    contractor.setDateFormSubmitted(SubmissionDate);
    contractor.setAppStatus(rs.getString("application_status"));
    contractor.setReasonFailed(rs.getString("reason_failed"));
    contractor.setGradeID(rs.getInt("grade_id"));
    contractor.setCidbExpiryDate(CidbExpiryDate);
    contractor.setStbExpiryDate(StbExpiryDate);
    contractor.setGroupingA(rs.getInt("speccidb_id"));
    //contractor.setCorrespondenceGpsLoc(rs.getString("correspondence_gpsloc"));
    contractor.setCidbRegNo(rs.getString("CIDB_reg_no"));
    contractor.setStbNo(rs.getString("STB_no"));
    contractor.setFormTypeID(rs.getString("formtype_id"));
    contractor.setFormTypeOthersDesc(rs.getString("formtypeothers_desc"));
    contractor.setContractorTypeID(rs.getInt("contractortype_id"));
    //contractor.setPicName(rs.getString("pic_name"));
    //contractor.setPicMobileNo2(rs.getString("pic_mobile_no_2"));
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
    
   preparedStatement3.setInt(1, contractor.getContractorID());
   ResultSet rs3 = preparedStatement3.executeQuery();
   if (rs3.next()) {
     contractor.setSpecialisationOthers(rs3.getString("specialisation_others"));  
   }
   
    }
        }
   
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return contractor;
 }

public List<ContractorBean> getSelectedSpecialisation(ContractorBean contractor) throws SQLException {

        List<ContractorBean> Specs = new ArrayList<ContractorBean>();
         try (Connection currentCon = ConnectionManager.getConnection();
              PreparedStatement preparedStatement2 = currentCon.prepareStatement
               ("select spec.specialisationmaster_id, specialisation_code, specialisation_desc,specialisation_others from bcms_contractor as contractor\n" +
                "inner join bcms_specialisation as spec on contractor.contractor_id = spec.contractor_id\n" +
                "inner join bcms_specialisationmaster as specmaster on spec.specialisationmaster_id = specmaster.specialisationmaster_id\n" +
                "where contractor.contractor_id=? and spec.is_active='Y'");){
            
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean Spec = new ContractorBean();
                Spec.setSpecialisationID(rs.getInt("specialisationmaster_id"));
                Spec.setSpecialisationCode(rs.getString("specialisation_code"));
                Spec.setSpecialisationDesc(rs.getString("specialisation_desc")); 
                Spec.setSpecialisationOthers(rs.getString("specialisation_others")); 
                Specs.add(Spec);
            }
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Specs;
    }

public List<ContractorBean> getSelectedSPKKCat(ContractorBean contractor) throws SQLException {

        List<ContractorBean> SPKKs = new ArrayList<ContractorBean>();
         try (Connection currentCon = ConnectionManager.getConnection();
              PreparedStatement preparedStatement2 = currentCon.prepareStatement
               ("select spkk.spkkcatmaster_id, spkkcatmaster_code, spkkcatmaster_desc from bcms_contractor as contractor\n" +
                "inner join bcms_spkkcategory as spkk on contractor.contractor_id = spkk.contractor_id\n" +
                "inner join bcms_spkkcategorymaster as spkkmaster on spkk.spkkcatmaster_id = spkkmaster.spkkcatmaster_id\n" +
                "where contractor.contractor_id=? and spkk.is_active='Y'");){
            
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean SPKK = new ContractorBean();
                SPKK.setSpkkCatID(rs.getInt("spkkcatmaster_id"));
                SPKK.setSpkkCatCode(rs.getString("spkkcatmaster_code"));
                SPKK.setSpkkCatDesc(rs.getString("spkkcatmaster_desc")); 
                SPKKs.add(SPKK);
            }
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return SPKKs;
    }

public List<ContractorBean> getSelectedSPKKSpec(ContractorBean contractor) throws SQLException {

        List<ContractorBean> spkkSpecs = new ArrayList<ContractorBean>();
         try (Connection currentCon = ConnectionManager.getConnection();
              PreparedStatement preparedStatement2 = currentCon.prepareStatement
               ("select spkkspec.spkkspecmaster_id, spkkspecmaster_code, spkkspecmaster_desc from bcms_contractor as contractor\n" +
                "inner join bcms_spkkspecialisation as spkkspec on contractor.contractor_id = spkkspec.contractor_id\n" +
                "inner join bcms_spkkspecialisationmaster as spkkspecmaster on spkkspec.spkkspecmaster_id= spkkspecmaster.spkkspecmaster_id\n" +
                "where contractor.contractor_id=? and spkkspec.is_active='Y'");){
            
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean spkkSpec = new ContractorBean();
                spkkSpec.setSpkkSpecID(rs.getInt("spkkspecmaster_id"));
                spkkSpec.setSpkkSpecCode(rs.getString("spkkspecmaster_code"));
                spkkSpec.setSpkkSpecDesc(rs.getString("spkkspecmaster_desc")); 
                spkkSpecs.add(spkkSpec);
            }
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spkkSpecs;
    }

public List<ContractorBean> getSelectedMof(ContractorBean contractor) throws SQLException {

        List<ContractorBean> Mofs = new ArrayList<ContractorBean>();
         try (Connection currentCon = ConnectionManager.getConnection();
              PreparedStatement preparedStatement2 = currentCon.prepareStatement
               ("SELECT mof.mofsub2master_id, mof_code + mofsub_code + mofsub2_code AS combined_mofcode, mofsub2_desc\n" +
                "from bcms_mofmaster as mofmaster inner join bcms_mofsubmaster as mofsub on mofmaster.mofmaster_id = mofsub.mofmaster_id\n" +
                "inner join bcms_mofsub2master as mofsub2 on mofsub.mofsubmaster_id = mofsub2.mofsubmaster_id\n" +
                "inner join bcms_mof as mof on mof.mofsub2master_id = mofsub2.mofsub2master_id\n" +
                "inner join bcms_contractor as contractor on contractor.contractor_id = mof.contractor_id\n" +
                "where contractor.contractor_id=? and mof.is_active = 'Y' order by combined_mofcode");){
            
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean Mof = new ContractorBean();
                Mof.setMofCodeID(rs.getInt("mofsub2master_id"));
                Mof.setMofCode(rs.getString("combined_mofcode"));
                Mof.setMofDesc(rs.getString("mofsub2_desc")); 
                Mofs.add(Mof);
            }
            }
            //currentCon.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Mofs;
    }

public List<ContractorBean> getRegisteredCompanyOwner(ContractorBean contractor) throws SQLException {

        List<ContractorBean> companyOwners = new ArrayList<ContractorBean>();
        try (Connection currentCon = ConnectionManager.getConnection(); 
             PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select owner_name, owner_ic_no, owner_contact_no from bcms_contractor as contractor\n" +
             "inner join bcms_companyowner as comp on contractor.contractor_id = comp.contractor_id\n" +
             "where contractor.contractor_id=? and comp.is_active='Y'");){
            
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean companyOwner = new ContractorBean();
                companyOwner.setCompanyOwner(rs.getString("owner_name"));
                companyOwner.setIcNo(rs.getString("owner_ic_no"));
                companyOwner.setOwnerContactNo(rs.getString("owner_contact_no"));
                
                               
                companyOwners.add(companyOwner);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companyOwners;
    }

public List<ContractorBean> getRegisteredPastProject(ContractorBean contractor) throws SQLException {

        List<ContractorBean> pastProjects = new ArrayList<ContractorBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
             PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select pastproject_desc, pastproject_amount, pastproject_year from bcms_contractor as contractor\n" +
             "inner join bcms_contractorpastproject as past on contractor.contractor_id = past.contractor_id\n" +
             "where contractor.contractor_id=? and past.is_active='Y'");){
            
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean pastProject = new ContractorBean();
                pastProject.setPastProject(rs.getString("pastproject_desc"));
                pastProject.setPastProjectAmount(rs.getString("pastproject_amount"));
                pastProject.setPastProjectYear(rs.getString("pastproject_year"));               
                pastProjects.add(pastProject);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pastProjects;
    }

public List<ContractorBean> getRegisteredCurrentProject(ContractorBean contractor) throws SQLException {

        List<ContractorBean> currentProjects = new ArrayList<ContractorBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select currproject_desc, currproject_amount, currproject_year from bcms_contractor as contractor\n" +
             "inner join bcms_contractorcurrentproject as past on contractor.contractor_id = past.contractor_id\n" +
             "where contractor.contractor_id=? and past.is_active='Y'");){
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean currentProject = new ContractorBean();
                currentProject.setCurrentProject(rs.getString("currproject_desc"));
                currentProject.setCurrentProjectAmount(rs.getString("currproject_amount")); 
                currentProject.setCurrentProjectYear(rs.getString("currproject_year")); 
                currentProjects.add(currentProject);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currentProjects;
    }

public List<ContractorBean> getRegisteredAwardGov(ContractorBean contractor) throws SQLException {

        List<ContractorBean> awardGovs = new ArrayList<ContractorBean>();
          try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select award_desc, award_year from bcms_contractor as contractor\n" +
             "inner join bcms_awardbygov as award on contractor.contractor_id = award.contractor_id\n" +
             "where contractor.contractor_id=? and award.is_active='Y'");){
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean awardGov = new ContractorBean();
                awardGov.setAwardGov(rs.getString("award_desc"));
                awardGov.setAwardGovYear(rs.getString("award_year"));               
                awardGovs.add(awardGov);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return awardGovs;
    }

public List<ContractorBean> getRegisteredOthersRemark(ContractorBean contractor) throws SQLException {

        List<ContractorBean> othersRemarks = new ArrayList<ContractorBean>();
          try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select programothers_desc from bcms_contractor as contractor\n" +
             "inner join bcms_programothers as others on contractor.contractor_id = others.contractor_id\n" +
             "where contractor.contractor_id=? and others.is_active='Y'");){
            preparedStatement2.setInt(1, contractor.getContractorID());
            try (ResultSet rs = preparedStatement2.executeQuery();){

            while (rs.next()) {

                ContractorBean othersRemark = new ContractorBean();
                othersRemark.setOthersRemark(rs.getString("programothers_desc"));              
                othersRemarks.add(othersRemark);
            }
            //currentCon.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return othersRemarks;
    }

public List<ContractorBean> getInsertedAttachment(ContractorBean contractor, String attachmentType) throws SQLException {
        
        List<ContractorBean> Attachments = new ArrayList<ContractorBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
            PreparedStatement preparedStatement2 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=1 and attachment.is_active='Y'");
            PreparedStatement preparedStatement3 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=2 and attachment.is_active='Y'");
            PreparedStatement preparedStatement4 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=3 and attachment.is_active='Y'"); 
            PreparedStatement preparedStatement5 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=4 and attachment.is_active='Y'"); 
            PreparedStatement preparedStatement6 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=5 and attachment.is_active='Y'"); 
            PreparedStatement preparedStatement7 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=6 and attachment.is_active='Y'"); 
            PreparedStatement preparedStatement8 = currentCon.prepareStatement
            ("select attachmentmaster_id, attachment_link, attachmentmaster_id, file_name, ori_file_name from bcms_attachmentcontractor as attachment\n" +
            "inner join bcms_contractor as contractor on attachment.contractor_id = contractor.contractor_id\n" +
            "where attachment.contractor_id=? and attachmentmaster_id=7 and attachment.is_active='Y'"); 
            ){
                
        if (null != attachmentType) switch (attachmentType) {
                   
                case "appForm":
                preparedStatement2.setInt(1, contractor.getContractorID());
                try (ResultSet rs = preparedStatement2.executeQuery();){

                while (rs.next()) {

                    ContractorBean Attachment = new ContractorBean();
                    Attachment.setAttachAppFormID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachAppFormMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachAppFormLink(rs.getString("attachment_link"));
                    Attachment.setAttachAppFormFileName(rs.getString("file_name"));
                    Attachment.setOriAttachAppFormFileName(rs.getString("ori_file_name"));
                    Attachments.add(Attachment);
                }
                }
                break;
                    
//                case "ssm":
//                preparedStatement3.setInt(1, contractor.getContractorID());
//                try (ResultSet rs = preparedStatement3.executeQuery();){
//
//                while (rs.next()) {
//
//                    ContractorBean Attachment = new ContractorBean();
//                    Attachment.setAttachSsmID(rs.getInt("attachmentmaster_id"));
//                    Attachment.setAttachSsmMasterID(rs.getInt("attachmentmaster_id"));
//                    Attachment.setAttachSsmLink(rs.getString("attachment_link"));
//                    Attachment.setAttachSsmFileName(rs.getString("file_name"));
//                    Attachment.setOriAttachSsmFileName(rs.getString("ori_file_name"));
//                    Attachments.add(Attachment);
//                }
//                }
//                break; 
                    
                case "cidbCert":
                preparedStatement4.setInt(1, contractor.getContractorID());
                try (ResultSet rs = preparedStatement4.executeQuery();){

                while (rs.next()) {

                    ContractorBean Attachment = new ContractorBean();
                    Attachment.setAttachCidbCertID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachCidbCertMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachCidbCertLink(rs.getString("attachment_link"));
                    Attachment.setAttachCidbCertFileName(rs.getString("file_name"));
                    Attachment.setOriAttachCidbCertFileName(rs.getString("ori_file_name"));
                    Attachments.add(Attachment);
                }
                }
                break; 
                    
                case "stbCert":
                preparedStatement5.setInt(1, contractor.getContractorID());
                try (ResultSet rs = preparedStatement5.executeQuery();){

                while (rs.next()) {

                    ContractorBean Attachment = new ContractorBean();
                    Attachment.setAttachStbCertID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachStbCertMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachStbCertLink(rs.getString("attachment_link"));
                    Attachment.setAttachStbCertFileName(rs.getString("file_name"));
                    Attachment.setOriAttachStbCertFileName(rs.getString("ori_file_name"));
                    Attachments.add(Attachment);
                }
                }
                break; 
                    
//                case "mofCert":
//                preparedStatement6.setInt(1, contractor.getContractorID());
//                try (ResultSet rs = preparedStatement6.executeQuery();){
//
//                while (rs.next()) {
//
//                    ContractorBean Attachment = new ContractorBean();
//                    Attachment.setAttachMofCertID(rs.getInt("attachmentmaster_id"));
//                    Attachment.setAttachMofCertMasterID(rs.getInt("attachmentmaster_id"));
//                    Attachment.setAttachMofCertLink(rs.getString("attachment_link"));
//                    Attachment.setAttachMofCertFileName(rs.getString("file_name"));
//                    Attachment.setOriAttachMofCertFileName(rs.getString("ori_file_name"));
//                    Attachments.add(Attachment);
//                }
//                }
//                break; 
                    
                case "screenForm":
                preparedStatement7.setInt(1, contractor.getContractorID());
                try (ResultSet rs = preparedStatement7.executeQuery();){

                while (rs.next()) {

                    ContractorBean Attachment = new ContractorBean();
                    Attachment.setAttachScreenFormID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachScreenFormMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachScreenFormLink(rs.getString("attachment_link"));
                    Attachment.setAttachScreenFormFileName(rs.getString("file_name"));
                    Attachment.setOriAttachScreenFormFileName(rs.getString("ori_file_name"));
                    Attachments.add(Attachment);
                }
                }
                break; 
                    
                case "contractorMisc":
                preparedStatement8.setInt(1, contractor.getContractorID());
                try (ResultSet rs = preparedStatement8.executeQuery();){

                while (rs.next()) {

                    ContractorBean Attachment = new ContractorBean();
                    Attachment.setAttachContractorMiscID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachContractorMiscMasterID(rs.getInt("attachmentmaster_id"));
                    Attachment.setAttachContractorMiscLink(rs.getString("attachment_link"));
                    Attachment.setAttachContractorMiscFileName(rs.getString("file_name"));
                    Attachment.setOriAttachContractorMiscFileName(rs.getString("ori_file_name"));
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

public int getContractorCount(ContractorBean contractor) throws SQLException{
 int count=0;
 String SearchCidbGrade = "";
 if ("".equals(contractor.getSearchCidbGrade())){
    }
    else {
    Double d = new Double(contractor.getSearchCidbGrade());
    int aa = d.intValue();
    SearchCidbGrade = String.valueOf(aa);
 }
  String SPsql = "EXEC sp_searchcontractorlist_count ?,?,?,?,?,?,?";  
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
                preparedStatement.setString(1, contractor.getSearchName());
                preparedStatement.setString(2, contractor.getSearchContractorType());
                preparedStatement.setString(3, contractor.getSearchCidbGrade());
                preparedStatement.setString(4, contractor.getSearchSpecialisationCode());
                preparedStatement.setString(5, contractor.getSearchSpkkSubCat());
                preparedStatement.setString(6, contractor.getSearchAppStatus());
                preparedStatement.setString(7, contractor.getSearchAwardStatus());
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

public int getContractorCount2(ContractorBean contractor) throws SQLException{
 int count=0;
 String SearchCidbGrade = "";
 if ("".equals(contractor.getSearchCidbGrade())){
    }
    else {
    Double d = new Double(contractor.getSearchCidbGrade());
    int aa = d.intValue();
    SearchCidbGrade = String.valueOf(aa);
 }
  try (Connection currentCon = ConnectionManager.getConnection();
       Statement statement = currentCon.createStatement();){
                
                
                if (null != contractor.getAction()) switch (contractor.getAction()) {
                    
                case "contractor_list":
                    if ("".equals(contractor.getSearchSpecialisationCode())){
                         if ("".equals(SearchCidbGrade)){
                            if ("".equals(contractor.getSearchSpkkSubCat())){ 
                             if ("".equals(contractor.getSearchAwardStatus())){ 
                               if ("".equals(contractor.getSearchContractorType())){   
                      try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "where contractor.is_active = 'Y' \n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                         } else {
                                  try (
                                   ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "where contractor.is_active = 'Y' \n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                                     ){
                            while (rs.next()) {
                            count=rs.getInt("count");       
                            }
                            }
                               }
                             
                             }
                             
                             else {
                            if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){ 
                            try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "where contractor.is_active = 'Y' and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                             } else {
                            try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "where contractor.is_active = 'Y' and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                            }
                             }
                         }
                        //else spkk sub category      
                        else {
                             if ("".equals(contractor.getSearchAwardStatus())){ 
                              try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+")\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                              }
                             }
                             else {
                             if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){ 
                             try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                              }
                             } else {
                             try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                                while (rs.next()) {
                                count=rs.getInt("count");       
                                }
                              }
                             }
                         }
                         }
                         }
                         //else cidb grade
                         else {
                             if ("".equals(contractor.getSearchAwardStatus())){ 
                         try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' " +
                                            //"and ta.spkkspecmaster_id in ('"+contractor.getSearchSpkkSubCat()+"')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                         }
                            else {
                            if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){ 
                             try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' " + 
                                            //"and ta.spkkspecmaster_id in ('"+contractor.getSearchSpkkSubCat()+"') " + 
                                            "and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                            } else {
                            try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' "
                                            //+ "and ta.spkkspecmaster_id in ('"+contractor.getSearchSpkkSubCat()+"')"
                                            + " and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                            }
                             }
                         }
                      }
                //else specialisation code
                else {
                        if ("".equals(contractor.getSearchAwardStatus())){ 
                    try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS se ON t.specialisationmaster_id = se.specialisationmaster_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' and se.specialisationmaster_id in ("+contractor.getSearchSpecialisationCode()+")\n" +
                                            //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+")\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%'  and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                    
                      } else {
                        if ("AVAILABLE".equals(contractor.getSearchAwardStatus())){ 
                        try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS se ON t.specialisationmaster_id = se.specialisationmaster_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' and se.specialisationmaster_id in ("+contractor.getSearchSpecialisationCode()+")\n" +
                                            //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") "+ 
                                            "and contractor.contractor_id not in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                        } else {
                        try (
                              
                              ResultSet rs = statement.executeQuery("select count(*) as count from (\n" +
                                            "SELECT distinct contractor.*, ct.contractortype_desc, cidb.grade_code, emp_name, COALESCE(NULLIF(ballot_status,''), '') as ballot_stat, COALESCE(CAST(NULLIF(CIDB_expiry_date,'')as varchar),'') as cidb_exp_date, ispecidcombine = STUFF(\n" +
                                            "(\n" +
                                            "SELECT ', ' + ta.specialisation_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_specialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS ta\n" +
                                            "ON l.specialisationmaster_id = ta.specialisationmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,''),\n" +
                                            "spkk_sub_category = STUFF(\n" +
                                            "(SELECT ', ' + ta.spkkspecmaster_code \n" +
                                            "FROM dbo.bcms_contractor as c\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on c.contractor_id = l.contractor_id\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta\n" +
                                            "ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "WHERE l.contractor_id = t.contractor_id and l.is_active = 'Y' \n" +
                                            "FOR XML PATH ('')),1,1,'')\n" +
                                            "FROM dbo.bcms_contractor AS contractor\n" +
                                            "left join bcms_specialisation as t on contractor.contractor_id = t.contractor_id\n" +
                                            "left JOIN dbo.bcms_specialisationmaster AS se ON t.specialisationmaster_id = se.specialisationmaster_id\n" +
                                            "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n" +
                                            "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                            "left join bcms_contractortypemaster as ct on contractor.contractortype_id = ct.contractortypemaster_id \n" +
                                            "inner join v_active_user as actv on actv.emp_id = contractor.emp_id\n" +
                                            "left join dbo.bcms_spkkspecialisation AS l on contractor.contractor_id = l.contractor_id and l.is_active = 'Y'\n" +
                                            "left JOIN dbo.bcms_spkkspecialisationmaster AS ta ON l.spkkspecmaster_id = ta.spkkspecmaster_id\n" +
                                            "where contractor.is_active = 'Y' and se.specialisationmaster_id in ("+contractor.getSearchSpecialisationCode()+")\n" +
                                            //"and ta.spkkspecmaster_id in ("+contractor.getSearchSpkkSubCat()+") "+ 
                                            "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')\n" +
                                            ")  as c where c.company_name like '%"+contractor.getSearchName()+"%' and c.grade_id like '%"+SearchCidbGrade+"%' and  isnull(c.application_status,'') like '%"+contractor.getSearchAppStatus()+"%' and isnull(contractortype_id,'') like '%"+contractor.getSearchContractorType()+"%'");
                              
                              ){
                    while (rs.next()) {
                    count=rs.getInt("count");       
                    }
                    }
                        }
                        }
                    }
                break;}
  
   
 } catch (SQLException e) {
  e.printStackTrace();
 }
 return count;
} 

public void addContractor(ContractorBean contractor) throws SQLException {
    try (Connection currentCon = ConnectionManager.getConnection();
         PreparedStatement preparedStatement = currentCon.prepareStatement
           ("insert into bcms_contractor(date_create,user_create,emp_id,company_name, SSM_cert_no, grade_id,CIDB_expiry_date, STB_expiry_date, "
                   + "correspondence_address_1, correspondence_address_2, correspondence_city, correspondence_postcode, correspondence_stateid, "
                   + "office_tel_no, office_fax_no, PIC_mobile_no, email_address_1, date_submission, application_status, reason_failed, cidb_reg_no, stb_no, formtype_id, formtypeothers_desc, date_application_status,contractortype_id) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);){
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
   preparedStatement.setString(2, contractor.getEmpAD());
   preparedStatement.setDouble(3, contractor.getEmpID());
   preparedStatement.setString(4, contractor.getCompanyName());
   preparedStatement.setString(5, contractor.getSsmCertNo());
   
//   if("".equals(contractor.getSsmExpiryDate())){
//   preparedStatement.setNull(6, java.sql.Types.DATE);
//   }
//   else {
//   preparedStatement.setString(6, contractor.getSsmExpiryDate());
//   }
   if( contractor.getGradeID() == 0.0){
       preparedStatement.setNull(6, java.sql.Types.DOUBLE);
   }
   else {
       preparedStatement.setDouble(6, contractor.getGradeID());
   }
   if("".equals(contractor.getCidbExpiryDate())){
   preparedStatement.setNull(7, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(7, contractor.getCidbExpiryDate());
   }
   
   if("".equals(contractor.getStbExpiryDate())){
   preparedStatement.setNull(8, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(8, contractor.getStbExpiryDate());
   }
   
   preparedStatement.setString(9, contractor.getCorrespondenceAddress1());
   preparedStatement.setString(10, contractor.getCorrespondenceAddress2());
   preparedStatement.setString(11, contractor.getCorrespondenceCity());
   preparedStatement.setString(12, contractor.getCorrespondencePostCode());
    if( contractor.getCorrespondenceStateID() == 0){
   preparedStatement.setNull(13, java.sql.Types.INTEGER);
   }
   else {
   preparedStatement.setInt(13, contractor.getCorrespondenceStateID());
   }
//   preparedStatement.setString(15, contractor.getCompSsmAddress1());
//   preparedStatement.setString(16, contractor.getCompSsmAddress2());
//   preparedStatement.setString(17, contractor.getCompSsmCity());
//   preparedStatement.setString(18, contractor.getCompSsmPostCode());
//   if( contractor.getCompSsmStateID() == 0){
//   preparedStatement.setNull(19, java.sql.Types.INTEGER);
//   }
//   else {
//   preparedStatement.setInt(19, contractor.getCompSsmStateID());
//   }
   preparedStatement.setString(14, contractor.getOfficePhoneNo());
   preparedStatement.setString(15, contractor.getOfficeFaxNo());
   preparedStatement.setString(16, contractor.getPicMobileNo());
   preparedStatement.setString(17, contractor.getEmailAddress1());
//   preparedStatement.setString(24, contractor.getEmailAddress2());
   
   if("".equals(contractor.getDateFormSubmitted())){
   preparedStatement.setNull(18, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(18, contractor.getDateFormSubmitted());
   }
  
   preparedStatement.setString(19, contractor.getAppStatus());
   //if ("PENDING".equals(contractor.getAppStatus()) || "FAILED".equals(contractor.getAppStatus())  || "BLACKLISTED".equals(contractor.getAppStatus())){
   preparedStatement.setString(20, contractor.getReasonFailed());
   //}
   //else {
   //preparedStatement.setNull(20, java.sql.Types.CHAR);
   //}
//   preparedStatement.setString(28, contractor.getCorrespondenceGpsLoc());
   preparedStatement.setString(21, contractor.getCidbRegNo());
//   preparedStatement.setInt(30, contractor.getProgramID());
   preparedStatement.setString(22, contractor.getStbNo());
   preparedStatement.setString(23, contractor.getFormTypeID());
   if ("2".equals(contractor.getFormTypeID())){
   preparedStatement.setString(24, contractor.getFormTypeOthersDesc());
   }
   else {
   preparedStatement.setNull(24, java.sql.Types.CHAR);
   }
//   preparedStatement.setString(34, contractor.getPicName());
//   preparedStatement.setString(35, contractor.getPicMobileNo2());
   if("".equals(contractor.getDateAppStatus())){
   preparedStatement.setNull(25, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(25, contractor.getDateAppStatus());
   }
   preparedStatement.setInt(26, contractor.getContractorTypeID());
   
   preparedStatement.executeUpdate();
   ResultSet rs = preparedStatement.getGeneratedKeys();
    if ( rs.next()) {
        // Retrieve the auto generated key(s).
        //ContractorBean newcontractor = new ContractorBean();
        contractor.setContractorID(rs.getInt(1));
        //int key = rs.getInt(1);
    }
   //String query = "SELECT SCOPE_IDENTITY() as currentcontractorid";
   //PreparedStatement preparedStatement2 = currentCon.prepareStatement
      //  (query);
   
   try (PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("insert into bcms_specialisation(date_create,user_create,contractor_id,specialisationmaster_id,specialisation_others) "
        + "values (?,?,?,?,?)");
        PreparedStatement preparedStatement4 = currentCon.prepareStatement
                   ("insert into bcms_spkkcategory(date_create,user_create,contractor_id,spkkcatmaster_id) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement5 = currentCon.prepareStatement
                   ("insert into bcms_spkkspecialisation(date_create,user_create,contractor_id,spkkspecmaster_id) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement6 = currentCon.prepareStatement
                   ("insert into bcms_companyowner(date_create,user_create,contractor_id,owner_name, owner_ic_no, owner_contact_no) "
                           + "values (?,?,?,?,?,?)");
        PreparedStatement preparedStatement7 = currentCon.prepareStatement
                   ("insert into bcms_contractorpastproject(date_create,user_create,contractor_id,pastproject_desc, pastproject_amount, pastproject_year) "
                           + "values (?,?,?,?,?,?)");
        PreparedStatement preparedStatement8 = currentCon.prepareStatement
                   ("insert into bcms_contractorcurrentproject(date_create,user_create,contractor_id,currproject_desc, currproject_amount, currproject_year) "
                           + "values (?,?,?,?,?,?)");
        PreparedStatement preparedStatement9 = currentCon.prepareStatement
                   ("insert into bcms_awardbygov(date_create,user_create,contractor_id,award_desc, award_year) "
                           + "values (?,?,?,?,?)");
        PreparedStatement preparedStatement10 = currentCon.prepareStatement
                   ("insert into bcms_mof(date_create,user_create,contractor_id,mofsub2master_id) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement11 = currentCon.prepareStatement
                   ("insert into bcms_attachmentcontractor(date_create,user_create,contractor_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
                           + "values (?,?,?,?,?,?,?)");
        PreparedStatement preparedStatement12 = currentCon.prepareStatement
                   ("insert into bcms_programothers(date_create,user_create,contractor_id,programothers_desc) "
                           + "values (?,?,?,?)");
        ){
//       ResultSet rs = preparedStatement2.executeQuery();
//       while (rs.next()) {
//       ContractorBean newcontractor = new ContractorBean();
//       newcontractor.setContractorID(rs.getDouble("currentcontractorid"));
       
       if (contractor.getSpecialisationIDs() != null){
       for (int i= 0; i < contractor.getSpecialisationIDs().length; i++){
           String specialisationID = (String) Array.get(contractor.getSpecialisationIDs(), i);
           if (specialisationID != null && !specialisationID.isEmpty()){
           preparedStatement3.setString(1, newDateStringNow);
           preparedStatement3.setString(2, contractor.getEmpAD());
           preparedStatement3.setInt(3, contractor.getContractorID());
           preparedStatement3.setString(4, specialisationID);
           preparedStatement3.setString(5, contractor.getSpecialisationOthers());
           preparedStatement3.executeUpdate();
           }
       }
       }
       
      if (contractor.getSpkkCatIDs() != null){
       for (int i= 0; i < contractor.getSpkkCatIDs().length; i++){
           
           String spkkCatID = (String) Array.get(contractor.getSpkkCatIDs(), i);
            if (spkkCatID != null && !spkkCatID.isEmpty()){
           preparedStatement4.setString(1, newDateStringNow);
           preparedStatement4.setString(2, contractor.getEmpAD());
           preparedStatement4.setInt(3, contractor.getContractorID());
           preparedStatement4.setString(4, spkkCatID);
           preparedStatement4.executeUpdate();
            }
       }
      }
       
        if (contractor.getSpkkSpecIDs() != null){
       for (int i= 0; i < contractor.getSpkkSpecIDs().length; i++){
           
           String spkkSpecID = (String) Array.get(contractor.getSpkkSpecIDs(), i);
            if (spkkSpecID != null && !spkkSpecID.isEmpty()){
           preparedStatement5.setString(1, newDateStringNow);
           preparedStatement5.setString(2, contractor.getEmpAD());
           preparedStatement5.setInt(3, contractor.getContractorID());
           preparedStatement5.setString(4, spkkSpecID);
           preparedStatement5.executeUpdate();
            }
       }
        }
       
        if (contractor.getCompanyOwners() != null){
        for (int i= 0; i < contractor.getCompanyOwners().length; i++){
           
           String companyOwner = (String) Array.get(contractor.getCompanyOwners(), i);
           String icNo = (String) Array.get(contractor.getIcNos(), i);
           String ownerContactNo = (String) Array.get(contractor.getOwnerContactNos(), i);
           if (companyOwner != null && !companyOwner.isEmpty()){
           
                
                preparedStatement6.setString(1, newDateStringNow);
                preparedStatement6.setString(2, contractor.getEmpAD());
                preparedStatement6.setInt(3, contractor.getContractorID());
                preparedStatement6.setString(4, companyOwner);
                preparedStatement6.setString(5, icNo);
                preparedStatement6.setString(6, ownerContactNo);
                preparedStatement6.executeUpdate();
           } else {
               //Do nothing
                }
           
            }
        }
       
       if (contractor.getPastProjects() != null){
       for (int i= 0; i < contractor.getPastProjects().length; i++) {
           
           String pastProject = (String) Array.get(contractor.getPastProjects(), i);
           String pastProjectAmount = (String) Array.get(contractor.getPastProjectAmounts(), i);
           String pastProjectYear = (String) Array.get(contractor.getPastProjectYears(), i);
           if (pastProject != null && !pastProject.isEmpty()){
           
           preparedStatement7.setString(1, newDateStringNow);
           preparedStatement7.setString(2, contractor.getEmpAD());
           preparedStatement7.setInt(3, contractor.getContractorID());
           preparedStatement7.setString(4, pastProject);
           preparedStatement7.setString(5, pastProjectAmount);
           preparedStatement7.setString(6, pastProjectYear);
           preparedStatement7.executeUpdate();
       }
       }
       }
       
       if (contractor.getCurrentProjects() != null){
       for (int i= 0; i < contractor.getCurrentProjects().length; i++) {
           
           String currentProject = (String) Array.get(contractor.getCurrentProjects(), i);
           String currentProjectAmount = (String) Array.get(contractor.getCurrentProjectAmounts(), i);
           String currentProjectYear = (String) Array.get(contractor.getCurrentProjectYears(), i);
           if (currentProject != null && !currentProject.isEmpty()){
           
           preparedStatement8.setString(1, newDateStringNow);
           preparedStatement8.setString(2, contractor.getEmpAD());
           preparedStatement8.setInt(3, contractor.getContractorID());
           preparedStatement8.setString(4, currentProject);
           preparedStatement8.setString(5, currentProjectAmount);
           preparedStatement8.setString(6, currentProjectYear);
           preparedStatement8.executeUpdate();
       }
       }
       }
       
       if (contractor.getAwardGovs() != null){
       for (int i= 0; i < contractor.getAwardGovs().length; i++) {
           String awardGov = (String) Array.get(contractor.getAwardGovs(), i);
           String awardYear = (String) Array.get(contractor.getAwardGovYears(), i);
           if (awardGov != null && !awardGov.isEmpty()){
           
           preparedStatement9.setString(1, newDateStringNow);
           preparedStatement9.setString(2, contractor.getEmpAD());
           preparedStatement9.setInt(3, contractor.getContractorID());
           preparedStatement9.setString(4, awardGov);
           preparedStatement9.setString(5, awardYear);
           preparedStatement9.executeUpdate();
       }
       }
       }
       
        if (contractor.getMofCodeIDs() != null){
       for (int i= 0; i < contractor.getMofCodeIDs().length; i++){
           
           String mof = (String) Array.get(contractor.getMofCodeIDs(), i);
           if (mof != null && !mof.isEmpty()){
           
           preparedStatement10.setString(1, newDateStringNow);
           preparedStatement10.setString(2, contractor.getEmpAD());
           preparedStatement10.setInt(3, contractor.getContractorID());
           preparedStatement10.setString(4, mof);
           preparedStatement10.executeUpdate();
       }
       }
        }
       
//       for (String attachApp : contractor.getAttachLinks()) {
//        preparedStatement11.setString(1, newDateStringNow);
//        preparedStatement11.setString(2, contractor.getEmpAD());
//        preparedStatement11.setInt(3, contractor.getContractorID());
//        preparedStatement11.setString(4, attachApp);
//        preparedStatement11.executeUpdate();
//       }
       
        if (contractor.getAttachAppFormLinks() != null){
       for (int i= 0; i < contractor.getAttachAppFormLinks().length; i++) {
           
           String attachAppFormLink = (String) Array.get(contractor.getAttachAppFormLinks(), i);
           String attachAppFormFileName = (String) Array.get(contractor.getAttachAppFormFileNames(), i);
           String oriAttachAppFormFileName = (String) Array.get(contractor.getOriAttachAppFormFileNames(), i);
           if (attachAppFormLink != null && !attachAppFormLink.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setInt(4, 1);
           preparedStatement11.setString(5, attachAppFormLink);
           preparedStatement11.setString(6, attachAppFormFileName);
           preparedStatement11.setString(7, oriAttachAppFormFileName);
           preparedStatement11.executeUpdate();
       }
       }
        }
       
//        if (contractor.getAttachSsmLinks() != null){
//        for (int i= 0; i < contractor.getAttachSsmLinks().length; i++) {
//           
//           String attachSsmLink = (String) Array.get(contractor.getAttachSsmLinks(), i);
//           String attachSsmFileName = (String) Array.get(contractor.getAttachSsmFileNames(), i);
//           String oriAttachSsmFileName = (String) Array.get(contractor.getOriAttachSsmFileNames(), i);
//           if (attachSsmLink != null && !attachSsmLink.isEmpty()){
//           
//           preparedStatement11.setString(1, newDateStringNow);
//           preparedStatement11.setString(2, contractor.getEmpAD());
//           preparedStatement11.setInt(3, contractor.getContractorID());
//           preparedStatement11.setInt(4, 2);
//           preparedStatement11.setString(5, attachSsmLink);
//           preparedStatement11.setString(6, attachSsmFileName);
//           preparedStatement11.setString(7, oriAttachSsmFileName);
//           preparedStatement11.executeUpdate();
//       }
//       }
//        }
       
       if (contractor.getAttachCidbCertLinks() != null){
       for (int i= 0; i < contractor.getAttachCidbCertLinks().length; i++) {
           
           String attachCidbCertLink = (String) Array.get(contractor.getAttachCidbCertLinks(), i);
           String attachCidbCertFileName = (String) Array.get(contractor.getAttachCidbCertFileNames(), i);
           String oriAttachCidbCertFileName = (String) Array.get(contractor.getOriAttachCidbCertFileNames(), i);
           if (attachCidbCertLink != null && !attachCidbCertLink.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setInt(4, 3);
           preparedStatement11.setString(5, attachCidbCertLink);
           preparedStatement11.setString(6, attachCidbCertFileName);
           preparedStatement11.setString(7, oriAttachCidbCertFileName);
           preparedStatement11.executeUpdate();
       }
       }
       }
       
       if (contractor.getAttachStbCertLinks() != null){
       for (int i= 0; i < contractor.getAttachStbCertLinks().length; i++) {
           
           String attachStbCertLink = (String) Array.get(contractor.getAttachStbCertLinks(), i);
           String attachStbCertFileName = (String) Array.get(contractor.getAttachStbCertFileNames(), i);
           String oriAttachStbCertFileName = (String) Array.get(contractor.getOriAttachStbCertFileNames(), i);
           if (attachStbCertLink != null && !attachStbCertLink.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setInt(4, 4);
           preparedStatement11.setString(5, attachStbCertLink);
           preparedStatement11.setString(6, attachStbCertFileName);
           preparedStatement11.setString(7, oriAttachStbCertFileName);
           preparedStatement11.executeUpdate();
       }
       }
       }
       
//       if (contractor.getAttachMofCertLinks() != null){
//       for (int i= 0; i < contractor.getAttachMofCertLinks().length; i++) {
//           
//           String attachMofCertLink = (String) Array.get(contractor.getAttachMofCertLinks(), i);
//           String attachMofCertFileName = (String) Array.get(contractor.getAttachMofCertFileNames(), i);
//           String oriAttachMofCertFileName = (String) Array.get(contractor.getOriAttachMofCertFileNames(), i);
//           if (attachMofCertLink != null && !attachMofCertLink.isEmpty()){
//           
//           preparedStatement11.setString(1, newDateStringNow);
//           preparedStatement11.setString(2, contractor.getEmpAD());
//           preparedStatement11.setInt(3, contractor.getContractorID());
//           preparedStatement11.setInt(4, 5);
//           preparedStatement11.setString(5, attachMofCertLink);
//           preparedStatement11.setString(6, attachMofCertFileName);
//           preparedStatement11.setString(7, oriAttachMofCertFileName);
//           preparedStatement11.executeUpdate();
//       }
//       }
//       }
       
        if (contractor.getAttachScreenFormLinks() != null){
       for (int i= 0; i < contractor.getAttachScreenFormLinks().length; i++) {
           
           String attachScreenFormLink = (String) Array.get(contractor.getAttachScreenFormLinks(), i);
           String attachScreenFormFileName = (String) Array.get(contractor.getAttachScreenFormFileNames(), i);
           String oriAttachScreenFormFileName = (String) Array.get(contractor.getOriAttachScreenFormFileNames(), i);
           if (attachScreenFormLink != null && !attachScreenFormLink.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setInt(4, 6);
           preparedStatement11.setString(5, attachScreenFormLink);
           preparedStatement11.setString(6, attachScreenFormFileName);
           preparedStatement11.setString(7, oriAttachScreenFormFileName);
           preparedStatement11.executeUpdate();
       }
       }
        }
       
        if (contractor.getAttachContractorMiscLinks() != null){ 
       for (int i= 0; i < contractor.getAttachContractorMiscLinks().length; i++) {
           
           String attachContractorMiscLink = (String) Array.get(contractor.getAttachContractorMiscLinks(), i);
           String attachContractorMiscFileName = (String) Array.get(contractor.getAttachContractorMiscFileNames(), i);
           String oriAttachContractorMiscFileName = (String) Array.get(contractor.getOriAttachContractorMiscFileNames(), i);
           if (attachContractorMiscLink != null && !attachContractorMiscLink.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setInt(4, 7);
           preparedStatement11.setString(5, attachContractorMiscLink);
           preparedStatement11.setString(6, attachContractorMiscFileName);
           preparedStatement11.setString(7, oriAttachContractorMiscFileName);
           preparedStatement11.executeUpdate();
       }
       }
        }
        
        if (contractor.getAttachCompanyLogoLinks() != null){ 
       for (int i= 0; i < contractor.getAttachCompanyLogoLinks().length; i++) {
           
           String attachCompanyLogoLink = (String) Array.get(contractor.getAttachCompanyLogoLinks(), i);
           String attachCompanyLogoFileName = (String) Array.get(contractor.getAttachCompanyLogoFileNames(), i);
           String oriAttachCompanyLogoFileName = (String) Array.get(contractor.getOriAttachCompanyLogoFileNames(), i);
           if (attachCompanyLogoLink != null && !attachCompanyLogoLink.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setInt(4, 7);
           preparedStatement11.setString(5, attachCompanyLogoLink);
           preparedStatement11.setString(6, attachCompanyLogoFileName);
           preparedStatement11.setString(7, oriAttachCompanyLogoFileName);
           preparedStatement11.executeUpdate();
       }
       }
        }
        
       if (contractor.getOthersRemarks() != null){
        for (int i= 0; i < contractor.getOthersRemarks().length; i++){
           
           if (contractor.getProgramID() == 4){
           String otherRemark = (String) Array.get(contractor.getOthersRemarks(), i);
           if (otherRemark != null && !otherRemark.isEmpty()){
           
                
                preparedStatement12.setString(1, newDateStringNow);
                preparedStatement12.setString(2, contractor.getEmpAD());
                preparedStatement12.setInt(3, contractor.getContractorID());
                preparedStatement12.setString(4, otherRemark);
                preparedStatement12.executeUpdate();
           } else {
               //Do nothing
                }
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
    } catch (SQLException e) {
        e.printStackTrace();
       // currentCon.rollback();
  }
   
 }

public void deleteContractor(double contractorID) throws SQLException {
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_contractor set is_active='N' where contractor_id=?");
        PreparedStatement preparedStatement2 = currentCon.prepareStatement
        ("update bcms_spkkcategory set is_active='N' where contractor_id=?");
        PreparedStatement preparedStatement7 = currentCon.prepareStatement
        ("update bcms_specialisation set is_active='N' where contractor_id=?");
        PreparedStatement preparedStatement8 = currentCon.prepareStatement
        ("update bcms_spkkspecialisation set is_active='N' where contractor_id=?");
        PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("update bcms_companyowner set is_active='N' where contractor_id=?");
        PreparedStatement preparedStatement4 = currentCon.prepareStatement
        ("update bcms_contractorpastproject set is_active='N' where contractor_id=?");
         PreparedStatement preparedStatement5 = currentCon.prepareStatement
        ("update bcms_contractorcurrentproject set is_active='N' where contractor_id=?");
         PreparedStatement preparedStatement6 = currentCon.prepareStatement
        ("update bcms_awardbygov set is_active='N' where contractor_id=?");
        PreparedStatement preparedStatement9 = currentCon.prepareStatement
        ("update bcms_mof set is_active='N' where contractor_id=?");){
   
   preparedStatement.setDouble(1, contractorID);
   preparedStatement.executeUpdate();
   
   preparedStatement2.setDouble(1, contractorID);
   preparedStatement2.executeUpdate();
   
   preparedStatement3.setDouble(1, contractorID);
   preparedStatement3.executeUpdate();
  

   preparedStatement4.setDouble(1, contractorID);
   preparedStatement4.executeUpdate();
   
   preparedStatement5.setDouble(1, contractorID);
   preparedStatement5.executeUpdate();
   
   preparedStatement6.setDouble(1, contractorID);
   preparedStatement6.executeUpdate();
   
   preparedStatement7.setDouble(1, contractorID);
   preparedStatement7.executeUpdate();
   
   preparedStatement8.setDouble(1, contractorID);
   preparedStatement8.executeUpdate();
   
   preparedStatement9.setDouble(1, contractorID);
   preparedStatement9.executeUpdate();
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public void updateContractor(ContractorBean contractor) throws ParseException, SQLException {
    String query = ("update bcms_contractor set date_update=?, user_update=?, company_name=?, SSM_cert_no=?, grade_id=?,CIDB_expiry_date=?, STB_expiry_date=?,"
                    + "correspondence_address_1=?, correspondence_address_2=?, correspondence_city=?, correspondence_postcode=?, correspondence_stateid=?, "
                    + "office_tel_no=?, office_fax_no=?, PIC_mobile_no=?, email_address_1=?, date_submission=?, application_status=?, reason_failed=?, CIDB_reg_no=?, stb_no=?, formtype_id=?, formtypeothers_desc=?, date_application_status=?, contractortype_id=? where contractor_id=? ");
  try (Connection currentCon = ConnectionManager.getConnection();
       PreparedStatement preparedStatement = currentCon.prepareStatement(query);){
    
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
   preparedStatement.setString(2, contractor.getEmpAD());
   preparedStatement.setString(3, contractor.getCompanyName());
   preparedStatement.setString(4, contractor.getSsmCertNo());
   if((contractor.getGradeID() == 0)){
      //System.out.println("gogogogo33" + contractor.getGradeID());
   preparedStatement.setNull(5, java.sql.Types.INTEGER);
   }
   else {
   preparedStatement.setDouble(5, contractor.getGradeID());
   }
   //preparedStatement.setDouble(5, contractor.getGradeID());
   if("".equals(contractor.getCidbExpiryDate())){
   preparedStatement.setNull(6, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(6, contractor.getCidbExpiryDate());
   }
   if("".equals(contractor.getStbExpiryDate())){
   preparedStatement.setNull(7, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(7, contractor.getStbExpiryDate());
   }
   preparedStatement.setString(8, contractor.getCorrespondenceAddress1());
   preparedStatement.setString(9, contractor.getCorrespondenceAddress2());
   preparedStatement.setString(10, contractor.getCorrespondenceCity());
   preparedStatement.setString(11, contractor.getCorrespondencePostCode());
   preparedStatement.setInt(12, contractor.getCorrespondenceStateID());
   preparedStatement.setString(13, contractor.getOfficePhoneNo());
   preparedStatement.setString(14, contractor.getOfficeFaxNo());
   preparedStatement.setString(15, contractor.getPicMobileNo());
   preparedStatement.setString(16, contractor.getEmailAddress1());
   if("".equals(contractor.getDateFormSubmitted())){
   preparedStatement.setNull(17, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(17, contractor.getDateFormSubmitted());
   }
   preparedStatement.setString(18, contractor.getAppStatus());
   //if ("PENDING".equals(contractor.getAppStatus()) || "FAILED".equals(contractor.getAppStatus()) || "BLACKLISTED".equals(contractor.getAppStatus())){
   preparedStatement.setString(19, contractor.getReasonFailed());
   //}
   //else {
   //preparedStatement.setNull(19, java.sql.Types.CHAR);
   //}
   preparedStatement.setString(20, contractor.getCidbRegNo());
   preparedStatement.setString(21, contractor.getStbNo());
   preparedStatement.setString(22, contractor.getFormTypeID());
   if ("2".equals(contractor.getFormTypeID())){
   preparedStatement.setString(23, contractor.getFormTypeOthersDesc());
   }
   else {
   preparedStatement.setNull(23, java.sql.Types.CHAR);
   }
   
   if("".equals(contractor.getDateAppStatus())){
   preparedStatement.setNull(24, java.sql.Types.DATE);
   }
   else {
   preparedStatement.setString(24, contractor.getDateAppStatus());
   }
   preparedStatement.setInt(25, contractor.getContractorTypeID());
   preparedStatement.setInt(26, contractor.getContractorID());
   
   
   preparedStatement.executeUpdate();
   
    PreparedStatement preparedStatement2 = currentCon.prepareStatement
        ("update bcms_spkkcategory set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement2.setDouble(1, contractor.getContractorID());
   preparedStatement2.executeUpdate();
   
   PreparedStatement preparedStatement14 = currentCon.prepareStatement
        ("update bcms_spkkspecialisation set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement14.setDouble(1, contractor.getContractorID());
   preparedStatement14.executeUpdate();
   
   PreparedStatement preparedStatement15 = currentCon.prepareStatement
        ("update bcms_specialisation set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement15.setDouble(1, contractor.getContractorID());
   preparedStatement15.executeUpdate();
   
   PreparedStatement preparedStatement3 = currentCon.prepareStatement
        ("update bcms_companyowner set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement3.setDouble(1, contractor.getContractorID());
   preparedStatement3.executeUpdate();
  
   PreparedStatement preparedStatement4 = currentCon.prepareStatement
        ("update bcms_contractorpastproject set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement4.setDouble(1, contractor.getContractorID());
   preparedStatement4.executeUpdate();
   
   PreparedStatement preparedStatement5 = currentCon.prepareStatement
        ("update bcms_contractorcurrentproject set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement5.setDouble(1, contractor.getContractorID());
   preparedStatement5.executeUpdate();
   
   PreparedStatement preparedStatement6 = currentCon.prepareStatement
        ("update bcms_awardbygov set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement6.setDouble(1, contractor.getContractorID());
   preparedStatement6.executeUpdate();
   
   PreparedStatement preparedStatement17 = currentCon.prepareStatement
        ("update bcms_mof set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement17.setDouble(1, contractor.getContractorID());
   preparedStatement17.executeUpdate();
   
   PreparedStatement preparedStatement19 = currentCon.prepareStatement
        ("update bcms_programothers set is_active='N' where contractor_id=?");
   // Parameters start with 1
   preparedStatement19.setDouble(1, contractor.getContractorID());
   preparedStatement19.executeUpdate();
   
//   for (String spkkID : contractor.getSpkkIDs()) {
//           PreparedStatement preparedStatement7 = currentCon.prepareStatement
//                   ("insert into bcms_SPKKcategory(date_create,user_create,contractor_id,spkk_id) "
//                           + "values (?,?,?,?)");
//           preparedStatement7.setString(1, newDateStringNow);
//           preparedStatement7.setString(2, contractor.getEmpAD());
//           preparedStatement7.setInt(3, contractor.getContractorID());
//           preparedStatement7.setString(4, spkkID);
//           preparedStatement7.executeUpdate();
//   //}
//       }
   
   try (PreparedStatement preparedStatement7 = currentCon.prepareStatement
        ("insert into bcms_specialisation(date_create,user_create,contractor_id,specialisationmaster_id, specialisation_others) "
        + "values (?,?,?,?,?)");
        PreparedStatement preparedStatement8 = currentCon.prepareStatement
                   ("insert into bcms_spkkcategory(date_create,user_create,contractor_id,spkkcatmaster_id) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement9 = currentCon.prepareStatement
                   ("insert into bcms_spkkspecialisation(date_create,user_create,contractor_id,spkkspecmaster_id) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement10 = currentCon.prepareStatement
                   ("insert into bcms_companyowner(date_create,user_create,contractor_id,owner_name, owner_ic_no, owner_contact_no) "
                           + "values (?,?,?,?,?,?)");
        PreparedStatement preparedStatement11 = currentCon.prepareStatement
                   ("insert into bcms_contractorpastproject(date_create,user_create,contractor_id,pastproject_desc, pastproject_amount, pastproject_year) "
                           + "values (?,?,?,?,?,?)");
        PreparedStatement preparedStatement12 = currentCon.prepareStatement
                   ("insert into bcms_contractorcurrentproject(date_create,user_create,contractor_id,currproject_desc, currproject_amount, currproject_year) "
                           + "values (?,?,?,?,?,?)");
        PreparedStatement preparedStatement13 = currentCon.prepareStatement
                   ("insert into bcms_awardbygov(date_create,user_create,contractor_id,award_desc,award_year) "
                           + "values (?,?,?,?,?)");
        PreparedStatement preparedStatement16 = currentCon.prepareStatement
                   ("insert into bcms_mof(date_create,user_create,contractor_id,mofsub2master_id) "
                           + "values (?,?,?,?)");
        PreparedStatement preparedStatement18 = currentCon.prepareStatement
                   ("insert into bcms_attachmentcontractor(date_create,user_create,contractor_id, attachmentmaster_id, attachment_link, file_name, ori_file_name) "
                           + "values (?,?,?,?,?,?,?)");
        PreparedStatement preparedStatement20 = currentCon.prepareStatement
                   ("insert into bcms_programothers(date_create,user_create,contractor_id,programothers_desc) "
                           + "values (?,?,?,?)");
        ){
//       ResultSet rs = preparedStatement2.executeQuery();
//       while (rs.next()) {
//       ContractorBean newcontractor = new ContractorBean();
//       newcontractor.setContractorID(rs.getDouble("currentcontractorid"));
       
//       for (String specialisationID : contractor.getSpecialisationIDs()) {
//           
//           preparedStatement7.setString(1, newDateStringNow);
//           preparedStatement7.setString(2, contractor.getEmpAD());
//           preparedStatement7.setInt(3, contractor.getContractorID());
//           preparedStatement7.setString(4, specialisationID);
//           preparedStatement7.executeUpdate();
//       }
       
       if (contractor.getSpecialisationIDs() != null){
       for (int i= 0; i < contractor.getSpecialisationIDs().length; i++){
           
           String specialisationID = (String) Array.get(contractor.getSpecialisationIDs(), i);
           if (specialisationID != null && !specialisationID.isEmpty()){
           
           preparedStatement7.setString(1, newDateStringNow);
           preparedStatement7.setString(2, contractor.getEmpAD());
           preparedStatement7.setInt(3, contractor.getContractorID());
           preparedStatement7.setString(4, specialisationID);
           preparedStatement7.setString(5, contractor.getSpecialisationOthers());
           preparedStatement7.executeUpdate();
           } 
           }
       }
       
       if (contractor.getSpkkCatIDs() != null){
       for (int i= 0; i < contractor.getSpkkCatIDs().length; i++){
           
           String spkkCatID = (String) Array.get(contractor.getSpkkCatIDs(), i);
           if (spkkCatID != null && !spkkCatID.isEmpty()){
           
           preparedStatement8.setString(1, newDateStringNow);
           preparedStatement8.setString(2, contractor.getEmpAD());
           preparedStatement8.setInt(3, contractor.getContractorID());
           preparedStatement8.setString(4, spkkCatID);
           preparedStatement8.executeUpdate();
           } 
           }    
       }
//       for (String spkkCatID : contractor.getSpkkCatIDs()) {
//           
//           preparedStatement8.setString(1, newDateStringNow);
//           preparedStatement8.setString(2, contractor.getEmpAD());
//           preparedStatement8.setInt(3, contractor.getContractorID());
//           preparedStatement8.setString(4, spkkCatID);
//           preparedStatement8.executeUpdate();
//       }
       
        if (contractor.getSpkkSpecIDs() != null){
       for (int i= 0; i < contractor.getSpkkSpecIDs().length; i++){
           
           String spkkSpecID = (String) Array.get(contractor.getSpkkSpecIDs(), i);
           if (spkkSpecID != null && !spkkSpecID.isEmpty()){
           
           preparedStatement9.setString(1, newDateStringNow);
           preparedStatement9.setString(2, contractor.getEmpAD());
           preparedStatement9.setInt(3, contractor.getContractorID());
           preparedStatement9.setString(4, spkkSpecID);
           preparedStatement9.executeUpdate();
           } 
           }  
        }

         if (contractor.getCompanyOwners() != null){
        for (int i= 0; i < contractor.getCompanyOwners().length; i++){
           
           String companyOwner = (String) Array.get(contractor.getCompanyOwners(), i);
           String icNo = (String) Array.get(contractor.getIcNos(), i);
           String ownerContactNo = (String) Array.get(contractor.getOwnerContactNos(), i);
           if (companyOwner != null && !companyOwner.isEmpty()){
           
                
                preparedStatement10.setString(1, newDateStringNow);
                preparedStatement10.setString(2, contractor.getEmpAD());
                preparedStatement10.setInt(3, contractor.getContractorID());
                preparedStatement10.setString(4, companyOwner);
                preparedStatement10.setString(5, icNo);
                preparedStatement10.setString(6, ownerContactNo);
                preparedStatement10.executeUpdate();
           } 
            }
         }
       
        if (contractor.getPastProjects() != null){
       for (int i= 0; i < contractor.getPastProjects().length; i++) {
           
           String pastProject = (String) Array.get(contractor.getPastProjects(), i);
           String pastProjectAmount = (String) Array.get(contractor.getPastProjectAmounts(), i);
           String pastProjectYear = (String) Array.get(contractor.getPastProjectYears(), i);
           if (pastProject != null && !pastProject.isEmpty()){
           
           preparedStatement11.setString(1, newDateStringNow);
           preparedStatement11.setString(2, contractor.getEmpAD());
           preparedStatement11.setInt(3, contractor.getContractorID());
           preparedStatement11.setString(4, pastProject);
           preparedStatement11.setString(5, pastProjectAmount);
           preparedStatement11.setString(6, pastProjectYear);
           preparedStatement11.executeUpdate();
       }
       }
        }
       
       if (contractor.getCurrentProjects() != null){
       for (int i= 0; i < contractor.getCurrentProjects().length; i++) {
           
           String currentProject = (String) Array.get(contractor.getCurrentProjects(), i);
           String currentProjectAmount = (String) Array.get(contractor.getCurrentProjectAmounts(), i);
           String currentProjectYear = (String) Array.get(contractor.getCurrentProjectYears(), i);
           if (currentProject != null && !currentProject.isEmpty()){
           
           preparedStatement12.setString(1, newDateStringNow);
           preparedStatement12.setString(2, contractor.getEmpAD());
           preparedStatement12.setInt(3, contractor.getContractorID());
           preparedStatement12.setString(4, currentProject);
           preparedStatement12.setString(5, currentProjectAmount);
           preparedStatement12.setString(6, currentProjectYear);
           preparedStatement12.executeUpdate();
       }
       }
       }
       
       if (contractor.getAwardGovs() != null){
       for (int i= 0; i < contractor.getAwardGovs().length; i++) {
           String awardGov = (String) Array.get(contractor.getAwardGovs(), i);
           String awardYear = (String) Array.get(contractor.getAwardGovYears(), i);
           if (awardGov != null && !awardGov.isEmpty()){
           
           preparedStatement13.setString(1, newDateStringNow);
           preparedStatement13.setString(2, contractor.getEmpAD());
           preparedStatement13.setInt(3, contractor.getContractorID());
           preparedStatement13.setString(4, awardGov);
           preparedStatement13.setString(5, awardYear);
           preparedStatement13.executeUpdate();
       }
       }
       }
       
       
       if (contractor.getMofCodeIDs() != null){
       for (int i= 0; i < contractor.getMofCodeIDs().length; i++){
           
           String mof = (String) Array.get(contractor.getMofCodeIDs(), i);
           if (mof != null && !mof.isEmpty()){
           
          preparedStatement16.setString(1, newDateStringNow);
           preparedStatement16.setString(2, contractor.getEmpAD());
           preparedStatement16.setInt(3, contractor.getContractorID());
           preparedStatement16.setString(4, mof);
           preparedStatement16.executeUpdate();
           } 
           }  
       }
       
       
       if (contractor.getAttachAppFormLinks() != null){
       for (int i= 0; i < contractor.getAttachAppFormLinks().length; i++) {
           
           String attachAppFormLink = (String) Array.get(contractor.getAttachAppFormLinks(), i);
           String attachAppFormFileName = (String) Array.get(contractor.getAttachAppFormFileNames(), i);
           String oriAttachAppFormFileName = (String) Array.get(contractor.getOriAttachAppFormFileNames(), i);
           if (attachAppFormLink != null && !attachAppFormLink.isEmpty()){
           
           preparedStatement18.setString(1, newDateStringNow);
           preparedStatement18.setString(2, contractor.getEmpAD());
           preparedStatement18.setInt(3, contractor.getContractorID());
           preparedStatement18.setInt(4, 1);
           preparedStatement18.setString(5, attachAppFormLink);
           preparedStatement18.setString(6, attachAppFormFileName);
           preparedStatement18.setString(7, oriAttachAppFormFileName);
           preparedStatement18.executeUpdate();
       }
       }
       }
    
       
//       if (contractor.getAttachSsmLinks() != null){
//       for (int i= 0; i < contractor.getAttachSsmLinks().length; i++) {
//           
//           String attachSsmLink = (String) Array.get(contractor.getAttachSsmLinks(), i);
//           String attachSsmFileName = (String) Array.get(contractor.getAttachSsmFileNames(), i);
//           String oriAttachSsmFileName = (String) Array.get(contractor.getOriAttachSsmFileNames(), i);
//           if (attachSsmLink != null && !attachSsmLink.isEmpty()){
//           
//           preparedStatement18.setString(1, newDateStringNow);
//           preparedStatement18.setString(2, contractor.getEmpAD());
//           preparedStatement18.setInt(3, contractor.getContractorID());
//           preparedStatement18.setInt(4, 2);
//           preparedStatement18.setString(5, attachSsmLink);
//           preparedStatement18.setString(6, attachSsmFileName);
//           preparedStatement18.setString(7, oriAttachSsmFileName);
//           preparedStatement18.executeUpdate();
//       }
//       }
//       }
       
        if (contractor.getAttachCidbCertLinks() != null){
       for (int i= 0; i < contractor.getAttachCidbCertLinks().length; i++) {
           
           String attachCidbCertLink = (String) Array.get(contractor.getAttachCidbCertLinks(), i);
           String attachCidbCertFileName = (String) Array.get(contractor.getAttachCidbCertFileNames(), i);
           String oriAttachCidbCertFileName = (String) Array.get(contractor.getOriAttachCidbCertFileNames(), i);
           if (attachCidbCertLink != null && !attachCidbCertLink.isEmpty()){
           
           preparedStatement18.setString(1, newDateStringNow);
           preparedStatement18.setString(2, contractor.getEmpAD());
           preparedStatement18.setInt(3, contractor.getContractorID());
           preparedStatement18.setInt(4, 3);
           preparedStatement18.setString(5, attachCidbCertLink);
           preparedStatement18.setString(6, attachCidbCertFileName);
           preparedStatement18.setString(7, oriAttachCidbCertFileName);
           preparedStatement18.executeUpdate();
       }
       }
        }
       
         if (contractor.getAttachStbCertLinks() != null){
       for (int i= 0; i < contractor.getAttachStbCertLinks().length; i++) {
           
           String attachStbCertLink = (String) Array.get(contractor.getAttachStbCertLinks(), i);
           String attachStbCertFileName = (String) Array.get(contractor.getAttachStbCertFileNames(), i);
           String oriAttachStbCertFileName = (String) Array.get(contractor.getOriAttachStbCertFileNames(), i);
           if (attachStbCertLink != null && !attachStbCertLink.isEmpty()){
           
           preparedStatement18.setString(1, newDateStringNow);
           preparedStatement18.setString(2, contractor.getEmpAD());
           preparedStatement18.setInt(3, contractor.getContractorID());
           preparedStatement18.setInt(4, 4);
           preparedStatement18.setString(5, attachStbCertLink);
           preparedStatement18.setString(6, attachStbCertFileName);
           preparedStatement18.setString(7, oriAttachStbCertFileName);
           preparedStatement18.executeUpdate();
       }
       }
         }
       
//        if (contractor.getAttachMofCertLinks() != null){
//       for (int i= 0; i < contractor.getAttachMofCertLinks().length; i++) {
//           
//           String attachMofCertLink = (String) Array.get(contractor.getAttachMofCertLinks(), i);
//           String attachMofCertFileName = (String) Array.get(contractor.getAttachMofCertFileNames(), i);
//           String oriAttachMofCertFileName = (String) Array.get(contractor.getOriAttachMofCertFileNames(), i);
//           if (attachMofCertLink != null && !attachMofCertLink.isEmpty()){
//           
//           preparedStatement18.setString(1, newDateStringNow);
//           preparedStatement18.setString(2, contractor.getEmpAD());
//           preparedStatement18.setInt(3, contractor.getContractorID());
//           preparedStatement18.setInt(4, 5);
//           preparedStatement18.setString(5, attachMofCertLink);
//           preparedStatement18.setString(6, attachMofCertFileName);
//           preparedStatement18.setString(7, oriAttachMofCertFileName);
//           preparedStatement18.executeUpdate();
//       }
//       }
//        }
       
        if (contractor.getAttachScreenFormLinks() != null){
       for (int i= 0; i < contractor.getAttachScreenFormLinks().length; i++) {
           
           String attachScreenFormLink = (String) Array.get(contractor.getAttachScreenFormLinks(), i);
           String attachScreenFormFileName = (String) Array.get(contractor.getAttachScreenFormFileNames(), i);
           String oriAttachScreenFormFileName = (String) Array.get(contractor.getOriAttachScreenFormFileNames(), i);
           if (attachScreenFormLink != null && !attachScreenFormLink.isEmpty()){
           
           preparedStatement18.setString(1, newDateStringNow);
           preparedStatement18.setString(2, contractor.getEmpAD());
           preparedStatement18.setInt(3, contractor.getContractorID());
           preparedStatement18.setInt(4, 6);
           preparedStatement18.setString(5, attachScreenFormLink);
           preparedStatement18.setString(6, attachScreenFormFileName);
           preparedStatement18.setString(7, oriAttachScreenFormFileName);
           preparedStatement18.executeUpdate();
       }
       }
        }
       
         if (contractor.getAttachContractorMiscLinks() != null){
        for (int i= 0; i < contractor.getAttachContractorMiscLinks().length; i++) {
           
           String attachContractorMiscLink = (String) Array.get(contractor.getAttachContractorMiscLinks(), i);
           String attachContractorMiscFileName = (String) Array.get(contractor.getAttachContractorMiscFileNames(), i);
           String oriAttachContractorMiscFileName = (String) Array.get(contractor.getOriAttachContractorMiscFileNames(), i);
           if (attachContractorMiscLink != null && !attachContractorMiscLink.isEmpty()){
           
           preparedStatement18.setString(1, newDateStringNow);
           preparedStatement18.setString(2, contractor.getEmpAD());
           preparedStatement18.setInt(3, contractor.getContractorID());
           preparedStatement18.setInt(4, 7);
           preparedStatement18.setString(5, attachContractorMiscLink);
           preparedStatement18.setString(6, attachContractorMiscFileName);
           preparedStatement18.setString(7, oriAttachContractorMiscFileName);
           preparedStatement18.executeUpdate();
       }
       }
         }
         
       if (contractor.getOthersRemarks() != null){
        for (int i= 0; i < contractor.getOthersRemarks().length; i++){
           if (contractor.getProgramID() == 4){
           String otherRemark = (String) Array.get(contractor.getOthersRemarks(), i);
           if (otherRemark != null && !otherRemark.isEmpty()){
           
                
                preparedStatement20.setString(1, newDateStringNow);
                preparedStatement20.setString(2, contractor.getEmpAD());
                preparedStatement20.setInt(3, contractor.getContractorID());
                preparedStatement20.setString(4, otherRemark);
                preparedStatement20.executeUpdate();
           } else {
               //Do nothing
                }
           }
           else {
               //Do nothing
           }
           
           
            }
        }
          
   }  
   catch (SQLException e) {
   e.printStackTrace();
  }
   //currentCon.commit();
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

public void deleteAttachment(double attachID) throws SQLException {
   try (Connection currentCon = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update bcms_attachmentcontractor set is_active='N' where attachmentmaster_id=?");
        ){
   
   preparedStatement.setDouble(1, attachID);
   preparedStatement.executeUpdate();
   
  } catch (SQLException e) {
   e.printStackTrace();
  }
 }

}


