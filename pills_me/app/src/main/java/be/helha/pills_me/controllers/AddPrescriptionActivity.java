package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.BankPrescription;
import be.helha.pills_me.models.Prescription;
import be.helha.pills_me.models.Pill;

public class AddPrescriptionActivity extends AppCompatActivity {

    public static final String KEY_ID_PILL = "idPill";
    public static final String KEY_ID_PRESCRIPTION = "idPrescription";
    private FloatingActionButton mAddPillButton;
    private FloatingActionButton mModifyPillButton;
    private Spinner mSpinnerListPills;
    private ArrayAdapter<String> adapter;
    private Button mAddPrescriptionButton;
    private checkBoxMMEFragment mFragmentController;
    private ImageButton mButtonCalendarPickerStart;
    private ImageButton mButtonCalendarPickerEnd;
    private TextView mStartDateTextView;
    private TextView mEndDateTextView;
    private TextView mDefaultTakeTextView;
    private Pill currentPill;

    String calculatedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_pills);

        Intent intentModifyPrescription = getIntent();
        int idPrescription = intentModifyPrescription.getIntExtra(KEY_ID_PRESCRIPTION, -1);
        if (idPrescription != -1)
        {
            Log.d("AddPrescriptionActivity", "editMode");
        }

        //button to add a prescription
        mAddPrescriptionButton = findViewById(R.id.add_take_pill_button);
        mAddPrescriptionButton.setOnClickListener(view -> {
            boolean isPrescriptionCreate = createPrescription();
            if (isPrescriptionCreate) {
                Toast.makeText(this, "Prescription added", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        mModifyPillButton = findViewById(R.id.floatingActionButton);
        mModifyPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddPrescriptionActivity.this, AddMedicineActivity.class);
            intent.putExtra(KEY_ID_PILL, currentPill.getId());
            startActivity(intent);
        });

        mFragmentController = (checkBoxMMEFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        mDefaultTakeTextView = findViewById(R.id.default_time_text_view);

        //Button pour ovrir la page pour ajouter des medicamants
        mAddPillButton = findViewById(R.id.open_add_pill_button);
        mAddPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddPrescriptionActivity.this, AddMedicineActivity.class);
            startActivity(intent);
        });

        mSpinnerListPills = findViewById(R.id.spinner);
        updateSpinner();
        mSpinnerListPills.setAdapter(adapter);

        mSpinnerListPills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Pill p = BankPills.getInstance(getApplicationContext()).getPill(mSpinnerListPills.getSelectedItemPosition()+1);
                if (p != null) {
                    setCheckBox(p);
                    currentPill = p;
                    mDefaultTakeTextView.setText(String.valueOf(currentPill.getDuration()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        mStartDateTextView = findViewById(R.id.date_start_text_view);
        mEndDateTextView = findViewById(R.id.date_end_text_view);

        mButtonCalendarPickerStart = findViewById(R.id.calendar_start_button);
        mButtonCalendarPickerStart.setOnClickListener(view -> {
            showPopUpCalendar(mStartDateTextView, mEndDateTextView);
        });

        mButtonCalendarPickerEnd = findViewById(R.id.calendar_end_button);
        mButtonCalendarPickerEnd.setOnClickListener(view -> {
            showPopUpCalendar(null, mEndDateTextView);
        });
    }

    private void showPopUpCalendar(TextView startTextView, TextView endTextView) {
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        String patternDateFormat = "d/M/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(patternDateFormat, Locale.FRENCH);

        DatePickerDialog datePicker = new DatePickerDialog(AddPrescriptionActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                String selectedDate = String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year);;
                Calendar c = Calendar.getInstance();
                String displayDate = "";
                try {
                    Log.d("date", selectedDate + sdf.parse(selectedDate));
                    c.setTime(sdf.parse(selectedDate));
                    c.add(Calendar.DATE, currentPill.getDuration()-1);
                    displayDate = sdf.format(c.getTime());
                    //Pour avoir la bonne date dans la vue car la date prend aussi le time
                    c.setTime(sdf.parse(selectedDate));
                    c.add(Calendar.DATE, currentPill.getDuration()-1);
                    calculatedDate = sdf.format(c.getTime());
                    Log.d("date", calculatedDate);
                } catch (Exception e) {
                    Log.d("e",e.getMessage());
                }
                if (startTextView != null) {
                    startTextView.setText(selectedDate);
                    endTextView.setText(displayDate);
                } else {
                    endTextView.setText(selectedDate);
                    calculatedDate = selectedDate;
                }
            }
        }, yy, mm, dd);
        datePicker.show();
    }

    protected void onResume() {
        super.onResume();
        updateSpinner();
    }

    //get the name of each pill to display in the spinner
    private void updateSpinner() {
        List<Pill> pills = BankPills.getInstance(this).getPills();
        List<String> pillsName = new ArrayList<>();
        for (Pill p : pills) {
            pillsName.add(p.getName());
        }
        adapter = new ArrayAdapter<>(AddPrescriptionActivity.this, android.R.layout.simple_spinner_dropdown_item, pillsName);
        mSpinnerListPills.setAdapter(adapter);
    }

    private void setCheckBox(Pill p) {
        if (mFragmentController != null) {
            mFragmentController.resetCheckBox();
            if (p.isMorning()) {
                mFragmentController.setMorningCheckBox(p.isMorning());
            }
            if (p.isMidDay()) {
                mFragmentController.setMidDayCheckBox(p.isMidDay());
            }
            if (p.isEvening()) {
                mFragmentController.setEveningCheckBox(p.isEvening());
            }
        }
    }

    private boolean createPrescription() {
        if(currentPill != null
                && !mStartDateTextView.getText().equals(String.valueOf(R.string.label_date_pill))
                && !mEndDateTextView.getText().equals(String.valueOf(R.string.label_date_pill))){
            String startDate = mStartDateTextView.getText().toString();
            String endDate = calculatedDate;
            boolean morning = mFragmentController.isMorningCheckBoxChecked();
            boolean midday = mFragmentController.isMidDayCheckBoxChecked();
            boolean evening = mFragmentController.isEveningCheckBoxChecked();
            Prescription p = new Prescription(startDate, endDate, morning, midday, evening, currentPill.getId());
            BankPrescription.getInstance(this).addPrescription(p);
            return true;
        }
        return false;
    }
}