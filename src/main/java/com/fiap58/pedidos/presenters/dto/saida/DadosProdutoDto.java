package com.fiap58.pedidos.presenters.dto.saida;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiap58.pedidos.core.domain.entity.Produto;

import java.math.BigDecimal;

@JsonSerialize
@JsonDeserialize
public record DadosProdutoDto(
        String nome,
        String descricao,
        String categoria,
        BigDecimal precoAtual

) {
    public DadosProdutoDto(Produto produto) {
        this(produto.getNome(), produto.getDescricao(),
                produto.getCategoria().getNomeCategoria(), produto.getPrecoAtual());
    }

}
