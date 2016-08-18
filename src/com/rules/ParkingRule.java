package com.rules;

import java.util.Scanner;

import com.dao.MySQLDB;

public class ParkingRule extends Rules {
	
	public void startprocess(){
		System.out.println("\nParking Cancellation request: checking rules..");
	}
	
	public String[] userDetails(){
		String loc[] = new String[2];
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter parking location:	");
	    loc[0]= scanner.next();
	    
	    return loc;
	}


}
