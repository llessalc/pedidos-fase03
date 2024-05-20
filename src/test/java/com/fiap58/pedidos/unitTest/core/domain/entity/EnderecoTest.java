package com.fiap58.pedidos.unitTest.core.domain.entity;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class EnderecoTest {

    private Endereco endereco;

    @BeforeEach
    public void setup() {
        EnderecoCadastro enderecoCadastro = new EnderecoCadastro("Rua Teste", "123", "São Paulo", "SP",
                "Complemento Teste");

        endereco = new Endereco(enderecoCadastro);
    }

    @Test
    public void testEnderecoCreation() {
        Assertions.assertNotNull(endereco);
        Assertions.assertEquals("Rua Teste", endereco.getRua());
        Assertions.assertEquals("123", endereco.getNumero());
        Assertions.assertEquals("São Paulo", endereco.getCidade());
        Assertions.assertEquals("SP", endereco.getEstado());
        Assertions.assertEquals("Complemento Teste", endereco.getComplemento());
        Assertions.assertNotNull(endereco.getCriadoEm());
    }

    @Test
    public void testEnderecoUpdate() {
        endereco.setRua("Nova Rua");
        endereco.setNumero("456");
        endereco.setCidade("Rio de Janeiro");
        endereco.setEstado("RJ");
        endereco.setComplemento("Novo Complemento");

        Instant updatedTime = Instant.now();
        endereco.setAtualizadoEm(updatedTime);

        Assertions.assertEquals("Nova Rua", endereco.getRua());
        Assertions.assertEquals("456", endereco.getNumero());
        Assertions.assertEquals("Rio de Janeiro", endereco.getCidade());
        Assertions.assertEquals("RJ", endereco.getEstado());
        Assertions.assertEquals("Novo Complemento", endereco.getComplemento());
        Assertions.assertEquals(updatedTime, endereco.getAtualizadoEm());
    }
}