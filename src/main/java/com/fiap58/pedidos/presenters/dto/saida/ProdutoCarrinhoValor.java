package com.fiap58.pedidos.presenters.dto.saida;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;

import java.math.BigDecimal;

public record ProdutoCarrinhoValor(

        @JsonIgnore
        Long idProduto,
        String nome,
        int quantidade,
        String precoAtual
) {
    public ProdutoCarrinhoValor(PedidoProduto pedidoProduto){
        this(pedidoProduto.getProduto().getIdProduto(),
                pedidoProduto.getProduto().getNome(),
                pedidoProduto.getQuantidade(),
                pedidoProduto.getPrecoVenda().toString());
    }
}
