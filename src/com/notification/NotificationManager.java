package com.notification;

import java.util.*;

public abstract class NotificationManager {
	
	public String message;
	private List<MemberObserverInterface> observers = new ArrayList<MemberObserverInterface>();
	
	public String getMsg() {
	    return message;
	 }
	
	 public void setMsg(String message) {
	    this.message = message;
	    notifyObserver();
	 }
	public void addObserver(MemberObserverInterface observer ){
		 observers.add(observer);
	}
	
	public void notifyObserver(){
		for (MemberObserverInterface obj : observers) {
			obj.generateMessage(message);
	     }
		
	}
}
