package com.fiap58.pedidos.presenters.dto.entrada;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosPedidosEntrada(
        @JsonProperty("carrinho") List<ProdutoCarrinho> carrinho,
        @JsonProperty("clienteId") Long clienteId) {
    public DadosPedidosEntrada(List<ProdutoCarrinho> carrinho) {
        this(carrinho, null);
    }

}
