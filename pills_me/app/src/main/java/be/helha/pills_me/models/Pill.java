package be.helha.pills_me.models;

import java.io.Serializable;

public class Pill {
    private int mIdDb;
    private String mName;
    private int mDuration; //Time in day
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;

    public Pill(int idDb, String name, int duration, boolean morning, boolean midDay, boolean evening) {
        mIdDb = idDb;
        mName = name;
        mDuration = duration;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public Pill(String name, int duration, boolean morning, boolean midDay, boolean evening) {
        mName = name;
        mDuration = duration;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public String getName() {
        return mName;
    }

    public int getDuration() {
        return mDuration;
    }

    public Boolean isMorning() {
        return mMorning;
    }

    public Boolean isMidDay() {
        return mMidDay;
    }

    public boolean isEvening() {
        return mEvening;
    }

    public Integer getId() {
        return mIdDb;
    }
}
