package com.fiap58.pedidos.core.usecase;

import java.util.List;

import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosValorDto;

public interface IPedidoService {

    DadosPedidosDto inserirPedidoFila(DadosPedidosEntrada dto);

    List<DadosPedidosPainelDto> listarPedidos();

    Pedido retornaPedido(Long id);

    List<PedidoProduto> retornaTabelaJuncao(Pedido pedido);

    DadosPedidosDto atualizarPedido(Long id, Boolean pagamentoRealizado) throws Exception;

    DadosPedidosDto recebePagamento(Long id) throws Exception;

    DadosPedidosPainelDto defineTempoEspera(Pedido pedido, long tempoEspera);

    DadosPedidosValorDto buscaPedido(Long id);

}