package com.fiap58.pedidos.unitTest.core.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.services.PedidoService;
import com.fiap58.pedidos.core.services.ProdutoService;
import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IPedidoProdutoService;
import com.fiap58.pedidos.core.usecase.IProdutoService;
import com.fiap58.pedidos.gateway.PedidoRepository;
import com.fiap58.pedidos.gateway.ProdutoRepository;
import com.fiap58.pedidos.gateway.impl.ImplConsumerApiPagamentos;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.ProdutoCarrinho;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosValorDto;

public class PedidoServiceTest {

    @Test
    void testDefineTempoEspera() {
        PedidoRepository repository = Mockito.mock(PedidoRepository.class);
        IClienteService clienteService = Mockito.mock(IClienteService.class);
        IProdutoService produtoService = Mockito.mock(IProdutoService.class);
        IPedidoProdutoService pedidoProdutoService = Mockito.mock(IPedidoProdutoService.class);
        ImplConsumerApiPagamentos implConsumerApiPagamentos = Mockito.mock(ImplConsumerApiPagamentos.class);

        PedidoService service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService,
                implConsumerApiPagamentos);

        DadosClienteCadastro dadoscliente = new DadosClienteCadastro("12345678901", "Teste", null, null);
        Cliente cliente = new Cliente(dadoscliente);
        Pedido pedido = new Pedido(1l, cliente);
        List<PedidoProduto> pedidosProdutos = new ArrayList<>();

        Mockito.when(pedidoProdutoService.retornaPedidoProduto(1l)).thenReturn(pedidosProdutos);

        DadosPedidosPainelDto result = service.defineTempoEspera(pedido, 1);

