package com.client;
import java.sql.Connection;
import com.dao.*;

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
		System.out.println("Please Select from Following options:\n"+"1. SignUp"+"\n2. SignIn"+"\n3. Admin SignIn"+"\n4. Exit");
		
		Scanner scanner = new Scanner(System.in);
		//scanner.useDelimiter("\\n");
		int selection = scanner.nextInt();
		switch (selection) {
        case 1:  
        	     db.signUp();
                 break;
        case 2:  
	        	System.out.print("Enter your Email-id:		");
	            email = scanner.next();
	            
	            System.out.print("Enter your Password:		");
	            password = scanner.next();
	            db.signIn(email, password);
                break;
        case 3: 
	        	System.out.print("Enter your Email-id:		");
	            email = scanner.next();
	            
	            System.out.print("Enter your Password:		");
	            password = scanner.next();
	            db.signIn(email, password);
                 break;
        case 4:  
        		System.out.print("Exited");
                 break;
                 
        default:  
	    		System.out.print("Wrong Choice, please try again");
	             break;
		}
//		 if (selection == 1) {                         //signup
//			 //System.out.println("Enter :\t");
//             
//			 db.signUp();
//         }
//         else if (selection == 2) {						//login
//        	 
//        	 System.out.print("Enter your Email-id:		");
//             email = scanner.next();
//             
//             System.out.print("Enter your Password:		");
//             password = scanner.next();
//             db.signIn(email, password);
//	            
////             System.out.println("\nYour sentence:\t" + email);
//             //System.out.println("Your Password:\t" + password);
//             
//         } 
//         else if (selection == 3) {		
//				
//         }
//         else if (selection == 4) {		
//        	 break;
//				
//         }
//         else{
//        	 System.out.println("Wrong selection");
//        	 break;
//         }
	}
}
