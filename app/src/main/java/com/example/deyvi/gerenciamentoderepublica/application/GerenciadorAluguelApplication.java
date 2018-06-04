package com.example.deyvi.gerenciamentoderepublica.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ContextWrapper;

import com.activeandroid.ActiveAndroid;
import com.pixplicity.easyprefs.library.Prefs;


@SuppressLint("Registered")
public class GerenciadorAluguelApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        ActiveAndroid.initialize(this);
    }

    private static final String TAG = "GerenciadorAluguelApplication";

    private Apparence appAppearance;
    private static GerenciadorAluguelApplication singleton;


    public static GerenciadorAluguelApplication getInstance() {
        return singleton;
    }
}
