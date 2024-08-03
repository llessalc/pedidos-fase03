package com.fiap58.pedidos.unitTest.presenters.dto.entrada;

import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.presenters.dto.entrada.ProdutoCarrinho;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProdutoCarrinhoTest {

    @Test
    void testProdutoCarrinhoConstructorWithNullObservacao() {
        Produto produto = new Produto("Test Product", "Sample description", new BigDecimal(1l));

        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(produto);
        pedidoProduto.setId(1L);
        pedidoProduto.setQuantidade(2);
        pedidoProduto.setObservacao(null);

        ProdutoCarrinho produtoCarrinho = new ProdutoCarrinho(pedidoProduto);

        Assertions.assertEquals("Test Product", produtoCarrinho.nome());
        Assertions.assertEquals(2, produtoCarrinho.quantidade());
        Assertions.assertEquals("", produtoCarrinho.observacao());
    }
}