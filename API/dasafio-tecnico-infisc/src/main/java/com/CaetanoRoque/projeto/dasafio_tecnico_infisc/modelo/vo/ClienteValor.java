package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteValor {
    private String nome;
    private BigDecimal totalGasto;


}
