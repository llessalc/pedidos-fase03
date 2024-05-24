package com.fiap58.pedidos.unitTest.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosEnderecoDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DadosEnderecoDtoTest {

    @Test
    void testConstructorWithEndereco() {
        // Arrange
        EnderecoCadastro enderecoCadastro = new EnderecoCadastro("Rua A", "Numero A", "Cidade A", "Estado A", "Complemento A");
        Endereco endereco = new Endereco(enderecoCadastro);
        

        // Act
        DadosEnderecoDto dadosEnderecoDto = new DadosEnderecoDto(endereco);

        // Assert
        Assertions.assertEquals("Rua A", dadosEnderecoDto.rua());
        Assertions.assertEquals("Cidade A", dadosEnderecoDto.cidade());
        Assertions.assertEquals("Estado A", dadosEnderecoDto.estado());
        Assertions.assertEquals("Complemento A", dadosEnderecoDto.complemento());
    }

    @Test
    void testConstructorWithValues() {
        // Arrange
        String rua = "Rua B";
        String cidade = "Cidade B";
        String estado = "Estado B";
        String complemento = "Complemento B";

        // Act
        DadosEnderecoDto dadosEnderecoDto = new DadosEnderecoDto(rua, cidade, estado, complemento);

        // Assert
        Assertions.assertEquals(rua, dadosEnderecoDto.rua());
        Assertions.assertEquals(cidade, dadosEnderecoDto.cidade());
        Assertions.assertEquals(estado, dadosEnderecoDto.estado());
        Assertions.assertEquals(complemento, dadosEnderecoDto.complemento());
    }
}