package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "SELECT * FROM pedidos WHERE id_cliente = :idCliente", nativeQuery = true)
    List<Pedido> findByIdCliente(Long idCliente);
}
