package com.notification;

import java.util.*;

public abstract class NotificationManager {
	
	public String message;
	private List<MemberObserver> observers = new ArrayList<MemberObserver>();
	
	
	public void addObserver(MemberObserver observer ){
		 observers.add(observer);
	}
	
	
	public void deleteObserver(MemberObserverInterface observer ){
		 observers.remove(observer);
	}
	
	public void notifyObserver(String message){
		for (MemberObserver obj : observers) {
			obj.generateMessage(message);
	     }
	}
}
