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
public class Debit implements PaymentType {
    public void fillType() {
        System.out.println("Debit card details");
    }
    
}
