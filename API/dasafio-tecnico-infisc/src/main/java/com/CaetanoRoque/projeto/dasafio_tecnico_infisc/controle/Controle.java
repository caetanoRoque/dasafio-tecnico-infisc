package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.controle;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.servico.Servico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
public class Controle {
    private final Servico servico;
    private List<String> listaLinhas = new ArrayList<>();

    @PostMapping("/upload")
    public void listaUpload(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {
        servico.arquivoProcessado(arquivo);
    }

    @GetMapping("/quantidadeFuncionarios")
    public Integer quantidadeFuncionarios() {
        return servico.quantidadeFuncionarios(listaLinhas);
    }

    @GetMapping("/quantidadeClientes")
    public Integer quantidadeClientes() {
        return servico.quantidadeClientes(listaLinhas);
    }
    
    @GetMapping("/quantidadeVendas")
    public Integer quantidadeVendas() {
        return servico.quantidadeVendas(listaLinhas);
    }

    @GetMapping("/totalVendas")
    public BigDecimal totalVendas() {
        return servico.totalVendas(listaLinhas);
    }



    
    

}
