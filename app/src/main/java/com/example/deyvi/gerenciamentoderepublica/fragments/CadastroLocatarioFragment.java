package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.widget.EditText;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.cadastro_locatario_fragment)
public class CadastroLocatarioFragment extends BaseFragment{

    @ViewById
    EditText edtNome;
    @ViewById
    EditText edtTelefone;
    @ViewById
    EditText edtEmail;
    @ViewById
    EditText edtSenha;
    @ViewById
    EditText edtConfirmacaoSenha;


    @AfterViews
    void initFragment() {



    }

}
