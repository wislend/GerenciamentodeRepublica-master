package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;

import java.util.List;

public class Quartos extends Dao<Quarto> {
    
    @Override
    public Long save(Quarto quarto) {
        return quarto.save();
    }

    @Override
    public void update(Quarto quarto) {

    }



    public void delete(Quarto quarto) {
        quarto.delete();
    }



    @Override
    public boolean exists(Integer numero) {
        try{
            return new Select().from(Quarto.class).where("numero = ?", numero).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_QUARTO_EXISTS,e,numero);
        }
        return false;
    }


        @Override
        public List<Quarto> selectAll() {
            try {
                return new Select().all().from(Quarto.class).execute();
            } catch (Exception e) {
                DbLogs.Log(SqliteConstantes.ERRO_SELECT_QUARTOS_ALL, e, SqliteConstantes.ERRO_SELECT_QUARTOS_ALL);
            }
            return  null;
        }

        public List<Quarto> listQuartosForId(Long id){
            return new Select().all().from(Quarto.class).where("imovelId= ?",id).execute();
        }

}
