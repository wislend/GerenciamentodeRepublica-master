package com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment{

    private OnDateSelectedListener mOnDateSelectedListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                public void onDateSet(DatePicker view, int year, int month, int day) {

                    Date date = new Date(year, month, day);
                    @SuppressLint("SimpleDateFormat")
                    String dateFormatted = new SimpleDateFormat("dd/MM/yyyy").format(date);


                    if (mOnDateSelectedListener != null) {
                        mOnDateSelectedListener.onDateSelected(view, dateFormatted);
                    }
                }
            };

    public OnDateSelectedListener getOnDateSelectedListener() {
        return mOnDateSelectedListener;
    }

    public void setOnDateSelectedListener(OnDateSelectedListener mOnDateSelectedListener) {
        this.mOnDateSelectedListener = mOnDateSelectedListener;
    }


    public interface OnDateSelectedListener {
        void onDateSelected(DatePicker view, String formattedDate);
    }



}



