package com.fiap58.pedidos.core.services;

import com.fiap58.pedidos.gateway.PedidoProdutoRepository;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.usecase.IPedidoProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoProdutoService implements IPedidoProdutoService {

    @Autowired
    private PedidoProdutoRepository repository;

    @Override
    public void inserirPedidoProduto(PedidoProduto pedidoProduto) {
        repository.save(pedidoProduto);
    }

    @Override
    public List<PedidoProduto> retornaPedidoProduto(Long id) {
        return repository.findAllByIdPedido(id);
    }
}
