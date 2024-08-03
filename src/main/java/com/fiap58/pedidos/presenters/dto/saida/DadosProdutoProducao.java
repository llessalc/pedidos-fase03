package com.fiap58.pedidos.presenters.dto.saida;


import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosProdutoProducao {

    private String nome;

    private String observacao;

    private int quantidade;

    private String statusProduto;

    public DadosProdutoProducao(PedidoProduto pedidoProduto) {
        this.nome = pedidoProduto.getProduto().getNome();
        this.observacao = pedidoProduto.getObservacao();
        this.quantidade = pedidoProduto.getQuantidade();
    }
}
