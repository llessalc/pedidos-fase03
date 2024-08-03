package com.fiap58.pedidos.unitTest.core.domain.entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PedidoTest {

    private Pedido pedido;
    private Cliente cliente;
    private List<PedidoProduto> produtos;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
        produtos = new ArrayList<>();
        pedido = new Pedido(1L, cliente);
        pedido.setProdutos(produtos);
        pedido.setDataPedido(Instant.now());
        pedido.setStatus(StatusPedido.RECEBIDO);
    }

    @Test
    public void testPedidoInitialization() {
        Assertions.assertEquals(1L, pedido.getIdPedido());
        Assertions.assertEquals(cliente, pedido.getCliente());
        Assertions.assertEquals(produtos, pedido.getProdutos());
        Assertions.assertNotNull(pedido.getDataPedido());
        Assertions.assertEquals(StatusPedido.RECEBIDO, pedido.getStatus());
    }

    @Test
    public void testPedidoEmptyConstructor() {
        Pedido emptyPedido = new Pedido();
        Assertions.assertNull(emptyPedido.getIdPedido());
        Assertions.assertNull(emptyPedido.getCliente());
        Assertions.assertNull(emptyPedido.getProdutos());
        Assertions.assertNull(emptyPedido.getDataPedido());
        Assertions.assertNull(emptyPedido.getStatus());
    }

    @Test
    public void testPedidoEstimativaPreparo() {
        Instant estimativaPreparo = Instant.now();
        pedido.setEstimativaPreparo(estimativaPreparo);
        Assertions.assertEquals(estimativaPreparo, pedido.getEstimativaPreparo());
    }
}