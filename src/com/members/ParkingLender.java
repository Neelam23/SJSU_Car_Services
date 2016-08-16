package com.members;

import com.dao.MySQLDB;
import java.util.Scanner;

public class ParkingLender extends Member{

    private String location;
    private int totalParkingSpots;       
    
    public ParkingLender(Scanner input, MySQLDB db){
        this.db = db;
        this.Category = "C";
        
        System.out.println("Please enter your credentials below in order to register as a Parking Lender ");
        System.out.print("Email: ");
        email = input.next();
        System.out.println("Password: ");
        password = input.next();
        System.out.println("Location of parking: ");
        location = input.next();
        System.out.println("Total number of parking spots: ");
        totalParkingSpots = input.nextInt();
        System.out.println("Phone number: ");
        phone = input.nextInt();
    }
    
    public void signUp() {

        // Insert member details
        String insertMemberString = "INSERT INTO members(id,category,name,email,password,address,creditCard,debitCard,availablity_status) VALUES (1 ," + this.Category + "," + this.name + "," + this.email + "," + this.password + ",null," + this.creditCard + "," + this.debitCard + ",null";
        db.registerMember(insertMemberString);

        // Insert garage (paring spot) details
        String insertGarageString = "";
        db.registerGarage(insertGarageString);
    }
    
    public void display(){
        System.out.println("New parking lender has been added to the system");
    }
    
}
