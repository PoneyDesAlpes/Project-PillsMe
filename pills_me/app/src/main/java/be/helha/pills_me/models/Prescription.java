package be.helha.pills_me.models;

import java.io.Serializable;

public class Prescription  implements Serializable {
    private int mIdDb;
    private String mStartDate;
    private String mEndDate;
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;
    private int mPillId;

    public Prescription(int idDb, String startDate, String endDate, boolean morning, boolean midDay, boolean evening, int pillId){
        mIdDb = idDb;
        mStartDate = startDate;
        mEndDate = endDate;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
        mPillId = pillId;
    }

    public Prescription(String startDate, String endDate, boolean morning, boolean midDay, boolean evening, int pillId){
        mStartDate = startDate;
        mEndDate = endDate;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
        mPillId = pillId;
    }

    public String getStartDate(){return mStartDate;}

    public String getEndDate(){return mEndDate;}

    public Boolean isMorning(){
        return mMorning;
    }

    public Boolean isMidDay(){
        return mMidDay;
    }

    public boolean isEvening(){
        return mEvening;
    }

    public Integer getId() {
        return mIdDb;
    }

    public int getPillId() {
        return mPillId;
    }

}
