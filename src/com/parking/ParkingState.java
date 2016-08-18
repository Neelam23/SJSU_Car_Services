package com.parking;


public interface ParkingState {
    public void parkingTime(int id);
    public void systemClock(int id);
    public void payment(int id);
}
