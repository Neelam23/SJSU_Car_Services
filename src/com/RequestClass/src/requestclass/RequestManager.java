package requestclass;

public class RequestManager {
    private RideRequest ride;
    private ParkingRequest parking;
        
    public RequestManager(){
      ride = new RideRequest();
      parking = new ParkingRequest();
    }
        
    public void manageRide(){
        ride.processRequest();
    }
    
    public void manageParking(){
        parking.processRequest();
    }
}
