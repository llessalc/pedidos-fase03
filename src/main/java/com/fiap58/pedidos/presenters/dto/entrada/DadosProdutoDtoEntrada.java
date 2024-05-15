package com.fiap58.pedidos.presenters.dto.entrada;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonProperty;

@JsonSerialize
@JsonDeserialize
public record DadosProdutoDtoEntrada(
                @JsonProperty("idCategoria") Long idCategoria,
                @JsonProperty("nome") String nome,
                @JsonProperty("descricao") String descricao,
                @JsonProperty("precoAtual") BigDecimal precoAtual) {

        public Long idCategoria() {
                return idCategoria;
        }

        public String nome() {
                return nome;
        }

        public String descricao() {
                return nome;
        }

        public BigDecimal precoAtual() {
                return precoAtual;
        }

        // public DadosProdutoDtoEntrada {
        // }
}
