package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import be.helha.pills_me.R;

public class AddPillsActivity extends AppCompatActivity {

    private EditText mNameOfPills = findViewById(R.id.name_pill_edit_text);
    private CheckBox mMorningCheckBox = findViewById(R.id.mornign_cb);
    private CheckBox mMidDayCheckBox = findViewById(R.id.mid_day_cb);
    private CheckBox mEveningCheckBox = findViewById(R.id.evening_cb);
    private Button mAddbutton = findViewById(R.id.add_button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pills);
    }
}