package com.example.deyvi.gerenciamentoderepublica.entitys;

import java.util.Date;

public class Locatario extends BaseEntitys{

    private int id;
    private String nome;
    private String telefone;
    private String whats;
    private String email;
    private int quartoId;
    private int camaId;
    private ReferenciaLocatario referenciaLocatario;
    private Date dataPagamento;
    private String statusPagamento;
    private Date dataEntrada;
    private Date dataSaida;
    //TIPO AINDA NÃO DEFINIDO
    private String contrato;
    private String fotoDocumento;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getWhats() {
        return whats;
    }

    public void setWhats(String whats) {
        this.whats = whats;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(int quartoId) {
        this.quartoId = quartoId;
    }

    public int getCamaId() {
        return camaId;
    }

    public void setCamaId(int camaId) {
        this.camaId = camaId;
    }

    public ReferenciaLocatario getReferenciaLocatario() {
        return referenciaLocatario;
    }

    public void setReferenciaLocatario(ReferenciaLocatario referenciaLocatario) {
        this.referenciaLocatario = referenciaLocatario;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getFotoDocumento() {
        return fotoDocumento;
    }

    public void setFotoDocumento(String fotoDocumento) {
        this.fotoDocumento = fotoDocumento;
    }
}
