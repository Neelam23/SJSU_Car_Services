package com.parking;

public class parkingRescheduled extends ParkingState{
    
    
    
    public void ParkingState(ParkingState state){
        System.out.println("The Parking was rescheduled");
        this.state = state;
    }
}
