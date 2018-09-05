package com.example.deyvi.gerenciamentoderepublica.entitys;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "MOVEL")
public class Movel extends BaseEntitys {

    public Movel() {
    }

    @Column
    private String nome;

    @Column
    private Boolean checkad;

    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long idQuarto;

    public Long getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(Long idQuarto) {
        this.idQuarto = idQuarto;
    }

    public Boolean isChecked() {
        return checkad;
    }

    public void setCheckad(Boolean checkad) {
        this.checkad = checkad;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
