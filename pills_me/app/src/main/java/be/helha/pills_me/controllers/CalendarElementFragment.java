package be.helha.pills_me.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import be.helha.pills_me.R;
import be.helha.pills_me.models.BankPills;
import be.helha.pills_me.models.CalendarElement;
import be.helha.pills_me.models.Pill;
import be.helha.pills_me.models.Prescription;

public class CalendarElementFragment extends Fragment { //TODO: Refactor this class
    private static final int FONT_SIZE = 22;
    public static final String CALENDAR_ELEMENT = "ce_key";
    //private CalendarElement mCalendarElement;
    private TextView mDate;
    private TextView mNoPrescription;
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

        CalendarElement calendarElement = (CalendarElement) getArguments().getSerializable(CALENDAR_ELEMENT);

        mDate = v.findViewById(R.id.date_text_view);
        mNoPrescription = v.findViewById(R.id.no_prescription_text_view);
        mMorning = v.findViewById(R.id.morning_text_view);
        mMidDay = v.findViewById(R.id.mid_day_text_view);
        mEvening = v.findViewById(R.id.evening_text_view);

        mContMorning = v.findViewById(R.id.morning_container_pill);
        mContMidDay = v.findViewById(R.id.mid_day_container_pill);
        mContEvening = v.findViewById(R.id.evening_container_pill);

        if (calendarElement != null) {
            mDate.setText(calendarElement.getDate());
            setVisibilityTimeLabel(calendarElement);
            if (calendarElement.getMorningPrescription().size() == 0
                    && calendarElement.getMidDayPrescription().size() == 0
                    && calendarElement.getEveningPrescription().size() == 0){
                mNoPrescription.setVisibility(View.VISIBLE);
            }
            for (Prescription morningPrescription : calendarElement.getMorningPrescription()) {
                Pill pill = BankPills.getInstance(getContext()).getPill(morningPrescription.getIdPill());
                mContMorning.addView(createtextView(pill.getName()));
            }
            for (Prescription midDayPrescription : calendarElement.getMidDayPrescription()) {
                Pill pill = BankPills.getInstance(getContext()).getPill(midDayPrescription.getIdPill());
                mContMidDay.addView(createtextView(pill.getName()));
            }
            for (Prescription eveningPrescription : calendarElement.getEveningPrescription()) {
                Pill pill = BankPills.getInstance(getContext()).getPill(eveningPrescription.getIdPill());
                mContEvening.addView(createtextView(pill.getName()));
            }
        }

        return v;
    }

    private void setVisibilityTimeLabel(CalendarElement ce){
        if (ce.getMorningPrescription().size() == 0){
            mMorning.setVisibility(View.GONE);
        }
        if (ce.getMidDayPrescription().size() == 0){
            mMidDay.setVisibility(View.GONE);
        }
        if (ce.getEveningPrescription().size() == 0){
            mEvening.setVisibility(View.GONE);
        }
    }

    private View createtextView(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextSize(FONT_SIZE);
        return textView;
    }
}
