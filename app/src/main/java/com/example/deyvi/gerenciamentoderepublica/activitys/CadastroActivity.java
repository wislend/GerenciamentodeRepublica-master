package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.activitys.base.BaseActivity;
import com.example.deyvi.gerenciamentoderepublica.adapters.CadastroStepAdapter;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro)
public class CadastroActivity extends BaseActivity implements StepperLayout.StepperListener{

    @InstanceState
    Locador locador;

    @InstanceState
    Endereco endereco;


    @InstanceState
    Imovel imovel;



    @AfterViews
    void initCadastroActitity() {

        if (locador == null) {
            locador = new Locador();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_content_clear);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        StepperLayout mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperLayout.setListener(this);
        mStepperLayout.setAdapter(new CadastroStepAdapter(getSupportFragmentManager(), this));
    }


    @Override
    public void onCompleted(View completeButton) {
            VisaoGeral_.intent(this).start();
            finish();
    }



    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Descartar seu cadastro?")
                .setMessage("As informações preenchidas até agora serão perdidas.")
                .setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CadastroActivity.super.onBackPressed();
                    }
                }).setNegativeButton("Cancelar", null)
                .show();
    }

    public Locador getlocador() {
        return locador;
    }

    public void setlocador(Locador locador) {
        this.locador = locador;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
