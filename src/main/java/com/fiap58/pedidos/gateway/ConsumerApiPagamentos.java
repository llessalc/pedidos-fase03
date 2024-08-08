package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;

import java.util.List;

public interface ConsumerApiPagamentos {

    PagamentoDto acionaListarPagamentoId(Long idPagamento);
    void acionaExcluirPagamento(List<PagamentoDto> pagamentosDto);

}
