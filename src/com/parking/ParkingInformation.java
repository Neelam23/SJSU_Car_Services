package com.parking;

public interface ParkingInformation {
    public void parkingTime(int id);
    public void systemClock(int id);
    public void payment(int id);
    
    public void setParkingState(ParkingState s);
}
