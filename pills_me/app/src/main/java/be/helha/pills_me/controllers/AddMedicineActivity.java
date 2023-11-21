package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
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

    private TextView mTitlePage;
    private EditText mNameOfPills;
    private Button mSubmitButton;
    private checkBoxMMEFragment mFragmentController;
    private NumberPicker mNumberPicker;
    private Pill mCurrentPill;

    private Boolean mIsEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentController = (checkBoxMMEFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);
        //mFragmentController.onCreateView(getLayoutInflater(), null, savedInstanceState);

        mTitlePage = findViewById(R.id.title_panel_add_pils_text_view);

        mNameOfPills = findViewById(R.id.name_pill_edit_text);
        mSubmitButton = findViewById(R.id.add_button);

        mNumberPicker = findViewById(R.id.day_number_picker);
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(30);

        Intent intent = getIntent();
        int idPill = intent.getIntExtra(AddPrescriptionActivity.KEY_ID_PILL, -1);
        if(idPill != -1){
            mCurrentPill = BankPills.getInstance(this).getPill(idPill);
            setEditModePill();
            mIsEditMode = true;
        }

        if (mIsEditMode){
            mSubmitButton.setText(R.string.modify_button);
        }
        if (!mIsEditMode){
            mSubmitButton.setText(R.string.add_button);
        }

        mSubmitButton.setOnClickListener(view -> {
            if(mIsEditMode){
                boolean isPillModified = modifyPill();
                if (isPillModified && mCurrentPill != null){
                    Toast.makeText(this, mCurrentPill.getName()
                            + " "
                            + getString(R.string.pillModified), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            if(!mIsEditMode){
                boolean isPillCreate =createPill();
                if (isPillCreate && mCurrentPill != null){
                    Toast.makeText(this, mCurrentPill.getName()
                            + " "
                            + getString(R.string.pillAdded), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void setEditModePill() {
        mTitlePage.setText(R.string.edit_pill_title);
        mNameOfPills.setText(mCurrentPill.getName());
        mNumberPicker.setValue(mCurrentPill.getDuration());
        if (mFragmentController != null) {
            mFragmentController.setMorningCheckBox(mCurrentPill.isMorning());
            mFragmentController.setMidDayCheckBox(mCurrentPill.isMidDay());
            mFragmentController.setEveningCheckBox(mCurrentPill.isEvening());
        }
    }

    private boolean createPill(){
        if(mNameOfPills.getText().toString().equals("")){
            return false;
        }

        mCurrentPill = new Pill(
                mNameOfPills.getText().toString(),
                mNumberPicker.getValue(),
                mFragmentController.isMorningCheckBoxChecked(),
                mFragmentController.isMidDayCheckBoxChecked(),
                mFragmentController.isEveningCheckBoxChecked());

        BankPills.getInstance(this).addPill(mCurrentPill);
        return true;
    }

    private boolean modifyPill() {
        if(mNameOfPills.getText().toString().equals("")){
            return false;
        }

        mCurrentPill.setName(mNameOfPills.getText().toString());
        mCurrentPill.setDuration(mNumberPicker.getValue());
        mCurrentPill.setMorning(mFragmentController.isMorningCheckBoxChecked());
        mCurrentPill.setMidDay(mFragmentController.isMidDayCheckBoxChecked());
        mCurrentPill.setEvening(mFragmentController.isEveningCheckBoxChecked());

        BankPills.getInstance(this).updatePill(mCurrentPill);
        return true;
    }

}