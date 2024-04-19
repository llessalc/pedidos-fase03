package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    // List<Endereco> buscarEnderecosPorCliente(Long idCliente);
}
