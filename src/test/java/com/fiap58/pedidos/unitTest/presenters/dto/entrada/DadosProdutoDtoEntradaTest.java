package com.fiap58.pedidos.unitTest.presenters.dto.entrada;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;

import java.math.BigDecimal;

public class DadosProdutoDtoEntradaTest {

    @Test
    public void testGetters() {
        Long idCategoria = 1L;
        String nome = "Produto Teste";
        String descricao = "Descrição do produto teste";
        BigDecimal precoAtual = BigDecimal.valueOf(10.99);

        DadosProdutoDtoEntrada produto = new DadosProdutoDtoEntrada(idCategoria, nome, descricao, precoAtual);

        Assertions.assertEquals(idCategoria, produto.idCategoria());
        Assertions.assertEquals(nome, produto.nome());
        Assertions.assertEquals(descricao, produto.descricao());
        Assertions.assertEquals(precoAtual, produto.precoAtual());
    }

    @Test
    public void testRecordConstructor() {
        Long idCategoria = 1L;
        String nome = "Produto Teste";
        String descricao = "Descrição do produto teste";
        BigDecimal precoAtual = BigDecimal.valueOf(10.99);

        DadosProdutoDtoEntrada produto = new DadosProdutoDtoEntrada(idCategoria, nome, descricao, precoAtual);

        Assertions.assertEquals(idCategoria, produto.idCategoria());
        Assertions.assertEquals(nome, produto.nome());
        Assertions.assertEquals(descricao, produto.descricao());
        Assertions.assertEquals(precoAtual, produto.precoAtual());
    }
}