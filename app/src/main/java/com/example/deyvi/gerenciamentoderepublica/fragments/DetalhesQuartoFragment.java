package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.bases.BaseQuartoDetalhesPageFragment;
import com.example.deyvi.gerenciamentoderepublica.bll.Imoveis;
import com.example.deyvi.gerenciamentoderepublica.bll.Moveis;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentArg;


@EFragment(R.layout.fragment_detalhes_quarto)
public class DetalhesQuartoFragment extends BaseQuartoDetalhesPageFragment {

    Imovel imovel;

    Moveis moveis;
    Movel movel;

    Quarto quarto;

    public DetalhesQuartoFragment() {
    }

    @AfterViews
    void afterViews(){
        moveis = new Moveis();
        quarto = new Quarto();
      //  moveis.listMoveis(movel,quarto);
    }

    @Override
    protected void onImovelChanged(Imovel imovel) {
        super.onImovelChanged(imovel);
        this.imovel = imovel;
    }
}
