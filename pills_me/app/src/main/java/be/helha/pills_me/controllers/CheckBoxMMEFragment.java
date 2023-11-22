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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_check_box_mme, container, false);

        Boolean morning = getArguments().getBoolean(KEY_CHECKBOX_MORNING);
        Boolean midDay = getArguments().getBoolean(KEY_CHECKBOX_MIDDAY);
        Boolean evening = getArguments().getBoolean(KEY_CHECKBOX_EVENING);

        mMorningCheckBox = (CheckBox) v.findViewById(R.id.mornign_cb);
        mMidDayCheckBox = (CheckBox) v.findViewById(R.id.mid_day_cb);
        mEveningCheckBox = (CheckBox) v.findViewById(R.id.evening_cb);

        mMorningCheckBox.setChecked(morning);
        mMidDayCheckBox.setChecked(midDay);
        mEveningCheckBox.setChecked(evening);

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

//    public void setMorningCheckBoxChecked(boolean b) {
//        mMorningCheckBox.setChecked(b);
//    }
//
//    public void setMidDayCheckBoxChecked(boolean b) {
//        mMidDayCheckBox.setChecked(b);
//    }
//
//    public void setEveningCheckBoxChecked(boolean b) {
//        mEveningCheckBox.setChecked(b);
//    }
//
//    public void resetCheckBox() {
//        mMorningCheckBox.setChecked(false);
//        mMidDayCheckBox.setChecked(false);
//        mEveningCheckBox.setChecked(false);
//    }
}
