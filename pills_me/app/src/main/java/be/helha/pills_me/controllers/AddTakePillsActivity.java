package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddTakePillsActivity extends AppCompatActivity {

    private FloatingActionButton mAddPillButton;
    private Spinner mSpinnerListPills;
    private ArrayAdapter<Pill> adapter;
    private Button mAddTakePillButton;
    private DefaultTakeFragment mFragmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_take_pills);

        //Button pour ajouter un pill dans la vue calendar
        //mAddTakePillButton = findViewById(R.id.add_take_pill_button);

        mFragmentController = (DefaultTakeFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);


        mAddPillButton = findViewById(R.id.open_add_pill_button);
        mAddPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddTakePillsActivity.this, AddPillActivity.class);
            startActivity(intent);
        });

        mSpinnerListPills = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(AddTakePillsActivity.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPillsName());
        mSpinnerListPills.setAdapter(adapter);

        mSpinnerListPills.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for(Pill p : BankPills.getInstance().getBankPills()){
                    if(p.getName().equals(mSpinnerListPills.getSelectedItem().toString())){
                        setCheckBox(p);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    protected void onResume(){
        super.onResume();
        updateSpinner();
    }

    private void updateSpinner(){
        adapter = new ArrayAdapter<>(AddTakePillsActivity.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPillsName());
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