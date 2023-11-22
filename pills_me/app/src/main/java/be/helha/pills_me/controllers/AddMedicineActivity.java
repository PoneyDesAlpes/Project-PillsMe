package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddMedicineActivity extends AppCompatActivity {
    private EditText mNameOfPills;
    private Button mAddbutton;
    private CheckBoxMMEFragment mFragmentMME;
    private NumberPicker mNumberPicker;

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);

        mNameOfPills = findViewById(R.id.name_pill_edit_text);

        mNumberPicker = findViewById(R.id.day_number_picker);
        mNumberPicker.setMinValue(MIN_VALUE);
        mNumberPicker.setMaxValue(MAX_VALUE);

        mAddbutton = findViewById(R.id.add_button);
        mAddbutton.setOnClickListener(view -> {
            if (createPill()) {
                Toast.makeText(this, getString(R.string.pillAdded), Toast.LENGTH_SHORT).show();
                finish(); //Close the activity
            }
        });

        mFragmentMME = (CheckBoxMMEFragment) initFragment();
    }

    private boolean createPill() {
        //Check if the name of the pill is not empty
        if (mNameOfPills.getText().toString().isEmpty()) {
            return false;
        }

        //Add the pill to the DB
        try {
            BankPills.getInstance(this).addPill(new Pill(
                    mNameOfPills.getText().toString(),
                    mNumberPicker.getValue(),
                    mFragmentMME.isMorningCheckBoxChecked(),
                    mFragmentMME.isMidDayCheckBoxChecked(),
                    mFragmentMME.isEveningCheckBoxChecked()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Fragment initFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container_lineare_layout);

        //defaultValue : false, false, false
        fragment = CheckBoxMMEFragment.newInstance(false, false, false);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_lineare_layout, fragment)
                .commit();
        return fragment;
    }
}