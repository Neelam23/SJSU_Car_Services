package com.parking;

public class ParkingContext {
    private ParkingState state;
    private Schedule schedule;
    private Rule ParkingRule;
    private Time endtime;
    
    public void ParkingState(ParkingState state){
        this.state=state;
    }
    
    public void getState(ParkingState state){
        this.state=state;
    }
    
    public void setState(ParkingState state){
        this.state=state;
    }
}
