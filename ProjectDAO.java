/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.ProjectBean;
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
public class ProjectDAO {
    
     private Connection currentCon = null;

 public ProjectDAO() {
  currentCon = ConnectionManager.getConnection();
 }

    ResultSet rs = null;
    
    //Region: Project
public void addProject(ProjectBean project) throws SQLException {
        try {
    currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
           ("insert into ams_projectmaster(date_create,user_create,project_name,is_active) "
                + "values (?,?,?,?)");
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
   preparedStatement.setString(3, project.getProjectName());
   preparedStatement.setString(4, "Y");
 
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
 
 public void deleteProject(double projectID) throws SQLException {
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("delete from ams_projectmaster where project_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, projectID);
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
 
 public void updateProject(ProjectBean project) throws ParseException, SQLException {
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
        ("update ams_projectmaster set date_update=?, user_update=?, project_name=? where project_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, "munawwarah.mahmod");
   preparedStatement.setString(3, project.getProjectName());   
   preparedStatement.setDouble(4, project.getProjectID());
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

 public List<ProjectBean> getAllProject(int jtStartIndex, int jtPageSize, String jtSorting) throws SQLException {
 List<ProjectBean> projects = new ArrayList<ProjectBean>();
  String startIndex=Integer.toString(jtStartIndex);
  String pageSize=Integer.toString(jtPageSize);
  String defaultSorting = jtSorting;

  String query="select project_id, project_name from ams_projectmaster ORDER BY "+defaultSorting+" limit "+startIndex+","+pageSize;;
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        (query);
   ResultSet rs = preparedStatement.executeQuery();
   
   while (rs.next()) {
    ProjectBean project = new ProjectBean();
    project.setProjectID(rs.getDouble("project_id"));
    project.setProjectName(rs.getString("project_name"));
    
    projects.add(project);
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

  return projects;
 }
 
 public ProjectBean getProjectById(int projectID) throws SQLException {
  ProjectBean project = new ProjectBean();
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("select project_name from ams_projectmaster where project_id=?");
   preparedStatement.setInt(1, projectID);
   ResultSet rs = preparedStatement.executeQuery();
   
   if (rs.next()) {
    project.setProjectName(rs.getString("project_name"));
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
  return project;
 }
 
public int getProjectCount() throws SQLException{
 int count=0;
 try {
     currentCon = ConnectionManager.getConnection();
                Statement statement =                     
                  currentCon.createStatement();
  ResultSet rs = 
                  statement.executeQuery("select count(*) as count from ams_projectmaster");
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
