package be.helha.pills_me.models;

import java.util.ArrayList;

public class BankPrescription {
    //contain the list of schedule pill
    //for each pill schedule one fragment

    private static BankPrescription instance;

    private ArrayList<Pill> mSchedulePills;

    private BankPrescription(){
        mSchedulePills = new ArrayList<>();
        for (int i = 0; i < 10; i++) {//TODO change this shit!
            mSchedulePills.add(new Pill("Pill " + i, 2 ,true, false, true));
        }
    }

    public static BankPrescription getInstance(){
        if(instance == null){
            instance = new BankPrescription();
        }
        return instance;
    }

    public ArrayList<Pill> getSchedulePills(){
        return mSchedulePills;
    }
}
