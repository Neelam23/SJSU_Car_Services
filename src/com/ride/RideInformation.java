package com.ride;

public interface RideInformation {
    public void pickUpAndDropOffLocation();
    public void gpsLocation();
    public void payment();
    
    public void setRideState(RideState s);
}
