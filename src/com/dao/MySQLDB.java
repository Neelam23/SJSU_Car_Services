package com.dao;

import com.members.Admin;
import com.members.Member;
import com.members.Rider;
import com.members.Driver;
import com.members.ParkingLender;
import com.members.Supervisor;
import com.parking.ParkingInfo;
import java.sql.*;
import java.util.*;
import com.request.*;
import com.schedule.*;
import com.ride.RideInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLDB {

    Connection conn = null;
    String driver = "com.mysql.jdbc.Driver";
    String db = "sys";
    String url = "jdbc:mysql://127.0.0.1:3306/" + db;
    String user = "root";
    String pass = "123456789";

    
    public void testConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(/*"jdbc:mysql://127.0.0.1:3306/sys"*/url, user, pass);
            System.out.println("Connected to database : " + conn);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            throw (e);
        }
    }

    
    public void registerMember(String insertString) throws SQLIntegrityConstraintViolationException, SQLException {

        try {
            //System.out.println(insertString);
            PreparedStatement pst = conn.prepareStatement(insertString);
            pst.execute();

            System.out.println("\nSuccessfully registered!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nEmail already registered, please use another email.");
            throw (e);
        } catch (SQLException e) {
            System.out.println("\nError occured during registration. Please try again later");
            throw (e);
        }
    }

    
    public void registerVehicle(String insertString) throws SQLIntegrityConstraintViolationException, SQLException {
        try {
            PreparedStatement pst = conn.prepareStatement(insertString);
            pst.execute();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("\nEmail already registered, please use another email.");
            throw (e);
        } catch (SQLException e) {
            System.out.println("\nError occured during vehicle registration. Please try again later");
            e.printStackTrace();
        }
    }

    
    public void registerGarage(String insertString) {
        try {
            PreparedStatement pst = conn.prepareStatement(insertString);
            pst.execute();

        } catch (SQLException e) {
            System.out.println("\nError occured during vehicle registration. Please try again later");
            e.printStackTrace();
        }
    }

    
    public String signIn(String email, String password) {

        String val = "";
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from members where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String Category = rs.getString("category");
                System.out.println("SignIn Successful!!");
                val = Category;
            } else {
                System.out.println("\nSignIn Unsuccessful!! Try again Later.");
                val = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    
    public int adminSignIn(String email, String password) {
        int val = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from admin where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("\nSignIn Successful!!");
                val = 1;
            } else {
                System.out.println("\nSignIn Unsuccessful!! Try again later");
                val = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    
    public int checkRoute(SimpleRideRequest reqObj) {
        int value = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from routes where routeStartCity=? and routeEndCity=?");
            pst.setString(1, reqObj.pickUpLocation);
            pst.setString(2, reqObj.dropOffLocation);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                value = 1;
            } else {
                value = 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    
    public void saveRideRequest(SimpleRideRequest reqObj) {
        java.sql.Date sDate = new java.sql.Date(reqObj.dateOfRide.getTime());
        System.out.println(sDate);
        int i = 1;
        try {
            String query = " insert into riderequests (id, rider_email, starting_location, destination_location, vehicleType, date_of_Ride)" + " values ( ?,?, ?, ?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setNull(1, i);
            pst.setString(2, reqObj.email);
            pst.setString(3, reqObj.pickUpLocation);
            pst.setString(4, reqObj.dropOffLocation);
            pst.setInt(5, reqObj.vehicleType);
            //pst.setInt(6, reqObj.numOfVehicle);
            pst.setDate(6, sDate);
            pst.execute();

            System.out.println("\nRide Request Received. Your booking will be scheduled shortly. Thank You!!");

        } catch (SQLException e) {
            System.out.println("\nError occured in Ride Request. Please try again later");
            e.printStackTrace();
        }
    }

    
    public void saveParkingRequest(ParkingRequest reqObj) {
        java.sql.Date sDate = new java.sql.Date(reqObj.dateOfParking.getTime());
        System.out.println(sDate);
        int i = 1;
        try {
            String query = " insert into parkingrequests (id, driver_email, parkinglocation, parking_hours, date_of_parking)" + " values ( ?,?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setNull(1, i);
            pst.setString(2, reqObj.email);
            pst.setString(3, reqObj.parkingLocation);
            pst.setInt(4, reqObj.numOfHours);
            pst.setDate(5, sDate);
            pst.execute(); //insert in table

            System.out.println("\nParking Request Received. Your booking will be scheduled shortly. Thank You!!");

        } catch (SQLException e) {
            System.out.println("\nError occured in Parking Request. Please try again later");
            e.printStackTrace();
        }
    }

    
    public List<String> scheduleRide() {
        List<String> emails = new ArrayList<String>();
        try {

            PreparedStatement pst = conn.prepareStatement("Select * from riderequests"); //Select * from rideRequests
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	
                int riderID = rs.getInt("id");
                String riderEmail = rs.getString("rider_email");
                emails.add(riderEmail); //for payment 
                String startingLocation = rs.getString("starting_location");
                String destinationLocation = rs.getString("destination_location");
                int vehicleType = rs.getInt("vehicleType");
                String dateOfRide = rs.getString("date_of_Ride");
                
                try {
                    PreparedStatement pst1 = conn.prepareStatement("Select * from members where category = 'B' and availablity_status = 'A'");
                    ResultSet rs1 = pst1.executeQuery();
                    if (rs1.next()) {
                        String driver_email = rs1.getString("email"); //member_email instead of id
                        // System.out.println("driver_email:	" + driver_email);
                        try {
                            PreparedStatement pst2 = conn.prepareStatement("select * from routes where routeStartCity ='" + startingLocation + "' and routeEndCity = '" + destinationLocation + "' ORDER BY distance_in_miles ");
                            ResultSet rs2 = pst2.executeQuery();
                            int routeID;
                            if (rs2.next()) {
                                routeID = rs2.getInt("id");
                            }//?
                            routeID = rs2.getInt("id");
                            PreparedStatement pst3 = conn.prepareStatement("insert into scheduleride (rider_email, driver_email, starting_location, destination_location, date, route_id, scheduleStatus) values ('" + riderEmail + "','" + driver_email + "', '" + startingLocation + "', '" + destinationLocation + "', '" + dateOfRide + "', " + routeID + ", 'Scheduled')");
                            pst3.executeUpdate();
                            PreparedStatement pst4 = conn.prepareStatement("update members set availablity_status = 'NA' where email = '" + driver_email + "'");
                            pst4.executeUpdate();
                            PreparedStatement pst5 = conn.prepareStatement("delete from riderequests where id = " + riderID + "");
                            pst5.executeUpdate();
                            
                            System.out.println("\n** Your ride is scheduled for "+dateOfRide);
                            System.out.println("\n** Advance payment will be deducted and you will receive an email notification shortly. Thank You!!"); 

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }//while end
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    
    public List<String> scheduleParking() {

        List<String> emails = new ArrayList<String>();
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from parkingrequests");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String driverEmail = rs.getString("driver_email");
                emails.add(driverEmail);
                String parkingLocation = rs.getString("parkinglocation");
                int noOfHours = rs.getInt("parking_hours");
                java.util.Date dateOfParking = rs.getDate("date_of_parking");;
                //  int parkingID = rs.getInt("id");
                System.out.println(id);
                //System.out.println(driverEmail);
                try {
                    PreparedStatement pst1 = conn.prepareStatement("Select * from members where category = 'C' and availablity_status = 'A'");
                    ResultSet rs1 = pst1.executeQuery();
                    if (rs1.next()) {
                        String plEmail = rs1.getString("email");
                        try {
                            PreparedStatement pst2 = conn.prepareStatement("select * from garage where garage_city ='" + parkingLocation + "' and member_email = '" + plEmail + "' ");
                            ResultSet rs2 = pst2.executeQuery();
                            while (rs2.next()) {
                                int garageID = rs2.getInt("id");
                                int parkingSlotsTotal = rs2.getInt("parking_slots_total");
                                int parkingSlotsBooked = rs2.getInt("parking_slots_booked");
                                if (parkingSlotsTotal > parkingSlotsBooked) {
                                    parkingSlotsBooked = parkingSlotsBooked + 1;
                                    PreparedStatement pst3 = conn.prepareStatement("insert into scheduleparking ( driver_email, parkingLender_email, parking_location, date, no_of_hours, garage_id, scheduleStatus) values ('" + driverEmail + "','" + plEmail + "', '" + parkingLocation + "', '" + dateOfParking + "', '" + noOfHours + "', " + garageID + ", 'Scheduled')");
                                    pst3.execute();
                                    PreparedStatement pst5 = conn.prepareStatement("delete from parkingrequests where id = " + id + "");
                                    pst5.executeUpdate();
                                    PreparedStatement pst6 = conn.prepareStatement("update garage set parking_slots_booked= " + parkingSlotsBooked + " where id = " + garageID + "");
                                    pst6.executeUpdate();
                                    
                                    System.out.println("\n** Your parking request is scheduled for "+dateOfParking);
                                    System.out.println("\n** Advance payment will be deducted and you will receive an email notification shortly. Thank You!!"); 


                                } else if (parkingSlotsTotal == parkingSlotsBooked) {
                                    PreparedStatement pst4 = conn.prepareStatement("update members set availablity_status = 'NA' where email = '" + plEmail + "'");
                                    pst4.executeUpdate();
                                }

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }//while end
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emails;
    }

    
    public List<RideInfo> readRideSchedule() {

        List<RideInfo> rideInfoList = new ArrayList<RideInfo>();
        try {

            PreparedStatement pst = conn.prepareStatement("Select * from scheduleride WHERE scheduleStatus = 'Scheduled' ORDER BY date");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //[id],rider_email, driver_id,starting_location,destination_location,[date],[route_id],[scheduleStatus]
                String riderEmail = rs.getString("rider_email");
                String driverEmail = rs.getString("driver_email");
                String destLoc = rs.getString("destination_location");
                String srcLoc = rs.getString("starting_location");
                RideInfo rideInfo = new RideInfo(riderEmail, driverEmail, srcLoc, destLoc);
                rideInfoList.add(rideInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rideInfoList;
    }

    
    public void updateScheduleRide(RideInfo info) {
        try {
            // Update the scheduleStatus to completed
            String updateString = "update scheduleride set scheduleStatus='Completed' where rider_email='" + info.getRiderEmail() + "' and driver_email='" + info.getDriverEmail() + "'";
            PreparedStatement pst = conn.prepareStatement(updateString);
            pst.executeUpdate();

            //Update the driver status to available
            updateString = "update members set availablity_status='A' where email='" + info.getDriverEmail() + "' and category='B'";
            pst = conn.prepareStatement(updateString);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<ParkingInfo> readParkingSchedule() {

        List<ParkingInfo> parkingInfoList = new ArrayList<ParkingInfo>();
        try {

            PreparedStatement pst = conn.prepareStatement("SELECT * FROM scheduleparking WHERE ScheduleStatus = \"Scheduled\" ORDER BY date");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //[id],driver_email,parkingLender_email,parking_location,no_of_hours,[date],[garage_id],[ScheduleStatus]
                String driverEmail = rs.getString("driver_email");
                String parkingLenderEmail = rs.getString("parkingLender_email");
                String parkLoc = rs.getString("parking_location");
                int numHours = rs.getInt("no_of_hours");
                ParkingInfo parkingInfo = new ParkingInfo(driverEmail, parkingLenderEmail, parkLoc, numHours);
                parkingInfoList.add(parkingInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return parkingInfoList;
    }

    
    public void updateScheduleParkng(ParkingInfo info) {
        try {
            // Update the scheduleStatus to completed
            String updateString = "update scheduleparking set scheduleStatus='Completed' where parkingLender_email='" + info.getParkingLenderId() + "' and driver_email='" + info.getDriverId() + "'";
            PreparedStatement pst = conn.prepareStatement(updateString);
            pst.executeUpdate();

            //Update the driver status to available
            updateString = "update members set availablity_status='A' where email='" + info.getParkingLenderId() + "' and category='C'";
            pst = conn.prepareStatement(updateString);
            pst.executeUpdate();
            
            //update garage booking
            updateString = "update garage set parking_slots_booked=parking_slots_booked-1 where member_email='"+ info.getParkingLenderId()+"'";
            pst = conn.prepareStatement(updateString);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    
    public Admin getMemberList() {
        Admin adminObj = new Admin();

        try {

            PreparedStatement pst = conn.prepareStatement("Select * from members order by category");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                //[category],name,email,[password],[address],[creditCard],[debitCard]
                String category = rs.getString("category");
                String email = rs.getString("email");
                String name = rs.getString("name");
                if (category.equals("A")) {
                    adminObj.addMember(new Rider(name, email));
                } else if (category.equals("B")) {
                    adminObj.addMember(new Driver(name, email));
                } else {
                    adminObj.addMember(new ParkingLender(name, email));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return adminObj;
    }
  
    
    public void getscheduleparking() {
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from scheduleparking order by date");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String driver_email = rs.getString("driver_email");
                String parkingLender_email = rs.getString("parkingLender_email");
                String parkingLocation = rs.getString("parking_location");
                String numHours = rs.getString("no_of_hours");
                String date = rs.getString("date");
                String scheduleStatus = rs.getString("ScheduleStatus");
                // printing all entries from Schedule table                              
                System.out.println(driver_email + "   " + parkingLender_email + "          " + parkingLocation + "                  " + numHours + "            " + date + "         " + scheduleStatus);
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void getscheduleRide() {
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from scheduleride order by date");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String rider_email = rs.getString("rider_email");
                String driver_email = rs.getString("driver_email");
                String StartingLocation = rs.getString("starting_location");
                String destination = rs.getString("destination_location");
                String date = rs.getString("date");
                String route = rs.getString("route_id");
                String scheduleStatus = rs.getString("scheduleStatus");
                // printing all entries from Schedule table                              
                System.out.println(rider_email + "   " + driver_email + "          " + StartingLocation + "                  " + destination + "            " + date + "         "+"   "+ route +"   " + scheduleStatus);
                System.out.println();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String checkPaymentType(String email) {
        String paymentTY = "debit";

//        try{
//        	PreparedStatement pst = conn.prepareStatement("Select * from members where email=?");
//            pst.setString(1, email);
//        	}catch(SQLException ex){
//        		System.out.println("\n");
//        	}
        return paymentTY;

    }


    public void cancelRequest(String email, MySQLDB db, String[] loc) {

        String location[] = new String[2];
        location = loc;

        if (location[1] == null) {
            String parkinglocation = location[0];
            //driver 
            try{
	            	PreparedStatement pst = conn.prepareStatement("Select * from parkingrequests where driver_email=?");
	                pst.setString(1, email);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()){
	                	try{
	        	            PreparedStatement pst5 = conn.prepareStatement("delete from parkingrequests where driver_email = ? and parkinglocation= ? ");
	        				pst5.setString(1,email);
	        				pst5.setString(2,parkinglocation);
	        				pst5.executeUpdate();
	        				System.out.println("\nParking Request Cancelled Successfully!");
	                	}catch (SQLException ex) {
	                    	System.out.println("\nError Occurred");
	                        Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
	                		}
	                	
	            	}else{
	            		System.out.println("\n***No unscheduled parking request found. Please contact our customer care for cancelling scheduled requests!***");
	            	}
	        	}catch(SQLException ex){
	        		System.out.println("\nParking Request Cancelled Successfully");
	        		}
        } else {
        	//rider
        	String starting_location = location[0];
            String destination_location = location[1];
	         
            try{
	            	PreparedStatement pst = conn.prepareStatement("Select * from riderequests where rider_email=?");
	                pst.setString(1, email);
	                ResultSet rs = pst.executeQuery();
	                if(rs.next()){
	                	try{
	        				PreparedStatement pst5 = conn.prepareStatement("delete from riderequests where rider_email = ? and starting_location= ? and destination_location=?");
	        				pst5.setString(1,email);
	        				pst5.setString(2,starting_location);
	        				pst5.setString(3,destination_location);
	        				pst5.executeUpdate();
	        				System.out.println("\nRide Request Cancelled Successfully");
	                	   }catch (SQLException ex) {
	        		              Logger.getLogger(MySQLDB.class.getName()).log(Level.SEVERE, null, ex);
	        		          }
	                }else{
	                	System.out.println("\n ***No unscheduled ride request found. Please contact our customer care for cancelling scheduled requests!***");
	                }
            	}catch(SQLException ex){
            		
            	}
            
        }
    }  

}
