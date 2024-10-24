package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.controle;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.Aniversario;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.TotalDeVendaPorMes;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.ClienteValor;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.FuncionarioVendas;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.FuncionarioNomeData;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.servico.Servico;

import java.io.IOException;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class Controle {
    private final Servico servico;


    @PostMapping("/upload")
    public void listaUpload(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {
        servico.arquivoProcessado(arquivo);
    }

    @GetMapping("/quantidadeFuncionarios")
    public Integer quantidadeFuncionarios() {
        return servico.quantidadeFuncionarios();
    }

    @GetMapping("/quantidadeClientes")
    public Integer quantidadeClientes() {
        return servico.quantidadeClientes();
    }

    @GetMapping("/quantidadeVendas")
    public Integer quantidadeVendas() {
        return servico.quantidadeVendas();
    }

    @GetMapping("/totalVendas")
    public BigDecimal totalVendas() {
        return servico.totalVendas();
    }

    @GetMapping("/totalVendasPorMes")
    public List<TotalDeVendaPorMes> totalDeVendasPorMes() {
        return servico.totalDeVendasPorMes();
    }

    @GetMapping("/aniversariantesDoMes")
    public List<Aniversario> aniversariantesDoMes() {
        return servico.aniversariantesDoMes();
    }

    @GetMapping("/totalComprasPorCliente")
    public List<ClienteValor> totalComprasPorCliente() {
        return servico.totalComprasPorCliente();
    }

    @GetMapping("/totalVendasPorFuncionario")
    public List<FuncionarioVendas> totalVendasPorFuncionario() {
        return servico.totalVendasPorFuncionario();
    }

    @GetMapping("/clienteQueMaisComprou")
    public String clienteQueMaisComprou() {
        return servico.clienteQueMaisComprou();
    }

    @GetMapping("/funcionariosMaisTempoTrabalho")
    public List<FuncionarioNomeData> funcionariosMaisTempoTrabalho() {
        return servico.funcionariosMaisTempoTrabalho();
    }
}
