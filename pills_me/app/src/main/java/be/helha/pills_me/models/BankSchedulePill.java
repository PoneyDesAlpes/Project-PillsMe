package be.helha.pills_me.models;

import java.util.ArrayList;

public class BankSchedulePill {
    //contain the list of schedule pill
    //for each pill schedule one fragment

    private static BankSchedulePill instance;

    private ArrayList<Pill> mSchedulePills;

    private BankSchedulePill(){
        mSchedulePills = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mSchedulePills.add(new Pill("Pill " + i, true, false, true));
        }
    }

    public static BankSchedulePill getInstance(){
        if(instance == null){
            instance = new BankSchedulePill();
        }
        return instance;
    }

    public ArrayList<Pill> getSchedulePills(){
        return mSchedulePills;
    }
}
