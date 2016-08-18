package com.notification;

public class ParkingNotification extends NotificationManager {

	public String message;

	public void setMsg(String message) {
	    this.message = message;
	    notifyObserver(this.message);
	 }
    public String getMsg() {
	    return message;
	 }
}
