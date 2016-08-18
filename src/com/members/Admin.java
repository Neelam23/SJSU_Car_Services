
package com.members;

import com.dao.MySQLDB;


public class Admin extends Supervisor{
   
   public Admin(){
       super();
   }
   
   public void addMember(Member mem){
        super.addMember(mem);
    }
   
   public void display(){
    super.display();
   }
   
    
    public void signUp(){
        
    }
    
 }    
