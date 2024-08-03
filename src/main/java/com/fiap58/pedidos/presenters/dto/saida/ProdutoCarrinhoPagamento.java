package com.fiap58.pedidos.presenters.dto.saida;

public record ProdutoCarrinhoPagamento(
        String nome,
        int quantidade,
        String precoAtual
) {
    public ProdutoCarrinhoPagamento(ProdutoCarrinhoSaida produtoCarrinhoSaida){
        this(produtoCarrinhoSaida.nome(), produtoCarrinhoSaida.quantidade(), produtoCarrinhoSaida.precoAtual());
    }
}
