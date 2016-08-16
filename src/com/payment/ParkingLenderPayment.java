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
public class ParkingLenderPayment extends PaymentAccount {
    
    
	 public ParkingLenderPayment(PaymentType paty) {
			super(paty);
		
		}

		public void payment() {
	        cardPayment();
	    }
    
}
