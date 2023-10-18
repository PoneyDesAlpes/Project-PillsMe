package be.helha.pills_me.models;

import java.util.ArrayList;

public class BankPills {
    private ArrayList<Pill> mPills;

    private static BankPills instance;
    public static BankPills getInstance(){
        if(instance == null){
            instance = new BankPills();
        }
        return instance;
    }

    private BankPills(){
        mPills = new ArrayList<>(); //ToDO : faire appel a une DB
        mPills.add(new Pill("Ma bite", true, true, true));
    }
    
    public void AddPill(Pill p){
        mPills.add(p);
    }

    public ArrayList getBankPills(){
        return mPills;
    }
}
