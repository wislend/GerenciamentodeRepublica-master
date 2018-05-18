package com.example.deyvi.gerenciamentoderepublica.application;

import android.annotation.SuppressLint;
import android.app.Application;



@SuppressLint("Registered")
public class GerenciadorAluguelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private static final String TAG = "GerenciadorAluguelApplication";

    private Apparence appAppearance;
    private static GerenciadorAluguelApplication singleton;


    public static GerenciadorAluguelApplication getInstance() {
        return singleton;
    }
}
