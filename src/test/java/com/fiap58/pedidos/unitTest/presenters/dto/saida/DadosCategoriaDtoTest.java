package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DadosCategoriaDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long idCategoria = 1L;
        String nomeCategoria = "Test Category";
        Categoria categoria = new Categoria(nomeCategoria);
        categoria.setIdCategoria(idCategoria);

        // Act
        DadosCategoriaDto dadosCategoriaDto = new DadosCategoriaDto(categoria);

        // Assert
        Assertions.assertEquals(idCategoria, dadosCategoriaDto.idCategoria());
        Assertions.assertEquals(nomeCategoria, dadosCategoriaDto.nomeCategoria());
    }
}