package com.fiap58.pedidos.gateway;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {
}
