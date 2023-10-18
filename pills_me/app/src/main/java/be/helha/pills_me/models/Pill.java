package be.helha.pills_me.models;

public class Pill {
    private String mName;
    //ToDo: implemente time.
    private boolean mMorning;
    private boolean mMidDay;
    private boolean mEvening;

    public Pill(String name, boolean morning, boolean midDay, boolean evening){
        mName = name;
        mMorning = morning;
        mMidDay = midDay;
        mEvening = evening;
    }

    public Pill(){

    }

    public String getName(){
        return mName;
    }

    public Boolean isMorning(){
        return mMorning;
    }

    public Boolean isMidDay(){
        return mMidDay;
    }

    public boolean isEvening(){
        return mEvening;
    }

    public void setMorning(Boolean b){
        mMorning = b;
    }
}
