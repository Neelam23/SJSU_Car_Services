/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedule;

import java.util.ArrayList;
import java.util.List;

import com.dao.MySQLDB;
import com.payment.Credit;
import com.payment.Debit;
import com.payment.DriverPayment;
import com.payment.PaymentType;
import com.payment.RiderPayment;

public class ScheduleParking implements SchedulingStrategy{

	List<String> emails= new ArrayList<String>();
	MySQLDB db;
    @Override
    public void doOperation(MySQLDB db) {
        this.db=db;
        emails= db.scheduleParking();
        
        int i=0;
        while(i< emails.size()){
        String emailid = emails.get(i);
        String paymentTY= db.checkPaymentType(emailid);
        PaymentType PT;
        if(paymentTY=="credit"){
     	   PT = new Credit();
        }else{
     	   PT = new Debit();
        }
        DriverPayment PA= new DriverPayment(PT);
        PA.payment();//call payment according to card no
        i++;
        }
    }
   
}