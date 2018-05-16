package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.cadastro_imovel_fragment)
public class CadastroImovelFragment extends BaseFragment {

        @ViewById
        Spinner spSituacaoImovel;


    @AfterViews
    void initCadastroImovelFrag(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.situacao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSituacaoImovel.setAdapter(adapter);
    }

}
