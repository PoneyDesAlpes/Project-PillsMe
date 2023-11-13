package be.helha.pills_me.models;

import java.io.Serializable;
import java.util.UUID;

public class Pill implements Serializable {
    private UUID uuid;
    private String mName;
    private int mDuration; //Time in day
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;

    public Pill(String name, int duration, boolean morning, boolean midDay, boolean evening){
        uuid = UUID.randomUUID();
        mName = name;
        mDuration = duration;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public Pill(UUID uuid, String name, int duration, boolean morning, boolean midDay, boolean evening){
        uuid = uuid;
        mName = name;
        mDuration = duration;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public UUID getUUID(){
        return uuid;
    }

    public String getName(){
        return mName;
    }

    public int getDuration(){return mDuration;}

    public Boolean isMorning(){
        return mMorning;
    }

    public Boolean isMidDay(){
        return mMidDay;
    }

    public boolean isEvening(){
        return mEvening;
    }
}
