package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoPagamento;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoSaida;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.PedidoFinalizadoDto;

public interface IQueuePublisher {

    void publicarPedidoCriado(DadosPedidoPagamento dadosPedidosDto);

    void publicarPedidoProducao(DadosPedidoSaida dadosPedidoProducao);

    void publicarFinalizacaoPedido(PedidoFinalizadoDto pedidoFinalizadoDto);
}
