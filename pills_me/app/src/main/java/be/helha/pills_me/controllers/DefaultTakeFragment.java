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
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        mPill = new Pill(); //ToDo: cahnger pour un aramètre qui est passé lors de la création du frag
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_default_take, container, false);

        mMorningCheckBox = (CheckBox) v.findViewById(R.id.mornign_cb);
        mMorningCheckBox.setChecked(mPill.isMorning());
        mMorningCheckBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mPill.setMorning(isChecked);
                    }
                }
        );

        return v;
    }
}
