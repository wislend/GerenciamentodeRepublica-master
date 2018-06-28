package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.support.v4.app.DialogFragment;
import android.view.View;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.Calendar.DatePickerFragment;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseFragment;

import org.androidannotations.annotations.EFragment;


@EFragment(R.layout.fragment_cadastro_quarto)
public class CadastroQuartoFragment extends BaseFragment {



    public void showDatePicker(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "date picker");
    }


}
