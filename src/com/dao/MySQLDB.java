package com.dao;
import java.sql.*;
import java.util.*;
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
  
  
  public void registerMember(String insertString) {
      //int i = 1;
      //String j;
        try {
            //String query = " insert into members (id,category,name,email,password,address,creditCard,debitCard,availablity_status)" + " values ( ?,?, ?, ?, ?,?,?)";
            //INSERT INTO sys.members(id,category,name,email,password,address,creditCard,debitCard,availablity_status) VALUES (3,'A','Test','test@sjsu.edu','password',null,565657765,null,null);
            PreparedStatement pst = conn.prepareStatement(insertString);
            /*pst.setInt(1, i);
            pst.setString(2, rider.Category);
            pst.setString(3, rider.name);
            pst.setString(4, rider.email);
            pst.setString(5, rider.password);
            pst.setInt(6, rider.creditCard);
            pst.setNull(7, rider.debitCard);
            pst.setNull(8, 0);*/
            pst.execute();

            System.out.println("You have been successfully registered");

        } catch (SQLException e) {
            System.out.println("Error occured during registration. Please try again later");
            e.printStackTrace();
        }
    }

  
  public void registerVehicle(String insertString) {
        try {
            PreparedStatement pst = conn.prepareStatement(insertString);
            pst.execute();

        } catch (SQLException e) {
            System.out.println("Error occured during vehicle registration. Please try again later");
            e.printStackTrace();
        }
    }
    
  
  public void registerGarage(String insertString) {
        try {
            PreparedStatement pst = conn.prepareStatement(insertString);
            pst.execute();

        } catch (SQLException e) {
            System.out.println("Error occured during vehicle registration. Please try again later");
            e.printStackTrace();
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
    		String query = " insert into riderequests (id, rider_email, starting_location, destination_location, vehicleType, date_of_Ride)"+ " values ( ?,?, ?, ?,?,?)";
    		PreparedStatement pst = conn.prepareStatement(query);
    		pst.setNull(1, i);
            pst.setString(2, reqObj.email);
            pst.setString(3, reqObj.pickUpLocation);
            pst.setString(4, reqObj.dropOffLocation);
            pst.setInt(5, reqObj.vehicleType);
            //pst.setInt(6, reqObj.numOfVehicle);
            pst.setDate(7, sDate);
            pst.execute();
            
            System.out.println("Ride Request Received. Your booking will be scheduled shortly. Thank You!!");
            	
    	    }catch (SQLException e) {
    	    	System.out.println("Error occured in Ride Request. Please try again later");
    	    	e.printStackTrace();  
    	    }
    }
   
   
   public void saveParkingRequest(ParkingRequest reqObj){
   	java.sql.Date sDate = new java.sql.Date(reqObj.dateOfParking.getTime());
   	System.out.println(sDate);
   	int i=1;
   	try{
   		String query = " insert into parkingrequests (id, driver_email, parkinglocation, parking_hours, date_of_parking)"+ " values ( ?,?, ?, ?, ?)";
   		PreparedStatement pst = conn.prepareStatement(query);
   		pst.setNull(1, i);
        pst.setString(2, reqObj.email);
        pst.setString(3, reqObj.parkingLocation);
        pst.setInt(4, reqObj.numOfHours);
        pst.setDate(5, sDate);
        pst.execute(); //insert in table
           
        System.out.println("Parking Request Received. Your booking will be scheduled shortly. Thank You!!");
           	
   	    }catch (SQLException e) {
   	    	System.out.println("Error occured in Parking Request. Please try again later");
   	    	e.printStackTrace();  
   	    }
   }
	
   public List<String> scheduleRide(){
	   List<String> emails= new ArrayList<String>();
     	try{
                
            PreparedStatement pst = conn.prepareStatement("Select * from riderequests"); //Select * from rideRequests
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int riderID = rs.getInt("id");
                System.out.println(riderID);
                String riderEmail= rs.getString("rider_email");
                System.out.println("riderEmail:	"+riderEmail);
                emails.add(riderEmail); //for payment 
//                int clientID = rs.getInt("client_id");
//                String startTime = rs.getString("start_time");
//                String endTime = rs.getString("end_time");
                String startingLocation = rs.getString("starting_location");
                System.out.println("starting loc:	" + startingLocation);
                String destinationLocation = rs.getString("destination_location");
                System.out.println("destinationloc:	" + destinationLocation); 
                int vehicleType = rs.getInt("vehicleType");
                System.out.println("vehicletype:	" + vehicleType);
//              int noOfVehicle = rs.getInt("no_of_vehicle");
//                System.out.println("noofvehicle" + noOfVehicle);
                String dateOfRide = rs.getString("date_of_Ride");
                System.out.println("dateofride:	" + dateOfRide);
                  try{
                        PreparedStatement pst1 = conn.prepareStatement("Select * from members where category = 'B' and availablity_status = 'A'");
                        ResultSet rs1 = pst1.executeQuery();
                        if (rs1.next()) {
                            int driverID= rs1.getInt("id");
                            System.out.println("driver id:	" + driverID);
                            try{
                            PreparedStatement pst2 = conn.prepareStatement("select * from routes where routeStartCity ='"+startingLocation+"' and routeEndCity = '"+destinationLocation+"' ORDER BY routeCost ");
                            ResultSet rs2 = pst2.executeQuery();
                            int routeID;
                            if (rs2.next()){
                                routeID= rs2.getInt("id");
                            }
                            routeID= rs2.getInt("id");
                            System.out.println("routeid" + routeID);
                            PreparedStatement pst3 = conn.prepareStatement("insert into scheduleride (id, rider_email, driver_id, starting_location, destination_location, date, route_id, scheduleStatus) values (111, '"+riderEmail+"',"+driverID+", '"+startingLocation+"', '"+destinationLocation+"', '"+dateOfRide+"', "+routeID+", 'Scheduled')");
                            pst3.executeUpdate();
                            PreparedStatement pst4 = conn.prepareStatement("update members set availablity_status = 'NA' where id = "+driverID+"");
                            pst4.executeUpdate();
                            PreparedStatement pst5 = conn.prepareStatement("delete from riderequests where id = "+riderID+""); 
                            pst5.executeUpdate();
                
                            }catch (SQLException e) {
                             e.printStackTrace();
                        }                 
                        }
                }catch (SQLException e) {
                e.printStackTrace();             
                }
            }//while end
        }catch (SQLException e) {
         e.printStackTrace();
        }
      
     	return emails;
    }
	
   
   
