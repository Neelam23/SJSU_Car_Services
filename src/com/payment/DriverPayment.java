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
    
	 public DriverPayment(PaymentType paty) {
			super(paty);
		//this is reardknncf booking advance
		}

		public void payment() {
	        cardPayment();
	    }
}
