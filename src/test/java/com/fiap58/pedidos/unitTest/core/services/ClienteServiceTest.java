package com.fiap58.pedidos.unitTest.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.services.ClienteService;
import com.fiap58.pedidos.core.usecase.IEnderecoService;
import com.fiap58.pedidos.core.usecase.ITelefoneService;
import com.fiap58.pedidos.gateway.ClienteRepository;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;

public class ClienteServiceTest {
    @Test
    void testBuscarClientePorCpf() {
        ClienteRepository repository = Mockito.mock(ClienteRepository.class);
        IEnderecoService enderecoService = Mockito.mock(IEnderecoService.class);
        ITelefoneService telefoneService = Mockito.mock(ITelefoneService.class);
        ClienteService service = new ClienteService(repository, enderecoService, telefoneService);

        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        String cpf = "123.456.789-00";

        Mockito.when(repository.findByCpf(cpf)).thenReturn(cliente);

        // Act
        Cliente result = service.buscarClientePorCpf(cpf);

        // Assert
        assertEquals(cliente, result);
    }

    @Test
    void testBuscarClientePorId() {
        ClienteRepository repository = Mockito.mock(ClienteRepository.class);
        IEnderecoService enderecoService = Mockito.mock(IEnderecoService.class);
        ITelefoneService telefoneService = Mockito.mock(ITelefoneService.class);
        ClienteService service = new ClienteService(repository, enderecoService, telefoneService);
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        Long id = 1L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        Cliente result = service.buscarClientePorId(id);

        // Assert
        assertEquals(cliente, result);
    }

    @Test
    void testCadastrarCliente() {
        // Arrange
        ClienteRepository repository = Mockito.mock(ClienteRepository.class);
        IEnderecoService enderecoService = Mockito.mock(IEnderecoService.class);
        ITelefoneService telefoneService = Mockito.mock(ITelefoneService.class);
        ClienteService service = new ClienteService(repository, enderecoService, telefoneService);
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");

        Mockito.when(repository.save(Mockito.any(Cliente.class))).thenReturn(cliente);

        // Act
        Cliente result = service.cadastrarCliente(cliente);

        // Assert
        assertEquals(cliente, result);
        Mockito.verify(repository, Mockito.times(1)).save(cliente);
    }

    @Test
    void testListarClientes() {
        // Arrange
        ClienteRepository repository = Mockito.mock(ClienteRepository.class);
        IEnderecoService enderecoService = Mockito.mock(IEnderecoService.class);
        ITelefoneService telefoneService = Mockito.mock(ITelefoneService.class);
        ClienteService service = new ClienteService(repository, enderecoService, telefoneService);
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Teste 1");
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Teste 2");
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        Mockito.when(repository.findAll()).thenReturn(clientes);

        // Act
        List<DadosClienteDto> result = service.listarClientes();

        // Assert
        assertNotNull(result);
    }

    @Test
    void testRetornaClienteCpf() {
        ClienteRepository repository = Mockito.mock(ClienteRepository.class);
        IEnderecoService enderecoService = Mockito.mock(IEnderecoService.class);
        ITelefoneService telefoneService = Mockito.mock(ITelefoneService.class);
        ClienteService service = new ClienteService(repository, enderecoService, telefoneService);
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        String cpf = "123.456.789-00";

        Mockito.when(repository.findByCpf(cpf)).thenReturn(cliente);

        // Act
        DadosClienteDto result = service.retornaClienteCpf(cpf);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testRetornaClienteId() {
        ClienteRepository repository = Mockito.mock(ClienteRepository.class);
        IEnderecoService enderecoService = Mockito.mock(IEnderecoService.class);
        ITelefoneService telefoneService = Mockito.mock(ITelefoneService.class);
        ClienteService service = new ClienteService(repository, enderecoService, telefoneService);
        Cliente cliente = new Cliente();
        cliente.setNome("Teste");
        Long id = 1L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        DadosClienteDto result = service.retornaClienteId(id);

        // Assert
        assertNotNull(result);
    }
}
