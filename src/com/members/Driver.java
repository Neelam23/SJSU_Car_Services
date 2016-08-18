package com.members;

import com.dao.MySQLDB;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Driver extends Member {
    
    private String carType = null;
    private int numSeats = 0;
    
    public Driver (String name, String email)
    {
        this.name = name;
        this.email = email;
        this.Category = "B";
    }
    
    public Driver(Scanner input, MySQLDB db){
        this.db = db;
        this.Category = "B";
        
        System.out.println("Please enter your details below: ");
        System.out.print("Email: ");
        email = input.next();
        System.out.print("Password: ");
        password = input.next();
        System.out.print("Name: ");
        name = input.next();
        System.out.println("Please Select from Following Vehicle optons:\n"+"1. Sedan"+"\n2. SUV"+"\n3. Truck"+"\n4. Ferry"+"\n5. Luxury Car");
        carType = input.next();
        System.out.print("Number of seats: ");
        numSeats = input.nextInt();
        System.out.print("Credit Card Number: ");
        creditCard = input.nextInt();
        System.out.print("Debit Card Number: ");
        debitCard = input.nextInt();
        System.out.print("Phone number: ");
        phone = input.nextInt();
    }
    
    public void signUp() throws SQLIntegrityConstraintViolationException, SQLException {
        
        // Insert member details
        String insertMemberString = "INSERT INTO members(category,name,email,password,address,creditCard,debitCard) VALUES ('" + this.Category + "','" + this.name + "','" + this.email + "','" + this.password + "',null," + this.creditCard + "," + this.debitCard+ ")";
        db.registerMember(insertMemberString);
        
        // Insert vehicle details
        String insertVehicleString = "INSERT into vehicle (member_email, vehicleType, numSeats) VALUES ('"+ this.email +"','" + this.carType + "'," + this.numSeats+ ")";
        db.registerVehicle(insertVehicleString);
    }
     
     
    public void display(){
        System.out.println(" Driver : " + name + ", email : " + email);
    }
    
}
