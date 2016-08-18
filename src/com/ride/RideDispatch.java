package com.ride;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NehaP
 */
public class RideDispatch extends Thread{

    RideInformation rideInfo;
    int rideId;
    Random rand = new Random(); 
    
    public RideDispatch(RideInformation info, int id)
    {
        this.rideInfo = info;
        this.rideId = id;
        start();
    }
    
    @Override
    public void run() {
        int rnd = rand.nextInt(3) + 1;
        
        rideInfo.pickUpAndDropOffLocation(rideId);
        try {
            Thread.sleep(2000*rnd);
        } catch (InterruptedException ex) {
            Logger.getLogger(RideDispatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        rideInfo.gpsLocation(rideId);
        rnd = rand.nextInt(3) + 1;
        try {
            Thread.sleep(2000*rnd);
        } catch (InterruptedException ex) {
            Logger.getLogger(RideDispatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        rideInfo.gpsLocation(rideId);
        
        rideInfo.payment(rideId);
        rnd = rand.nextInt(3) + 1;
        try {
            Thread.sleep(2000*rnd);
        } catch (InterruptedException ex) {
            Logger.getLogger(RideDispatch.class.getName()).log(Level.SEVERE, null, ex);
        }
        rideInfo.gpsLocation(rideId);
        rideInfo.payment(rideId);
    }
    
}
