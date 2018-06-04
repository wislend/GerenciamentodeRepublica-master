package com.example.deyvi.gerenciamentoderepublica.entitys;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "LOCATARIO")
public class Locatario extends BaseEntitys {

    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String whats;
    @Column
    private String email;
    @Column
    private int quartoId;

    @Column
    private ReferenciaLocatario referenciaLocatario;
    @Column
    private Date dataPagamento;
    @Column
    private String statusPagamento;
    @Column
    private Date dataEntrada;
    @Column
    private Date dataSaida;
    //TIPO AINDA NÃO DEFINIDO
    @Column
    private String contrato;
    @Column
    private String fotoDocumento;
    @Column
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
