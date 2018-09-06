package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;

public class Quartos extends Dao<Quarto> {
    
    @Override
    public Long save(Quarto quarto) {
        return quarto.save();
    }

    @Override
    public void update(Quarto quarto) {

    }

    @Override
    public boolean delete(Quarto quarto) {
        return false;
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




}
