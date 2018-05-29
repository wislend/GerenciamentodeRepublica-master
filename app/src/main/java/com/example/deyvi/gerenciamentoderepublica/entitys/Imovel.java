package com.example.deyvi.gerenciamentoderepublica.entitys;

public class Imovel {

    private int id;
    private String nome;
    private Endereco enderecoImovel;
    private int quantQuartos;
    private boolean alugado;
    private double valor;
    private double jurosDia;
    private double jurosMes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEnderecoImovel() {
        return enderecoImovel;
    }

    public void setEnderecoImovel(Endereco enderecoImovel) {
        this.enderecoImovel = enderecoImovel;
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
