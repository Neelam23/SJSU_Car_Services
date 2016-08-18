package com.members;

import com.dao.MySQLDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Rider extends Member {
    
    public Rider(String name, String email)
    {
        this.name = name;
        this.email = email;
        this.Category = "A";
    }
            
    public Rider(Scanner input, MySQLDB db){
        this.db = db;
        this.Category = "A";
        System.out.println("Please enter your details below to sign up as a rider");
        System.out.print("Email: ");
        email = input.next();
        System.out.print("Password: ");
        password = input.next();
        System.out.print("Name: ");
        name = input.next();
        System.out.print("Credit Card Number: ");
        creditCard = input.nextInt();
        System.out.print("Debit Card Number: ");
        debitCard = input.nextInt();
        System.out.print("Phone number: ");
        phone = input.nextInt();
    }
    
    public void signUp() throws SQLIntegrityConstraintViolationException, SQLException {
        String insertString = "INSERT INTO members(category,name,email,password,address,creditCard,debitCard,availablity_status) VALUES ('" + this.Category + "','" + this.name + "','" + this.email + "','" + this.password + "',null," + this.creditCard + "," + this.debitCard + ",null)";
        db.registerMember(insertString);
    }
    
    
    public void display(){
        System.out.println(" Rider : " + name + ", email : " + email);
    }
}
