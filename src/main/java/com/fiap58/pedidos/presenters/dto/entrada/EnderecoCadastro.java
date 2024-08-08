package com.fiap58.pedidos.presenters.dto.entrada;

import com.fiap58.pedidos.core.domain.entity.Endereco;

public record EnderecoCadastro(String rua,
                               String numero,
                               String cidade,
                               String estado,
                               String complemento) {
    public EnderecoCadastro (Endereco endereco){
        this(endereco.getRua(), endereco.getNumero(), endereco.getCidade(),
                endereco.getEstado(), endereco.getComplemento());
    }
}
