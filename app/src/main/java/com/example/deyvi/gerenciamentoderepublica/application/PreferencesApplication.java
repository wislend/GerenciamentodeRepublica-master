package com.example.deyvi.gerenciamentoderepublica.application;


import android.content.Context;

import com.example.deyvi.gerenciamentoderepublica.application.basePreferences.BasePreferences;

public class PreferencesApplication extends BasePreferences {

    public static final String PREF_NAME_FILE = "Gerenciador_Aluguel";
    private static final String THEME = "THEME";


    public static int getCurrentTheme(Context context) {
        return getInt(context, PREF_NAME_FILE, THEME, ThemeApplication.Theme.LIGHT.getValue());
    }




    }
