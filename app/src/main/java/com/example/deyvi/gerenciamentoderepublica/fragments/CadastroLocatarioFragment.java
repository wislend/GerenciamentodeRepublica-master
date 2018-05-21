package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.activitys.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.activitys.CadastroActivity;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locatario;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment
        .BaseStepCadastroLocatarioFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EFragment(R.layout.cadastro_locatario_fragment)
public class CadastroLocatarioFragment extends BaseStepCadastroLocatarioFragment implements Step {

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

    void inserirMask() {
        edtTelefone.addTextChangedListener(MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType
                .TELEFONE));
    }


    public boolean validation() {

        boolean valid = true;
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(edtEmail.getText().toString());


        if (TextUtils.isEmpty(edtNome.getText())) {
            edtNome.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtSenha.getText())) {
            edtSenha.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }else if (!edtSenha.getText().toString().contains(edtConfirmacaoSenha.getText().toString())){
            edtSenha.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }


        if (TextUtils.isEmpty(edtEmail.getText())) {
            edtEmail.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }else if (!matcher.find()){
            valid = false;
        edtEmail.setError(getString(R.string.email_invalido));
        }

        if(TextUtils.isEmpty(edtSenha.getText())){
            edtSenha.setError(getString(R.string.senha_invalido));
        }else  if (edtSenha.getText().toString().length() < 6) {
            edtSenha.setError(getString(R.string.tamanho_senha));
        }

        return valid;

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        //Valida se o campo está vazio e regex informados no afterViews
        if (validation()) {
            return new VerificationError("Corrija as pendências no formulário");
        }






            //DADOS CADASTRAIS VALIDADOS
            getLocatario().setNome (edtNome.getText().toString());
            getLocatario().setEmail(edtEmail.getText().toString());
            getLocatario().setSenha(edtSenha.getText().toString());
            getLocatario().setTelefone(edtTelefone.getText().toString());

        return null;
    }

    @Override
    public void onSelected() {
        final BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null && baseActivity.getSupportActionBar() != null) {
            baseActivity.setTitle("Cadastro locatario");
        }
    }


    @Override
    public void onError(@NonNull VerificationError error) {

    }


/*

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        showProgressDialog("Salvando seu cadastro...");
        salvarCadastroClienteBackground();
    }
*/




}
