package com.members;

import com.dao.MySQLDB;

public abstract class Member {
    protected String email = null;
    protected String password = null;
    protected String name = null;
    protected int creditCard = 0;
    protected int debitCard = 0;
    protected int phone = 0;
    protected String Category = null;
    
    protected MySQLDB db;
    public abstract void signUp();
    protected abstract void display();
}
