package com.example.deyvi.gerenciamentoderepublica.dal;

import com.activeandroid.query.Select;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;

import java.util.ArrayList;
import java.util.List;

public class Moveis extends Dao<Movel> {

    @Override
    public Long save(Movel movel) {
        return movel.save();
    }

    @Override
    public boolean update(Movel movel) {
        return false;
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
}
