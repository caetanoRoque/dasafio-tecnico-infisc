package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public class Venda {
    private Integer idVenda;
    private Integer idFuncionario;
    private Integer idCliente;
    private BigDecimal valor;
    private LocalDate dataVenda;

    public Venda() {
    }

    public Venda(Integer idVenda, Integer idFuncionario, Integer idCliente, BigDecimal valor, LocalDate dataVenda) {
        this.idVenda = idVenda;
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
        this.valor = valor;
        this.dataVenda = dataVenda;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String toString() {
        return "Venda [idVenda=" + idVenda + ", idFuncionario=" + idFuncionario +", idCliente=" + idCliente +", valor=" + valor + ", dataVenda=" + dataVenda + "]";
    }
}
