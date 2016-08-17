/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedule;

import java.util.ArrayList;
import java.util.List;
import com.dao.MySQLDB;
import com.payment.*;

public class ScheduleRide implements SchedulingStrategy{
	MySQLDB db;
	List<String> emails= new ArrayList<String>();
    @Override
    public void doOperation(MySQLDB db){
       this.db= db;
      // db.scheduleRide();
        emails= db.scheduleRide();
        
  // payment advance after scheduling requests

       int i=0;
       while(emails.size()!= 0){
       String emailid = emails.get(i);
       String paymentTY= db.checkPaymentType(emailid);
       PaymentType PT;
       if(paymentTY=="credit"){
    	   PT = new Credit();
       }else{
    	   PT = new Debit();
       }
       PaymentAccount PA= new RiderPayment(PT);
       i++;
       }
    }   
    
}
