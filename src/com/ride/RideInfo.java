package com.ride;


public class RideInfo implements RideInformation{
    RideState state;
    
    public RideInfo(){
      state = new Ride_Not_Started(this);
    }
    
    public void pickUpAndDropOffLocation(){
        state.pickUpAndDropOffLocation();
    }
    public void gpsLocation(){
        state.gpsLocation();
    }
    public void payment(){
        state.payment();
    }
    
    public void setRideState(RideState s){
        this.state = s;
    }
    
}
