package be.helha.pills_me.models;

import java.io.Serializable;

public class Prescription implements Serializable {
    private int mIdDb;
    private String mStartDate;
    private String mEndDate;
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;
    private int mPillId;

    public Prescription(int idDb, String startDate, String endDate, boolean morning, boolean midDay, boolean evening, int pillId) {
        mIdDb = idDb;
        mStartDate = startDate;
        mEndDate = endDate;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
        mPillId = pillId;
    }

    public Prescription(String startDate, String endDate, boolean morning, boolean midDay, boolean evening, int pillId) {
        mStartDate = startDate;
        mEndDate = endDate;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
        mPillId = pillId;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public Boolean isMorning() {
        return mMorning;
    }

    public void setMorning(boolean morning) {
        mMorning = morning;
    }

    public Boolean isMidDay() {
        return mMidDay;
    }

    public void setMidDay(boolean midDay) {
        mMidDay = midDay;
    }

    public boolean isEvening() {
        return mEvening;
    }

    public void setEvening(boolean evening) {
        mEvening = evening;
    }

    public Integer getId() {
        return mIdDb;
    }

    public int getIdPill() {
        return mPillId;
    }

    public void setIdPill(int pillId) {
        mPillId = pillId;
    }
}
