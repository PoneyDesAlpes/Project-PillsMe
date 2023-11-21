package be.helha.pills_me.models;

import java.io.Serializable;

public class Pill{
    private int mIdDb;
    private String mName;
    private int mDuration; //Time in day
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;

    public Pill(int idDb, String name, int duration, boolean morning, boolean midDay, boolean evening){
        mIdDb = idDb;
        mName = name;
        mDuration = duration;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public Pill(String name, int duration, boolean morning, boolean midDay, boolean evening){
        mName = name;
        mDuration = duration;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public int getDuration(){return mDuration;}

    public void setDuration(int duration){
        mDuration = duration;
    }

    public Boolean isMorning(){
        return mMorning;
    }

    public void setMorning(boolean morning){
        mMorning = morning;
    }

    public Boolean isMidDay(){
        return mMidDay;
    }

    public void setMidDay(boolean midDay){
        mMidDay = midDay;
    }

    public boolean isEvening(){
        return mEvening;
    }

    public void setEvening(boolean evening){
        mEvening = evening;
    }

    public Integer getId() {
        return mIdDb;
    }
}
