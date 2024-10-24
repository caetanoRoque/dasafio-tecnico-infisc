package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence;

import java.time.LocalDate;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Cliente;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@Entity(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeCliente {

    @Id
    private Integer id;

    @Column(name = "nome_cliente")
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public static EntidadeCliente paraEntidade(Cliente cliente) {
        return EntidadeCliente.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .dataNascimento(cliente.getDataNascimento())
                .build();

    }

    public static Cliente paraModelo(EntidadeCliente entidade) {
        return Cliente.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .dataNascimento(entidade.getDataNascimento())
                .build();
    }
}