package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Imovel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;

import java.util.ArrayList;
import java.util.List;

public class Moveis extends Dao<Movel> {

    @Override
    public Long save(Movel movel) {
        return movel.save();
    }

    @Override
    public void update(Movel movel) {
        int intchecado = movel.isChecked() ? 1 : 0;
        if (movel.getQuartoId() != null) {

                new Update(Movel.class)
                        .set("checkad = " + intchecado, "idQuarto = " + movel.getQuartoId())
                        .where("nome = ?", movel.getNome())
                        .execute();

        }else {

                new Update(Movel.class)
                        .set("checkad = " + intchecado)
                        .where("nome = ?", movel.getNome())
                        .execute();

        }

    }

    @Override
    public void update(String nome, boolean checado) {
        int intchecado = checado ? 1 : 0;

        try {
            new Update(Movel.class)
                    .set("checkad = " + intchecado )
                    .where("nome = ?", nome)
                    .execute();

        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_ATUALIZAR_MOVEL, e, SqliteConstantes.ERRO_ATUALIZAR_MOVEL);
        }

    }

    @Override
    public boolean delete(Movel movel) {
        return false;
    }

    @Override
    public List<Movel> selectAll() {
        try {
            return new Select().all().from(Movel.class).execute();

        } catch (Exception e) {
            DbLogs.Log(SqliteConstantes.ERRO_SELECT_IMOVEL, e, SqliteConstantes.ERRO_SELECT_IMOVEL);
        }
        return  null;
    }


    public List<Movel> listMovel(Long quartoId){
        try{
            return new Select().all()
                    .from(Movel.class)
                     .where("quartoId = ?",quartoId)
                    .execute();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_INNER_JOIN, e, SqliteConstantes.ERRO_INNER_JOIN);

        }
        return null;
    }


    @Override
    public boolean exists(String nome) {
            return new Select().all()
                    .from(Movel.class)
                    .where("nome = ?",nome).exists();

    }
}
