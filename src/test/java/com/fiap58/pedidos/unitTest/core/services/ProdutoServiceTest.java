package com.fiap58.pedidos.unitTest.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.services.ClienteService;
import com.fiap58.pedidos.core.services.ProdutoService;
import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.core.usecase.IEnderecoService;
import com.fiap58.pedidos.core.usecase.ITelefoneService;
import com.fiap58.pedidos.gateway.ClienteRepository;
import com.fiap58.pedidos.gateway.ProdutoRepository;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;

public class ProdutoServiceTest {
    @Test
    void testBuscarProduto() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
        ProdutoService service = new ProdutoService(repository, categoriaService);

        Produto produto = new Produto("Teste", "Teste descricao", new BigDecimal(10.0));
        Long id = 1L;

        Mockito.when(repository.getReferenceById(id)).thenReturn(produto);

        // Act
        Produto result = service.buscarProduto(id);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testDeleteProduto() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
        ProdutoService service = new ProdutoService(repository, categoriaService);

        Produto produto = new Produto("Teste", "Teste descricao", new BigDecimal(10.0));
        Long id = 1L;
        Mockito.when(service.buscarProduto(id)).thenReturn(produto);

        // Act
        service.deleteProduto(id);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(produto);
    }

    @Test
    void testInserirProduto() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
        ProdutoService service = new ProdutoService(repository, categoriaService);
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("Teste Categoria");

        DadosProdutoDtoEntrada dadosProdutoDtoEntrada = new DadosProdutoDtoEntrada(1l, "Teste", "Teste descricao",
                new BigDecimal(10.0));

        Produto produto = new Produto("Teste", "Teste descricao", new BigDecimal(10.0));
        produto.setNome(dadosProdutoDtoEntrada.nome());
        produto.setCategoria(categoria);

        Mockito.when(categoriaService.buscarCategoria(1l)).thenReturn(categoria);
        Mockito.when(repository.save(Mockito.any(Produto.class))).thenReturn(produto);

        DadosProdutoDto result = service.inserirProduto(dadosProdutoDtoEntrada);
        assertNotNull(result);
    }

    @Test
    void testListarProdutos() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
        ProdutoService service = new ProdutoService(repository, categoriaService);

        Produto produto1 = new Produto("Teste1", "Descricao", new BigDecimal(10));
        Produto produto2 = new Produto("Teste2", "Descricao2", new BigDecimal(10));
        List<Produto> produtos = Arrays.asList(produto1, produto2);

        Mockito.when(repository.findAll()).thenReturn(produtos);

        // Act
        List<Produto> result = service.listarProdutos();

        // Assert
        assertEquals(produtos, result);

    }

    @Test
    void testRetornaListaProdutos() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
        ProdutoService service = new ProdutoService(repository, categoriaService);

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("Teste Categoria");
        Produto produto1 = new Produto("Teste1", "Descricao", new BigDecimal(10));
        produto1.setCategoria(categoria);
        Produto produto2 = new Produto("Teste2", "Descricao2", new BigDecimal(10));
        produto2.setCategoria(categoria);
        List<Produto> produtos = Arrays.asList(produto1, produto2);

        Mockito.when(repository.findAll()).thenReturn(produtos);

        // Act
        List<DadosProdutoDto> result = service.retornaListaProdutos();

        // Assert
        assertNotNull(result);

    }

    // @Test
    // void testRetornaListaProdutosCategoria() {
    // ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
    // ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
    // ProdutoService service = new ProdutoService(repository, categoriaService);

    // Categoria categoria = new Categoria();
    // categoria.setNomeCategoria("Teste Categoria");
    // Produto produto1 = new Produto("Teste1", "Descricao", new BigDecimal(10));
    // produto1.setCategoria(categoria);
    // Produto produto2 = new Produto("Teste2", "Descricao2", new BigDecimal(10));
    // produto2.setCategoria(categoria);
    // List<Produto> produtos = Arrays.asList(produto1, produto2);

    // Mockito.when(repository.findByCategoriaNomeCategoria("Teste
    // Categoria")).thenReturn(produtos);

    // // Act
    // List<Produto> result = service.RetornaListaProdutosCategoria("Teste
    // Categoria");

    // // Assert
    // assertEquals(produtos, result);
    // }

    // @Test
    // void testRetornaProduto() {
    // ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
    // ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
    // ProdutoService service = new ProdutoService(repository, categoriaService);

    // Produto produto = new Produto("Teste", "Teste descricao", new
    // BigDecimal(10.0));
    // produto.setDeletadoEm(Instant.now());
    // Long id = 1L;

    // Mockito.when(repository.findById(id)).thenReturn(Optional.of(produto));

    // // Act
    // DadosProdutoDto result = service.retornaProduto(id);

    // // Assert
    // assertEquals(produto, result);

    // }

    // @Test
    // void testUpdateProduto() {
    // ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
    // ICategoriaService categoriaService = Mockito.mock(ICategoriaService.class);
    // ProdutoService service = new ProdutoService(repository, categoriaService);

    // Categoria categoria = new Categoria();
    // categoria.setNomeCategoria("Teste Categoria");

    // Produto produto = new Produto("Teste", "Teste descricao", new
    // BigDecimal(10.0));
    // produto.setCategoria(categoria);
    // Long id = 1L;

    // DadosProdutoDto dto = new DadosProdutoDto(produto);

    // Mockito.when(repository.findById(id)).thenReturn(Optional.of(produto));
    // Mockito.when(repository.save(Mockito.any(Produto.class))).thenAnswer(i ->
    // i.getArguments()[0]);

    // // Act
    // Produto result = service.updateProduto(id, dto);

    // // Assert
    // assertNotNull(result);

    // }
}
