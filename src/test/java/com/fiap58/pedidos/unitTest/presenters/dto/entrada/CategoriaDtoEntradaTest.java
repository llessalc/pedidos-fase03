package com.fiap58.pedidos.unitTest.presenters.dto.entrada;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;

public class CategoriaDtoEntradaTest {

    @Test
    void testGetNomeCategoria() {
        String nomeCategoria = "Test Category";
        CategoriaDtoEntrada categoriaDtoEntrada = new CategoriaDtoEntrada(nomeCategoria);

        Assertions.assertEquals(nomeCategoria, categoriaDtoEntrada.getnomeCategoria());
    }
}