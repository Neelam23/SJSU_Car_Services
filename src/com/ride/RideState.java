package com.ride;


public interface RideState {
    public void pickUpAndDropOffLocation(int id);
    public void gpsLocation(int id);
    public void payment(int id);
}
