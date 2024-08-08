package com.fiap58.pedidos.presenters.dto.entrada;

import java.math.BigDecimal;
import java.time.Instant;

public record PagamentoDto(
        Long idPagamento,
        Long idPedido,
        String qrCode,
        String inStoreOrderId,
        BigDecimal valorTotal,
        String status,
        Instant criadoEm,
        Instant atualizadoEm,
        Instant deletadoEm
) {
}
