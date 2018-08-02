package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.application.Permissoes;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@SuppressLint("Registered")
@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity implements DialogInterface.OnClickListener{


    private String [] permissoes = new String[]{
            Manifest.permission.SEND_SMS,
            Manifest.permission.INTERNET
    };

    @ViewById
    Button btnRegistrar;

    @ViewById
    Button btnLogin;


    @AfterViews
    void initWelcomeActivity(){
        Permissoes.validaPermissoes(1,this,permissoes);
    }

    @Click
    void btnRegistrar(){
        startActivity(new Intent(getBaseContext(),CadastroActivity_.class));
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int resultado :  grantResults){
            if (resultado == PackageManager.PERMISSION_DENIED){
             AlertDialog.Builder  builder =  new AlertDialog.
                       Builder(this).
                       setTitle("Permissoes Negadas").
                       setMessage("Não podemos proseguir se as permissões não forem aceitas.").
                       setPositiveButton("Tentar Novamente",this).
                       setNegativeButton("Não aceitar",this );

             AlertDialog alertDialog = builder.create();
             alertDialog.show();

            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int id) {
        if (id == -1) {
            Permissoes.validaPermissoes(1,this,permissoes);
        }else{
            finish();
        }
    }
}
