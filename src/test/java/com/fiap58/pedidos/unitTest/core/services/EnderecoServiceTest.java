package com.fiap58.pedidos.unitTest.core.services;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.core.services.EnderecoService;
import com.fiap58.pedidos.gateway.EnderecoRepository;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosEnderecoDto;

public class EnderecoServiceTest {
    @Test
    void testCadstrarEndereco() {
        // Arrange
        EnderecoRepository repository = Mockito.mock(EnderecoRepository.class);
        EnderecoService service = new EnderecoService(repository);

        EnderecoCadastro enderecoCadastro = new EnderecoCadastro("lorem ipsum", "123", "São Paulo", "SP", "apto 123");
        DadosClienteCadastro clienteCadastro = new DadosClienteCadastro("12345678901", "Cliente Teste", null, null);

        Cliente cliente = new Cliente(clienteCadastro);
        Endereco endereco = new Endereco(enderecoCadastro);

        Mockito.when(repository.save(Mockito.any(Endereco.class))).thenReturn(endereco);

        // Act
        service.cadstrarEndereco(enderecoCadastro, cliente);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Endereco.class));
    }
}
