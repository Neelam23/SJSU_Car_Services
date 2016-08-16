package com.members;

import com.dao.MySQLDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Rider extends Member {
    
    public Rider(Scanner input, MySQLDB db){
        this.db = db;
        this.Category = "A";
        System.out.println("Please enter your details below to sign up as a rider");
        System.out.print("Email: ");
        email = input.next();
        System.out.println("Password: ");
        password = input.next();
        System.out.println("Name: ");
        name = input.next();
        System.out.println("Credit Card Number: ");
        creditCard = input.nextInt();
        System.out.println("Debit Card Number: ");
        debitCard = input.nextInt();
        System.out.println("Phone number: ");
        phone = input.nextInt();
    }
    
    public void signUp(){
        String insertString = "INSERT INTO members(id,category,name,email,password,address,creditCard,debitCard,availablity_status) VALUES (1 ," + this.Category + "," + this.name + "," + this.email + "," + this.password + ",null," + this.creditCard + "," + this.debitCard + ",null";
        db.registerMember(insertString);
    }
    
    
    public void display(){
        System.out.println("New rider has been added to the system");
    }
}
