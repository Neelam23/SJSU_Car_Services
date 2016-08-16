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
abstract class PaymentAccount {
    protected PaymentType payment;
    protected PaymentType getPayment()
    {
        return payment;
    }
    
    
    protected void setPayment (PaymentType payment)
    {
        this.payment = payment;
    }
    abstract void payit();
    abstract void pay();
}
