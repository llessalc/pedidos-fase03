package com.fiap58.pedidos.presenters.dto.saida;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;

public record ProdutoCarrinhoSaida(


        Long idProduto,
        String nome,
        int quantidade,
        String precoAtual,
        String observacao
) {
    public ProdutoCarrinhoSaida(PedidoProduto pedidoProduto){
        this(pedidoProduto.getProduto().getIdProduto(),
                pedidoProduto.getProduto().getNome(),
                pedidoProduto.getQuantidade(),
                pedidoProduto.getPrecoVenda().toString(),
                pedidoProduto.getObservacao() != null
                        ? pedidoProduto.getObservacao()
                        : "");
    }
}
