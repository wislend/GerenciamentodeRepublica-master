package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;

public class Enderecos extends Dao<Endereco> {

    @Override
    public Long save(Endereco endereco) {
        return endereco.save();
    }

    @Override
    public boolean update(Endereco endereco) {
        return false;
    }

    @Override
    public boolean delete(Endereco endereco) {
        return false;
    }

    @Override
    public Endereco select(Long id) {

        Endereco endereco = new Endereco();

        try{
            endereco = new Select().from(Endereco.class).where("id = ?", id).executeSingle();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_SELECT_ENDERECO, e , "");
        }
        return endereco;
    }

    @Override
    public boolean exists(String cep) {
        try{
            return new Select().from(Endereco.class).where("cep = ?", cep).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_ENDERECO_EXISTS,e,cep);
        }
        return false;
    }
}
