package be.helha.pills_me.models;

import java.util.ArrayList;

public class BankPills {
    private ArrayList<Pill> mPills;

    public BankPills(){
        mPills = new ArrayList<>(); //ToDO : faire appel a une DB
    }
    
    public void AddPill(Pill p){
        mPills.add(p);
    }
}
