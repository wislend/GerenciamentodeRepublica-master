package com.example.deyvi.gerenciamentoderepublica.entitys;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "REF_LOCATARIO")
public class ReferenciaLocatario extends BaseEntitys {

    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long moradorId;


    @Column
    private String nome;

    @Column
    private int tel;

    @Column
    private String email;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
