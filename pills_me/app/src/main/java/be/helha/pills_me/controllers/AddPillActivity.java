package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.Pill;

public class AddPillActivity extends AppCompatActivity {

    private EditText mNameOfPills;
    private Button mAddbutton;

    private BankPills mBankPills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);

        mNameOfPills = findViewById(R.id.name_pill_edit_text);
        mAddbutton = findViewById(R.id.add_button);
        mAddbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BankPills.getInstance().AddPill(new Pill(mNameOfPills.getText().toString(),true, false, false));
            }
        });
    }
}