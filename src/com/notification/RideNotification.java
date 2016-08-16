package com.notification;

public class RideNotification extends NotificationManager{

   private String message;
	 public void setMsg(String message) {
		    this.message = message;
		    notifyObserver(this.message);
		 }
	 public String getMsg() {
		    return message;
		 }
}
