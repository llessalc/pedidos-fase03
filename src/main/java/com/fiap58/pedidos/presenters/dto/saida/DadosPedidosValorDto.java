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
public class DadosPedidosValorDto {
    @JsonIgnore
    private Long id;
    private List<ProdutoCarrinhoValor> produtos;
    private String nomeCliente = "";
    private Instant dataPedido;
    private StatusPedido status;

    public DadosPedidosValorDto(Pedido pedido, List<PedidoProduto> pedidoProdutos){
        this.id = pedido.getIdPedido();
        this.dataPedido = pedido.getDataPedido();
        this.produtos = this.retornaCarrinho(pedidoProdutos);
        if(pedido.getCliente() != null)
            this.nomeCliente = pedido.getCliente().getNome();
        this.status = pedido.getStatus();
    }

    public List<ProdutoCarrinhoValor> retornaCarrinho(List<PedidoProduto> pedidoProdutos){
        List<ProdutoCarrinhoValor> produtos = new ArrayList<>();
        for (PedidoProduto pedidoProduto : pedidoProdutos
        ) {
            produtos.add(new ProdutoCarrinhoValor(pedidoProduto));
        }
        return produtos;
    }
}
