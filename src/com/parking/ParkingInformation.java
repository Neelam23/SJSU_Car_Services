package com.parking;

public interface ParkingInformation {
    public void parkingTime();
    public void gpsLocation();
    public void payment();
    
    public void setParkingState(ParkingState s);
}
