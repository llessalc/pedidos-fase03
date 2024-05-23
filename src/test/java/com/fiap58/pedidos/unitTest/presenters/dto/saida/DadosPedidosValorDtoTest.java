package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosValorDto;
import com.fiap58.pedidos.presenters.dto.saida.ProdutoCarrinhoValor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DadosPedidosValorDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long idPedido = 1L;

        List<PedidoProduto> pedidoProdutos = new ArrayList<>();
        DadosClienteCadastro cliente = new DadosClienteCadastro("123456789", "FULANO DE TAL", null, null);
        Pedido pedido = new Pedido(idPedido, new Cliente(cliente));
        pedido.setCliente(new Cliente(cliente));

        Long idProduto = 1L;
        Produto produto = new Produto("Produto Teste", "Teste de Produto", new BigDecimal(10.0));
        produto.setIdProduto(idProduto);

        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(produto);
        pedidoProduto.setPrecoVenda(new BigDecimal(10.0));

        List<ProdutoCarrinhoValor> produtos = new ArrayList<>();

        produtos.add(new ProdutoCarrinhoValor(pedidoProduto));

        // Act
        DadosPedidosValorDto dadosPedidosValorDto = new DadosPedidosValorDto(pedido, pedidoProdutos);
        dadosPedidosValorDto.setProdutos(produtos);

        // Assert
        Assertions.assertEquals(idPedido, dadosPedidosValorDto.getId());
        Assertions.assertEquals(produtos, dadosPedidosValorDto.getProdutos());
        Assertions.assertEquals("FULANO DE TAL", dadosPedidosValorDto.getNomeCliente());

    }
}