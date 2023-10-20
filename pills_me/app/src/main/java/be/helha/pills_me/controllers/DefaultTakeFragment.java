package be.helha.pills_me.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import be.helha.pills_me.R;
import be.helha.pills_me.models.Pill;

public class DefaultTakeFragment extends Fragment {
    protected Pill mPill;

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
        View v = inflater.inflate(R.layout.fragment_default_take, container, false);

        if(mPill != null){
            mMorningCheckBox = (CheckBox) v.findViewById(R.id.mornign_cb);
            mMorningCheckBox.setChecked(mPill.isMorning());
            mMorningCheckBox.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> mPill.setMorning(isChecked)
            );

            mMidDayCheckBox = (CheckBox) v.findViewById(R.id.mid_day_cb);
            mMidDayCheckBox.setChecked(mPill.isMidDay());
            mMidDayCheckBox.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> mPill.setMidDay(isChecked)
            );

            mEveningCheckBox = (CheckBox) v.findViewById(R.id.evening_cb);
            mEveningCheckBox.setChecked(mPill.isEvening());
            mEveningCheckBox.setOnCheckedChangeListener(
                    (buttonView, isChecked) -> mPill.setEvening(isChecked)
            );
        }


        return v;
    }

    public boolean isMorningCheckBoxChecked(){
        return mMorningCheckBox.isChecked();
    }

    public boolean isMidDayCheckBoxChecked(){
        return mMidDayCheckBox.isChecked();
    }

    public boolean isEveningCheckBoxChecked(){
        return mEveningCheckBox.isChecked();
    }
}
