package com.client;

import com.schedule.*;
import java.sql.Connection;
import com.dao.*;
import com.members.Driver;
import com.members.Member;
import com.members.ParkingLender;
import com.members.Rider;
import com.members.Supervisor;
import com.parking.*;
import com.request.RequestManager;
import com.ride.*;
import com.parking.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    
    private static MySQLDB db = null;
    private static Scanner scanner = null;
    
    private static void connectDB()
    {
        db = new MySQLDB();
        try {
            db.testConnection();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
            System.exit(-1);
        }
        catch (SQLException e) {
            System.out.println("Failed to connect to database. Please try again later");
            System.exit(-1);
        }
    }
    
    
    public static void main(String[] args) {

        String email;
        String password;
        int selection = 0;
        
        // Connect to database
        connectDB();
        
        // Print banner information
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println("**		WELCOME TO SJSU CAR SERVICES           **");
        System.out.println("*********************************************************");
        System.out.println("*********************************************************");
        System.out.println();
        
        while (selection != 4)
        {
        // Get user input
        System.out.println("Please Select from Following options:\n" + "1. Member SignUp" + "\n2. Member SignIn" + "\n3. Admin SignIn" + "\n4. Exit");

        scanner = new Scanner(System.in);
        selection = scanner.nextInt();
        switch (selection) 
        {
            case 1:
                registerMember();
                break;

            case 2:
            	memberSignInOptions();
                break;

            case 3:
                System.out.print("Enter your Email-id:	");
                email = scanner.next();
                System.out.print("Enter your Password:	");
                password = scanner.next();

                int val = db.adminSignIn(email, password);
                if (val == 1) {
                    handleAdminOptions();
                }
                else {
                    System.out.println("Login Error");
                }
                break;
            case 4:
                System.out.print("Thank you for using the application.");
                break;

            default:
                System.out.print("Wrong Choice, please try again");
                break;
            }
        }
    }
    
 
    
    private static void registerMember()
    {
        System.out.println("\nPlease select one of the following options:\n" + "1. Rider" + "\n2. Driver" + "\n3.Parking Lender");
        int memberType;
        Member memberObj = null;

        do {
            memberType = scanner.nextInt();

            if (memberType == 1) // Rider
            {
                memberObj = new Rider(scanner, db);
            } else if (memberType == 2) // Driver
            {
                memberObj = new Driver(scanner, db);
            } else if (memberType == 3) // Parking Lender
            {
                memberObj = new ParkingLender(scanner, db);
            } else // Invalid input
            {
                System.out.println("Invalid selection. Please select one of the following options:\n" + "1. Rider" + "\n2. Driver" + "\n3. Parking Lender");
            }
        } while (memberType < 1 || memberType > 3);

        // Sign up member
        try {
            memberObj.signUp();
        } 
        catch (SQLIntegrityConstraintViolationException e) {
            registerMember();
        } 
        catch (SQLException e) {
            System.exit(-1);
        }
    }
    
    private static void memberSignInOptions(){
    	
    	System.out.print("Enter your Email-id:	");
        String email = scanner.next();
        System.out.print("Enter your Password:	");
        String password = scanner.next();
        
        String Category = db.signIn(email, password);
        if (Category.length() != 0) {
            System.out.println("\nPlease select from following option:");
            if (Category.equals("A")) {

                System.out.println("1. Request a ride");
                System.out.println("2. Request ride Cancellation");
            } else if (Category.equals("B")) {
                System.out.println("1. Request a Parking");
                System.out.println("2. Request parking Cancellation ");
            }

            System.out.println("3. Exit");
        }

        RequestManager reqManager = new RequestManager(email, db);
        int selection2 = scanner.nextInt();
        switch (selection2) {
            case 1:
                if (Category.equals("A")) {
                	System.out.println("Calling manage ride!");
                    reqManager.manageRide();   
                } else if (Category.equals("B")) {
                	System.out.println("Calling manage parking!");
                    reqManager.manageParking();
                }
                break;
            case 2:
                if (Category.equals("A")) {
                    //reqManager.manageRide(); rules related stuff
                } else if (Category.equals("B")) {
                    //reqManager.manageParking();  //rules related stuff
                }
                break;
            case 3:
                System.out.print("Exited!");
                break;
            default:
                System.out.print("Wrong Choice, please try again");
                break;
        }
    }

    
    private static void handleAdminOptions()
    {
        int selection = 0;
        do
        {
            System.out.println("\nPlease Select from following Options");
            System.out.println("1. Start Schedule Ride");
            System.out.println("2. Start Schedule Parking");
            System.out.println("3. Dispatch Ride Request");
            System.out.println("4. Start Parking");
            System.out.println("5. Display members");
            System.out.println("6. Exit");

            selection = scanner.nextInt();
            SchedulingContext schedulingContext;

            switch (selection) {
                case 1:
                    System.out.println();
                    System.out.println("Start Schedule Ride!");
                    //	manageSchedule();
                    schedulingContext = new SchedulingContext(new ScheduleRide());
                    schedulingContext.executeStrategy(db);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Start Schedule Parking");
                    schedulingContext = new SchedulingContext(new ScheduleParking());
                    schedulingContext.executeStrategy(db);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Dispatch Ride!");
                    readRideSchedules();
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Start Parking!");
                    readParkingSchedule();
                    //ParkingInfo parkingInfo = new ParkingInfo();
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Member list:");
                    displayMemberList();
                    break;
                case 6:
                    System.out.println();
                    break;
                default:
                    System.out.print("Wrong Choice, please try again");
                    break;
            }
        }while (selection != 6);
    }
    
    private static void displayMemberList()
    {
        Supervisor supervisor = db.getMemberList();
        supervisor.display();
        System.out.println();
    }
    
    private static void readRideSchedules(){
        
        // Get Ride information from database
        List<RideInfo> rideInfoList = db.readRideSchedule();
        int i=0, size = rideInfoList.size();
        Thread threadPool[] = new Thread[size];
        while (i < size)
        {
           threadPool[i] = new RideDispatch(rideInfoList.get(i), i+1);
           i++;
        }
        
        for ( i= 0; i < size; i++) 
        {
            try {
                threadPool[i].join(); //todo add catch exception
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println();
    }
    
    private static void readParkingSchedule(){
        
        // Get parking information from database
       List<ParkingInfo> parkingInfoList = db.readParkingSchedule();
        int i=0, size = parkingInfoList.size();
        Thread threadPool[] = new Thread[size];
        while (i < size)
        {
           threadPool[i] = new ParkingInfoDispatch(parkingInfoList.get(i), i+1);
           i++;
        }
        
        for ( i= 0; i < size; i++) 
        {
            try {
                threadPool[i].join(); //todo add catch exception
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println();
    }
}
