package com.parking;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ParkingInfoDispatch extends Thread{
    ParkingInformation parkingInfo;
    int parkingId;
    Random rand = new Random(); 
    
    public ParkingInfoDispatch(ParkingInformation info, int id)
    {
        this.parkingInfo = info;
        this.parkingId = id;
        start();
    }
    
    @Override
    public void run() {
        int rnd = rand.nextInt(3) + 1;
        
        parkingInfo.parkingTime(parkingId);
        try {
            Thread.sleep(2000*rnd);
        } catch (InterruptedException ex) {
            Logger.getLogger(ParkingInfoDispatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        parkingInfo.systemClock(parkingId);
        rnd = rand.nextInt(3) + 1;
        try {
            Thread.sleep(2000*rnd);
        } catch (InterruptedException ex) {
            Logger.getLogger(ParkingInfoDispatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        parkingInfo.systemClock(parkingId);
        
        parkingInfo.payment(parkingId);
        rnd = rand.nextInt(3) + 1;
        try {
            Thread.sleep(2000*rnd);
        } catch (InterruptedException ex) {
            Logger.getLogger(ParkingInfoDispatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        parkingInfo.systemClock(parkingId);
        parkingInfo.payment(parkingId);
    }
}
