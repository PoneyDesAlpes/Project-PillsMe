package be.helha.pills_me.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarElement implements Serializable {

    private String mStringDate;
    private List<Prescription> mMorningPrescription;
    private List<Prescription> mMidDayPrescription;
    private List<Prescription> mEveningPrescription;

    public CalendarElement(String date){
        mMorningPrescription = new ArrayList<>();
        mMidDayPrescription = new ArrayList<>();
        mEveningPrescription = new ArrayList<>();
        mStringDate = date;
    }

    public void addMorningPrescription(Prescription prescription){
        mMorningPrescription.add(prescription);
    }

    public void addMidDayPrescription(Prescription prescription){
        mMidDayPrescription.add(prescription);
    }

    public void addEveningPrescription(Prescription prescription){
        mEveningPrescription.add(prescription);
    }

    public List<Prescription> getMorningPrescription() {
        return mMorningPrescription;
    }

    public List<Prescription> getMidDayPrescription() {
        return mMidDayPrescription;
    }

    public List<Prescription> getEveningPrescription() {
        return mEveningPrescription;
    }

    public String getDate() {
        return mStringDate;
    }
}
