package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Endereco;

public record DadosEnderecoDto(String rua, String cidade, String estado, String complemento) {

    public DadosEnderecoDto(Endereco endereco){
        this(endereco.getRua(), endereco.getCidade(), endereco.getEstado(), endereco.getComplemento());
    }
}
