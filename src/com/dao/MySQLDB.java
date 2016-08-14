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
    
    
    
    public void signIn(String email, String password){
    	try{
    		PreparedStatement pst = conn.prepareStatement("Select * from members where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
            	String Category= rs.getString("category");
                System.out.println("Signup Successful!!");
                System.out.println("Please select from following option:");
                System.out.println(Category);
                if(Category.equals("A")){
                	System.out.println("1. Request a ride");
                } else if(Category.equals("B")){
                	System.out.println("1. Request a Parking");
                	}
                
                System.out.println("2. Exit");
                Scanner scanner = new Scanner(System.in);
                int selection = scanner.nextInt();
	        	switch (selection) {
	            case 1:  
	            	   RequestManager reqManager = new RequestManager(email);
		       	       if(Category.equals("A")){
		       	    	reqManager.manageRide();
		       	       }else if(Category.equals("B")){
		       	    	reqManager.manageParking(); 
		       	       }
		                break;
	             case 2:  
		        	 System.out.print("Enter your Email-id:		");
		        	 
	                 break;
	             default:  
	 	    		 System.out.print("Wrong Choice, please try again");
	 	             break;
	            } 
         	}
            else {
                System.out.println("Signup Unsuccessful");
            }
    	}catch (SQLException e) {
            e.printStackTrace();
        }
    	
    }
    
    
    
    
    public void sendRideRequest(RideRequest reqObj){
    	System.out.println(reqObj.pickUpLocation);
    	
    }
  
  
}