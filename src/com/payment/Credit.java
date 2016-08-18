/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payment;

import com.notification.MemberObserver;
import com.notification.*;

/**
 *
 * @author personal
 */
public class Credit implements PaymentType
{
	public void cardPayment(){
		//	System.out.println("Rider has been charged with Debit card payment");
		String message= " Credit card payment is successful!!";
	//	Call notification
		MemberObserver observer = new MemberObserver();
		ParkingNotification parkingNotification = new ParkingNotification();
		parkingNotification.addObserver(observer);
		parkingNotification.setMsg(message);
	}
   
}
    
    
    
