package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.StatusPedido;

import java.time.Instant;
import java.util.List;

public record DadosPedidoPagamento(
        Long id,
        List<ProdutoCarrinhoPagamento> produtos,
        String nomeCliente,
        Instant dataPedido,
        StatusPedido status) {
    public DadosPedidoPagamento(DadosPedidosDto dto, List<ProdutoCarrinhoPagamento> produtos){
        this(dto.getId(), produtos, dto.getNomeCliente(), dto.getDataPedido(), dto.getStatus());
    }
}
