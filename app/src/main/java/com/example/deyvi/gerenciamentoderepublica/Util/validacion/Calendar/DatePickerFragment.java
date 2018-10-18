package com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DatePickerFragment extends DialogFragment {

    private OnDateSelectedListener mOnDateSelectedListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateTime dateTime = new DateTime();
        int year = dateTime.getYear();
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        assert getActivity() != null;
        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @SuppressLint("SetTextI18n")
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    DateTime dateTime = new DateTime(year, month, day, 0, 0, 0);
                    DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-YYYY");
                    if (mOnDateSelectedListener != null) {
                        mOnDateSelectedListener.onDateSelected(view, fmt.print(dateTime));
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



