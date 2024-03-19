package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

    @Query("SELECT pp FROM PedidoProduto pp WHERE pp.pedido.id = :idPedido")
    List<PedidoProduto> findAllByIdPedido(Long idPedido);
}
