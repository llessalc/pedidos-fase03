package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.presenters.dto.saida.ProdutoCarrinhoSaida;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProdutoCarrinhoSaidaTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String nome = "Product";
        String descricao = "Description";
        int quantidade = 2;
        String observacao = "Some observation";

        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(new Produto(nome, descricao, new BigDecimal("10.0")));
        pedidoProduto.setQuantidade(quantidade);
        pedidoProduto.setObservacao(observacao);

        // Act
        ProdutoCarrinhoSaida produtoCarrinhoSaida = new ProdutoCarrinhoSaida(pedidoProduto);

        // Assert
        // Assertions.assertEquals(idProduto, produtoCarrinhoSaida.idProduto());
        Assertions.assertEquals(nome, produtoCarrinhoSaida.nome());
        Assertions.assertEquals(quantidade, produtoCarrinhoSaida.quantidade());
        Assertions.assertEquals(observacao, produtoCarrinhoSaida.observacao());
    }
}