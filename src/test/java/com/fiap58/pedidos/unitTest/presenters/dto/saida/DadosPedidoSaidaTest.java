package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoSaida;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoProducao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DadosPedidoSaidaTest {

    @Test
    void testaDadosPedidoSaida(){
        DadosProdutoProducao dadosProdutoProducao = new DadosProdutoProducao("Produto1", "Obs", 1, "Recebido");
        List<DadosProdutoProducao> dadosProdutoProducaos = new ArrayList<>();
        dadosProdutoProducaos.add(dadosProdutoProducao);

        DadosPedidoSaida dadosPedidoSaida = new DadosPedidoSaida();
        dadosPedidoSaida.setIdPedido(1L);
        dadosPedidoSaida.setProdutos(dadosProdutoProducaos);

        DadosPedidoSaida dadosPedidoSaida1 = new DadosPedidoSaida(2L, dadosProdutoProducaos);

        assertThat(dadosPedidoSaida.getProdutos()).isEqualTo(dadosPedidoSaida1.getProdutos());
        assertThat(dadosPedidoSaida.getIdPedido()).isNotEqualTo(dadosPedidoSaida1.getIdPedido());
    }

}