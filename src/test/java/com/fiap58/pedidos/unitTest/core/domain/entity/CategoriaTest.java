package com.fiap58.pedidos.unitTest.core.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.Categoria;

import java.time.Instant;

public class CategoriaTest {

    private Categoria categoria;

    @BeforeEach
    public void setup() {
        categoria = new Categoria();
    }

    @Test
    public void testSetAndGetIdCategoria() {
        Long id = 1L;
        categoria.setIdCategoria(id);
        Assertions.assertEquals(id, categoria.getIdCategoria());
    }

    @Test
    public void testSetAndGetNameCategoria() {
        String nome = "Test Category";
        categoria.setNomeCategoria(nome);
        Assertions.assertEquals(nome, categoria.getNomeCategoria());
    }

    @Test
    public void testSetAndGetCriadoEm() {
        Instant criadoEm = Instant.now();
        categoria.setCriadoEm(criadoEm);
        Assertions.assertEquals(criadoEm, categoria.getCriadoEm());
    }

    @Test
    public void testSetAndGetAtualizadoEm() {
        Instant atualizadoEm = Instant.now();
        categoria.setAtualizadoEm(atualizadoEm);
        Assertions.assertEquals(atualizadoEm, categoria.getAtualizadoEm());
    }

    @Test
    public void testSetAndGetDeletadoEm() {
        Instant deletadoEm = Instant.now();
        categoria.setDeletadoEm(deletadoEm);
        Assertions.assertEquals(deletadoEm, categoria.getDeletadoEm());
    }

    @Test
    public void testCategoriaConstructorWithName() {
        String nome = "Test Category";
        Categoria categoria = new Categoria(nome);
        Assertions.assertEquals(nome, categoria.getNomeCategoria());
        Assertions.assertNotNull(categoria.getCriadoEm());
    }
}