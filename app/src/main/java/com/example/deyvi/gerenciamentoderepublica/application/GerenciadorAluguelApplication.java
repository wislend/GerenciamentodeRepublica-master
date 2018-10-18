package com.example.deyvi.gerenciamentoderepublica.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;
import com.facebook.stetho.Stetho;
import com.pixplicity.easyprefs.library.Prefs;

import net.danlew.android.joda.JodaTimeAndroid;


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
        Stetho.initializeWithDefaults(this);
        JodaTimeAndroid.init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private static final String TAG = "GerenciadorAluguelApplication";

    private Apparence appAppearance;
    private static GerenciadorAluguelApplication singleton;


    public static GerenciadorAluguelApplication getInstance() {
        return singleton;
    }
}
