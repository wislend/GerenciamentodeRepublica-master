package com.example.deyvi.gerenciamentoderepublica.adapters.bases;

import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.FragmentW;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;


@EFragment
public class BaseImovelDetalhesPageFragment extends FragmentW {


    @FragmentArg
    protected Imovel imovel;

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }


}
