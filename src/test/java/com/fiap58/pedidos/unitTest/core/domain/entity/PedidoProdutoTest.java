package com.fiap58.pedidos.unitTest.core.domain.entity;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;

public class PedidoProdutoTest {

    @Test
    void testPedidoProdutoCreation() {
        // Create a PedidoProduto instance
        PedidoProduto pedidoProduto = new PedidoProduto();

        // Set the properties
        Pedido pedido = new Pedido();
        Produto produto = new Produto("Teste", "Lorem ipsum", new BigDecimal("10.0"));
        int quantidade = 2;
        BigDecimal precoVenda = new BigDecimal("20.0");
        String observacao = "Test observation";

        pedidoProduto.setPedido(pedido);
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(quantidade);
        pedidoProduto.setPrecoVenda(precoVenda);
        pedidoProduto.setObservacao(observacao);

        // Verify the properties
        Assertions.assertEquals(pedido, pedidoProduto.getPedido());
        Assertions.assertEquals(produto, pedidoProduto.getProduto());
        Assertions.assertEquals(quantidade, pedidoProduto.getQuantidade());
        Assertions.assertEquals(precoVenda, pedidoProduto.getPrecoVenda());
        Assertions.assertEquals(observacao, pedidoProduto.getObservacao());
    }
}