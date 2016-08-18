package com.rules;
import java.sql.SQLException;
import java.util.Scanner;

import com.dao.*;


public abstract class Rules {

	public void cancellationRule(String email, MySQLDB db){
		
		String loc[] = new String[2];
		
		startprocess(); 
		loc= userDetails(); 
		try{
			db.cancelRequest(email,db,loc); 
		}catch (Exception e) {
        	System.out.println("Error in cancellation");
        	e.getMessage();
        	System.exit(-1);
        }
			
        
	}
	
	public abstract String[] userDetails();
	public abstract void startprocess(); 
	
}
