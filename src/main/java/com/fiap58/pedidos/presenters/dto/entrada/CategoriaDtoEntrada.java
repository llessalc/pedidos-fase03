package com.fiap58.pedidos.presenters.dto.entrada;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize

public record CategoriaDtoEntrada(
        @JsonProperty("nomeCategoria") String nomeCategoria) {
    public String getnomeCategoria() {
        return nomeCategoria;
    }
}
