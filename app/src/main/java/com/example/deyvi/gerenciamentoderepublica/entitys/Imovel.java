package com.example.deyvi.gerenciamentoderepublica.entitys;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name ="IMOVEL")
public class Imovel extends BaseEntitys {


    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long enderecoId;

    @Column
    private String nome;

    @Column
    private String imagem;

    @Column
    private String caminhoImagem;

    @Column
    private int quantQuartos;
    @Column
    private boolean alugado;
    @Column
    private double valor;
    @Column
    private double jurosDia;
    @Column
    private double jurosMes;


    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantQuartos() {
        return quantQuartos;
    }

    public void setQuantQuartos(int quantQuartos) {
        this.quantQuartos = quantQuartos;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getJurosDia() {
        return jurosDia;
    }

    public void setJurosDia(double jurosDia) {
        this.jurosDia = jurosDia;
    }

    public double getJurosMes() {
        return jurosMes;
    }

    public void setJurosMes(double jurosMes) {
        this.jurosMes = jurosMes;
    }
}
