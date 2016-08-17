package com.client;
import com.schedule.*;
import java.sql.Connection;
import com.dao.*;
import com.members.Driver;
import com.members.Member;
import com.members.ParkingLender;
import com.members.Rider;
import com.parking.*;
import com.request.RequestManager;
import com.ride.*;

import java.util.Scanner; 

public class Client {

	
	public static void main(String[] args){
		
		String email;
		String password;
		MySQLDB db= new MySQLDB();
		try{
			db.testConnection();
		}catch(ClassNotFoundException e) {	
            System.out.println("ClassNotFoundException: "+e.getMessage());
            }
		
		System.out.println("*********************************************************");
		System.out.println("*********************************************************");
		System.out.println("**		WELCOME TO SJSU CAR SERVICES           **");
		System.out.println("*********************************************************");
		System.out.println("*********************************************************");
		System.out.println();
		System.out.println("Please Select from Following options:\n"+"1. Member SignUp"+"\n2. Member SignIn"+"\n3. Admin SignIn"+"\n4. Exit");
		
		Scanner scanner = new Scanner(System.in);
		int selection = scanner.nextInt();
		switch (selection) {
        case 1:  
        	    
	        	System.out.println("Please select one of the following options:\n" + "1. Rider" + "\n2. Driver" + "\n3.Parking Lender");
	            int memberType;
	            Member memberObj = null;
	            
	            do {
	                memberType = scanner.nextInt();
	
	                if (memberType == 1) // Rider
	                {
	                    memberObj = new Rider(scanner, db); 
	                }
	                else if (memberType == 2) // Driver
	                {
	                    memberObj = new Driver(scanner, db);
	                }
	                else if (memberType == 3) // Parking Lender
	                {
	                    memberObj = new ParkingLender(scanner, db);
	                }
	                else // Invalid input
	                {
	                    System.out.println("Invalid selection. Please select one of the following options:\n" + "1. Rider" + "\n2. Driver" + "\n3.Parking Lender");
	                }
	            } while (memberType<1 || memberType>3);
	            //call member classes
	            memberObj.signUp();
	            //db.signUp();
	            //db.signUp(memberObj);
                 break;
                 
        case 2:  
        	
	        	System.out.print("Enter your Email-id:	");
	            email = scanner.next();
	            System.out.print("Enter your Password:	");
	            password = scanner.next();
	            
	            String Category= db.signIn(email, password);
	            if(Category.length()!= 0){
	            	System.out.println("\nPlease select from following option:");
	            	 if(Category.equals("A")){
	                 	
	                 	System.out.println("1. Request a ride");
	                 	System.out.println("2. Request ride Cancellation");
	                    } else if(Category.equals("B")){
	                 	   System.out.println("1. Request a Parking");
	                 	   System.out.println("2. Request parking Cancellation ");
	                 	   }
	                 
	                 System.out.println("3. Exit");
	            }
               
                RequestManager reqManager = new RequestManager(email,db);
                int selection2 = scanner.nextInt();
	        	switch (selection2) {
		            case 1:  
		       	       if(Category.equals("A")){
		       	    	reqManager.manageRide();
		       	        }else if(Category.equals("B")){
		       	    	   		reqManager.manageParking(); 
		       	       		  }
		                break;
		             case 2:  
		            	 if(Category.equals("A")){
				       	    	//reqManager.manageRide(); rules related stuff
				       	        }else if(Category.equals("B")){
				       	    	   		//reqManager.manageParking();  //rules related stuff
				       	       		  }
				         break;
		             case 3: 
		            	 System.out.print("Existed!!");
		                 break;
		             default:  
		 	    		 System.out.print("Wrong Choice, please try again");
		 	             break;
	             } 
                break;
                
        case 3: 
	        	System.out.print("Enter your Email-id:	");
	            email = scanner.next();
	            System.out.print("Enter your Password:	");
	            password = scanner.next();
	            
	            int val= db.adminSignIn(email, password);
	            if(val==1){
	            	System.out.println("\nPlease Select from following Options");
                	System.out.println("1. Start Schedule Ride");
                	System.out.println("2. Start Schedule Parking");
                	System.out.println("3. Dispatch Ride Request");
                	System.out.println("4. Start Parking");
                    System.out.println("5. Exit");
	            }else{
	            	System.out.println("Login Error");
	            	}
	            
                int selection3 = scanner.nextInt();
                SchedulingContext schedulingContext;
                switch (selection3) {
		            case 1:  
		            	 System.out.println("Start Schedule Ride!");
			       	    //	manageSchedule();
		            	 schedulingContext = new SchedulingContext(new ScheduleRide()); 
		                 schedulingContext.executeStrategy(db);
			                break;
		             case 2:  
		            	 System.out.println("Start Schedule Parking");
		            	 schedulingContext = new SchedulingContext(new ScheduleParking()); 
		                 schedulingContext.executeStrategy(db);
		                    break;  
		             case 3:
	    	               System.out.print("Dispatch Ride Request");
	    	               RideInfo rideInfo = new RideInfo(); //send ride data which ride is starting
		            	   break;
		             case 4:
	    	               System.out.print("Start Parking!");
	    	               ParkingInfo parkingInfo = new ParkingInfo(); 
		            	   break;
		             case 5:
	    	               System.out.print("Existed!!");
		            	   break;
		             default:  
		 	    		 System.out.print("Wrong Choice, please try again");
		 	             break;
		         } 
	            
                 break;
        case 4:  
        		System.out.print("Exited!!");
                 break;
                 
        default:  
	    		System.out.print("Wrong Choice, please try again");
	             break;
		}
	}
}
