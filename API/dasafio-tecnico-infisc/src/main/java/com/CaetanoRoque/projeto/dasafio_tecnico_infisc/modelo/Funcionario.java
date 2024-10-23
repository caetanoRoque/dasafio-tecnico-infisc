package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo;

import java.time.LocalDate;

public class Funcionario {
    private Integer id;
    private String nome;
    private LocalDate dataContratacao;

    public Funcionario () {
    }
    
    public Funcionario(Integer id, String nome, LocalDate dataContratacao) {
        this.id = id;
        this.nome = nome;
        this.dataContratacao = dataContratacao;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String toString() {
        return "Funcionario [id=" + id + ", nome=" + nome + ", dataContratacao=" + dataContratacao + "]";
    }
}
