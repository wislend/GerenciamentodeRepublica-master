package com.example.deyvi.gerenciamentoderepublica.entitys;


import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "QUARTO")
public class Quarto extends BaseEntitys {

    @Column
    private String nome;
    @Column
    private Integer numero;
    @Column
    private Integer quantidadeCamas;
    @Column
    private Double valor;
    @Column
    private Integer status;
    @Column
    private String descricao;
    @Column
    private Double preco;


    @Column(onDelete = Column.ForeignKeyAction.CASCADE, onUpdate = Column.ForeignKeyAction.CASCADE)
    private Long imovelId;


    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeCamas() {
        return quantidadeCamas;
    }

    public void setQuantidadeCamas(Integer quantidadeCamas) {
        this.quantidadeCamas = quantidadeCamas;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
