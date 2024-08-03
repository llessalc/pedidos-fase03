package com.fiap58.pedidos.adapter;

import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Instant;

public class PagamentoMsgToPagamentoDto {

    public static PagamentoDto pagamentoToPagamentoDto(String pagamentoMsg) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantAdapter()).create();
        return gson.fromJson(pagamentoMsg, PagamentoDto.class);

    }
}
