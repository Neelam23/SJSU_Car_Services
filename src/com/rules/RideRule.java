package com.rules;

import java.util.Scanner;

import com.dao.MySQLDB;

public class RideRule extends Rules {
	
	public void startprocess(){
		System.out.println("\nRide Cancellation request: checking rules..");
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Do you want to cancel all ride requests (Y/N)");
//	    String val= scanner.next();
//	    return val;
	}
	
	public String[] userDetails(){
		String loc[] = new String[2];
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Starting location of the ride:	");
	    loc[0]= scanner.next();
	    
	    System.out.print("Enter destination location of the ride:	");
	    loc[1]= scanner.next();
	    return loc;
	}

}
