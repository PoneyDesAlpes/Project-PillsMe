package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
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
    private checkBoxMMEFragment mFragmentController;
    private NumberPicker mNumberPicker;
    private Pill mCurrentPillCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentController = (checkBoxMMEFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);

        mNameOfPills = findViewById(R.id.name_pill_edit_text);
        mAddbutton = findViewById(R.id.add_button);
        mAddbutton.setOnClickListener(view -> {
            boolean isPillCreate =createPill();
            if (isPillCreate && mCurrentPillCreated != null){
                Toast.makeText(this, mCurrentPillCreated.getName()
                        + " "
                        + getString(R.string.pillAdded), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        mNumberPicker = findViewById(R.id.day_number_picker);
        mNumberPicker.setMinValue(0b1);
        mNumberPicker.setMaxValue(31);

    }
    private boolean createPill(){
        if(mNameOfPills.getText().toString().equals("")){
            return false;
        }

        mCurrentPillCreated = new Pill(
                mNameOfPills.getText().toString(),
                mNumberPicker.getValue(),
                mFragmentController.isMorningCheckBoxChecked(),
                mFragmentController.isMidDayCheckBoxChecked(),
                mFragmentController.isEveningCheckBoxChecked());

        BankPills.getInstance(this).addPill(mCurrentPillCreated);
        return true;
    }
}