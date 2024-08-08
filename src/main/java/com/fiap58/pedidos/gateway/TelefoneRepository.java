package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    @Query(value = "SELECT t.* FROM telefones t WHERE id_cliente = :idCliente", nativeQuery = true)
    List<Telefone> findByIdCliente(Long idCliente);
}
