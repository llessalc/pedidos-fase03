package com.fiap58.pedidos.presenters.dto.entrada;

import com.fiap58.pedidos.core.domain.entity.Telefone;

public record TelefoneCadastro(String ddd,
                               String numero,
                               String tipo) {
    public TelefoneCadastro (Telefone telefone){
        this(telefone.getDdd(), telefone.getNumero(), telefone.getTipo());
    }
}
