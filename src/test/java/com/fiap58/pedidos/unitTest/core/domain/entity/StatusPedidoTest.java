package com.fiap58.pedidos.unitTest.core.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.StatusPedido;

public class StatusPedidoTest {

    @Test
    void testGetValor() {
        Assertions.assertEquals(1, StatusPedido.RECEBIDO.getValor());
        Assertions.assertEquals(2, StatusPedido.EM_PREPARACAO.getValor());
        Assertions.assertEquals(3, StatusPedido.PRONTO.getValor());
        Assertions.assertEquals(4, StatusPedido.FINALIZADO.getValor());
    }

    @Test
    void testGetStatus() {
        Assertions.assertEquals("Recebido", StatusPedido.RECEBIDO.getStatus());
        Assertions.assertEquals("Em Preparação", StatusPedido.EM_PREPARACAO.getStatus());
        Assertions.assertEquals("Pronto", StatusPedido.PRONTO.getStatus());
        Assertions.assertEquals("Finalizado", StatusPedido.FINALIZADO.getStatus());
    }
}