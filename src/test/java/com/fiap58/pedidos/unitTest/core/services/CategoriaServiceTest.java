package com.fiap58.pedidos.unitTest.core.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.services.CategoriaService;
import com.fiap58.pedidos.gateway.CategoriaRepository;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;

public class CategoriaServiceTest {
    @Test
    void testBuscarCategoria() {
        // Arrange
        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);
        Categoria expectedCategoria = new Categoria("Teste");
        Long id = 1L;

        Mockito.when(repository.getReferenceById(id)).thenReturn(expectedCategoria);

        // Act
        Categoria actualCategoria = service.buscarCategoria(id);

        // Assert
        assertEquals(expectedCategoria, actualCategoria);
    }

    @Test
    void testRetornarCategoria() {
        // Arrange
        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);
        Categoria expectedCategoria = new Categoria("Teste");
        Long id = 1L;

        Mockito.when(repository.getReferenceById(id)).thenReturn(expectedCategoria);

        // Act
        DadosCategoriaDto actualCategoria = service.retornarCategoria(id);

        // Assert
        assertEquals(expectedCategoria.getNomeCategoria(), actualCategoria.nomeCategoria());
    }

    @Test
    void testCadastrarCategoria() {
        // Arrange
        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);
        Categoria expectedCategoria = new Categoria("Teste");
        CategoriaDtoEntrada dto = new CategoriaDtoEntrada("Teste");
        Mockito.when(repository.save(Mockito.any(Categoria.class))).thenReturn(expectedCategoria);

        // Act
        DadosCategoriaDto actualCategoria = service.cadastrarCategoria(dto);

        assertNotNull(actualCategoria);
        assertEquals(expectedCategoria.getNomeCategoria(), actualCategoria.getNomeCategoria());
    }

    @Test
    void testCadastrarCategoriaJaCadastrada() {
        // Arrange
        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);


        List<Categoria> categorias = new ArrayList<>();
        Categoria expectedCategoria = new Categoria("Teste");
        categorias.add(expectedCategoria);

        CategoriaDtoEntrada dto = new CategoriaDtoEntrada("Teste");

        Mockito.when(repository.findAll()).thenReturn(categorias);

        // Act
        DadosCategoriaDto actualCategoria = service.cadastrarCategoria(dto);

        assertNull(actualCategoria);
    }

    @Test
    void testDeletarCategoria() {

        // Arrange
        CategoriaRepository repository = Mockito.mock(CategoriaRepository.class);
        CategoriaService service = new CategoriaService(repository);
        Categoria categoria = new Categoria("Teste");
        Long id = 1L;

        Mockito.when(repository.getReferenceById(id)).thenReturn(categoria);
        Mockito.when(repository.save(categoria)).thenReturn(categoria);

        // Act
        service.deletarCategoria(id);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(categoria);
    }
}