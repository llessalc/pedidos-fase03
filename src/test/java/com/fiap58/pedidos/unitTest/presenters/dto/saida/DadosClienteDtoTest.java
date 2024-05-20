package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DadosClienteDtoTest {

    @Test
    void testDadosClienteDtoConstructor() {

        List<TelefoneCadastro> telefonelist = new ArrayList<>();
        TelefoneCadastro telefoneCadastro = new TelefoneCadastro("11", "123456789", "Celular");
        telefonelist.add(telefoneCadastro);

        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro("123456789", "FULANO DE TAL", null,
                telefonelist);

        // Create a sample Cliente object
        Cliente cliente = new Cliente(dadosClienteCadastro);

        // Create a DadosClienteDto object using the constructor
        DadosClienteDto dadosClienteDto = new DadosClienteDto(cliente);

        // Assert that the properties are set correctly
        Assertions.assertEquals(cliente.getIdCliente(), dadosClienteDto.idCliente());
        Assertions.assertEquals(cliente.getCpf(), dadosClienteDto.cpf());
        Assertions.assertEquals(cliente.getNome(), dadosClienteDto.nome());
    }

    @Test
    void testDadosClienteDtoRecord() {

        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro("123456789", "FULANO DE TAL", null,
                null);
        // Create a sample Cliente object
        Cliente cliente = new Cliente(dadosClienteCadastro);

        // Create a DadosClienteDto object using the record
        DadosClienteDto dadosClienteDto = new DadosClienteDto(1L, "123456789", "FULANO DE TAL");

        // Assert that the properties are set correctly
        // Assertions.assertEquals(cliente.getIdCliente(), dadosClienteDto.idCliente());
        Assertions.assertEquals(cliente.getCpf(), dadosClienteDto.cpf());
        Assertions.assertEquals(cliente.getNome(), dadosClienteDto.nome());
    }
}