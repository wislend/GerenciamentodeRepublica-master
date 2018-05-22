package com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment;

import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locatario;

public class BaseStepCadastroLocatarioFragment extends BaseFragment {

    public Locatario getLocatario() {
        return getActivity() == null || !(getActivity() instanceof CadastroActivity) ? null :
                ((CadastroActivity) getActivity()).getLocatario();
    }

    public void setLocatario(Locatario locatario) {
        if (getActivity() == null || !(getActivity() instanceof CadastroActivity)) {
            return;
        }
        ((CadastroActivity) getActivity()).setLocatario(locatario);
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




}
