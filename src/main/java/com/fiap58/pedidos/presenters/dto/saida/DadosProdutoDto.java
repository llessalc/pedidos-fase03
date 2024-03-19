package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.domain.entity.Produto;

import java.math.BigDecimal;
import java.time.Instant;

public record DadosProdutoDto(
        String nome,
        String descricao,
        String categoria,
        BigDecimal precoAtual

) {
    public DadosProdutoDto(Produto produto){
        this(produto.getNome(), produto.getDescricao(),
                produto.getCategoria().getNomeCategoria(), produto.getPrecoAtual());
    }

}
