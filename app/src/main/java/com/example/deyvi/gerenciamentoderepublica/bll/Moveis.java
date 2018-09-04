package com.example.deyvi.gerenciamentoderepublica.bll;



import com.example.deyvi.gerenciamentoderepublica.entitys.Movel;

import java.util.List;

public class Moveis {

    private com.example.deyvi.gerenciamentoderepublica.dal.Moveis moveis;

    public Moveis() {
        this.moveis = new com.example.deyvi.gerenciamentoderepublica.dal.Moveis();
    }

    public List<Movel> todosMoveis() {
        return moveis.selectAll();
    }


}
