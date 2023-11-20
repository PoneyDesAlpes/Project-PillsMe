package be.helha.pills_me.controllers;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.BankPrescription;
import be.helha.pills_me.models.CalendarElement;
import be.helha.pills_me.models.Pill;
import be.helha.pills_me.models.Prescription;

public class CalendarElementFragment extends Fragment {
    private static final int FONT_SIZE = 22;
    public static final String CALENDAR_ELEMENT = "ce_key";
    private CalendarElement mCalendarElement;
    private TextView mDate;
    private TextView mMorning;
    private TextView mMidDay;
    private TextView mEvening;
    private LinearLayout mContMorning;
    private LinearLayout mContMidDay;
    private LinearLayout mContEvening;

    public static CalendarElementFragment newInstance(CalendarElement ce) {
        CalendarElementFragment fragment = new CalendarElementFragment();
        Bundle args = new Bundle();
        args.putSerializable(CALENDAR_ELEMENT, ce);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar_element, container, false);

        mCalendarElement = (CalendarElement) getArguments().getSerializable(CALENDAR_ELEMENT);

        mDate = v.findViewById(R.id.date_text_view);
        mMorning = v.findViewById(R.id.morning_text_view);
        mMidDay = v.findViewById(R.id.mid_day_text_view);
        mEvening = v.findViewById(R.id.evening_text_view);

        mContMorning = v.findViewById(R.id.morning_container_pill);
        mContMidDay = v.findViewById(R.id.mid_day_container_pill);
        mContEvening = v.findViewById(R.id.evening_container_pill);

        if (mCalendarElement != null) {
            mDate.setText(mCalendarElement.getDate());
            for (Prescription morningPrescription : mCalendarElement.getMorningPrescription()) {
                Pill pill = BankPills.getInstance(getContext()).getPill(morningPrescription.getPillId());
                mContMorning.addView(createtextView(pill.getName()));

            }
            for (Prescription midDayPrescription : mCalendarElement.getMidDayPrescription()) {
                Pill pill = BankPills.getInstance(getContext()).getPill(midDayPrescription.getPillId());
                mContMidDay.addView(createtextView(pill.getName()));
            }
            for (Prescription eveningPrescription : mCalendarElement.getEveningPrescription()) {
                Pill pill = BankPills.getInstance(getContext()).getPill(eveningPrescription.getPillId());
                mContEvening.addView(createtextView(pill.getName()));
            }
        }

        return v;
    }

    private View createtextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextSize(FONT_SIZE);
        return textView;
    }
}
