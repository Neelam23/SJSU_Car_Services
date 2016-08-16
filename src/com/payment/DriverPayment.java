/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payment;

/**
 *
 * @author personal
 */
public class DriverPayment extends PaymentAccount {
    
    
    
    public void payit() {
        System.out.print("Account filled with");
        payment.fillType();
    }

    public void pay() {
        System.out.println("Driver has been charged");
    }
}
