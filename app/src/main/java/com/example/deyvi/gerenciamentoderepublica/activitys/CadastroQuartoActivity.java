package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro_quarto)
public class CadastroQuartoActivity extends BaseActivity {

    @ViewById
    MaterialEditText dataEntrada;

    @ViewById
    MaterialEditText dataSaida;


    @Click
    void dataEntrada(){
        setEditTextDatePicker(dataEntrada);
    }


    @Click
    void dataSaida(){
        setEditTextDatePicker(dataSaida);
    }
}
