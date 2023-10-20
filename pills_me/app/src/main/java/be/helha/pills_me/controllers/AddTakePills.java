package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddTakePills extends AppCompatActivity {

    private FloatingActionButton mAddPillButton;
    private Spinner mSpinnerListPills;
    private ArrayAdapter<Pill> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_take_pills);

        mAddPillButton = findViewById(R.id.open_add_pill_button);
        mAddPillButton.setOnClickListener(view -> {
            Intent intent = new Intent(AddTakePills.this, AddPillActivity.class);
            startActivity(intent);
        });

        mSpinnerListPills = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(AddTakePills.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPillsName());
        mSpinnerListPills.setAdapter(adapter);

        Log.d("MAIN", "onCreate");
    }

    protected void onResume(){
        super.onResume();
        updateSpinner();
    }

    private void updateSpinner(){
        adapter = new ArrayAdapter<>(AddTakePills.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPillsName());
        mSpinnerListPills.setAdapter(adapter);
    }
}