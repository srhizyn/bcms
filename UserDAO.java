package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author munawwarah.mahmod
 */
import Bean.FormBean;
import Utility.ConnectionManager;
import Bean.UserBean;
import java.text.*;
import java.util.*;
import java.sql.*;
import Utility.StringUtil;
import Utility.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import org.apache.commons.dbutils.DbUtils;

public class UserDAO {

    public UserBean login(UserBean user) throws SQLException, LoginException {
        String username = user.getUsername();
        String password = user.getPassword();
        ActiveDirectoryAuthentication authentication = new ActiveDirectoryAuthentication("mymrt.net");

        //Statement st=currentCon.createStatement();
        //ResultSet rs=st.executeQuery("SELECT ad_id,usr_passwd FROM ams.v_active_user WHERE ad_id='"+username+"'");
        String query = ("SELECT count(*) as count from v_active_user as vactive WHERE ad_id='" + username + "'");
        try (Connection currentCon = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = currentCon.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();) {

            while (rs.next()) {
                if (rs.getDouble("count") != 0) {

                    String query3 = ("SELECT count(role_id) as count_roleid from v_active_user as vactive WHERE ad_id='" + username + "'");

                    try (PreparedStatement preparedStatement3 = currentCon.prepareStatement(query3);
                            ResultSet rs8 = preparedStatement3.executeQuery();) {
                        while (rs8.next()) {
                            if (rs8.getDouble("count_roleid") != 0) {

                                boolean authResult = authentication.authenticate(username, password);
                                //String user_name=rs.getString(1);
                                //String user_password=rs.getString(2);
                                if (authResult == true) {
                                    //System.out.print("Auth: " + authResult);

                                    String query7 = ("SELECT ad_id,usr_passwd, vactive.dept_id, dept_name, role_id,emp_id, emp_name, username FROM v_active_user as vactive "
                                            + "   inner join bcms_deptmaster as dept on vactive.dept_id = dept.dept_id WHERE ad_id='" + username + "'");

                                    //currentCon = ConnectionManager.getConnection();
                                    //currentCon.setAutoCommit(false);
                                    try (PreparedStatement preparedStatement7 = currentCon.prepareStatement(query7);
                                            ResultSet rs7 = preparedStatement7.executeQuery();) {
                                        while (rs7.next()) {
                                            user.setValid(true);
                                            user.setDeptID(rs7.getDouble("dept_id"));
                                            user.setDeptName(rs7.getString("dept_name"));
                                            user.setRoleID(rs7.getDouble("role_id"));
                                            user.setEmpID(rs7.getDouble("emp_id"));
                                            user.setEmpAD(rs7.getString("ad_id"));
                                            user.setDisplayName(rs7.getString("username"));
                                        }

                                    }
                                    Statement statement6 = currentCon.createStatement();
                                    if (user.getRoleID() == 3 || user.getRoleID() == 6) {
//                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g1passedcount from bcms_contractor as contractor\n"
//                                                + "where contractor.is_active = 'Y' and contractor.grade_id in (1) and application_status = 'passed'");) 

                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g1passedcount from v_g1passedcount where project_id = 1");) {
                                            while (rs6.next()) {
                                                user.setG1PassedCount(rs6.getInt("g1passedcount"));
                                            }
                                        }
                                        //          try (ResultSet rs6 = statement6.executeQuery("select count(*) as g2passedcount from bcms_contractor as contractor\n" +
                                        //               "where contractor.is_active = 'Y' and contractor.grade_id in (2) and application_status = 'passed'");)

                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g2passedcount from v_g2passedcount where project_id = 1");) {
                                            while (rs6.next()) {
                                                user.setG2PassedCount(rs6.getInt("g2passedcount"));
                                            }
                                        }
//                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g3passedcount from bcms_contractor as contractor\n"
//                                                + "where contractor.is_active = 'Y' and contractor.grade_id in (3) and application_status = 'passed'");) 
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g3passedcount from v_g3passedcount where project_id = 1");) {

                                            while (rs6.next()) {
                                                user.setG3PassedCount(rs6.getInt("g3passedcount"));
                                            }
                                        }
//                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g4passedcount from bcms_contractor as contractor\n"
//                                                + "where contractor.is_active = 'Y' and contractor.grade_id in (4) and application_status = 'passed'");)
                                        try (ResultSet rs6 = statement6.executeQuery("select g4passedcount from v_g4passedcount");) {
                                            while (rs6.next()) {
                                                user.setG4PassedCount(rs6.getInt("g4passedcount"));
                                            }
                                        }
//                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g5passedcount from bcms_contractor as contractor\n"
//                                                + "where contractor.is_active = 'Y' and contractor.grade_id in (5) and application_status = 'passed'");)
                                        try (ResultSet rs6 = statement6.executeQuery("select g5passedcount from v_g5passedcount");) {
                                            while (rs6.next()) {
                                                user.setG5PassedCount(rs6.getInt("g5passedcount"));
                                            }
                                        }
//                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g6passedcount from bcms_contractor as contractor\n"
//                                                + "where contractor.is_active = 'Y' and contractor.grade_id in (6) and application_status = 'passed'");)
                                        try (ResultSet rs6 = statement6.executeQuery("select g6passedcount from v_g6passedcount;");) {
                                            while (rs6.next()) {
                                                user.setG6PassedCount(rs6.getInt("g6passedcount"));
                                            }
                                        }
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g1awardedcount from dbo.bcms_contractor AS contractor\n"
                                                + "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n"
                                                + "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n"
                                                + "where contractor.is_active = 'Y' and ballot.is_active = 'Y' and contractor.grade_id = 1 \n"
                                                + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')");) {
                                            while (rs6.next()) {
                                                user.setG1AwardedCount(rs6.getInt("g1awardedcount"));
                                            }
                                        }
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g2awardedcount from dbo.bcms_contractor AS contractor\n"
                                                + "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n"
                                                + "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n"
                                                + "where contractor.is_active = 'Y' and ballot.is_active = 'Y' and contractor.grade_id = 2 \n"
                                                + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')");) {
                                            while (rs6.next()) {
                                                user.setG2AwardedCount(rs6.getInt("g2awardedcount"));
                                            }
                                        }
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g3awardedcount from dbo.bcms_contractor AS contractor\n"
                                                + "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n"
                                                + "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n"
                                                + "where contractor.is_active = 'Y' and ballot.is_active = 'Y' and contractor.grade_id = 3 \n"
                                                + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')");) {
                                            while (rs6.next()) {
                                                user.setG3AwardedCount(rs6.getInt("g3awardedcount"));
                                            }
                                        }
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g4awardedcount from dbo.bcms_contractor AS contractor\n"
                                                + "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n"
                                                + "left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n"
                                                + "where contractor.is_active = 'Y' and ballot.is_active = 'Y' and contractor.grade_id = 4 \n"
                                                + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')");) {
                                            while (rs6.next()) {
                                                user.setG4AwardedCount(rs6.getInt("g4awardedcount"));
                                            }
                                        }
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g5awardedcount from dbo.bcms_contractor AS contractor\n"
                                                + "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n"
                                                + //"left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                                "where contractor.is_active = 'Y' "
                                                + //"and ballot.is_active = 'Y' " +
                                                "and contractor.grade_id = 5 \n"
                                                + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')");) {
                                            while (rs6.next()) {
                                                user.setG5AwardedCount(rs6.getInt("g5awardedcount"));
                                            }
                                        }
                                        try (ResultSet rs6 = statement6.executeQuery("select count(*) as g6awardedcount from dbo.bcms_contractor AS contractor\n"
                                                + "left join bcms_cidbgrademaster as cidb on cidb.grade_id = contractor.grade_id\n"
                                                + //"left join bcms_ballot as ballot on ballot.contractor_id = contractor.contractor_id\n" +
                                                "where contractor.is_active = 'Y' "
                                                + //"and ballot.is_active = 'Y' " +
                                                "and contractor.grade_id = 6 \n"
                                                + "and contractor.contractor_id in (select contractor_id from bcms_contractaward where is_active = 'Y')");) {
                                            while (rs6.next()) {
                                                user.setG6AwardedCount(rs6.getInt("g6awardedcount"));
                                            }
                                        }
                                    }
                                } else {
                                    user.setValid(false);
                                }
                            } else {
                                user.setValid(false);
                            }
                        }
                    }
                } else {
                    user.setValid(false);
                }

            }
