package com.fiap58.pedidos.unitTest.core.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Telefone;

import java.time.Instant;

public class TelefoneTest {

    private Telefone telefone;

    @BeforeEach
    void setUp() {
        telefone = new Telefone();
    }

    @Test
    void testSetAndGetIdTelefone() {
        Long idTelefone = 1L;
        telefone.setIdTelefone(idTelefone);
        Assertions.assertEquals(idTelefone, telefone.getIdTelefone());
    }

    @Test
    void testSetAndGetCliente() {
        Cliente cliente = new Cliente();
        telefone.setCliente(cliente);
        Assertions.assertEquals(cliente, telefone.getCliente());
    }

    @Test
    void testSetAndGetDdd() {
        String ddd = "11";
        telefone.setDdd(ddd);
        Assertions.assertEquals(ddd, telefone.getDdd());
    }

    @Test
    void testSetAndGetNumero() {
        String numero = "1234567890";
        telefone.setNumero(numero);
        Assertions.assertEquals(numero, telefone.getNumero());
    }

    @Test
    void testSetAndGetTipo() {
        String tipo = "Celular";
        telefone.setTipo(tipo);
        Assertions.assertEquals(tipo, telefone.getTipo());
    }

    @Test
    void testSetAndGetCriadoEm() {
        Instant criadoEm = Instant.now();
        telefone.setCriadoEm(criadoEm);
        Assertions.assertEquals(criadoEm, telefone.getCriadoEm());
    }

    @Test
    void testSetAndGetAtualizadoEm() {
        Instant atualizadoEm = Instant.now();
        telefone.setAtualizadoEm(atualizadoEm);
        Assertions.assertEquals(atualizadoEm, telefone.getAtualizadoEm());
    }

    @Test
    void testSetAndGetDeletadoEm() {
        Instant deletadoEm = Instant.now();
        telefone.setDeletadoEm(deletadoEm);
        Assertions.assertEquals(deletadoEm, telefone.getDeletadoEm());
    }
}