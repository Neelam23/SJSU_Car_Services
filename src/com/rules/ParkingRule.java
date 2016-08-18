package com.rules;

import java.util.Scanner;

import com.dao.MySQLDB;

public class ParkingRule extends Rules {
	
	public String startprocess(){
		System.out.println("\nParking Cancellation request made: checking rules..");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Do you want to cancel all parking requests (Y/N)");
	    String val= scanner.next();
	    return val;
	}
	
	public String[] userDetails(){
		String loc[] = new String[2];
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter parking location");
	    loc[0]= scanner.next();
	    
	    return loc;
	}


}
