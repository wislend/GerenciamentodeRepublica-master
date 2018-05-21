package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.CadastroStepAdapter;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locatario;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro)
public class CadastroActivity extends BaseActivity implements StepperLayout.StepperListener{

    private StepperLayout mStepperLayout;


    @InstanceState
    Locatario locatario;

    @AfterViews
    void initCadastroActitity() {

        if (locatario == null) {
            locatario = new Locatario();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_content_clear);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperLayout.setListener(this);
        mStepperLayout.setAdapter(new CadastroStepAdapter(getSupportFragmentManager(), this));
    }


    @Override
    public void onCompleted(View completeButton) {

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

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
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
