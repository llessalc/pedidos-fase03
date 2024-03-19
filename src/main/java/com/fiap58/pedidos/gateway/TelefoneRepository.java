package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
