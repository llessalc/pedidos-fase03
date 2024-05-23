package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosTelefoneDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DadosTelefoneDtoTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String ddd = "11";
        String numero = "123456789";
        String tipo = "Celular";
        TelefoneCadastro telefoneCadastro = new TelefoneCadastro(ddd, numero, tipo);
        
        
        Telefone telefone = new Telefone(telefoneCadastro);

        // Act
        DadosTelefoneDto dadosTelefoneDto = new DadosTelefoneDto(telefone);

        // Assert
        Assertions.assertEquals(ddd, dadosTelefoneDto.ddd());
        Assertions.assertEquals(numero, dadosTelefoneDto.numero());
        Assertions.assertEquals(tipo, dadosTelefoneDto.tipo());
    }
}