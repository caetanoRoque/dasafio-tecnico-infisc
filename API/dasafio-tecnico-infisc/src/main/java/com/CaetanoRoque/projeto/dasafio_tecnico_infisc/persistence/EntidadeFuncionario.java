package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence;

import java.time.LocalDate;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Funcionario;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@Entity(name = "funcionario")
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeFuncionario {

    @Id
    private Integer id;

    @Column(name = "nome_funcionario")
    private String nome;

    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;

    public static EntidadeFuncionario paraEntidade(Funcionario funcionario) {
        return EntidadeFuncionario.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .dataContratacao(funcionario.getDataContratacao())
                .build();

    }

    public static Funcionario paraModelo(EntidadeFuncionario entidade) {
        return Funcionario.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .dataContratacao(entidade.getDataContratacao())
                .build();
    }
}
