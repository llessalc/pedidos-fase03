package com.fiap58.pedidos.presenters.dto.entrada;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosClienteCadastro(
        @JsonProperty("cpf") String cpf,
        @JsonProperty("nome") String nome,
        @JsonProperty("enderecos") List<EnderecoCadastro> enderecos,
        @JsonProperty("telefones") List<TelefoneCadastro> telefones) {
    @JsonCreator
    public DadosClienteCadastro {
        // O corpo do construtor é vazio porque os campos são final e inicializados
        // diretamente
    }
}
