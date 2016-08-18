package com.members;

import com.dao.MySQLDB;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class ParkingLender extends Member{

    private String location = null;
    private int totalParkingSpots = 0;
    private int bookedParkingSpots = 0;
    
    public ParkingLender(String name, String email)
    {
        this.name = name;
        this.email = email;
        this.Category = "C";
    }
    
    public ParkingLender(Scanner input, MySQLDB db){
        this.db = db;
        this.Category = "C";
        
        System.out.println("Please enter your details below: ");
        System.out.print("Email: ");
        email = input.next();
        System.out.print("Password: ");
        password = input.next();
        System.out.print("Name: ");
        name = input.next();
        System.out.print("Location of parking: ");
        location = input.next();
        System.out.print("Total number of parking spots: ");
        totalParkingSpots = input.nextInt();
        System.out.print("Credit Card Number: ");
        creditCard = input.nextInt();
        System.out.print("Debit Card Number: ");
        debitCard = input.nextInt();
        System.out.print("Phone number: ");
        phone = input.nextInt();
    }
    
    public void signUp() throws SQLIntegrityConstraintViolationException, SQLException {

        // Insert member details
        String insertMemberString = "INSERT INTO members(category,name,email,password,address,creditCard,debitCard) VALUES ('" + this.Category + "','" + this.name + "','" + this.email + "','" + this.password + "',null," + this.creditCard + "," + this.debitCard + ")";
        db.registerMember(insertMemberString);

        // Insert garage (paring spot) details
        String insertGarageString = "INSERT into sys.garage (member_email,garage_city,parking_slots_total,parking_slots_booked) VALUES ('" + this.email + "','" + this.location + "'," + this.totalParkingSpots + "," + this.bookedParkingSpots +")";
        db.registerGarage(insertGarageString);
    }
    
    public void display(){
        System.out.println(" Parking Lender : " + name + ", email : " + email);
    }
    
}
