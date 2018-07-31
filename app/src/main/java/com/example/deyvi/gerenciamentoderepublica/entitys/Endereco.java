package com.example.deyvi.gerenciamentoderepublica.entitys;


import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "ENDERECO")
public class Endereco extends BaseEntitys {


    @Column
    private String rua;
    @Column
    private int numero;
    @Column
    private String cep;
    @Column
    private String pontoReferencia;
    @Column
    private String bairro;
    @Column
    private String cidade;
    @Column
    private String estado;

    public String getRua() {
        return rua;
    }



    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }


    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
