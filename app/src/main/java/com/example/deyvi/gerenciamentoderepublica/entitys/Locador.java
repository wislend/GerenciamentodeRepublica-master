package com.example.deyvi.gerenciamentoderepublica.entitys;


import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name ="LOCADOR")
public class Locador extends BaseEntitys{

    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long imovelId;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

    @Column
    private  String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
