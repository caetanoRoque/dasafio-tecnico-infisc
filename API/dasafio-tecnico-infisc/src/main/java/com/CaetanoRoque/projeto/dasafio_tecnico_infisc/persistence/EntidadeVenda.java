package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Venda;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@Entity(name = "venda")
@NoArgsConstructor
@AllArgsConstructor
public class EntidadeVenda {

    @Id
    private Integer id;

    @Column(name = "id_funcionario")
    private Integer idFuncionario;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    public static EntidadeVenda paraEntidade(Venda venda) {
        return EntidadeVenda.builder()
        .id(venda.getIdVenda())
        .idFuncionario(venda.getIdFuncionario())
        .idCliente(venda.getIdCliente())
        .valor(venda.getValor())
        .dataVenda(venda.getDataVenda())
        .build();

    }

    public static Venda paraModelo(EntidadeVenda entidade) {
        return Venda.builder()
            .idVenda(entidade.getId())
            .idFuncionario(entidade.getIdFuncionario())
            .idCliente(entidade.getIdCliente())
            .valor(entidade.getValor())
            .dataVenda(entidade.getDataVenda())
            .build();
    }
}
    
    