package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.ProdutoCarrinhoSaida;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DadosPedidosDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long idPedido = 1L;

        List<PedidoProduto> pedidoProdutos = new ArrayList<>();

        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro("123456789", "Fulano de tAL", null, null);

        Cliente clienteFake = new Cliente(dadosClienteCadastro);
        Pedido pedido = new Pedido(1l, clienteFake);

        Long idProduto = 1L;
        Produto produto = new Produto("Produto Teste", "Teste de Produto", new BigDecimal(10.0));
        produto.setIdProduto(idProduto);

        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(produto);
        pedidoProduto.setPrecoVenda(new BigDecimal(10.0));

        List<ProdutoCarrinhoSaida> produtos = new ArrayList<>();
        produtos.add(new ProdutoCarrinhoSaida(pedidoProduto));

        // Act
        DadosPedidosDto dadosPedidosDto = new DadosPedidosDto(pedido, pedidoProdutos);
        dadosPedidosDto.setProdutos(produtos);

        // Assert
        Assertions.assertEquals(idPedido, dadosPedidosDto.getId());
        Assertions.assertEquals(produtos, dadosPedidosDto.getProdutos());
        Assertions.assertEquals("Fulano de tAL", dadosPedidosDto.getNomeCliente());

    }
}