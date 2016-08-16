package com.parking;


public class ParkingInfo implements ParkingInformation{
    ParkingState state;
    
    public ParkingInfo(){
      state = new Parking_Not_Started(this);
    }
    
    public void parkingTime(){
        state.parkingTime();
    }
    public void gpsLocation(){
        state.gpsLocation();
    }
    public void payment(){
        state.payment();
    }
    
    public void setParkingState(ParkingState s){
        this.state = s;
    }
    
}
