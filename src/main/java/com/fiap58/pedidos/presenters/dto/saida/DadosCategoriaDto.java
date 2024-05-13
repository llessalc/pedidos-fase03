package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Categoria;

public record DadosCategoriaDto(
        Long idCategoria,
        String nomeCategoria) {
    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public DadosCategoriaDto(Categoria categoria) {
        this(categoria.getIdCategoria(), categoria.getNomeCategoria());
    }

}
