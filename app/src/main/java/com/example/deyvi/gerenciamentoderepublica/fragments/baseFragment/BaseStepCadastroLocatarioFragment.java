package com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment;

import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locatario;

public class BaseStepCadastroLocatarioFragment extends BaseFragment {

    public Locatario getLocatario() {
        return getActivity() == null || !(getActivity() instanceof CadastroActivity) ? null :
                ((CadastroActivity) getActivity()).getLocatario();
    }

    public void setLocatario(Locatario Locatario) {
        if (getActivity() == null || !(getActivity() instanceof CadastroActivity)) {
            return;
        }
        ((CadastroActivity) getActivity()).setLocatario(Locatario);
    }
}
