package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.*;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoProducao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DadosProdutoProducaoTest {

    @Test
    void testaDadosProdutoProducaoTest(){
        DadosProdutoProducao dadosProdutoProducao = new DadosProdutoProducao();
        dadosProdutoProducao.setNome("Produto1");
        dadosProdutoProducao.setObservacao("Obs");
        dadosProdutoProducao.setQuantidade(1);
        dadosProdutoProducao.setStatusProduto("Recebido");

        Categoria categoria = new Categoria("lanche");
        Produto produto = new Produto("Produto1", "Descricao 1", new BigDecimal("10.00"));
        produto.setIdProduto(100L);
        produto.setCategoria(categoria);
        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro("0000", "Cliente 1", null, null);
        Cliente cliente = new Cliente(dadosClienteCadastro);
        Pedido pedido = new Pedido(10L, cliente);

        PedidoProduto pedidoProduto = new PedidoProduto(1L, pedido, produto, 1, new BigDecimal(10), "Obs");
        DadosProdutoProducao dadosProdutoProducao2 = new DadosProdutoProducao(pedidoProduto);

        DadosProdutoProducao dadosProdutoProducao3 = new DadosProdutoProducao("Produto1", "Obs", 1, "Recebido");


        assertThat(dadosProdutoProducao.getNome()).isEqualTo(dadosProdutoProducao2.getNome());
        assertThat(dadosProdutoProducao.getNome()).isEqualTo(dadosProdutoProducao3.getNome());
        assertThat(dadosProdutoProducao.getObservacao()).isEqualTo(dadosProdutoProducao2.getObservacao());
        assertThat(dadosProdutoProducao.getObservacao()).isEqualTo(dadosProdutoProducao3.getObservacao());
        assertThat(dadosProdutoProducao.getQuantidade()).isEqualTo(dadosProdutoProducao2.getQuantidade());
        assertThat(dadosProdutoProducao.getQuantidade()).isEqualTo(dadosProdutoProducao3.getQuantidade());
        assertThat(dadosProdutoProducao.getStatusProduto()).isNotEqualTo(dadosProdutoProducao2.getStatusProduto());
        assertThat(dadosProdutoProducao.getStatusProduto()).isEqualTo(dadosProdutoProducao3.getStatusProduto());


    }

}