//     currentCon.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserBean resetPassword(UserBean user) throws SQLException {
        String oldPassword = user.getOldPassword();
        String newPassword = user.getNewPassword();
        String confirmPassword = user.getConfirmPassword();
        String hashOldPwd = StringUtil.getHash(oldPassword);
        String empAD = user.getEmpAD();

        String query = ("SELECT ad_id, usr_passwd FROM v_active_user WHERE ad_id='" + empAD + "'");
        try (Connection currentCon = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = currentCon.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                Statement statement = currentCon.createStatement();) {
//    currentCon.setAutoCommit(false);

            while (rs.next()) {
                user.setEmpAD(rs.getString("ad_id"));
                String user_password = rs.getString(2);
                if (newPassword.equals(confirmPassword)) {

                    if (hashOldPwd.equals(user_password)) {

                        statement.executeUpdate("update employee set usr_passwd='" + newPassword + "' where ad_id='" + user.getEmpAD() + "'");
                        user.setValid(true);
                    } else {
                        user.setValid(false);
                    }
                } else {
                    user.setValid(false);
                }
            }
//     currentCon.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<UserBean> getUserName() throws SQLException {
        String query = ("SELECT emp_id, emp_name from employee where usr_stat='A' order by emp_name");
        List<UserBean> users = new ArrayList<UserBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
                Statement statement = currentCon.createStatement();
                ResultSet rs = statement.executeQuery(query);) {

            while (rs.next()) {

                UserBean user = new UserBean();
                user.setUserID(rs.getDouble("emp_id"));
                user.setUserFullName(rs.getString("emp_name"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<FormBean> getDeptName() throws SQLException {
        String query = ("SELECT dept_id, dept_name from bcms_deptmaster where is_active='Y' order by dept_name");
        List<FormBean> departments = new ArrayList<FormBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
                Statement statement = currentCon.createStatement();
                ResultSet rs = statement.executeQuery(query);) {

            while (rs.next()) {

                FormBean department = new FormBean();
                department.setValue(rs.getDouble("dept_id"));
                department.setDisplayText(rs.getString("dept_name"));
                department.setDeptID(rs.getDouble("dept_id"));
                department.setDeptName(rs.getString("dept_name"));

                departments.add(department);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;
    }

    public List<UserBean> getRegisteredUserName() throws SQLException {
        String query = ("SELECT emp_id, emp_name from employee where usr_stat='A' and ad_id is not null and usr_passwd is not null and username is not null order by emp_name");
        List<UserBean> users = new ArrayList<UserBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
                Statement statement = currentCon.createStatement();
                ResultSet rs = statement.executeQuery(query);) {

            while (rs.next()) {

                UserBean user = new UserBean();
                user.setUserID(rs.getDouble("emp_id"));
                user.setUserFullName(rs.getString("emp_name"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<UserBean> getRole() throws SQLException {
        String query = ("SELECT role_id, role_name from ref_usr_role");
        List<UserBean> users = new ArrayList<UserBean>();
        try (Connection currentCon = ConnectionManager.getConnection();
                Statement statement = currentCon.createStatement();
                ResultSet rs = statement.executeQuery(query);) {

            while (rs.next()) {

                UserBean user = new UserBean();
                user.setUserRoleID(rs.getDouble("role_id"));
                user.setUserRoleName(rs.getString("role_name"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void regUser(UserBean user) throws ParseException, SQLException {
        String query = ("SELECT dept_code from bcms_deptmaster where dept_id ='" + user.getDeptID() + "'");
        String query2 = ("update employee set date_update=?, usr_update=?, dept_id=?, dept_code=?, location_id=?, project_id=?, ad_id=?, usr_passwd=?, username=? where emp_id=?");
        String query3 = ("insert into sec_role_usr(date_create,usr_create,ad_id,emp_id) "
                + "values (?,?,?,?)");
        try (Connection currentCon = ConnectionManager.getConnection();
                Statement statement = currentCon.createStatement();
                ResultSet rs = statement.executeQuery(query);
                PreparedStatement preparedStatement = currentCon.prepareStatement(query2);
                PreparedStatement preparedStatement2 = currentCon.prepareStatement(query3);) {

            final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

            java.util.Date now = new java.util.Date();
            String newDateStringNow;
            String dateString = now.toString();
            SimpleDateFormat sdf3
                    = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            java.util.Date d3 = sdf3.parse(dateString);
            sdf3.applyPattern(FORMAT_DATETIME);
            newDateStringNow = sdf3.format(d3);

            while (rs.next()) {
                user.setDeptCode(rs.getString("dept_code"));
            }

            // Parameters start with 1   
            preparedStatement.setString(1, newDateStringNow);
            preparedStatement.setString(2, user.getEmpAD());
            preparedStatement.setDouble(3, user.getDeptID());
            preparedStatement.setString(4, user.getDeptCode());
            preparedStatement.setDouble(5, user.getLocationID());
            preparedStatement.setDouble(6, user.getProjectID());
            preparedStatement.setString(7, user.getUserAD());
            preparedStatement.setString(8, user.getUserPassword());
            preparedStatement.setString(9, user.getRegUsername());
            preparedStatement.setDouble(10, user.getUserID());
            preparedStatement.executeUpdate();
            user.setValid(true);

            preparedStatement2.setString(1, newDateStringNow);
            preparedStatement2.setString(2, user.getEmpAD());
            preparedStatement2.setString(3, user.getUserAD());
            preparedStatement2.setDouble(4, user.getUserID());
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            user.setValid(false);
        }
    }

    public void addWorkflow(UserBean user) throws SQLException {
        String query = ("UPDATE sec_role_usr\n"
                + "   SET date_update=?, usr_update=?, date_effective=?\n"
                + "	 ,role_id = COALESCE(NULLIF( ? ,0),role_id)\n"
                + "    ,pic = COALESCE(NULLIF( ? ,0),pic)\n"
                + "    ,hod = COALESCE(NULLIF( ? ,0),hod)\n"
                + "    ,archivist = COALESCE(NULLIF( ? ,0),archivist)\n"
                + "WHERE emp_id = ?");
        try (Connection currentCon = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = currentCon.prepareStatement(query);) {

            try {

                final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

                java.util.Date now = new java.util.Date();
                String newDateStringNow;
                String dateString = now.toString();
                SimpleDateFormat sdf3
                        = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                java.util.Date d3 = sdf3.parse(dateString);
                sdf3.applyPattern(FORMAT_DATETIME);
                newDateStringNow = sdf3.format(d3);

                preparedStatement.setString(1, newDateStringNow);
                preparedStatement.setString(2, user.getEmpAD());
                preparedStatement.setString(3, newDateStringNow);
                preparedStatement.setDouble(4, user.getUserRoleID());
                preparedStatement.setDouble(5, user.getPIC());
                preparedStatement.setDouble(6, user.getHOD());
                preparedStatement.setDouble(7, user.getArchivist());
                preparedStatement.setDouble(8, user.getUserID());

                preparedStatement.executeUpdate();
                user.setValid(true);
                //currentCon.commit();
            } catch (ParseException pe) {
                pe.printStackTrace();
                user.setValid(false);
            }
//   currentCon.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            user.setValid(false);
        }
    }

    public Map getPICHODArc(String val) throws SQLException {
        String query = ("SELECT dept_id from employee where emp_id =" + val + " ");
        Map users = new HashMap();
        try (Connection currentCon = ConnectionManager.getConnection();
                Statement statement = currentCon.createStatement();
                ResultSet rs = statement.executeQuery(query);) {

            while (rs.next()) {

                UserBean user = new UserBean();
                user.setDeptID(rs.getDouble("dept_id"));

                try (Statement statement2 = currentCon.createStatement();
                        ResultSet rs2 = statement2.executeQuery("SELECT emp_id, emp_name from employee where ad_id is not null and usr_passwd is not null and username is not null and dept_id =" + user.getDeptID() + " ");) {

                    while (rs2.next()) {

                        user.setEmpID(rs2.getDouble("emp_id"));
                        user.setEmpName(rs2.getString("emp_name"));

                        users.put(user.getEmpID(), user.getEmpName());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
