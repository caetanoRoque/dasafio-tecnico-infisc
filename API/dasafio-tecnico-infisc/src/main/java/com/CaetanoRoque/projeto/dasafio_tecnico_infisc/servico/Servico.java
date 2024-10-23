package com.CaetanoRoque.projeto.dasafio_tecnico_infisc.servico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.NoArgsConstructor;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Funcionario;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Cliente;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Venda;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Aniversario;


@Service
@NoArgsConstructor
public class Servico {
    List<Funcionario> listaFuncionarios = new ArrayList<>();
    List<Cliente> listaClientes= new ArrayList<>();
    List<Venda> listaVendas= new ArrayList<>();
    public void arquivoProcessado(MultipartFile arquivo) throws IOException  {
        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                String[]valoresColuna = linha.split("\\|");
                if(valoresColuna[0].equals("0")){
                    Funcionario funcionario = new Funcionario(Integer.parseInt(valoresColuna[1]), valoresColuna[2], LocalDate.parse(valoresColuna[3])); 
                    listaFuncionarios.add(funcionario);
                }
                if(valoresColuna[0].equals("1")){
                    Cliente cliente = new Cliente(Integer.parseInt(valoresColuna[1]), valoresColuna[2], LocalDate.parse(valoresColuna[3]));
                    listaClientes.add(cliente);
                }
                if (valoresColuna[0].equals("2")){
                    Venda venda = new Venda(Integer.parseInt(valoresColuna[1]), Integer.parseInt(valoresColuna[2]), Integer.parseInt(valoresColuna[3]), new BigDecimal(valoresColuna[4].replace(",", ".")), LocalDate.parse(valoresColuna[5]));
                    listaVendas.add(venda);
                }
            }
        }
    }

    public Integer quantidadeFuncionarios(List<String> listaLinhas) {
        return listaFuncionarios.size();
    }

    public Integer quantidadeClientes(List<String> listaLinhas) {
        return listaClientes.size();
    }

    public Integer quantidadeVendas(List<String> listaLinhas) {
        return listaVendas.size();
    }

    public BigDecimal totalVendas(List<String> listaLinhas){
        BigDecimal total = new BigDecimal(0);
        for (Venda venda: listaVendas){
            total = total.add(venda.getValor());
        }
        return total;

    }

    // nao fiz controller apartir daqui

    public void calcularVendasPorMes(List<Venda> listaVendas) {
        Map<YearMonth, BigDecimal> totalVendasPorMes = new HashMap<>();

        for (Venda venda : listaVendas) {
            YearMonth mesAno = YearMonth.from(venda.getDataVenda());

            totalVendasPorMes.merge(mesAno, venda.getValor(), BigDecimal::add);
        }

        for (Map.Entry<YearMonth, BigDecimal> entry : totalVendasPorMes.entrySet()) {
            System.out.println("Mês: " + entry.getKey() + " - Total de Vendas: " + entry.getValue());
        }
    }

    //esquecer a F

    // g) Clientes que fazem aniversário no mês, listando o mês, seu nome, sua idade e o dia de aniversário
    public List<Aniversario> aniversariantesDoMes() {
        List<Aniversario> aniversariantes = new ArrayList<>();
        YearMonth mesAtual = YearMonth.now();

        for (Cliente cliente : listaClientes) {
            YearMonth mesAniversario = YearMonth.from(cliente.getDataNascimento());

            if (mesAniversario.equals(mesAtual)) {
                Integer idade = mesAtual.getYear() - cliente.getDataNascimento().getYear();
                aniversariantes.add(new Aniversario(mesAniversario.getMonth().toString(), cliente.getNome(), idade, cliente.getDataNascimento().getDayOfMonth()));
            }
        }

        return aniversariantes;
    }

    // h) Total de valor de vendas por cliente (listar cada cliente e o valor total gasto);
    public void totalVendasPorCliente() {
        Map<Integer, BigDecimal> totalVendasPorCliente = new HashMap<>();

        for (Venda venda : listaVendas) {
            totalVendasPorCliente.merge(venda.getIdCliente(), venda.getValor(), BigDecimal::add);
        }

        for (Map.Entry<Integer, BigDecimal> entry : totalVendasPorCliente.entrySet()) {
            System.out.println("Cliente: " + entry.getKey() + " - Total de Vendas: " + entry.getValue());
        }
    }

    // i) Total de valor de vendas por funcionário (listar cada funcionário e o valor total de vendas);
    public void totalVendasPorFuncionario() {
        Map<Integer, BigDecimal> totalVendasPorFuncionario = new HashMap<>();

        for (Venda venda : listaVendas) {
            totalVendasPorFuncionario.merge(venda.getIdFuncionario(), venda.getValor(), BigDecimal::add);
        }

        for (Map.Entry<Integer, BigDecimal> entry : totalVendasPorFuncionario.entrySet()) {
            System.out.println("Funcionário: " + entry.getKey() + " - Total de Vendas: " + entry.getValue());
        }
    }

    // j) Qual o NOME do cliente que mais comprou na história da empresa;
    public void clienteQueMaisComprou() {
        Map<Integer, BigDecimal> totalVendasPorCliente = new HashMap<>();

        for (Venda venda : listaVendas) {
            totalVendasPorCliente.merge(venda.getIdCliente(), venda.getValor(), BigDecimal::add);
        }

        Integer idClienteMaisComprou = 0;
        BigDecimal maiorValor = new BigDecimal(0);

        for (Map.Entry<Integer, BigDecimal> entry : totalVendasPorCliente.entrySet()) {
            if (entry.getValue().compareTo(maiorValor) > 0) {
                idClienteMaisComprou = entry.getKey();
                maiorValor = entry.getValue();
            }
        }

        for (Cliente cliente : listaClientes) {
            if (cliente.getId().equals(idClienteMaisComprou)) {
                System.out.println("Cliente que mais comprou: " + cliente.getNome());
            }
        }
    }

    // k) Listar os 3 funcionários (nome e data de contratação), de forma ordenada, que possuem mais tempo de trabalho na empresa.
    public void funcionariosMaisTempoTrabalho() {
        listaFuncionarios.sort((f1, f2) -> f1.getDataContratacao().compareTo(f2.getDataContratacao()));

        for (int i = 0; i < 3; i++) {
            Funcionario funcionario = listaFuncionarios.get(i);
            System.out.println("Funcionário: " + funcionario.getNome() + " - Data de Contratação: " + funcionario.getDataContratacao());
        }
    }

}
