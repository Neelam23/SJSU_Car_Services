/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payment;
import com.notification.*;

/**
 *
 * @author personal
 */
public class Debit implements PaymentType {
   
	public void cardPayment(){
		System.out.println("Rider has been charged with Debit card payment");
//		String message= "Rider has been charged with Debit card payment";
//		notify
//		MemberObserver observer = new MemberObserver();
//		RideNotification rideNotification = new RideNotification();
//		rideNotification.addObserver(observer);
//		rideNotification.setMsg(message);
	}
}
	