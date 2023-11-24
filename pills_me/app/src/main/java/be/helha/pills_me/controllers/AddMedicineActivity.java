package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddMedicineActivity extends AppCompatActivity {

    public static final String EXTRA_ID_PILL = "id_pill";
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 30;
    private Pill editPill;

    private TextView mTitle;
    private EditText mNameOfPills;
    private Button mAddbutton;
    private CheckBoxMMEFragment mFragmentMME;
    private NumberPicker mNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);

        mTitle = findViewById(R.id.title_pils_text_view);

        mNameOfPills = findViewById(R.id.name_pill_edit_text);

        mNumberPicker = findViewById(R.id.day_number_picker);
        mNumberPicker.setMinValue(MIN_VALUE);
        mNumberPicker.setMaxValue(MAX_VALUE);

        Intent intent = getIntent();
        mAddbutton = findViewById(R.id.add_button);

        if (intent.hasExtra(EXTRA_ID_PILL)) {
            int idPill = intent.getIntExtra(EXTRA_ID_PILL, -1);
            setEditMode(idPill);
            mFragmentMME = (CheckBoxMMEFragment) initFragment(
                    editPill.isMorning(),
                    editPill.isMidday(),
                    editPill.isEvening());

            mAddbutton.setOnClickListener(view -> {
                if (modifyPill()) {
                    Toast.makeText(this, getString(R.string.pillEdited), Toast.LENGTH_SHORT).show();
                    finish(); //Close the activity
                } else {
                    Toast.makeText(this, getString(R.string.pillNotEdited), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mFragmentMME = (CheckBoxMMEFragment) initFragment(false, false, false);
            mAddbutton.setOnClickListener(view -> {
                if (createPill()) {
                    Toast.makeText(this, getString(R.string.pillAdded), Toast.LENGTH_SHORT).show();
                    finish(); //Close the activity
                }
            });
        }
    }

    private boolean modifyPill() {
        try {
            editPill.setName(mNameOfPills.getText().toString());
            editPill.setDuration(mNumberPicker.getValue());
            editPill.setMorning(mFragmentMME.isMorningCheckBoxChecked());
            editPill.setMidday(mFragmentMME.isMidDayCheckBoxChecked());
            editPill.setEvening(mFragmentMME.isEveningCheckBoxChecked());
            BankPills.getInstance(this).updatePill(editPill);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void setEditMode(int idPill) {
        editPill = BankPills.getInstance(this).getPill(idPill);
        //change page title
        mTitle.setText(getString(R.string.editPill));
        //set the name of the pill in the edit text
        mNameOfPills.setText(editPill.getName());
        //set the number of day in the number picker
        mNumberPicker.setValue(editPill.getDuration());
        //change the text of the button
        mAddbutton.setText(getString(R.string.submit_button));
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

    private Fragment initFragment(boolean morning, boolean midDay, boolean evening){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        fragment = CheckBoxMMEFragment.newInstance(morning, midDay, evening);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_lineare_layout, fragment)
                .commit();
        return fragment;
    }
}