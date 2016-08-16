/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schedule;

import com.dao.MySQLDB;

public interface SchedulingStrategy {
    public void doOperation(MySQLDB db);
}
