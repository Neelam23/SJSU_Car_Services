package com.ride;

public interface RideInformation {
    public void pickUpAndDropOffLocation(int id);
    public void gpsLocation(int id);
    public void payment(int id);
    
    public void setRideState(RideState s);
}
