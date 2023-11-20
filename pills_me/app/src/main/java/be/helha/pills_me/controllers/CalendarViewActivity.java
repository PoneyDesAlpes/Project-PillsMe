package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPrescription;
import be.helha.pills_me.models.CalendarElement;
import be.helha.pills_me.models.Prescription;

public class CalendarViewActivity extends AppCompatActivity {

    private static final int FONT_SIZE = 22;
    private FloatingActionButton mAddTakePill;
    private LinearLayout mCalendarContainer;

    private static final int AFTER = 1;
    private static final int BEFORE = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_view);

        mAddTakePill = findViewById(R.id.open_add_take_pill_button);
        mAddTakePill.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddPrescriptionActivity.class);
            startActivity(intent);
        });

        initCalendar();
    }

    public void initCalendar(){
        mCalendarContainer = findViewById(R.id.container_element_calendare);
        mCalendarContainer.removeAllViews();

        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy", Locale.FRANCE);
        Calendar dayToDisplay = Calendar.getInstance();
        dayToDisplay.getTime();
        Log.d("DEBUG", "dayToDisplay: " + dayToDisplay.getTime());

        for(int indexDay = 0; indexDay < 30 ; indexDay++) {

            //dayToDisplay.setTime(dateOfTheDay);
            dayToDisplay = Calendar.getInstance();
            dayToDisplay.getTime();//reset to current day
            dayToDisplay.add(Calendar.DAY_OF_MONTH, indexDay);
            CalendarElement calendarElement = new CalendarElement(sdf.format(dayToDisplay.getTime()));

            for (Prescription p : BankPrescription.getInstance(this).getPrescriptions()) {
                Calendar startDayPrescription = Calendar.getInstance();
                Calendar endDayPrescription = Calendar.getInstance();

                String simplifiedDateToDisplay = "";
                String simplifiedStartDate;
                String simplifiedEndDate;

                try {
                    startDayPrescription.setTime(sdf.parse(p.getStartDate()));
                    endDayPrescription.setTime(sdf.parse(p.getEndDate()));


                    simplifiedDateToDisplay = sdf.format(dayToDisplay.getTime());
                    simplifiedStartDate = sdf.format(startDayPrescription.getTime());
                    simplifiedEndDate = sdf.format(endDayPrescription.getTime());

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if(p.isMorning()){
                    if(startDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == AFTER
                            && endDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == AFTER){
                        Log.d("DEBUG", "1:No Display");
                    }

                    if(simplifiedStartDate.equals(simplifiedDateToDisplay)
                            && endDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == AFTER){
                        Log.d("DEBUG", "2:Display");
                    }

                    if(startDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == BEFORE
                            && endDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == AFTER){
                        Log.d("DEBUG", "3:Display");
                    }

                    if(startDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == BEFORE
                            && simplifiedEndDate.equals(simplifiedDateToDisplay)){
                        Log.d("DEBUG", "4:Display");
                    }

                    if(startDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == BEFORE
                            && endDayPrescription.getTime().compareTo(dayToDisplay.getTime()) == BEFORE){
                        Log.d("DEBUG", "5:No Display");
                    }
                }
                if(p.isMidDay()){
                    calendarElement.addMidDayPrescription(p);
                }
                if(p.isEvening()){
                    calendarElement.addEveningPrescription(p);
                }
            }
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment fragment = CalendarElementFragment.newInstance(calendarElement);
            ft.add(R.id.container_element_calendare, fragment);
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCalendar();
    }
}