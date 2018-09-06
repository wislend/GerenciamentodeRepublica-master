package com.example.deyvi.gerenciamentoderepublica.entitys;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "MORADOR")
public class Morador extends BaseEntitys {

    @Column
    private String nome;
    @Column
    private String telefone;
    @Column
    private String whats;
    @Column
    private String email;

    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long quartoId;



    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long enderecoId;

    @Column
    private ReferenciaLocatario referenciaLocatario;
    @Column
    private Date dataPagamento;
    @Column
    private String statusPagamento;
    @Column
    private String dataEntrada;
    @Column
    private String dataSaida;
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


    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
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

    public Long getQuartoId() {
        return quartoId;
    }

    public void setQuartoId(Long quartoId) {
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

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
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
