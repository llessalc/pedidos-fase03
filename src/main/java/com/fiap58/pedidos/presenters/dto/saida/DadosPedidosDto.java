package com.fiap58.pedidos.presenters.dto.saida;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DadosPedidosDto {

    private Long id;
    private List<ProdutoCarrinhoSaida> produtos;
    private String nomeCliente = "";
    private Instant dataPedido;
    private StatusPedido status;

    public void setId(Long id) {
        this.id = id;
    }

    public void setProdutos(List<ProdutoCarrinhoSaida> produtos) {
        this.produtos = produtos;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setDataPedido(Instant dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public DadosPedidosDto(Pedido pedido, List<PedidoProduto> pedidoProdutos) {
        this.id = pedido.getIdPedido();
        this.dataPedido = pedido.getDataPedido();
        this.produtos = this.retornaCarrinho(pedidoProdutos);
        if (pedido.getCliente() != null)
            this.nomeCliente = pedido.getCliente().getNome();
        this.status = pedido.getStatus();
    }

    public List<ProdutoCarrinhoSaida> retornaCarrinho(List<PedidoProduto> pedidoProdutos) {
        List<ProdutoCarrinhoSaida> produtos = new ArrayList<>();
        for (PedidoProduto pedidoProduto : pedidoProdutos) {
            produtos.add(new ProdutoCarrinhoSaida(pedidoProduto));
        }
        return produtos;
    }
}
