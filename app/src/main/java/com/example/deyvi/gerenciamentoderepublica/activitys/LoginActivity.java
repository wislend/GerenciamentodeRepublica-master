package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.bll.Locadores;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;


@SuppressLint("Registered")
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewById
    EditText edtEmail;
    @ViewById
    EditText edtSenha;
    @ViewById
    CheckBox checkLembrarSenha;
    @ViewById
    Button btnEntrar;
    @ViewById
    TextView txtEsqueceuSenha;

    @AfterViews
    void afterViews() {

    }

    @Click
    void btnEntrar() {
        showProgressDialog("Validando...");
        validarUsuario(edtEmail.getText().toString(), edtSenha.getText().toString());
    }


    @Background
    public void validarUsuario(String email, String senha) {
        Locadores locadores = new Locadores();
        Locador locador = new Locador();
        try {
            locador = locadores.senhaValida(email, senha);
            validarClienteFinish(locador, null);
        } catch (Exception e) {
            validarClienteFinish(locador, e);
        }

    }

    @UiThread
    public void validarClienteFinish(Locador locador, Exception ex) {
        dismissProgressDialog();
        if (ex != null) {
            DbLogs.Log(SqliteConstantes.ERRO_VERIFICAR_EXISTENCIA_LOCADOR, ex, locador.getEmail());
            return;
        }

        if (locador != null) {
            VisaoGeral_.intent(this).start();
        } else {
            Toast.makeText(this, "Não encontramos Email ou senha semelhantes ao digitado.", Toast.LENGTH_SHORT).show();
        }
    }


}
