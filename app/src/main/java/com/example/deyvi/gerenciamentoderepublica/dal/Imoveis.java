package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

public class Imoveis extends Dao<Imovel> {


    @Override
    public Long save(Imovel imovel) {
        return null;
    }

    @Override
    public boolean update(Imovel imovel) {
        return false;
    }

    @Override
    public boolean delete(Imovel imovel) {
        return false;
    }

    @Override
    public Imovel select(Imovel imovel) {
        return null;
    }

    @Override
    public boolean exists(String nome) {
        try{
            return new Select().from(Imovel.class).where("nome = ?", nome).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_SELECT_IMOVEL,e,nome);
        }
        return false;
    }
}
