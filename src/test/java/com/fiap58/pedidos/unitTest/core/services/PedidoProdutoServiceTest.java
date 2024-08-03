package com.fiap58.pedidos.unitTest.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.services.PedidoProdutoService;
import com.fiap58.pedidos.gateway.PedidoProdutoRepository;

public class PedidoProdutoServiceTest {
    @Test
    void testInserirPedidoProduto() {
        // Arrange
        PedidoProdutoRepository repository = Mockito.mock(PedidoProdutoRepository.class);
        PedidoProdutoService service = new PedidoProdutoService(repository);
        PedidoProduto pedidoProduto = new PedidoProduto();

        // Act
        service.inserirPedidoProduto(pedidoProduto);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(pedidoProduto);
    }

    @Test
    void testRetornaPedidoProduto() {
        // Arrange
        Long id = 1L;
        PedidoProdutoRepository repository = Mockito.mock(PedidoProdutoRepository.class);
        PedidoProdutoService service = new PedidoProdutoService(repository);
        List<PedidoProduto> listpedidoProduto = new ArrayList<>();
        PedidoProduto pedidoProduto = new PedidoProduto();
        listpedidoProduto.add(pedidoProduto);

        // Act
        service.retornaPedidoProduto(id);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).findAllByIdPedido(id);

    }
}
