package com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment;

import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;

public class BaseStepCadastroLocatarioFragment extends BaseFragment {

    public Locador getLocador() {
        return getActivity() == null || !(getActivity() instanceof CadastroActivity) ? null :
                ((CadastroActivity) getActivity()).getlocador();
    }

    public void setLocador(Locador locador) {
        if (getActivity() == null || !(getActivity() instanceof CadastroActivity)) {
            return;
        }
        ((CadastroActivity) getActivity()).setlocador(locador);
    }


    public Endereco getEndereco() {
        return getActivity() == null || !(getActivity() instanceof CadastroActivity) ? null :
                ((CadastroActivity) getActivity()).getEndereco();
    }

    public void setEndereco(Endereco endereco) {
        if (getActivity() == null || !(getActivity() instanceof CadastroActivity)) {
            return;
        }
        ((CadastroActivity) getActivity()).setEndereco(endereco);
    }


    public Imovel getImovel() {
        return getActivity() == null || !(getActivity() instanceof CadastroActivity) ? null :
                ((CadastroActivity) getActivity()).getImovel();
    }

    public void setImovel(Imovel imovel) {
        if (getActivity() == null || !(getActivity() instanceof CadastroActivity)) {
            return;
        }
        ((CadastroActivity) getActivity()).setImovel(imovel);
    }


}
