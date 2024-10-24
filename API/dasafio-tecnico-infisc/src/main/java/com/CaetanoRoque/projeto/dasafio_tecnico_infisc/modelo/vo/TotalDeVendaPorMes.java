package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.YearMonth;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalDeVendaPorMes {
    private YearMonth mes;
    private BigDecimal totalVendas;


}
