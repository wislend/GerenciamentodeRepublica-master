package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.text.TextUtils;
import android.widget.EditText;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseFragment;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EFragment(R.layout.cadastro_locatario_fragment)
public class CadastroLocatarioFragment extends BaseFragment implements BlockingStep {

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

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    @AfterViews
    void initFragment() {
        inserirMask();
    }

    void inserirMask(){
        edtTelefone.addTextChangedListener(MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType.TELEFONE));
    }


    public boolean validation() {

        boolean valid = true;

        if (TextUtils.isEmpty(edtNome.getText())) {
            edtNome.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtNome.getText())) {
            edtNome.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }
        if (TextUtils.isEmpty(edtNome.getText())) {
            edtNome.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(edtEmail.getText().toString());
        if (!matcher.find()) {
            valid = false;
            edtEmail.setError("Email não é válido.");
        }

        return valid;

    }



    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

}
