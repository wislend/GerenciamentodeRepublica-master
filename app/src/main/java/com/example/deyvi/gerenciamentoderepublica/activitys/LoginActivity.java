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
import com.pixplicity.easyprefs.library.Prefs;

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
        try {
            validarClienteFinish(locadores.senhaValida(email, senha), null);
        } catch (Exception e) {
            validarClienteFinish(false, e);
        }

    }

    @UiThread
    public void validarClienteFinish(boolean exists, Exception ex) {
        dismissProgressDialog();
        if (ex != null) {
            DbLogs.Log(SqliteConstantes.ERRO_VERIFICAR_EXISTENCIA_LOCADOR, ex, "");
            return;
        }

        if (exists) {
            Prefs.putBoolean("LOGADO",true);
            VisaoGeral_.intent(this).start();
        } else {
            Toast.makeText(this, "Não encontramos Email ou senha semelhantes ao digitado.", Toast.LENGTH_SHORT).show();
        }
    }


}
