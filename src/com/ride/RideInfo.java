package com.ride;


public class RideInfo implements RideInformation{
    private RideState state;
    
    private String riderEmail, driverEmail, startingLocation, destinationLocation;
    
    public RideInfo(String riderEmail, String driverEmail, String startingLocation, String destinationLocation) {
        this.riderEmail = riderEmail;
        this.driverEmail = driverEmail;
        this.startingLocation = startingLocation;
        this.destinationLocation = destinationLocation;
        this.state = new Ride_Not_Started(this);
    }
    
    public void pickUpAndDropOffLocation(int id){
        state.pickUpAndDropOffLocation(id);
    }
    public void gpsLocation(int id){
        state.gpsLocation(id);
    }
    public void payment(int id){
        state.payment(id);
    }
    
    public void setRideState(RideState s){
        this.state = s;
    }
    
    public String getRiderEmail()
    {
        return this.riderEmail;
    }
    
    public String getDriverEmail()
    {
        return this.driverEmail;
    }
    
}