//   public List<String> scheduleRide(){
//	   List<String> emails= new ArrayList<String>();
//    	try{
//		   PreparedStatement pst = conn.prepareStatement("Select * from riderequests");
//           ResultSet rs = pst.executeQuery();
//           System.out.println("reached here"+rs);
//           while (rs.next()) {
//               
//               int ID= rs.getInt("id");
//               String email= rs.getString("rider_email"); //neelam
//               emails.add(email);
////               int clientID = rs.getInt("client_id");
////               String startTime = rs.getString("start_time");
////               String endTime = rs.getString("end_time");
//               String startingLocation = rs.getString("starting_location");
//               String destinationLocation = rs.getString("destination_location");  
//               try{
//                   PreparedStatement pst1 = conn.prepareStatement("Select * from members where availablity_status = 'A' and category= 'B'");
//                   ResultSet rs1 = pst1.executeQuery();
//                   if (rs1.next()) {
//                       int driverID= rs1.getInt("id");
////	                       try{
////	                       PreparedStatement pst2 = conn.prepareStatement("update schedule set driver_id = "+driverID+", ride_status = 'C' where id = "+ID+"");
////	                       pst2.executeQuery();
////	                       PreparedStatement pst3 = conn.prepareStatement("update driver set status = 'A' where driver_id = "+driverID+"");
////	                       pst3.executeQuery();
////	                       }catch (SQLException e) {
////	                        e.printStackTrace();
////	                       }                 
//                   }
//               }catch (SQLException e) {
//               e.printStackTrace();             
//               }
//	       }//while end
//	   }catch (SQLException e) {
//	       e.printStackTrace();  
//	   }
//	   return emails;
//	}
//
//   
//   
	public void scheduleParking(){
//	     	try{
//			PreparedStatement pst = conn.prepareStatement("Select * from schedule where parking_status is NULL");
//	           ResultSet rs = pst.executeQuery();
//	           while (rs.next()) {
//	               
//	               int ID= rs.getInt("id");
//	               String destinationLocation = rs.getString("destination_location"); 
//	               try{
//	               PreparedStatement pst1 = conn.prepareStatement("Select * from parking_lender where status = 'A' and location = "+destinationLocation+"");
//	               ResultSet rs1 = pst1.executeQuery();
//	               if (rs1.next()) {                 
//	                   int plID= rs1.getInt("pl_id");
//	                   PreparedStatement pst2 = conn.prepareStatement("update schedule set pl_id = "+plID+", parking_status= 'C' where id = "+ID+"");
//	                   pst2.executeQuery();
//	                   PreparedStatement pst3 = conn.prepareStatement("update parking_lender set status = 'NA' where pl_id = "+plID+"");
//	                   pst3.executeQuery();
//	                   
//	                }
//	               }catch (SQLException e) {
//	               e.printStackTrace();
//	           
//	           }
//	           }
//	   }catch (SQLException e) {
//	       e.printStackTrace();
//	   }
	}


	public String checkPaymentType(String email){
		String paymentTY= "debit";
		
		//select debit from memebrs where email ==
		
		
		return paymentTY;
		 
	}
  
  
}