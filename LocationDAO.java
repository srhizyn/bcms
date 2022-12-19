/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.LocationBean;
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
public class LocationDAO {
    
     private Connection currentCon = null;

 public LocationDAO() {
  currentCon = ConnectionManager.getConnection();
 }

    ResultSet rs = null;
    
    //Region: Location
public void addLocation(LocationBean location) throws SQLException {
        try {
    currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
           ("insert into ams_locationmaster(date_create,user_create,location_name,is_active) "
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
   preparedStatement.setString(3, location.getLocationName());
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
 
 public void deleteLocation(double locationID) throws SQLException {
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("delete from ams_projectmaster where project_id=?");
   // Parameters start with 1
   preparedStatement.setDouble(1, locationID);
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
 
 public void updateLocation(LocationBean location) throws ParseException, SQLException {
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
        ("update ams_locationmaster set date_update=?, user_update=?, location_name=? where location_id=?");
   // Parameters start with 1   
   preparedStatement.setString(1, newDateStringNow);
   preparedStatement.setString(2, "munawwarah.mahmod");
   preparedStatement.setString(3, location.getLocationName());   
   preparedStatement.setDouble(4, location.getLocationID());
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

 public List<LocationBean> getAllLocation(int jtStartIndex, int jtPageSize, String jtSorting) throws SQLException {
 List<LocationBean> locations = new ArrayList<LocationBean>();
  String startIndex=Integer.toString(jtStartIndex);
  String pageSize=Integer.toString(jtPageSize);
  String defaultSorting = jtSorting;

  String query="select location_id, location_name from ams_locationmaster ORDER BY "+defaultSorting+" limit "+startIndex+","+pageSize;;
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        (query);
   ResultSet rs = preparedStatement.executeQuery();
   
   while (rs.next()) {
    LocationBean location = new LocationBean();
    location.setLocationID(rs.getDouble("location_id"));
    location.setLocationName(rs.getString("location_name"));
    
    locations.add(location);
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

  return locations;
 }
 
 public LocationBean getLocationById(int locationID) throws SQLException {
  LocationBean location = new LocationBean();
  try {
   currentCon = ConnectionManager.getConnection();
   PreparedStatement preparedStatement = currentCon.prepareStatement
        ("select location_name from ams_locationmaster where location_id=?");
   preparedStatement.setInt(1, locationID);
   ResultSet rs = preparedStatement.executeQuery();
   
   if (rs.next()) {
    location.setLocationName(rs.getString("location_name"));
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
  return location;
 }
 
public int getLocationCount() throws SQLException{
 int count=0;
 try {
     currentCon = ConnectionManager.getConnection();
                Statement statement =                     
                  currentCon.createStatement();
  ResultSet rs = 
                  statement.executeQuery("select count(*) as count from ams_locationmaster");
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
