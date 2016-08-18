package com.members;

import com.dao.MySQLDB;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public abstract class Member {
    protected String email = null;
    protected String password = null;
    protected String name = null;
    protected long creditCard = 0;
    protected long debitCard = 0;
    protected long phone = 0;
    protected String Category = null;
    
    protected MySQLDB db;
    public abstract void signUp () throws SQLIntegrityConstraintViolationException, SQLException;
    protected abstract void display();
}
