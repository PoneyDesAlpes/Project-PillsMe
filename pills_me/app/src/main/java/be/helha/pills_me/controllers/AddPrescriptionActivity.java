package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.BankPrescription;
import be.helha.pills_me.models.MyCalendarUtils;
import be.helha.pills_me.models.Pill;
import be.helha.pills_me.models.Prescription;

public class AddPrescriptionActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PRESCRIPTION = "id_prescription";

    private TextView mTitlePage;
    private FloatingActionButton mAddPillButton;
    private FloatingActionButton mModifyPillButton;
    private Spinner mSpinnerListPills;
    private ArrayAdapter<String> adapter;
    private Button mAddPrescriptionButton;
    private ImageButton mButtonCalendarPickerStart;
    private ImageButton mButtonCalendarPickerEnd;
    private TextView mStartDateTextView;
    private TextView mEndDateTextView;
    private TextView mDefaultTakeTextView;
    private CheckBoxMMEFragment mFragmentController;
    private Pill selectedPill;
    private Prescription editPrescription;
    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_pills);

        mTitlePage = findViewById(R.id.title_prescription_text_view);
        mDefaultTakeTextView = findViewById(R.id.default_time_text_view);

        mSpinnerListPills = findViewById(R.id.spinner);
        setSpinner();

        mSpinnerListPills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Pill p = BankPills.getInstance(getApplicationContext()).getPill(mSpinnerListPills.getSelectedItemPosition() + 1);
                if (p != null) {
                    selectedPill = p;
                    mFragmentController = initFragment(p.isMorning(), p.isMidday(), p.isEvening());
                    mDefaultTakeTextView.setText(String.valueOf(selectedPill.getDuration()));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if (selectedPill == null) {
            //show the fragment with the default value
            initFragment(false, false, false);
        }

        //Button open the activity to add a pill
        mAddPillButton = findViewById(R.id.add_pill_fab);
        mAddPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddPrescriptionActivity.this, AddMedicineActivity.class);
            startActivity(intent);
        });

        mModifyPillButton = findViewById(R.id.modify_pill_fab);
        mModifyPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddPrescriptionActivity.this, AddMedicineActivity.class);
            intent.putExtra(AddMedicineActivity.EXTRA_ID_PILL, selectedPill.getId());
            startActivity(intent);
        });

        mStartDateTextView = findViewById(R.id.date_start_text_view);
        mEndDateTextView = findViewById(R.id.date_end_text_view);

        mButtonCalendarPickerStart = findViewById(R.id.calendar_start_button);
        mButtonCalendarPickerStart.setOnClickListener(view -> {
            showPopUpCalendarStartDate();
        });

        mButtonCalendarPickerEnd = findViewById(R.id.calendar_end_button);
        mButtonCalendarPickerEnd.setOnClickListener(view -> {
            showPopUpCalendarEndDate();
        });

        //button to add a prescription
        mAddPrescriptionButton = findViewById(R.id.add_take_pill_button);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID_PRESCRIPTION)) {
            int idPrescription = intent.getIntExtra(EXTRA_ID_PRESCRIPTION, -1);
            setEditMode(idPrescription);
            editMode = true;
            mAddPrescriptionButton.setOnClickListener(view -> {
                if (modifyPrescription()) {
                    Toast.makeText(this, R.string.prescription_modified, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, R.string.fill_all_the_fields, Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            mAddPrescriptionButton.setOnClickListener(view -> {
                if (createPrescription()) {
                    Toast.makeText(this, R.string.prescription_added, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, R.string.fill_all_the_fields, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showPopUpCalendarStartDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddPrescriptionActivity.this, (view, year, month, dayOfMonth) -> {
            String startDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            mStartDateTextView.setText(startDate);
            calculateEndDate(startDate);
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showPopUpCalendarEndDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddPrescriptionActivity.this, (view, year, month, dayOfMonth) -> {
            String endDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            mEndDateTextView.setText(endDate);
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void calculateEndDate(String startDate) {
        Date date = MyCalendarUtils.parseStringInDate(startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, selectedPill.getDuration() - 1); //count the start date in the duration
        String calculatedDate = MyCalendarUtils.parseDateInString(calendar.getTime());
        mEndDateTextView.setText(calculatedDate);
    }

    protected void onResume() {
        super.onResume();
        if (editMode) {
            setSpinner(editPrescription.getIdPill());
        }
        else {
            setSpinner();
        }
    }

    private void setSpinner() {
        adapter = convertPillToSpinner();
        mSpinnerListPills.setAdapter(adapter);
    }

    private void setSpinner(int idPill) {
        adapter = convertPillToSpinner();
        mSpinnerListPills.setAdapter(adapter);
        mSpinnerListPills.setSelection(idPill - 1);
    }

    private ArrayAdapter<String> convertPillToSpinner() {
        List<Pill> pills = BankPills.getInstance(this).getPills();
        List<String> pillsName = new ArrayList<>();
        for (Pill pill : pills) {
            pillsName.add(pill.getName());
        }
        return new ArrayAdapter<>(AddPrescriptionActivity.this, android.R.layout.simple_spinner_dropdown_item, pillsName);
    }

    private boolean createPrescription() {
        if (selectedPill == null
                || mStartDateTextView.getText().toString().equals(getResources().getString(R.string.choseDate))
                || mEndDateTextView.getText().toString().equals(getResources().getString(R.string.choseDate))) {
            return false;
        }

        try {
            String startDate = mStartDateTextView.getText().toString();
            String endDate = mEndDateTextView.getText().toString();

            boolean morning = mFragmentController.isMorningCheckBoxChecked();
            boolean midday = mFragmentController.isMidDayCheckBoxChecked();
            boolean evening = mFragmentController.isEveningCheckBoxChecked();
            BankPrescription.getInstance(this).addPrescription(new Prescription(startDate, endDate, morning, midday, evening, selectedPill.getId()));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean modifyPrescription(){
        try {
            editPrescription.setIdPill(selectedPill.getId());
            editPrescription.setStartDate(mStartDateTextView.getText().toString());
            editPrescription.setEndDate(mEndDateTextView.getText().toString());

            editPrescription.setMorning(mFragmentController.isMorningCheckBoxChecked());
            editPrescription.setMidDay(mFragmentController.isMidDayCheckBoxChecked());
            editPrescription.setEvening(mFragmentController.isEveningCheckBoxChecked());
            BankPrescription.getInstance(this).updatePrescription(editPrescription);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setEditMode(int idPrescription){
        editPrescription = BankPrescription.getInstance(this).getPrescription(idPrescription);
        //change page title
        mTitlePage.setText(R.string.title_prescription_modify);
        //set the spinner with the correct pill
        mSpinnerListPills.setSelection(editPrescription.getIdPill() - 1);
        //set the text view with the correct date
        mStartDateTextView.setText(editPrescription.getStartDate());
        mEndDateTextView.setText(editPrescription.getEndDate());
        //change the button text to save change
        mAddPrescriptionButton.setText(R.string.submit_button);
    }

    private CheckBoxMMEFragment initFragment(boolean morning, boolean midday, boolean evening){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        fragment = CheckBoxMMEFragment.newInstance(
                morning,
                midday,
                evening);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_lineare_layout, fragment)
                .commit();
        return (CheckBoxMMEFragment) fragment;
    }
}