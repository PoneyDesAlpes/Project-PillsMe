package be.helha.pills_me.models;

import java.util.UUID;

public class Prescription {
    private UUID uuid;
    private String mPillName;
    private String mStartDate;
    private String mEndDate;
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;

    public Prescription(String pillName, String startDate, String endDate, boolean morning, boolean midDay, boolean evening){
        uuid = UUID.randomUUID();
        mPillName = pillName;
        mStartDate = startDate;
        mEndDate = endDate;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }
}
