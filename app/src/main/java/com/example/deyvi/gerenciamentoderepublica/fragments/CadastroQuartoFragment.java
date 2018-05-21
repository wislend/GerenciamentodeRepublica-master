package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.support.v4.app.Fragment;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locatario;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;


@EFragment(R.layout.fragment_cadastro_quarto)
public class CadastroQuartoFragment extends Fragment {

/*

    @Background
    void salvarCadastroClienteBackground() {

        try {

            //TODO: SALVAR_CLIENTE
            Locatario locatario = getLocatario();

            salvarCadastroClienteFinished(locatario, null);
        } catch (Exception ex) {
            salvarCadastroClienteFinished(null, ex);
        }
    }

    @UiThread(delay = 3000)
        //todo remover esse delay
    void salvarCadastroClienteFinished(Locatario locatario, Exception ex) {

        dismissProgressDialog();

        //Cliente cadastrado, seu usuário foi criado e ele agora tem acesso ao ION Direto
        //Vai pra tela de boas vindas e pode fechar essa activity
        setLocatario(locatario);
        //chamando aqui, o método do callback .complete() não funcionou
        ((CadastroActivity) getActivity()).onCompleted(null);
    }
*/




}
