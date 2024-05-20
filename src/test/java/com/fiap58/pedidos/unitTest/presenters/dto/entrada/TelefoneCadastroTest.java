package com.fiap58.pedidos.unitTest.presenters.dto.entrada;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;

public class TelefoneCadastroTest {

    @Test
    public void testTelefoneCadastro() {
        String ddd = "11";
        String numero = "123456789";
        String tipo = "Celular";

        TelefoneCadastro telefoneCadastro = new TelefoneCadastro(ddd, numero, tipo);

        Assertions.assertEquals(ddd, telefoneCadastro.ddd());
        Assertions.assertEquals(numero, telefoneCadastro.numero());
        Assertions.assertEquals(tipo, telefoneCadastro.tipo());
    }
}