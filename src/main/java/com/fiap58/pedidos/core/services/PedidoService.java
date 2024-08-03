package com.fiap58.pedidos.core.services;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import com.fiap58.pedidos.gateway.impl.QueuePublisher;
import com.fiap58.pedidos.presenters.dto.entrada.BuscaClienteDto;
import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;
import com.fiap58.pedidos.presenters.dto.saida.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IPedidoProdutoService;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.core.usecase.IProdutoService;
import com.fiap58.pedidos.gateway.PedidoRepository;
import com.fiap58.pedidos.gateway.impl.ImplConsumerApiPagamentos;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.entrada.ProdutoCarrinho;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IProdutoService produtoService;

    @Autowired
    private IPedidoProdutoService pedidoProdutoService;

    @Autowired
    private ImplConsumerApiPagamentos consumerApiPagamentos;

    @Autowired
    private QueuePublisher queuePublisher;

    @Autowired
    private Environment environment;

    @Override
    public DadosPedidosDto inserirPedidoFila(DadosPedidosEntrada dto) {
        Cliente cliente;
        if (dto.clienteId() != null) {
            cliente = clienteService.buscarClientePorId(dto.clienteId());
        } else {
            cliente = null;
        }
        Pedido pedido = new Pedido(null, cliente);
        Pedido pedidoCriado = repository.save(pedido);
        List<PedidoProduto> pedidosProdutos = processaCarrinhoPedido(dto.carrinho(), pedidoCriado);

        DadosPedidosDto dadosPedidosDto = new DadosPedidosDto(pedidoCriado, pedidosProdutos);

        List<ProdutoCarrinhoSaida> produtos = dadosPedidosDto.getProdutos();
        List<ProdutoCarrinhoPagamento> produtosPagamentos = new ArrayList<>();
        for(ProdutoCarrinhoSaida prod : produtos){
            produtosPagamentos.add(new ProdutoCarrinhoPagamento(prod));
        }

        queuePublisher.publicarPedidoCriado(new DadosPedidoPagamento(dadosPedidosDto, produtosPagamentos));

        return dadosPedidosDto;
    }

    private List<PedidoProduto> processaCarrinhoPedido(List<ProdutoCarrinho> carrinhoProdutos,
            Pedido pedidoCriado) {
        List<PedidoProduto> pedidosProdutos = new ArrayList<>();
        for (ProdutoCarrinho carrinho : carrinhoProdutos) {
            Produto produto = produtoService.buscarProduto(carrinho.idProduto());
            PedidoProduto pedidoProduto = new PedidoProduto(null, pedidoCriado,
                    produto, carrinho.quantidade(), produto.getPrecoAtual(),
                    carrinho.observacao());
            pedidoProdutoService.inserirPedidoProduto(pedidoProduto);
            pedidosProdutos.add(pedidoProduto);
        }
        return pedidosProdutos;
    }

    @Override
    public List<DadosPedidosPainelDto> listarPedidos() {
        List<Pedido> pedidos = this.retornarTodosPedidos();
        List<DadosPedidosPainelDto> dadosPedidosDtos = ordenaDadosPedidoDto(
                pedidos.stream().map(this::mapperDadosPedidoPainelDto).collect(Collectors.toList()));
        return dadosPedidosDtos;
    }

    private List<DadosPedidosPainelDto> ordenaDadosPedidoDto(List<DadosPedidosPainelDto> listaInicial) {

        List<DadosPedidosPainelDto> lista = listaInicial.stream()
                .filter(pedidos -> !pedidos.getStatus().equals(StatusPedido.FINALIZADO))
                .sorted(Comparator.comparing(DadosPedidosPainelDto::getDataPedido))
                .sorted((p1, p2) -> p2.getStatus().getValor() - p1.getStatus().getValor())
                .collect(Collectors.toList());

        return lista;
    }

    private DadosPedidosDto mapperDadosPedidoDto(Pedido pedido) {
        List<PedidoProduto> pedidoProdutos = this.retornaTabelaJuncao(pedido);
        List<PedidoProduto> produtosDoPedido = pedidoProdutos.stream()
                .filter(pedidoProduto -> pedidoProduto.getPedido().getIdPedido() == pedido.getIdPedido())
                .collect(Collectors.toList());
        return new DadosPedidosDto(pedido, produtosDoPedido);
    }

    private List<Pedido> retornarTodosPedidos() {
        return repository.findAll();
    }

    @Override
    public Pedido retornaPedido(Long id) {

        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<PedidoProduto> retornaTabelaJuncao(Pedido pedido) {
        return pedidoProdutoService.retornaPedidoProduto(pedido.getIdPedido());
    }

    @Override
    public DadosPedidosDto atualizarPedido(Long id, Boolean pagamentoRealizado) throws Exception {
        Pedido pedido = this.retornaPedido(id);
        if (pedido.getStatus().equals(StatusPedido.RECEBIDO)) {
            if(pagamentoRealizado){
                DadosPedidoSaida dadosPedidoSaida = mapperDadosPedidoSaida(pedido);
                queuePublisher.publicarPedidoProducao(dadosPedidoSaida);
                pedido.setStatus(StatusPedido.EM_PREPARACAO);
            }
            repository.save(pedido);
            return mapperDadosPedidoDto(pedido);
        } else {
            defineProximoStatus(pedido);
            if(pedido.getStatus().equals(StatusPedido.FINALIZADO)){
                queuePublisher.publicarFinalizacaoPedido(new PedidoFinalizadoDto(pedido.getIdPedido()));
            }
            repository.save(pedido);
            return mapperDadosPedidoDto(pedido);
        }
    }

    private void defineProximoStatus(Pedido pedido) {
        if (pedido.getStatus().equals(StatusPedido.RECEBIDO)) {
            pedido.setStatus(StatusPedido.EM_PREPARACAO);
        } else if (pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)) {
            pedido.setStatus(StatusPedido.PRONTO);
        } else {
            pedido.setStatus(StatusPedido.FINALIZADO);
            pedido.setDataFinalizado(Instant.now());
        }
    }


    private DadosPedidoSaida mapperDadosPedidoSaida(Pedido pedido){
        List<PedidoProduto> pedidoProdutoList = pedidoProdutoService.retornaPedidoProduto(pedido.getIdPedido());
        List<DadosProdutoProducao> produtos =  new ArrayList<>();
        for(PedidoProduto pedidoProduto : pedidoProdutoList){
            produtos.add(new DadosProdutoProducao(pedidoProduto));
        }
        return new DadosPedidoSaida(pedido.getIdPedido(), produtos);
    }

    @Override
    public DadosPedidosDto recebePagamento(Long id) throws Exception {
        Pedido pedido = this.retornaPedido(id);
        DadosPedidosDto dadosPedidosDto = null;
        if (pedido.getStatus().equals(StatusPedido.RECEBIDO)) {
            defineTempoEstimadoDePreparoPadrao(pedido);
            dadosPedidosDto = this.atualizarPedido(id, true);
        }
        return dadosPedidosDto;
    }

    @Override
    public DadosPedidosPainelDto defineTempoEspera(Pedido pedido, long tempoEspera) {
        Duration duracao = Duration.ofMinutes(tempoEspera);
        pedido.setEstimativaPreparo(pedido.getDataPedido().plus(duracao));
        return mapperDadosPedidoPainelDto(pedido);
    }

    private long retornaTempoPedido(Instant tempoInicio, Instant tempoEstimado) {
        Duration duracao = Duration.between(tempoInicio, tempoEstimado);
        return duracao.toMinutes();
    }

    private DadosPedidosPainelDto mapperDadosPedidoPainelDto(Pedido pedido) {
        long tempoEspera = 0;
        if (pedido.getEstimativaPreparo() != null) {
            tempoEspera = retornaTempoPedido(pedido.getDataPedido(), pedido.getEstimativaPreparo());
        }
        DadosPedidosDto dadosPedidosDto = mapperDadosPedidoDto(pedido);
        return new DadosPedidosPainelDto(dadosPedidosDto, tempoEspera);
    }

    private void defineTempoEstimadoDePreparoPadrao(Pedido pedido) {
        long tempoPreparo = 0;
        // Lanche >= 3und -> 25min | < 3 -> 15min
        // Acompanhamento >= 3und -> 15min | < 3 -> 10min
        // Sobremesa >= 3 -> 15min | < 3 -> 10min
        int qtdLanches = retornaQuantidadeLista(pedido.getProdutos().stream()
                .filter(pedidoProduto -> pedidoProduto.getProduto().getCategoria().getNomeCategoria()
                        .equalsIgnoreCase("lanche"))
                .collect(Collectors.toList()));
        int qtdAcompanhamento = retornaQuantidadeLista(pedido.getProdutos().stream()
                .filter(pedidoProduto -> pedidoProduto.getProduto().getCategoria().getNomeCategoria()
                        .equalsIgnoreCase("acompanhamento"))
                .collect(Collectors.toList()));
        int qtdSobremesa = retornaQuantidadeLista(pedido.getProdutos().stream()
                .filter(pedidoProduto -> pedidoProduto.getProduto().getCategoria().getNomeCategoria()
                        .equalsIgnoreCase("sobremesa"))
                .collect(Collectors.toList()));

        tempoPreparo += qtdLanches >= 3 ? 25 : 15;
        tempoPreparo += qtdAcompanhamento >= 3 ? 15 : 10;
        tempoPreparo += qtdSobremesa >= 3 ? 15 : 10;

        defineTempoEspera(pedido, tempoPreparo);
    }

    private int retornaQuantidadeLista(List<PedidoProduto> pedidoProdutos) {
        return pedidoProdutos.stream().mapToInt(PedidoProduto::getQuantidade).sum();
    }

    private DadosPedidosValorDto mapperDadosPedidoValor(Pedido pedido) {
        List<PedidoProduto> pedidoProdutos = this.retornaTabelaJuncao(pedido);
        List<PedidoProduto> produtosDoPedido = pedidoProdutos.stream()
                .filter(pedidoProduto -> pedidoProduto.getPedido().getIdPedido() == pedido.getIdPedido())
                .collect(Collectors.toList());
        return new DadosPedidosValorDto(pedido, produtosDoPedido);
    }

    @Override
    public DadosPedidosValorDto buscaPedido(Long id) {
        return mapperDadosPedidoValor(retornaPedido(id));
    }

    @Override
    public void excluiPedido(Long id){
        Pedido pedido = this.retornaPedido(id);
        pedido.setDataFinalizado(Instant.now());
        pedido.setStatus(StatusPedido.CANCELADO);
        repository.save(pedido);
    }

    @Override
    public List<PagamentoDto> retornaPedidosCliente(BuscaClienteDto dto) {
        Cliente cliente = new Cliente();
        if(dto.cpf() != null){
            cliente = clienteService.buscarClientePorCpf(dto.cpf());
        } else {
            cliente = clienteService.buscarClientePorNome(dto.nome());
        }
        if(cliente != null){
            List<Pedido> pedidos = repository.findByIdCliente(cliente.getIdCliente());

            List<PagamentoDto> pagamentos = new ArrayList<>();
            for(Pedido pedido : pedidos){
                PagamentoDto pagamentoDto = consumerApiPagamentos.acionaListarPagamentoId(pedido.getIdPedido());
                if(pagamentoDto != null){
                    pagamentos.add(pagamentoDto);
                }
            }

            return pagamentos;
        }
        return null;
    }

    @Override
    public void excluirPagamentosCliente(List<PagamentoDto> pagamentos) {
        if(pagamentos != null)
            consumerApiPagamentos.acionaExcluirPagamento(pagamentos);
    }
}
