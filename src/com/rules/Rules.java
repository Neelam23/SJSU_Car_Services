package com.rules;
import java.util.Scanner;

import com.dao.*;


public abstract class Rules {

	public void cancellationRule(String email, MySQLDB db){
		
		String loc[] = new String[2];
		
		boolean req = db.checkRequest(email,db); //check if req is availble in req table
		if(req==true){
		String val = startprocess(); 
		if(val.equalsIgnoreCase("N"))
		loc= userDetails(); 
		db.CancelRequest(email,db,loc); 
		}else{
			System.out.println("No unscheduled request found.  Please contact customer care to cancel scheduled requests");
		}
	}
	
	public abstract String[] userDetails();
	public abstract String startprocess(); 
	
}
