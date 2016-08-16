package com.members;

import java.util.ArrayList;
import java.util.List;

public abstract class Supervisor extends Member{
    
    protected List<Member> member = new ArrayList<Member>();
    
    
    public void display(){
        for(Member mem: member )
            mem.display();
    }
    
    private void addMember(Member mem){
        member.add(mem);
    }
    
    private void removeMember(Member mem){
        member.remove(mem);
    }
}
