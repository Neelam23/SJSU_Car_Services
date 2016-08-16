package com.members;

import com.dao.MySQLDB;
import java.util.Scanner;

public class Driver extends Member {
    
    private String carType;
    private int size;
    
    public Driver(Scanner input, MySQLDB db){
        this.db = db;
        this.Category = "B";
        
        System.out.println("Please enter your credentials below in order to register as a driver");
        System.out.print("Email: ");
        email = input.next();
        System.out.println("Password: ");
        password = input.next();
        System.out.println("Car Type: ");
        carType = input.next();
        System.out.println("Size: ");
        size = input.nextInt();
        System.out.println("Phone number: ");
        phone = input.nextInt();
    }
    
    public void signUp() {
        
        // Insert member details
        String insertMemberString = "INSERT INTO members(id,category,name,email,password,address,creditCard,debitCard,availablity_status) VALUES (1 ," + this.Category + "," + this.name + "," + this.email + "," + this.password + ",null," + this.creditCard + "," + this.debitCard + ",null";
        db.registerMember(insertMemberString);
        
        // Insert vehicle details
        String insertVehicleString ="";
        db.registerVehicle(insertVehicleString);
    }
     
     
    public void display(){
        System.out.println("New driver has been added to the system");
    }
    
}