        assertNotNull(result);
    }

    @Test
    void testInserirPedidoFila() {
        PedidoRepository repository = Mockito.mock(PedidoRepository.class);
        IClienteService clienteService = Mockito.mock(IClienteService.class);
        IProdutoService produtoService = Mockito.mock(IProdutoService.class);
        IPedidoProdutoService pedidoProdutoService = Mockito.mock(IPedidoProdutoService.class);
        ImplConsumerApiPagamentos implConsumerApiPagamentos = Mockito.mock(ImplConsumerApiPagamentos.class);

        PedidoService service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService,
                implConsumerApiPagamentos);
        List<EnderecoCadastro> enderecos = new ArrayList<>();
        List<TelefoneCadastro> telefones = new ArrayList<>();

        Pedido pedido = new Pedido(null, null);
        DadosClienteCadastro dadoscliente = new DadosClienteCadastro("12345678901", "Teste", enderecos, telefones);
        Cliente cliente = new Cliente(dadoscliente);

        Produto produto = new Produto("Teste", "Teste descricao", new BigDecimal(10.0));
        List<ProdutoCarrinho> carrinho = new ArrayList<>();
        DadosPedidosEntrada dto = new DadosPedidosEntrada(carrinho, 1l);

        PedidoProduto pedidoProduto = new PedidoProduto(null, pedido, produto, 1, new BigDecimal(10.0), "Teste");

        Mockito.when(clienteService.buscarClientePorId(1l)).thenReturn(cliente);
        Mockito.when(repository.save(Mockito.any(Pedido.class))).thenReturn(pedido);
        Mockito.when(produtoService.buscarProduto(1l)).thenReturn(produto);
        Mockito.doAnswer(invocation -> null).when(pedidoProdutoService).inserirPedidoProduto(pedidoProduto);
        DadosPedidosDto dadospedido = service.inserirPedidoFila(dto);

        assertNotNull(dadospedido);
    }

    @Test
    void testListarPedidos() {
        PedidoRepository repository = Mockito.mock(PedidoRepository.class);
        IClienteService clienteService = Mockito.mock(IClienteService.class);
        IProdutoService produtoService = Mockito.mock(IProdutoService.class);
        IPedidoProdutoService pedidoProdutoService = Mockito.mock(IPedidoProdutoService.class);
        ImplConsumerApiPagamentos implConsumerApiPagamentos = Mockito.mock(ImplConsumerApiPagamentos.class);

        PedidoService service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService,
                implConsumerApiPagamentos);

        DadosClienteCadastro dadoscliente = new DadosClienteCadastro("12345678901", "Teste", null, null);
        Cliente cliente = new Cliente(dadoscliente);
        Pedido pedido = new Pedido(1l, cliente);
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);

        Mockito.when(repository.findAll()).thenReturn(pedidos);

        List<DadosPedidosPainelDto> lDadosPedidosPainelDtos = service.listarPedidos();

        assertNotNull(lDadosPedidosPainelDtos);
    }

    @Test
    void testRecebePagamento() throws Exception {
        PedidoRepository repository = Mockito.mock(PedidoRepository.class);
        IClienteService clienteService = Mockito.mock(IClienteService.class);
        IProdutoService produtoService = Mockito.mock(IProdutoService.class);
        IPedidoProdutoService pedidoProdutoService = Mockito.mock(IPedidoProdutoService.class);
        ImplConsumerApiPagamentos implConsumerApiPagamentos = Mockito.mock(ImplConsumerApiPagamentos.class);

        PedidoService service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService,
                implConsumerApiPagamentos);

        DadosClienteCadastro dadoscliente = new DadosClienteCadastro("12345678901", "Teste", null, null);
        Cliente cliente = new Cliente(dadoscliente);
        Pedido pedido = new Pedido(1l, cliente);
        pedido.setProdutos(new ArrayList<>()); // Inicializa a lista de produtos

        Mockito.when(repository.findById(1l)).thenReturn(Optional.of(pedido));

        DadosPedidosDto dadosPedidosDto = service.recebePagamento(1l);

        assertNotNull(dadosPedidosDto);
    }

    @Test
    void testRetornaPedido() {
        PedidoRepository repository = Mockito.mock(PedidoRepository.class);
        IClienteService clienteService = Mockito.mock(IClienteService.class);
        IProdutoService produtoService = Mockito.mock(IProdutoService.class);
        IPedidoProdutoService pedidoProdutoService = Mockito.mock(IPedidoProdutoService.class);
        ImplConsumerApiPagamentos implConsumerApiPagamentos = Mockito.mock(ImplConsumerApiPagamentos.class);

        PedidoService service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService,
                implConsumerApiPagamentos);

        DadosClienteCadastro dadoscliente = new DadosClienteCadastro("12345678901", "Teste", null, null);
        Cliente cliente = new Cliente(dadoscliente);
        Pedido pedido = new Pedido(1l, cliente);

        Mockito.when(repository.findById(1l)).thenReturn(Optional.of(pedido));

        Pedido pedidoresult = service.retornaPedido(1l);

        assertNotNull(pedidoresult);
    }

    @Test
    void testRetornaTabelaJuncao() {
        PedidoRepository repository = Mockito.mock(PedidoRepository.class);
        IClienteService clienteService = Mockito.mock(IClienteService.class);
        IProdutoService produtoService = Mockito.mock(IProdutoService.class);
        IPedidoProdutoService pedidoProdutoService = Mockito.mock(IPedidoProdutoService.class);
        ImplConsumerApiPagamentos implConsumerApiPagamentos = Mockito.mock(ImplConsumerApiPagamentos.class);

        PedidoService service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService,
                implConsumerApiPagamentos);

        DadosClienteCadastro dadoscliente = new DadosClienteCadastro("12345678901", "Teste", null, null);
        Cliente cliente = new Cliente(dadoscliente);
        Pedido pedido = new Pedido(1l, cliente);

        List<PedidoProduto> pedidosProdutos = new ArrayList<>();
        Mockito.when(pedidoProdutoService.retornaPedidoProduto(1l)).thenReturn(pedidosProdutos);

        List<PedidoProduto> result = service.retornaTabelaJuncao(pedido);

        assertNotNull(result);
    }
}
