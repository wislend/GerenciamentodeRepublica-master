package com.example.deyvi.gerenciamentoderepublica.application.basePreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.deyvi.gerenciamentoderepublica.constantsApp.PreferencsConstants;

public class Preferencs {

    private Context context;
    private SharedPreferences preferences;
    private String NOMEARQUIVO = "aluguel.prefences";
    private Integer MODE = 0;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public Preferencs(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(NOMEARQUIVO, MODE);
        this.editor = preferences.edit();
    }

    private void userPrefences(String nome, String telefone, Integer token) {
            editor.putString(PreferencsConstants.NOME,nome);
            editor.putString(PreferencsConstants.TELEFONE,telefone);
            editor.putInt(PreferencsConstants.TOKEN,token);
            editor.commit();


    }
}
