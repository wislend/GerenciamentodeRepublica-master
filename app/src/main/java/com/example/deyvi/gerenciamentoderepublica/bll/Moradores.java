package com.example.deyvi.gerenciamentoderepublica.bll;

import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;

public class Moradores {

    private com.example.deyvi.gerenciamentoderepublica.dal.Moradores moradores;

    public Moradores() {
        this.moradores = new com.example.deyvi.gerenciamentoderepublica.dal.Moradores();
    }

    public Long salvarMorador(Morador morador){
        try {
            return morador.save();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_SALVAR_MORADOR,e,morador.getNome());
        }
        return null;
    }

    public boolean moradorExiste(String telefone){
       return moradores.exists(telefone);
    }


}
