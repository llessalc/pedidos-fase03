package com.fiap58.pedidos.presenters.dto.entrada;

import java.math.BigDecimal;

public record DadosProdutoDtoEntrada(Long idCategoria,
                                     String nome,
                                     String descricao,
                                     BigDecimal precoAtual) {
}
