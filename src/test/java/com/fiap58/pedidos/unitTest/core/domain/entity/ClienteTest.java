package com.fiap58.pedidos.unitTest.core.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {

    @Test
    void testClienteCreation() throws Exception {
        // Arrange
        String cpf = "12345678901";
        String nome = "John Doe";
        Instant criadoEm = Instant.now();

        // Act
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setCriadoEm(criadoEm);

        // Assert
        assertEquals(cpf, cliente.getCpf());
        assertEquals(nome, cliente.getNome());
        assertEquals(criadoEm, cliente.getCriadoEm());
    }

    @Test
    void testClienteCreationFromDto() throws Exception {
        // Arrange
        List<EnderecoCadastro> enderecos = new ArrayList<>();
        List<TelefoneCadastro> telefones = new ArrayList<>();
        EnderecoCadastro enderecoCadastro = new EnderecoCadastro("lorem ipsum", "123", "São Paulo", "SP", "apto 123");
        TelefoneCadastro telefoneCadastro = new TelefoneCadastro("11", "999999999", "celular");
        enderecos.add(enderecoCadastro);
        telefones.add(telefoneCadastro);

        DadosClienteCadastro clienteDto = new DadosClienteCadastro("12345678901", "John Doe",
                enderecos,
                telefones);
        // Act
        Cliente cliente = new Cliente(clienteDto);

        // Assert
        assertEquals(clienteDto.getCpf(), cliente.getCpf());
        assertEquals(clienteDto.getNome(), cliente.getNome());
        assertNotNull(cliente.getCriadoEm());
    }

    @Test
    void testClienteWithEnderecos() {
        // Arrange
        Cliente cliente = new Cliente();
        List<Endereco> enderecos = new ArrayList<>();
        EnderecoCadastro enderecoCadastro1 = new EnderecoCadastro("lorem ipsum", "123", "São Paulo", "SP", "apto 123");
        Endereco endereco1 = new Endereco(enderecoCadastro1);
        enderecos.add(endereco1);

        // Act
        cliente.setEnderecos(enderecos);

        // Assert
        assertEquals(enderecos, cliente.getEnderecos());
    }

    @Test
    void testClienteWithTelefones() {
        // Arrange
        Cliente cliente = new Cliente();
        List<Telefone> telefones = new ArrayList<>();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();
        telefones.add(telefone1);
        telefones.add(telefone2);

        // Act
        cliente.setTelefones(telefones);

        // Assert
        assertEquals(telefones, cliente.getTelefones());
    }
}