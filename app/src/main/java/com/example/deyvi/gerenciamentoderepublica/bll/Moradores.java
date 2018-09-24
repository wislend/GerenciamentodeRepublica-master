package com.example.deyvi.gerenciamentoderepublica.bll;

import com.activeandroid.ActiveAndroid;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;

public class Moradores {

    private com.example.deyvi.gerenciamentoderepublica.dal.Moradores moradores;

    public Moradores() {
        this.moradores = new com.example.deyvi.gerenciamentoderepublica.dal.Moradores();
    }

    public Long salvarMorador(Morador morador){
        return moradores.save(morador);
    }

    public boolean moradorExiste(String telefone){
       return moradores.exists(telefone);
    }


}
