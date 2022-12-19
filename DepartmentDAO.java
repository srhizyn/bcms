/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.DepartmentBean;
import Utility.ConnectionManager;
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

/**
 *
 * @author munawwarah.mahmod
 */
public class DepartmentDAO {
    
     private Connection currentCon = null;

 public DepartmentDAO() {
  currentCon = ConnectionManager.getConnection();
 }

    ResultSet rs = null;
    
    //Region: Location
public void addDepartment(DepartmentBean department) throws SQLException {
        try {
    currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
           ("insert into ams.ams_deptmaster(date_create,user_create,dept_code, dept_name,is_active) "
                + "values (?,?,?,?,?)");
   // Parameters start with 1
   try{

   final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

   Date now = new Date();
   String newDateStringNow;
   String dateString = now.toString();    
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringNow = sdf3.format(d3);
   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, "munawwarah.mahmod");
   preparedStatement.setString(3, department.getDepartmentCode());
   preparedStatement.setString(4, department.getDepartmentName());
   preparedStatement.setString(5, "Y");
 
   preparedStatement.executeUpdate();
   }
   catch(ParseException pe) {
        pe.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
  }finally {
            if (currentCon != null && !currentCon.isClosed()) {
                try {
                    currentCon.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 }
 
 public void deleteDepartment(double departmentID) throws SQLException {
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("delete from ams.ams_deptmaster where dept_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, departmentID);
   preparedStatement.executeUpdate();
   
  } catch (SQLException e) {
   e.printStackTrace();
  }finally {
            if (currentCon != null && !currentCon.isClosed()) {
                try {
                    currentCon.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 }
 
 public void updateDepartment(DepartmentBean department) throws ParseException, SQLException {
  try {
    currentCon = ConnectionManager.getConnection();  
    final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

   Date now = new Date();
   String newDateStringNow;
   String dateString = now.toString();    
   SimpleDateFormat sdf3 =
            new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
   Date d3 = sdf3.parse(dateString);
   sdf3.applyPattern(FORMAT_DATETIME);
   newDateStringNow = sdf3.format(d3);
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("update ams.ams_deptmaster set date_update=?, user_update=?, dept_code=?, dept_name=? where dept_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, "munawwarah.mahmod");
   preparedStatement.setString(3, department.getDepartmentCode());   
   preparedStatement.setString(4, department.getDepartmentName());
   preparedStatement.setDouble(5, department.getDepartmentID());
   preparedStatement.executeUpdate();

  } catch (SQLException e) {
   e.printStackTrace();
  }finally {
            if (currentCon != null && !currentCon.isClosed()) {
                try {
                    currentCon.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 }

 public List<DepartmentBean> getAllDepartment(int jtStartIndex, int jtPageSize, String jtSorting) throws SQLException {
 List<DepartmentBean> departments = new ArrayList<DepartmentBean>();
  String startIndex=Integer.toString(jtStartIndex);
  String pageSize=Integer.toString(jtPageSize);
  String defaultSorting = jtSorting;

  String query="select dept_id, dept_code, dept_name from ams.ams_deptmaster ORDER BY "+defaultSorting+" limit "+startIndex+","+pageSize;;
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        (query);
   ResultSet rs = preparedStatement.executeQuery();
   
   while (rs.next()) {
    DepartmentBean department = new DepartmentBean();
    department.setDepartmentID(rs.getDouble("dept_id"));
    department.setDepartmentCode(rs.getString("dept_code"));
    department.setDepartmentName(rs.getString("dept_name"));
    
    departments.add(department);
   }
  } catch (SQLException e) {
   e.printStackTrace();
  }finally {
            if (currentCon != null && !currentCon.isClosed()) {
                try {
                    currentCon.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

  return departments;
 }
 
 public DepartmentBean getDepartmentById(int departmentID) throws SQLException {
  DepartmentBean department = new DepartmentBean();
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("select dept_name from ams.ams_deptmaster where dept_id=?");
   preparedStatement.setInt(1, departmentID);
   ResultSet rs = preparedStatement.executeQuery();
   
   if (rs.next()) {
    department.setDepartmentName(rs.getString("dept_name"));
   }
  
  } catch (SQLException e) {
   e.printStackTrace();
  }finally {
            if (currentCon != null && !currentCon.isClosed()) {
                try {
                    currentCon.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
  return department;
 }
 
public int getDepartmentCount() throws SQLException{
 int count=0;
 try {
     currentCon = ConnectionManager.getConnection();
                Statement statement =                     
                  currentCon.createStatement();
  ResultSet rs = 
                  statement.executeQuery("select count(*) as count from ams.ams_deptmaster");
   while (rs.next()) {
   count=rs.getInt("count");
  }
 } catch (SQLException e) {
  e.printStackTrace();
 }finally {
            if (currentCon != null && !currentCon.isClosed()) {
                try {
                    currentCon.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 return count;
}  
}
