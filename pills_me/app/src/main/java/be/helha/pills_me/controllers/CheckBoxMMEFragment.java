package be.helha.pills_me.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.helha.pills_me.R;

public class CheckBoxMMEFragment extends Fragment {
    public static final String KEY_CHECKBOX_MORNING = "KEY_CHECKBOX_MORNING";
    public static final String KEY_CHECKBOX_MIDDAY = "KEY_CHECKBOX_MIDDAY";
    public static final String KEY_CHECKBOX_EVENING = "KEY_CHECKBOX_EVENING";

    private CheckBox mMorningCheckBox;
    private CheckBox mMidDayCheckBox;
    private CheckBox mEveningCheckBox;

    public static Fragment newInstance(boolean morning, boolean midday, boolean evening) {
        Bundle args = new Bundle();
        args.putBoolean(KEY_CHECKBOX_MORNING, morning);
        args.putBoolean(KEY_CHECKBOX_MIDDAY, midday);
        args.putBoolean(KEY_CHECKBOX_EVENING, evening);

        CheckBoxMMEFragment fragment = new CheckBoxMMEFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_check_box_mme, container, false);

        mMorningCheckBox = (CheckBox) v.findViewById(R.id.mornign_cb);
        mMidDayCheckBox = (CheckBox) v.findViewById(R.id.mid_day_cb);
        mEveningCheckBox = (CheckBox) v.findViewById(R.id.evening_cb);

        mMorningCheckBox.setChecked(getArguments().getBoolean(KEY_CHECKBOX_MORNING));
        mMidDayCheckBox.setChecked(getArguments().getBoolean(KEY_CHECKBOX_MIDDAY));
        mEveningCheckBox.setChecked(getArguments().getBoolean(KEY_CHECKBOX_EVENING));

        return v;
    }

    public boolean isMorningCheckBoxChecked() {
        return mMorningCheckBox.isChecked();
    }

    public boolean isMidDayCheckBoxChecked() {
        return mMidDayCheckBox.isChecked();
    }

    public boolean isEveningCheckBoxChecked() {
        return mEveningCheckBox.isChecked();
    }
}
