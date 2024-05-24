package com.fiap58.pedidos.core.specifications;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.domain.entity.Produto;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecification {
    public static Specification<Produto> temCategoria(String nomeCategoria) {
        return (root, query, criteriaBuilder) -> {
            Join<Categoria, Produto> produtosCategoria = root.join("categoria");
            return criteriaBuilder.equal(produtosCategoria.get("nomeCategoria"), nomeCategoria);
        };
    }
}
