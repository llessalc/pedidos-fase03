package com.fiap58.pedidos.presenters.dto.entrada;

import java.util.List;

public record DadosPedidosEntrada(
        List<ProdutoCarrinho> carrinho,
        Long clienteId
) {
    public DadosPedidosEntrada(List<ProdutoCarrinho> carrinho){
        this(carrinho, null);
    }

}
