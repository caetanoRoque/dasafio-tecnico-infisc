package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioNomeData {
    private String nome;
    private LocalDate dataContratacao;
}
