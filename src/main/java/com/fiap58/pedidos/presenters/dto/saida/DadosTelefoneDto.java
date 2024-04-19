package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Telefone;

public record DadosTelefoneDto(String ddd,
                               String numero,
                               String tipo) {

    public DadosTelefoneDto(Telefone telefone){
        this(telefone.getDdd(), telefone.getNumero(), telefone.getTipo());
    }
}
