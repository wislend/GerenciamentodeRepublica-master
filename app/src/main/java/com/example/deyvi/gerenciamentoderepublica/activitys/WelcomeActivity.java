package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.deyvi.gerenciamentoderepublica.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@SuppressLint("Registered")
@EActivity(R.layout.activity_welcome)
public class WelcomeActivity extends AppCompatActivity {


    @ViewById
    Button btnRegistrar;

    @ViewById
    Button btnLogin;


    @AfterViews
    void initWelcomeActivity(){

    }

    @Click
    void btnRegistrar(){
        startActivity(new Intent(getBaseContext(),CadastroActivity_.class));
    }

}
