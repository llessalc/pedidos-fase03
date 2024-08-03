package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DadosProdutoDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String nome = "Product Name";
        String descricao = "Product Description";
        BigDecimal precoAtual = BigDecimal.valueOf(9.99);
        String nomeCategoria = "Category Name";

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(nomeCategoria);

        Produto produto = new Produto(nome, descricao, precoAtual);
        produto.setCategoria(categoria);
        // Act
        DadosProdutoDto dadosProdutoDto = new DadosProdutoDto(produto);

        // Assert
        Assertions.assertEquals(nome, dadosProdutoDto.nome());
        Assertions.assertEquals(descricao, dadosProdutoDto.descricao());
        Assertions.assertEquals(precoAtual, dadosProdutoDto.precoAtual());
        Assertions.assertEquals(nomeCategoria, dadosProdutoDto.categoria());

    }
}