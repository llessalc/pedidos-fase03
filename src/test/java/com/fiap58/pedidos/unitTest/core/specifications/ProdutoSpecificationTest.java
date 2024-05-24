package com.fiap58.pedidos.unitTest.core.specifications;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.specifications.ProdutoSpecification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.mockito.Mockito.*;

public class ProdutoSpecificationTest {

    @Test
    void testTemCategoria() {
        // Mock the necessary objects
        Root<Produto> root = mock(Root.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Join<Object, Object> produtosCategoria = mock(Join.class);

        // Set up the mock behavior
        when(root.join("categoria")).thenReturn(produtosCategoria);
        when(produtosCategoria.get("nomeCategoria")).thenReturn(mock(Path.class));
        when(criteriaBuilder.equal(any(), any())).thenReturn(mock(Predicate.class));

        // Call the method under test
        Specification<Produto> specification = ProdutoSpecification.temCategoria("Teste");
        specification.toPredicate(root, query, criteriaBuilder);

        // Verify the expected interactions
        verify(root).join("categoria");
        verify(produtosCategoria).get("nomeCategoria");

    }
}