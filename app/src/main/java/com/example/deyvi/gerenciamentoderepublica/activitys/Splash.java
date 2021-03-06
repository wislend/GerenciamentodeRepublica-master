package com.example.deyvi.gerenciamentoderepublica.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.deyvi.gerenciamentoderepublica.R;
import com.pixplicity.easyprefs.library.Prefs;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;


@SuppressLint("Registered")
@EActivity(R.layout.activity_splash)
public class Splash extends AppCompatActivity {


    @AfterViews
    void initSplash(){

        if (Prefs.getBoolean("LOGADO",false)){
            VisaoGeral_.intent(this).start();
        }else {
            goWelcome();
        }

    }


    void goWelcome(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(),WelcomeActivity_.class));
                finish();
            }
        }, 2000);

        try{

        }catch (Exception e){

        }
    }

}
