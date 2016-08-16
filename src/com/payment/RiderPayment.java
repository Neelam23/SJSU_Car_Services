/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payment;
import java.util.*;


public class RiderPayment extends PaymentAccount {
	
//	List<String> emailList= new ArrayList<String>();

    public RiderPayment(PaymentType paty) {
		super(paty);
	
	}

	public void payment() {
        cardPayment();
    }
    
}
