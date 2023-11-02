//package be.helha.pills_me.controllers;
//
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.os.Bundle;
//import android.widget.DatePicker;
//import android.widget.TextView;
//
//import androidx.fragment.app.DialogFragment;
//import java.util.Calendar;
//
//public class DatePickerFragment extends DialogFragment
//        implements DatePickerDialog.OnDateSetListener {
//
//    private int year;
//    private int month;
//    private int day;
//
//    private TextView test;
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState){
//        final Calendar c = Calendar.getInstance();
//        year = c.get(Calendar.YEAR);
//        month = c.get(Calendar.MONTH);
//        day = c.get(Calendar.DAY_OF_MONTH);
//
//        return new DatePickerDialog(requireContext(), this, year, month, day);
//    }
//    @Override
//    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//    }
//}
