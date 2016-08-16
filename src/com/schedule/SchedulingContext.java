/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedule;
import com.dao.MySQLDB;


public class SchedulingContext {
    
    public SchedulingStrategy schedulingStrategy;
    MySQLDB db;
    
    
    public SchedulingContext (SchedulingStrategy strategy){
        this.schedulingStrategy = strategy;
    }

    public void executeStrategy(MySQLDB db){
    	this.db= db;
        schedulingStrategy.doOperation(this.db);
    }
}
