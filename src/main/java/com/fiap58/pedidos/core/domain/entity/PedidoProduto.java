package com.fiap58.pedidos.core.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedido_Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO_PRODUTO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private int quantidade;

    @Column(name = "PRECO_VENDA", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoVenda;

    @Column(name = "OBSERVACAO", length = 200)
    private String observacao;



}
