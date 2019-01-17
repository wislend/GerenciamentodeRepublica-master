package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;

import java.util.ArrayList;
import java.util.List;

public class Imoveis extends Dao<Imovel> {


    @Override
    public Long save(Imovel imovel) {
        try{
            DbLogs.Log(SqliteConstantes.ENDERECO_SALVO);
            return imovel.save();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_SALVAR_IMOVEL, e , "");
        }
        return null;
    }




    @Override
    public void update(Imovel imovel) {
    }

    @Override
    public void delete(Imovel imovel) {
         imovel.delete();
    }

    @Override
    public Imovel select(Imovel imovel) {
        return null;
    }

    @Override
    public boolean exists(String nome) {
        try{
            DbLogs.Log(SqliteConstantes.SUCESSFUL_SELECT_IMOVEL);
            return new Select().from(Imovel.class).where("nome = ?", nome).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_SELECT_IMOVEL,e,nome);
        }

        return false;
    }

    @Override
    public List<Imovel> selectAll() {
        try {
            DbLogs.Log(SqliteConstantes.SUCESSFUL_SELECT_IMOVEL_ALL);
            return new Select().all().from(Imovel.class).execute();
        } catch (Exception e) {
            DbLogs.Log(SqliteConstantes.ERRO_SELECT_IMOVEL, e, SqliteConstantes.ERRO_LISTA_IMOVEIS);
        }
        return  new ArrayList<>();
    }
}
