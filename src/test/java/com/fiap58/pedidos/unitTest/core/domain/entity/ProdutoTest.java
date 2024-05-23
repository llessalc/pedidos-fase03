package com.fiap58.pedidos.unitTest.core.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.domain.entity.Produto;

import java.math.BigDecimal;
import java.time.Instant;

public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    public void setup() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNomeCategoria("Eletrônicos");

        produto = new Produto("Smartphone", "Descrição do smartphone", new BigDecimal("1500.00"));
        produto.setCategoria(categoria);
    }

    @Test
    public void testProdutoCreation() {
        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Smartphone", produto.getNome());
        Assertions.assertEquals("Descrição do smartphone", produto.getDescricao());
        Assertions.assertEquals(new BigDecimal("1500.00"), produto.getPrecoAtual());
        Assertions.assertNotNull(produto.getCriadoEm());
        Assertions.assertNotNull(produto.getAtualizadoEm());
    }

    @Test
    public void testProdutoSettersAndGetters() {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setIdCategoria(2L);
        novaCategoria.setNomeCategoria("Informática");

        produto.setNome("Laptop");
        produto.setDescricao("Descrição do laptop");
        produto.setPrecoAtual(new BigDecimal("2500.00"));
        produto.setCategoria(novaCategoria);

        Instant updatedTime = Instant.now();
        produto.setAtualizadoEm(updatedTime);

        Assertions.assertEquals("Laptop", produto.getNome());
        Assertions.assertEquals("Descrição do laptop", produto.getDescricao());
        Assertions.assertEquals(new BigDecimal("2500.00"), produto.getPrecoAtual());
        Assertions.assertEquals(novaCategoria, produto.getCategoria());
        Assertions.assertEquals(updatedTime, produto.getAtualizadoEm());
    }
}