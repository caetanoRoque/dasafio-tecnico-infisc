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
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Funcionario;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Cliente;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.Venda;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.Aniversario;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.TotalDeVendaPorMes;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.ClienteValor;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.FuncionarioVendas;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.modelo.vo.FuncionarioNomeData;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence.EntidadeVenda;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence.RepositorioVenda;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence.EntidadeCliente;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence.RepositorioCliente;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence.EntidadeFuncionario;
import com.CaetanoRoque.projeto.dasafio_tecnico_infisc.persistence.RepositorioFuncionario;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class Servico {

    private final RepositorioVenda repositorioVenda;
    private final RepositorioCliente repositorioCliente;
    private final RepositorioFuncionario repositorioFuncionario;
    
    public void arquivoProcessado(MultipartFile arquivo) throws IOException  {
        repositorioVenda.deleteAll();
        repositorioCliente.deleteAll();
        repositorioFuncionario.deleteAll();

        try (BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
            String linha;
            
            while ((linha = leitor.readLine()) != null) {
                String[]valoresColuna = linha.split("\\|");
                if(valoresColuna[0].equals("0")){
                    Funcionario funcionario = new Funcionario(Integer.parseInt(valoresColuna[1]), valoresColuna[2], LocalDate.parse(valoresColuna[3])); 
                    repositorioFuncionario.save(EntidadeFuncionario.paraEntidade(funcionario));
                }
                if(valoresColuna[0].equals("1")){
                    Cliente cliente = new Cliente(Integer.parseInt(valoresColuna[1]), valoresColuna[2], LocalDate.parse(valoresColuna[3]));
                    // listaClientes.add(cliente);
                    repositorioCliente.save(EntidadeCliente.paraEntidade(cliente));
                }
                if (valoresColuna[0].equals("2")){
                    Venda venda = new Venda(Integer.parseInt(valoresColuna[1]), Integer.parseInt(valoresColuna[2]), Integer.parseInt(valoresColuna[3]), new BigDecimal(valoresColuna[4].replace(",", ".")), LocalDate.parse(valoresColuna[5]));
                    // listaVendas.add(venda);

                    repositorioVenda.save(EntidadeVenda.paraEntidade(venda));
                }
            }
        }
    }

    public Integer quantidadeFuncionarios() {
        return Integer.valueOf((int) repositorioFuncionario.count());
    }

    public Integer quantidadeClientes() {
        return Integer.valueOf((int) repositorioCliente.count());
    }

    public Integer quantidadeVendas() {
        return Integer.valueOf((int) repositorioVenda.count());
    }

    public BigDecimal totalVendas(){
        List<Venda> listaVendas = repositorioVenda.findAll().stream().map(EntidadeVenda::paraModelo).collect(Collectors.toList());


        BigDecimal total = new BigDecimal(0);
        for (Venda venda: listaVendas){
            total = total.add(venda.getValor());
        }
        return total;

    }

    public List<TotalDeVendaPorMes> totalDeVendasPorMes() {
        List<TotalDeVendaPorMes> totalDeVendasPorMes = new ArrayList<>();
        List<Venda> listaVendas = repositorioVenda.findAll().stream().map(EntidadeVenda::paraModelo).collect(Collectors.toList());

        Map<YearMonth, BigDecimal> totalVendasPorMes = new HashMap<>();

        for (Venda venda : listaVendas) {
            YearMonth mesAno = YearMonth.from(venda.getDataVenda());

            totalVendasPorMes.merge(mesAno, venda.getValor(), BigDecimal::add);
        }

        for (Map.Entry<YearMonth, BigDecimal> entry : totalVendasPorMes.entrySet()) {
            totalDeVendasPorMes.add(TotalDeVendaPorMes.builder().mes(entry.getKey()).totalVendas(entry.getValue()).build());
        }

        return totalDeVendasPorMes;
    }

    public List<Aniversario> aniversariantesDoMes() {
        List<Aniversario> aniversariantes = new ArrayList<>();
        YearMonth mesAtual = YearMonth.now();
        List<Cliente> listaClientes = repositorioCliente.findAll().stream().map(EntidadeCliente::paraModelo).collect(Collectors.toList());

        for (Cliente cliente : listaClientes) {
            if (cliente.getDataNascimento().getMonthValue() == mesAtual.getMonthValue()) {
                int idade = mesAtual.getYear() - cliente.getDataNascimento().getYear();
                if (mesAtual.getMonthValue() < cliente.getDataNascimento().getMonthValue() ||
                    (mesAtual.getMonthValue() == cliente.getDataNascimento().getMonthValue() && mesAtual.atEndOfMonth().getDayOfMonth() < cliente.getDataNascimento().getDayOfMonth())) {
                    idade--;
                }
                aniversariantes.add(new Aniversario(mesAtual.getMonthValue(), cliente.getNome(), idade, cliente.getDataNascimento().getDayOfMonth()));
            }
        }

        return aniversariantes;
    }

    public List<ClienteValor> totalComprasPorCliente() {
        List<ClienteValor> totalVendasPorCliente = new ArrayList<>();
        List<Venda> listaVendas = repositorioVenda.findAll().stream().map(EntidadeVenda::paraModelo).collect(Collectors.toList());
        List<Cliente> listaClientes = repositorioCliente.findAll().stream().map(EntidadeCliente::paraModelo).collect(Collectors.toList());

        Map<Integer, BigDecimal> mapTotalVendasPorCliente = new HashMap<>();

        for (Venda venda : listaVendas) {
            mapTotalVendasPorCliente.merge(venda.getIdCliente(), venda.getValor(), BigDecimal::add);
        }

        for (Map.Entry<Integer, BigDecimal> entry : mapTotalVendasPorCliente.entrySet()) {
            Cliente cliente = listaClientes.stream().filter(c -> c.getId().equals(entry.getKey())).findFirst().orElse(null);
            if (cliente != null) {
                totalVendasPorCliente.add(new ClienteValor(cliente.getNome(), entry.getValue()));
            }
        }

        return totalVendasPorCliente;
    }

    public List<FuncionarioVendas> totalVendasPorFuncionario() {
        List<FuncionarioVendas> totalVendasPorFuncionario = new ArrayList<>();
        List<Venda> listaVendas = repositorioVenda.findAll().stream().map(EntidadeVenda::paraModelo).collect(Collectors.toList());
        List<Funcionario> listaFuncionarios = repositorioFuncionario.findAll().stream().map(EntidadeFuncionario::paraModelo).collect(Collectors.toList());

        Map<Integer, BigDecimal> mapTotalVendasPorFuncionario = new HashMap<>();

        for (Venda venda : listaVendas) {
            mapTotalVendasPorFuncionario.merge(venda.getIdFuncionario(), venda.getValor(), BigDecimal::add);
        }

        for (Map.Entry<Integer, BigDecimal> entry : mapTotalVendasPorFuncionario.entrySet()) {
            Funcionario funcionario = listaFuncionarios.stream().filter(f -> f.getId().equals(entry.getKey())).findFirst().orElse(null);
            if (funcionario != null) {
                totalVendasPorFuncionario.add(new FuncionarioVendas(funcionario.getNome(), entry.getValue()));
            }
        }
        return totalVendasPorFuncionario;
    }

    public String clienteQueMaisComprou() {
        Map<Integer, BigDecimal> totalVendasPorCliente = new HashMap<>();
        List<Venda> listaVendas = repositorioVenda.findAll().stream().map(EntidadeVenda::paraModelo).collect(Collectors.toList());
        List<Cliente> listaClientes = repositorioCliente.findAll().stream().map(EntidadeCliente::paraModelo).collect(Collectors.toList());

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
                return cliente.getNome();
            }
        }

        return "";
    }

    public List<FuncionarioNomeData> funcionariosMaisTempoTrabalho() {
        List<Funcionario> listaFuncionarios = repositorioFuncionario.findAll().stream().map(EntidadeFuncionario::paraModelo).collect(Collectors.toList());
        listaFuncionarios.sort((f1, f2) -> f1.getDataContratacao().compareTo(f2.getDataContratacao()));

        List<FuncionarioNomeData> funcionariosMaisTempo = new ArrayList<>();
            for (int i = 0; i < 3 && i < listaFuncionarios.size(); i++) {
                Funcionario funcionario = listaFuncionarios.get(i);
                funcionariosMaisTempo.add(new FuncionarioNomeData(funcionario.getNome(), funcionario.getDataContratacao()));
            }
        return funcionariosMaisTempo;
    }

}
