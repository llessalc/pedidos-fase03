package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Cliente;

public record DadosClienteDto (Long idCliente, String cpf, String nome){

    public DadosClienteDto(Cliente cliente) {
        this(cliente.getIdCliente(), cliente.getCpf(), cliente.getNome());
    }
}
