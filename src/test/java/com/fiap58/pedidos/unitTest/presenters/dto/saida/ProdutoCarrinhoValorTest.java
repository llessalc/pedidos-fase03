package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.presenters.dto.saida.ProdutoCarrinhoValor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class ProdutoCarrinhoValorTest {

    private ProdutoCarrinhoValor produtoCarrinhoValor;

    @BeforeEach
    public void setup() {
        // Cria um objeto Produto e define seu ID e nome
        Produto produto = new Produto("Product 1", "Description 1", new BigDecimal("10.0"));

        // Cria um objeto PedidoProduto e associa o Produto a ele
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(2);
        pedidoProduto.setPrecoVenda(new BigDecimal("10.0"));

        produtoCarrinhoValor = new ProdutoCarrinhoValor(pedidoProduto);
    }

    @Test
    void testConstructor() {
        assertEquals("Product 1", produtoCarrinhoValor.nome());
        assertEquals(2, produtoCarrinhoValor.quantidade());
        assertEquals("10.0", produtoCarrinhoValor.precoAtual());
    }
}