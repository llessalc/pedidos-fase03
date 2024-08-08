package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import com.fiap58.pedidos.presenters.dto.saida.ProdutoCarrinhoSaida;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DadosPedidosPainelDtoTest {

    private DadosPedidosPainelDto dadosPedidosPainelDto;

    @BeforeEach
    public void setup() {
        List<ProdutoCarrinhoSaida> produtos = new ArrayList<>();
        produtos.add(new ProdutoCarrinhoSaida(1l, "Product 1", 1, "19.00", ""));
        produtos.add(new ProdutoCarrinhoSaida(2l, "Product 2", 2, "29.99",""));

        DadosPedidosDto dadosPedidosDto = new DadosPedidosDto();
        dadosPedidosDto.setId(1L);
        dadosPedidosDto.setProdutos(produtos);
        dadosPedidosDto.setNomeCliente("John Doe");
        dadosPedidosDto.setDataPedido(Instant.now());
        dadosPedidosDto.setStatus(StatusPedido.RECEBIDO);

        long tempoEspera = 1000L;

        dadosPedidosPainelDto = new DadosPedidosPainelDto(dadosPedidosDto, tempoEspera);
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals(1L, dadosPedidosPainelDto.getId());
    }

    @Test
    public void testGetProdutos() {
        Assertions.assertEquals(2, dadosPedidosPainelDto.getProdutos().size());
    }

    @Test
    public void testGetNomeCliente() {
        Assertions.assertEquals("John Doe", dadosPedidosPainelDto.getNomeCliente());
    }

    @Test
    public void testGetDataPedido() {
        Assertions.assertNotNull(dadosPedidosPainelDto.getDataPedido());
    }

    @Test
    public void testGetStatus() {
        Assertions.assertEquals(StatusPedido.RECEBIDO, dadosPedidosPainelDto.getStatus());
    }

    @Test
    public void testGetTempoEspera() {
        Assertions.assertEquals(1000L, dadosPedidosPainelDto.getTempoEspera());
    }
}