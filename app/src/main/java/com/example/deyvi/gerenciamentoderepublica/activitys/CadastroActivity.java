package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.example.deyvi.gerenciamentoderepublica.adapters.CadastroStepAdapter;
import com.stepstone.stepper.StepperLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@SuppressLint("Registered")
@EActivity(R.layout.activity_cadastro)
public class CadastroActivity extends AppCompatActivity {

    private StepperLayout mStepperLayout;

    @AfterViews
    void initCadastroActitity() {
        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new CadastroStepAdapter(getSupportFragmentManager(), this));
    }

}
