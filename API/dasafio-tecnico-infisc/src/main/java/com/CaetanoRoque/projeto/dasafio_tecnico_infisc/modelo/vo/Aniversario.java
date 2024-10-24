package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo;

public class Aniversario {
    private Integer mesAniversario;
    private String nome;
    private Integer idade;
    private Integer diaAniversario;

    public Aniversario(){ 
    }

    public Aniversario(Integer mesAniversario, String nome, Integer idade, Integer diaAniversario) {
        this.mesAniversario = mesAniversario;
        this.nome = nome;
        this.idade = idade;
        this.diaAniversario = diaAniversario;
    }

    public Integer getMesAniversario() {
        return mesAniversario;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Integer getDiaAniversario() {
        return diaAniversario;
    }

    public void setMesAniversario(Integer mesAniversario) {
        this.mesAniversario = mesAniversario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setDiaAniversario(Integer diaAniversario) {
        this.diaAniversario = diaAniversario;
    }

    public String toString() {
        return "Aniversario [mesAniversario=" + mesAniversario + ", nome=" + nome + ", idade=" + idade + ", diaAniversario=" + diaAniversario + "]";
    }
}   
