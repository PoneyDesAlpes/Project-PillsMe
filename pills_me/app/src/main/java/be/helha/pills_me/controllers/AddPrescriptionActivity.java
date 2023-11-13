package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddPrescriptionActivity extends AppCompatActivity {

    private FloatingActionButton mAddPillButton;
    private Spinner mSpinnerListPills;
    private ArrayAdapter<Pill> adapter;
    private Button mAddTakePillButton;
    private checkBoxMMEFragment mFragmentController;
    private ImageButton mButtonCalendarPickerStart;
    private ImageButton mButtonCalendarPickerEnd;
    private TextView mStartDateTextView;
    private TextView mEndDateTextView;
    private TextView mDefaultTakeTextView;

    private Pill currentPill;

    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_pills);

        //Button pour ajouter un pill dans la vue calendar
        //mAddTakePillButton = findViewById(R.id.add_take_pill_button);


        mFragmentController = (checkBoxMMEFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        mDefaultTakeTextView = findViewById(R.id.default_time_text_view);

        //Button pour ovrir la page pour ajouter des medicamants
        mAddPillButton = findViewById(R.id.open_add_pill_button);
        mAddPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddPrescriptionActivity.this, AddMedicineActivity.class);
            startActivity(intent);
        });

        mSpinnerListPills = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(AddPrescriptionActivity.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPillsName());
        mSpinnerListPills.setAdapter(adapter);

        mSpinnerListPills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for(Pill p : BankPills.getInstance().getBankPills()){
                    if(p.getName().equals(mSpinnerListPills.getSelectedItem().toString())){
                        setCheckBox(p);
                        currentPill = p;
                    }
                }
                mDefaultTakeTextView.setText(String.valueOf(currentPill.getDuration()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mStartDateTextView = findViewById(R.id.date_start_text_view);
        mEndDateTextView = findViewById(R.id.date_end_text_view);

        mButtonCalendarPickerStart = findViewById(R.id.calendar_start_button);
        mButtonCalendarPickerStart.setOnClickListener(view -> {
            showPopUpCalendar(mStartDateTextView,mEndDateTextView);
        });

        mButtonCalendarPickerEnd = findViewById(R.id.calendar_end_button);
        mButtonCalendarPickerEnd.setOnClickListener(view -> {
            showPopUpCalendar(null, mEndDateTextView);
        });
            //DatePickerFragment dpf = new DatePickerFragment();
            //dpf.show(getSupportFragmentManager(), "datePicker");
    }

    private void showPopUpCalendar(TextView startTextView, TextView endTextView) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(
                AddPrescriptionActivity.this,
                (datePicker, year, month, day) -> {
                    if (startTextView != null) {
                        startTextView.setText(day + "/" + (month + 1) + "/" + year);
                        endTextView.setText((day + currentPill.getDuration()) + "/" + (month + 1) + "/" + year);
                    } else {
                        endTextView.setText(day + "/" + (month + 1) + "/" + year);
                    }
                },
                year, month, day);
        dpd.show();
    }

    protected void onResume(){
        super.onResume();
        updateSpinner();
    }

    private void updateSpinner(){
        adapter = new ArrayAdapter<>(AddPrescriptionActivity.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPillsName());
        mSpinnerListPills.setAdapter(adapter);
    }

    private void setCheckBox(Pill p){
        if(mFragmentController != null){
            mFragmentController.resetCheckBox();
            if(p.isMorning()){
                mFragmentController.setMorningCheckBoxChecked(p.isMorning());
            }
            if(p.isMidDay()){
                mFragmentController.setMidDayCheckBoxChecked(p.isMidDay());
            }
            if(p.isEvening()){
                mFragmentController.setEveningCheckBoxChecked(p.isEvening());
            }
        }
    }
}