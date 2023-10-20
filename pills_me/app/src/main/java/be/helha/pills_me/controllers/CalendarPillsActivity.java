package be.helha.pills_me.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankSchedulePill;
import be.helha.pills_me.models.Pill;

public class CalendarPillsActivity extends AppCompatActivity {

    private static final int FONT_SIZE = 22;
    private FloatingActionButton mAddTakePill;
    private LinearLayout mCalendarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_pills);

        mAddTakePill = findViewById(R.id.open_add_take_pill_button);
        mAddTakePill.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddTakePillsActivity.class);
            startActivity(intent);
        });

        mCalendarContainer = findViewById(R.id.container_element_calendare);

        for(Pill p : BankSchedulePill.getInstance().getSchedulePills()){
            mCalendarContainer.addView(initContainerView(p));
        }
    }

    private View initContainerView(Pill p){
        View itemView = getLayoutInflater().inflate(R.layout.template_calendar_element, null);
        itemView.findViewById(R.id.date_text_view);//ToDo: ajouter la date dynamiquement

        TextView morning = itemView.findViewById(R.id.morning_text_view);
        TextView midDay = itemView.findViewById(R.id.mid_day_text_view);
        TextView evening = itemView.findViewById(R.id.evening_text_view);

        LinearLayout contMorning = itemView.findViewById(R.id.morning_container_pill);
        LinearLayout contMidDay = itemView.findViewById(R.id.mid_day_container_pill);
        LinearLayout contEvening = itemView.findViewById(R.id.evening_container_pill);

        if(p.isMorning()){
            contMorning.addView(createtextView(p.getName()));
        }
        else {
            morning.setVisibility(View.GONE);
            contMorning.setVisibility(View.GONE);
        }
        if(p.isMidDay()){
            contMidDay.addView(createtextView(p.getName()));
        }
        else {
            midDay.setVisibility(View.GONE);
            contMidDay.setVisibility(View.GONE);
        }
        if(p.isEvening()){
            contEvening.addView(createtextView(p.getName()));
        }
        else {
            evening.setVisibility(View.GONE);
            contEvening.setVisibility(View.GONE);
        }
        return itemView;
    }

    private View createtextView(String text){
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(FONT_SIZE);
        return textView;
    }
}