package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoSaida;

public interface ConsumerApiProducao {

    void acionaCriarPedidoProducao(DadosPedidoSaida pedidoSaida);
}
