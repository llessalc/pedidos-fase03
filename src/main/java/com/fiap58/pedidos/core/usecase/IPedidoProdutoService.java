package com.fiap58.pedidos.core.usecase;

import java.util.List;

import com.fiap58.pedidos.core.domain.entity.PedidoProduto;

public interface IPedidoProdutoService {

    void inserirPedidoProduto(PedidoProduto pedidoProduto);

    List<PedidoProduto> retornaPedidoProduto(Long id);

}