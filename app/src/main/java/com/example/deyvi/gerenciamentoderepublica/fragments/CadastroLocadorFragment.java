package com.example.deyvi.gerenciamentoderepublica.fragments;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.Util.validacion.MaskEditUtil;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.bll.Locadores;
import com.example.deyvi.gerenciamentoderepublica.firebase.ConfigFireBase;
import com.example.deyvi.gerenciamentoderepublica.firebase.FireBaseManager;
import com.example.deyvi.gerenciamentoderepublica.fragments.baseFragment.BaseStepCadastroLocatarioFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;

@EFragment(R.layout.cadastro_locatario_fragment)
public class CadastroLocadorFragment extends BaseStepCadastroLocatarioFragment implements Step {

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

    FirebaseAuth firebaseAuth;


    @AfterViews
    void initFragment() {
        test();
        inserirMask();
    }

    void inserirMask() {
        edtTelefone.addTextChangedListener(MaskEditUtil.insert(edtTelefone, MaskEditUtil.MaskType.TELEFONE));

    }


    public boolean validation() {

        boolean valid = true;
        Matcher matcher = MaskEditUtil.VALID_EMAIL_ADDRESS_REGEX.matcher(edtEmail.getText().toString());

        if (TextUtils.isEmpty(edtNome.getText().toString())) {
            edtNome.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        }

        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError(getString(R.string.campo_obrigatorio));
            valid = false;
        } else if (!matcher.find()) {
            valid = false;
            edtEmail.setError(getString(R.string.email_invalido));
        }


        if (TextUtils.isEmpty(edtSenha.getText().toString())) {
            edtSenha.setError(getString(R.string.campo_obrigatorio));
            edtConfirmacaoSenha.setText("");
            valid = false;
        } else if (edtSenha.getText().toString().length() < 6) {
            edtSenha.setError(getString(R.string.tamanho_senha));
            edtConfirmacaoSenha.setText("");
            valid = false;
        } else if (!edtSenha.getText().toString().equals(edtConfirmacaoSenha.getText().toString())) {
            edtSenha.setText("");
            edtConfirmacaoSenha.setText("");
            edtConfirmacaoSenha.setError(getString(R.string.senhas_diferentes));
            valid = false;
        }

        return valid;

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        //Valida se o campo está vazio e regex informados no afterViews
        if (!validation()) {
            return new VerificationError("Corrija as pendências no formulário");
        }else {
            //DADOS CADASTRAIS VALIDADOS
            getLocador().setNome(edtNome.getText().toString());
            getLocador().setEmail(edtEmail.getText().toString());
            getLocador().setSenha(edtSenha.getText().toString());
            getLocador().setTelefone(edtTelefone.getText().toString());
            salvarLocador();
        }
        return null;
    }

    @Override
    public void onSelected() {
        final BaseActivity baseActivity = getBaseActivity();
        if (baseActivity != null && baseActivity.getSupportActionBar() != null) {
            baseActivity.setTitle("");
        }
    }


    @Background
    void salvarLocador(){

        if (getActivity() == null){
            return;
        }

        firebaseAuth = ConfigFireBase.getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(
                getLocador().getEmail(),
                getLocador().getSenha()
        ).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FireBaseManager.getInstance().saveLocatario(getLocador(),task.getResult().getUser().getUid());
                    Toast.makeText(getContext(), "Usuário cadastrado pelo FireBase", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "FireBase não salvou o usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //inicia bll locadores
         Locadores locadores = new Locadores();

        //Salva locador
        locadores.salvarLocador(getLocador());
        //fecha progresso
        dismissProgressDialog();
    }

     void test(){
        edtNome.setText("Joao");
        edtEmail.setText("joao@joao.com");
        edtTelefone.setText("9999999999");
        edtSenha.setText("123456");
        edtConfirmacaoSenha.setText("123456");

     }

    @Override
    public void onError(@NonNull VerificationError error) {

    }


}
