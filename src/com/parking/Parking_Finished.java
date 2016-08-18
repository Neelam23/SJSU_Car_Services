package com.parking;

import com.payment.Debit;
import com.payment.DriverPayment;
import com.payment.PaymentType;

public class Parking_Finished implements ParkingState{
    private ParkingInformation info;
    
    public Parking_Finished(ParkingInformation r){
         this.info = r;
    }
    
    public void parkingTime(int id){
        System.out.println("Parking ID: " + id + ". The parking time is completed");
    }
    public void systemClock(int id){
        System.out.println("Parking ID: " + id + ". The parking time is completed, you have 5 more minutes to pick up your car without extra charges.");
    }
    public void payment(int id){
        System.out.println("The parking time is completed, and you have been charged");
        
        PaymentType PT;
        PT = new Debit();
        DriverPayment PA= new DriverPayment(PT);
        PA.payment();
        
        
    
    }  
}
