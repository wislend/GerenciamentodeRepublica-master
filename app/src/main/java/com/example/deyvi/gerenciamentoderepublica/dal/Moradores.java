package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Morador;

public class Moradores extends  Dao<Morador>{


    @Override
    public Long save(Morador morador) {
        return morador.save();
    }

    @Override
    public boolean update(Morador morador) {
        return false;
    }

    @Override
    public boolean delete(Morador morador) {
        return false;
    }

    @Override
    public boolean exists(String telefone) {
        try{
            return new Select().from(Morador.class).where("telefone = ?", telefone).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_MORADOR_EXISTS,e,telefone);
        }
        return false;
    }
}
