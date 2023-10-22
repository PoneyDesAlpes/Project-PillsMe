package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddPillActivity extends AppCompatActivity {

    private EditText mNameOfPills;
    private Button mAddbutton;
    private DefaultTakeFragment mFragmentController;
    private boolean mPillCreated;
    private Pill mCurrentPillCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);

        mPillCreated = false;

        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragmentController = (DefaultTakeFragment) fragmentManager.findFragmentById(R.id.fragmentContainerView);

        mNameOfPills = findViewById(R.id.name_pill_edit_text);
        mAddbutton = findViewById(R.id.add_button);
        mAddbutton.setOnClickListener(view -> {
            createPill();
            if (mPillCreated && mCurrentPillCreated != null){
                Toast.makeText(this, mCurrentPillCreated.getName()
                        + " "
                        + getString(R.string.pillAdded), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    private void createPill(){
        if(mNameOfPills.getText().toString().equals("")){
            return;
        }

        mCurrentPillCreated = new Pill(mNameOfPills.getText().toString(),
                mFragmentController.isMorningCheckBoxChecked(),
                mFragmentController.isMidDayCheckBoxChecked(),
                mFragmentController.isEveningCheckBoxChecked());

        BankPills.getInstance().AddPill(mCurrentPillCreated);
        mPillCreated = true;
    }
}