package com.dao;
import java.sql.*;
import java.util.Scanner;
import com.request.*;
 
public class MySQLDB {
	
	Connection conn = null; 
    String driver   = "com.mysql.jdbc.Driver";
    String db       = "sys";
    String url      = "jdbc:mysql://127.0.0.1:3306" + db;
    String user     = "root";
    String pass     = "Psword@1";
 
    
   
  public void testConnection() throws ClassNotFoundException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys",user,pass);
            System.out.println("Connected to database : " + conn);
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
    }
  
  
  
  
  public void signUp(){
    	try{
    		PreparedStatement pst = conn.prepareStatement("INSERT INTO members (id,category, name, email, password, address, creditCard, debitCard, travelCard) VALUES(2 ,'A','Neelam', 'neelam@sjsu.edu', 'sdscdc' ,'','sdfg','dgsag','gfd');");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Signup Successful");
            } 
            else {
                System.out.println("Signup Unsuccessful");
            }
    	  }catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }
    
    
    
  
  
  public String signIn(String email, String password){
    	
    	String val="";
    	try{
    		PreparedStatement pst = conn.prepareStatement("Select * from members where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            
            	String Category= rs.getString("category");
                System.out.println("SignIn Successful!!");
                val= Category;
         	}
            else {
                System.out.println("SignIn Unsuccessful!! Try again Later.");
                val= "";
            }
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    	return val;
    }
    
  
  
  public int adminSignIn(String email, String password){
  	int val =0;
  	try{
  		PreparedStatement pst = conn.prepareStatement("Select * from admin where email=? and password=?");
          pst.setString(1, email);
          pst.setString(2, password);
          ResultSet rs = pst.executeQuery();
          if (rs.next()) {
        	  System.out.println("SignIn Successful!!");
              val=1;
       	 }else {
              System.out.println("SignIn Unsuccessful!! Try again later");
              val= 0;
               }
  	  }catch (SQLException e) {
          e.printStackTrace();
          }
  	return val;
  }
 
 
   public int checkRoute(RideRequest reqObj){
	   int value =0;
	   try{
    		PreparedStatement pst = conn.prepareStatement("Select * from routes where routeStartCity=? and routeEndCity=?");
    		pst.setString(1, reqObj.pickUpLocation);
            pst.setString(2, reqObj.dropOffLocation);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
            	value= 1;
             }else
            	 value=0;
    	    }catch (SQLException e) {
		        e.printStackTrace();  
    	    }
    	return value;
    }
 
    
    
   public void saveRideRequest(RideRequest reqObj){
    	java.sql.Date sDate = new java.sql.Date(reqObj.dateOfRide.getTime());
    	System.out.println(sDate);
    	int i=1;
    	try{
    		String query = " insert into riderequests (id, rider_email, starting_location, destination_location, vehicleType, no_of_vehicle, date_of_Ride)"+ " values ( ?,?, ?, ?, ?,?,?)";
    		PreparedStatement pst = conn.prepareStatement(query);
    		pst.setNull(1, i);
            pst.setString(2, reqObj.email);
            pst.setString(3, reqObj.pickUpLocation);
            pst.setString(4, reqObj.dropOffLocation);
            pst.setInt(5, reqObj.vehicleType);
            pst.setInt(6, reqObj.numOfVehicle);
            pst.setDate(7, sDate);
            pst.execute();
            
            System.out.println("Ride Request Received. Your booking will be scheduled shortly. Thank You!!");
            	
    	    }catch (SQLException e) {
    	    	System.out.println("Error occured in Ride Request. Please try again later");
    	    	e.printStackTrace();  
    	    }
    }
   
   
   public void saveParkingRequest(ParkingRequest reqObj){
   //	java.sql.Date sDate = new java.sql.Date(reqObj.dateOfRide.getTime());
   //	System.out.println(sDate);
   	int i=1;
   	try{
   		String query = " insert into riderequests (id, rider_email, starting_location, destination_location, vehicleType, no_of_vehicle, date_of_Ride)"+ " values ( ?,?, ?, ?, ?,?,?)";
   		PreparedStatement pst = conn.prepareStatement(query);
   		pst.setNull(1, i);
//           pst.setString(2, reqObj.email);
//           pst.setString(3, reqObj.pickUpLocation);
//           pst.setString(4, reqObj.dropOffLocation);
//           pst.setInt(5, reqObj.vehicleType);
//           pst.setInt(6, reqObj.numOfVehicle);
//           pst.setDate(7, sDate);
//           pst.execute();
           
           System.out.println("Ride Request Received. Your booking will be scheduled shortly. Thank You!!");
           	
   	    }catch (SQLException e) {
   	    	System.out.println("Error occured in Ride Request. Please try again later");
   	    	e.printStackTrace();  
   	    }
   }
  
  
}