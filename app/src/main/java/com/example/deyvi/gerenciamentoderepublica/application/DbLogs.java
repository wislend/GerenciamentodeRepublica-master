package com.example.deyvi.gerenciamentoderepublica.application;

import com.activeandroid.util.Log;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;

public class DbLogs {


    public static void Log(String mensagem,Exception e, String nome){

        Log.i(SqliteConstantes.LOG, mensagem +
                nome + " " +
                e.getCause() + " "
                + e.getMessage());
    }

    public static void Log(String mensagem,Exception e, Integer numero){

        Log.i(SqliteConstantes.LOG, mensagem +
                numero + " " +
                e.getCause() + " "
                + e.getMessage());
    }

    public static void Log(String mensagem){
        Log.i(SqliteConstantes.LOG, mensagem);
    }

}
