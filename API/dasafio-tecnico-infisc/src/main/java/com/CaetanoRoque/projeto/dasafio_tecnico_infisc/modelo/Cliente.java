package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo;

import java.time.LocalDate;

public class Cliente {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;

    public Cliente () {
    }

    public Cliente(Integer id, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + "]";
    }
}
