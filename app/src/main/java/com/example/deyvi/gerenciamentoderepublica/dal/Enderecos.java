package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Endereco;

public class Enderecos extends Dao<Endereco> {

    @Override
    public Long save(Endereco endereco) {
       try{
           DbLogs.Log(SqliteConstantes.ENDERECO_SALVO);
           return endereco.save();
       }catch (Exception e){
           DbLogs.Log(SqliteConstantes.ERRO_SALVAR_ENDERECO, e , "");
       }
        return null;

    }

    @Override
    public void update(Endereco endereco) {
    }

    @Override
    public void delete(Endereco endereco) {
           endereco.delete();
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


    public boolean exists(String cep,String numero) {
        try{
            return new Select().from(Endereco.class).where("cep = ? and numero = ? ", cep,numero).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_ENDERECO_EXISTS,e,cep);
        }
        return false;
    }
}
