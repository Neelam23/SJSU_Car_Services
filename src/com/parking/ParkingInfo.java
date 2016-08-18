package com.parking;


public class ParkingInfo implements ParkingInformation{
    ParkingState state;
    
    private String driverId, parkingLenderId, parkingLocation;
    private int numHours;
    
    public ParkingInfo(String driverId, String parkingLenderId, String parkingLocation, int numOfHours){
      this.driverId = driverId;
      this.parkingLenderId = parkingLenderId;
      this.parkingLocation = parkingLocation;
      this.numHours = numOfHours;
      state = new Parking_Not_Started(this);
    }
    
    public void parkingTime(int id){
        state.parkingTime(id);
    }
    public void systemClock(int id){
        state.systemClock(id);
    }
    public void payment(int id){
        state.payment(id);
    }
    
    public void setParkingState(ParkingState s){
        this.state = s;
    }
    
}
