package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Locador;

public class Locadores extends Dao<Locador> {
    
    @Override
    public Long save(Locador locador) {

        try{
            DbLogs.Log(SqliteConstantes.LOCADOR_SALVO);
            return locador.save();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_SALVAR_LOCADOR, e , "");
        }
        return null;
    }

    @Override
    public void update(Locador locador) {
    }

    @Override
    public boolean delete(Locador locador) {
        return false;
    }



    public boolean exists(String email){
      return new Select().from(Locador.class).where("email = ?",email).exists();
    }

    public boolean senhaValida(String email,String senha){
        return new Select().from(Locador.class).where("email = ? AND senha = ?",email,senha).exists();
    }
}
