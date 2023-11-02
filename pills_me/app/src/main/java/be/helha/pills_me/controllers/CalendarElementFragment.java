package be.helha.pills_me.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import be.helha.pills_me.R;
import be.helha.pills_me.models.Pill;

public class CalendarElementFragment extends Fragment {
    private static final int FONT_SIZE = 22;
    public static final String PILL_KEY = "pill_key";
    private Pill mPill;
    private TextView mDate;
    private TextView mMorning;
    private TextView mMidDay;
    private TextView mEvening;
    private LinearLayout mContMorning;
    private LinearLayout mContMidDay;
    private LinearLayout mContEvening;

    public static CalendarElementFragment newInstance(Pill pill){
        CalendarElementFragment fragment = new CalendarElementFragment();
        Bundle args = new Bundle();
        args.putSerializable(PILL_KEY, pill);
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

        mPill = (Pill) getArguments().getSerializable(PILL_KEY);

        mDate = v.findViewById(R.id.date_text_view);
        mMorning = v.findViewById(R.id.morning_text_view);
        mMidDay = v.findViewById(R.id.mid_day_text_view);
        mEvening = v.findViewById(R.id.evening_text_view);

        mContMorning = v.findViewById(R.id.morning_container_pill);
        mContMidDay = v.findViewById(R.id.mid_day_container_pill);
        mContEvening = v.findViewById(R.id.evening_container_pill);

        if(mPill != null){
            if (mPill.isMorning()) {
                mContMorning.addView(createtextView(mPill.getName()));
            } else {
                mMorning.setVisibility(View.GONE);
                mContMorning.setVisibility(View.GONE);
            }
            if (mPill.isMidDay()) {
                mContMidDay.addView(createtextView(mPill.getName()));
            } else {
                mMidDay.setVisibility(View.GONE);
                mContMidDay.setVisibility(View.GONE);
            }
            if (mPill.isEvening()) {
                mContEvening.addView(createtextView(mPill.getName()));
            } else {
                mEvening.setVisibility(View.GONE);
                mContEvening.setVisibility(View.GONE);
            }
        }
        return v;
    }

    private View createtextView(String text){
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextSize(FONT_SIZE);
        return textView;
    }
}
