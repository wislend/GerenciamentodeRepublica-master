package com.example.deyvi.gerenciamentoderepublica.bll;



import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.example.deyvi.gerenciamentoderepublica.application.DbLogs;
import com.example.deyvi.gerenciamentoderepublica.constantsApp.SqliteConstantes;
import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;
import com.example.deyvi.gerenciamentoderepublica.entitys.Quarto;

import java.util.List;

public class Moveis {

    private com.example.deyvi.gerenciamentoderepublica.dal.Moveis moveis;

    public Moveis() {
        this.moveis = new com.example.deyvi.gerenciamentoderepublica.dal.Moveis();
    }

    public List<Movel> todosMoveis() {
        return moveis.selectAll();
    }

    public Long salvarMovel(Movel movel){
        return moveis.save(movel);

    }

    public boolean movelExiste(String nome){
        try{
            return new Select().from(Movel.class).where("nome = ?", nome).exists();
        }catch (Exception e){
            DbLogs.Log(SqliteConstantes.ERRO_MOVEL_EXISTS,e,nome);
        }
        return false;
    }

    public void atualizarMovel(String nome, boolean checado){
         moveis.update(nome, checado);
    }

    public void atualizarMovel(Movel movel){
        moveis.update(movel);
    }

    public List<Movel> listMoveis(Movel movel,Quarto quarto){
         return moveis.listMovel(movel,quarto);
    }
}
