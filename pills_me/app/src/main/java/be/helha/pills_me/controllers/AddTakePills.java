package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddTakePills extends AppCompatActivity {

    private Button mAddPillButton;
    private Spinner mSpinnerListPills;

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
        ArrayAdapter<Pill> adapter = new ArrayAdapter<>(AddTakePills.this, android.R.layout.simple_spinner_dropdown_item, BankPills.getInstance().getBankPills());
        mSpinnerListPills.setAdapter(adapter);
    }
}