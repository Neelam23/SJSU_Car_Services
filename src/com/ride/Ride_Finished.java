package com.ride;

import com.payment.Debit;
import com.payment.RiderPayment;
import com.payment.PaymentType;

public class Ride_Finished implements RideState{
    private RideInformation info;
    
    public Ride_Finished(RideInformation r){
         this.info = r;
    }
    
    public void pickUpAndDropOffLocation(int id){
        System.out.println("Ride ID: " + id + ". The ride is completed.");
    }
    public void gpsLocation(int id){
        System.out.println("Ride ID: " + id + ". The ride is completed, we have reached the destination.");
    }
    public void payment(int id){
        System.out.println("Ride ID: " + id + ". The ride has been finished and you have been charged");
       //Calling payment
        PaymentType PT;
        PT = new Debit();
        RiderPayment PA= new RiderPayment(PT);
        PA.payment();
    }  
}
