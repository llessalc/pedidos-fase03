package com.fiap58.pedidos.unitTest.core.services;

import com.fiap58.pedidos.core.domain.entity.*;
import com.fiap58.pedidos.core.services.PedidoService;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IPedidoProdutoService;
import com.fiap58.pedidos.core.usecase.IProdutoService;
import com.fiap58.pedidos.gateway.PedidoRepository;
import com.fiap58.pedidos.gateway.impl.ImplConsumerApiPagamentos;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.entrada.ProdutoCarrinho;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

public class PedidoServiceTest {

    @Mock
    private PedidoRepository repository;
    @Mock
    private IClienteService clienteService;
    @Mock
    private IProdutoService produtoService;
    @Mock
    private IPedidoProdutoService pedidoProdutoService;
    @Mock
    private ImplConsumerApiPagamentos consumerApiPagamentos;

    AutoCloseable openMocks;
    private PedidoService service;

    private Produto produto;
    private Categoria categoria;
    private Produto produto2;
    private Categoria categoria2;
    private Produto produto3;
    private Categoria categoria3;
    private Pedido pedidoMock;
    private Cliente cliente;
    private DadosClienteCadastro dadosClienteCadastro;
    private PedidoProduto pedidoProduto;
    private ProdutoCarrinho produtoCarrinho;
    private List<ProdutoCarrinho> produtoCarrinhoList = new ArrayList<>();
    private List<PedidoProduto> pedidoProdutoList = new ArrayList<>();
    private DadosPedidosEntrada dadosPedidosEntrada;
    private List<PedidoProduto> produtos = new ArrayList<>();

//    @BeforeEach
//    void setup(){
//        openMocks = MockitoAnnotations.openMocks(this);
//        service = new PedidoService(repository, clienteService, produtoService, pedidoProdutoService, consumerApiPagamentos, consumerApiProducao);
//
//        categoria = new Categoria("lanche");
//        produto = new Produto("Produto1", "Descricao 1", new BigDecimal("10.00"));
//
//        categoria2 = new Categoria("acompanhamento");
//        produto2 = new Produto("Produto2", "Descricao 2", new BigDecimal("10.00"));
//
//        categoria3 = new Categoria("sobremesa");
//        produto3 = new Produto("Produto3", "Descricao 3", new BigDecimal("10.00"));
//
//        produto.setIdProduto(100L);
//        produto.setCategoria(categoria);
//        produto2.setIdProduto(200L);
//        produto2.setCategoria(categoria2);
//        produto3.setIdProduto(300L);
//        produto3.setCategoria(categoria3);
//        dadosClienteCadastro = new DadosClienteCadastro("0000", "Cliente 1", null, null);
//        cliente = new Cliente(dadosClienteCadastro);
//        pedidoMock = new Pedido(10L, cliente);
//        pedidoProduto = new PedidoProduto(1L, pedidoMock, produto, 10, new BigDecimal("10.00"), "nenhuma");
//        produtos.add(pedidoProduto);
//        pedidoMock.setProdutos(produtos);
//        produtoCarrinho = new ProdutoCarrinho(pedidoProduto);
//        produtoCarrinhoList.add(produtoCarrinho);
//        dadosPedidosEntrada = new DadosPedidosEntrada(produtoCarrinhoList);
//        pedidoProdutoList.add(pedidoProduto);
//    }
//
//    @AfterEach
//    void tearDown() throws Exception{
//        openMocks.close();
//
//    }
//
//    @Test
//    @DisplayName("Inserindo pedido na fila sem identificar cliente")
//    void testInserirPedidoFila() {
//        when(repository.save(any(Pedido.class))).thenReturn(pedidoMock);
//        doNothing().when(consumerApiPagamentos).acionaCriarPagamento(anyLong());
//        when(produtoService.buscarProduto(anyLong())).thenReturn(produto);
//        doNothing().when(pedidoProdutoService).inserirPedidoProduto(any(PedidoProduto.class));
//        DadosPedidosDto dadosPedidosDto = service.inserirPedidoFila(dadosPedidosEntrada);
//
//        assertThat(dadosPedidosDto.getProdutos().get(0).idProduto()).isEqualTo(100L);
//        verify(pedidoProdutoService, times(1)).inserirPedidoProduto(any(PedidoProduto.class));
//    }
//
//    @Test
//    @DisplayName("Inserindo pedido na fila com identificação do cliente")
//    void testInserirPedidoFilaComIdentificacaoDeCliente() {
//        DadosPedidosEntrada dadosPedidosEntradaCliente = new DadosPedidosEntrada(produtoCarrinhoList, 10L);
//        when(repository.save(any(Pedido.class))).thenReturn(pedidoMock);
//        when(clienteService.buscarClientePorId(anyLong())).thenReturn(cliente);
//        doNothing().when(consumerApiPagamentos).acionaCriarPagamento(anyLong());
//        when(produtoService.buscarProduto(anyLong())).thenReturn(produto);
//        doNothing().when(pedidoProdutoService).inserirPedidoProduto(any(PedidoProduto.class));
//        DadosPedidosDto dadosPedidosDto = service.inserirPedidoFila(dadosPedidosEntradaCliente);
//
//        assertThat(dadosPedidosDto.getProdutos().get(0).idProduto()).isEqualTo(100L);
//        verify(pedidoProdutoService, times(1)).inserirPedidoProduto(any(PedidoProduto.class));
//        verify(clienteService, times(1)).buscarClientePorId(anyLong());
//    }
//
//    @Test
//    @DisplayName("Atualiza pedido de RECEBIDO para EM_PREPARACAO")
//    void testAtualizarPedido() throws Exception {
//        boolean pagamentoRealizado = true;
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//        DadosPedidosDto dadosPedidosDto = service.atualizarPedido(1L, pagamentoRealizado);
//
//        verify(repository, times(1)).findById(anyLong());
//        assertThat(dadosPedidosDto.getStatus()).isEqualTo(StatusPedido.EM_PREPARACAO);
//    }
//
//    @Test
//    @DisplayName("Atualiza pedido de RECEBIDO sem Pagamento - Erro")
//    void testAtualizarPedidoSemPagar() throws Exception {
//        boolean pagamentoRealizado = false;
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//
//        assertThatThrownBy(()-> service.atualizarPedido(1L, pagamentoRealizado));
//    }
//
//    @Test
//    @DisplayName("Atualiza pedido de EM_PREPARACAO para PRONTO")
//    void testAtualizarPedidoParaPronto() throws Exception {
//        boolean pagamentoRealizado = true;
//        pedidoMock.setStatus(StatusPedido.EM_PREPARACAO);
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//        DadosPedidosDto dadosPedidosDto = service.atualizarPedido(1L, pagamentoRealizado);
//
//        verify(repository, times(1)).findById(anyLong());
//        assertThat(dadosPedidosDto.getStatus()).isEqualTo(StatusPedido.PRONTO);
//    }
//
//    @Test
//    @DisplayName("Finaliza pedido")
//    void testAtualizarPedidoParaFinalizado() throws Exception {
//        boolean pagamentoRealizado = true;
//        pedidoMock.setStatus(StatusPedido.PRONTO);
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//        DadosPedidosDto dadosPedidosDto = service.atualizarPedido(1L, pagamentoRealizado);
//
//        verify(repository, times(1)).findById(anyLong());
//        assertThat(dadosPedidosDto.getStatus()).isEqualTo(StatusPedido.FINALIZADO);
//    }
//
//    @Test
//    void testListarPedidos() {
//        Instant dataAtual = Instant.now();
//        Duration duracao = Duration.ofMinutes(10L);
//
//        // Pedido mais antigo junto com 2
//        Pedido pedidoMock0 = new Pedido(2L, cliente);
//        pedidoMock.setDataPedido(dataAtual);
//
//        // Pedido mais novo
//        Pedido pedidoMock1 = new Pedido(3L, cliente);
//        pedidoMock1.setDataPedido(dataAtual.plus(duracao));
//
//        // Status mais alto e data mais antiga - 1 Lugar
//        Pedido pedidoMock2 = new Pedido(1L, cliente);
//        pedidoMock2.setDataPedido(dataAtual);
//        pedidoMock2.setStatus(StatusPedido.EM_PREPARACAO);
//        // FINALIZADO NÃO DEVE APARECER
//        Pedido pedidoMock3 = new Pedido(4L, cliente);
//        pedidoMock3.setStatus(StatusPedido.FINALIZADO);
//
//        List<Pedido> pedidoList = new ArrayList<>();
//        pedidoList.add(pedidoMock0);
//        pedidoList.add(pedidoMock1);
//        pedidoList.add(pedidoMock2);
//        pedidoList.add(pedidoMock3);
//        when(repository.findAll()).thenReturn(pedidoList);
//
//        List<DadosPedidosPainelDto> dadosPedidosPainelDtos = service.listarPedidos();
//
//
//        assertThat(dadosPedidosPainelDtos.size()).isEqualTo(3);
//        assertThat(dadosPedidosPainelDtos.get(0).getId()).isEqualTo(1L);
//        assertThat(dadosPedidosPainelDtos.get(1).getId()).isEqualTo(2L);
//        assertThat(dadosPedidosPainelDtos.get(2).getId()).isEqualTo(3L);
//
//    }
//
//    @Test
//    void testBuscaPedido() {
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//
//        DadosPedidosValorDto dadosPedidosValorDto = service.buscaPedido(1L);
//        assertThat(dadosPedidosValorDto.getId()).isEqualTo(10L);
//        verify(repository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    void testDefineTempoEspera() {
//        Instant dataAtual = Instant.now();
//        long tempo = 10L;
//        DadosPedidosPainelDto dadosPedidosPainelDto = service.defineTempoEspera(pedidoMock, tempo);
//
//        assertThat(dadosPedidosPainelDto.getTempoEspera()).isEqualTo(10);
//    }
//
//    @Test
//    void testRecebePagamento() throws Exception {
//        pedidoProduto.setQuantidade(1);
//        produtos.add(pedidoProduto);
//        PedidoProduto pedidoProduto2 = new PedidoProduto(2L, pedidoMock, produto2, 5, new BigDecimal("10.00"), "nenhuma");
//        produtos.add(pedidoProduto2);
//        PedidoProduto pedidoProduto3 = new PedidoProduto(3L, pedidoMock, produto3, 5, new BigDecimal("10.00"), "nenhuma");
//        produtos.add(pedidoProduto3);
//        pedidoMock.setProdutos(produtos);
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//        pedidoMock.setStatus(StatusPedido.RECEBIDO);
//
//        DadosPedidosDto dadosPedidosDto = service.recebePagamento(1L);
//        assertThat(dadosPedidosDto.getStatus()).isEqualTo(StatusPedido.EM_PREPARACAO);
//        verify(repository, times(2)).findById(anyLong());
//    }
//
//    @Test
//    void testRecebePagamentoProdutosDiferentes() throws Exception {
//
//        PedidoProduto pedidoProduto2 = new PedidoProduto(2L, pedidoMock, produto2, 2, new BigDecimal("10.00"), "nenhuma");
//        produtos.add(pedidoProduto2);
//        PedidoProduto pedidoProduto3 = new PedidoProduto(3L, pedidoMock, produto3, 3, new BigDecimal("10.00"), "nenhuma");
//        produtos.add(pedidoProduto3);
//        pedidoMock.setProdutos(produtos);
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//        pedidoMock.setStatus(StatusPedido.RECEBIDO);
//
//        DadosPedidosDto dadosPedidosDto = service.recebePagamento(1L);
//        assertThat(dadosPedidosDto.getStatus()).isEqualTo(StatusPedido.EM_PREPARACAO);
//        verify(repository, times(2)).findById(anyLong());
//    }
//
//    @Test
//    void testRetornaPedido() {
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(pedidoMock));
//        Pedido pedido = service.retornaPedido(1L);
//        assertThat(pedido).isEqualTo(pedidoMock);
//        verify(repository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    void testRetornaTabelaJuncao() {
//        when(pedidoProdutoService.retornaPedidoProduto(anyLong())).thenReturn(pedidoProdutoList);
//        List<PedidoProduto> pedidoProdutos = service.retornaTabelaJuncao(pedidoMock);
//        assertThat(pedidoProdutos.size()).isEqualTo(1);
//        assertThat(pedidoProdutos.get(0)).isEqualTo(pedidoProduto);
//        verify(pedidoProdutoService, times(1)).retornaPedidoProduto(anyLong());
//    }
}
