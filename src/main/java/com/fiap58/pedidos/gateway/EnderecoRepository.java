package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT e.* FROM enderecos e WHERE id_cliente = :idCliente", nativeQuery = true)
    List<Endereco> findByIdCliente(Long idCliente);
}
