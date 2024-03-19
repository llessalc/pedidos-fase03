package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.gateway.PedidoProdutoRepository;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class pedidoProdutoService {

    @Autowired
    private PedidoProdutoRepository repository;

    public void inserirPedidoProduto(PedidoProduto pedidoProduto){
        repository.save(pedidoProduto);
    }

    public List<PedidoProduto> retornaPedidoProduto(Long id){
        return repository.findAllByIdPedido(id);
    }
}
