package com.client;
import java.sql.Connection;
import com.dao.*;
import com.request.RequestManager;

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
        	    //call member classes
        	     db.signUp();
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
	                    } else if(Category.equals("B")){
	                 	   System.out.println("1. Request a Parking");
	                 	   }
	                 System.out.println("2. Exit");
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
                	System.out.println("1. Start Schedule Request");
                	System.out.println("2. Dispatch Ride Request");
                    System.out.println("2. Exit");
	            }else{
	            	System.out.println("Login Error");
	            	}
	            
                int selection3 = scanner.nextInt();
                switch (selection3) {
		            case 1:  
		            	 System.out.println("start scheduling!");
			       	    //	manageSchedule();
			                break;
		             case 2:  
		            	 System.out.println("dispatch ride- call ride client!");
			        	//dispatch ride
		                    break;
		                    
		             case 3:
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
