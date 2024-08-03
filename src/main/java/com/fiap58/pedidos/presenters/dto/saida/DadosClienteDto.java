package com.fiap58.pedidos.presenters.dto.saida;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiap58.pedidos.core.domain.entity.Cliente;

@JsonSerialize
@JsonDeserialize
public record DadosClienteDto(
        @JsonProperty("idCliente") Long idCliente,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("nome") String nome) {

    public DadosClienteDto(Cliente cliente) {
        this(cliente.getIdCliente(), cliente.getCpf(), cliente.getNome());
    }
}
