package com.fiap58.pedidos.unitTest.core.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.core.services.TelefoneService;
import com.fiap58.pedidos.gateway.TelefoneRepository;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosTelefoneDto;

public class TelefoneServiceTest {
    @Test
    void testCadastrarTelefone() {
        // Arrange
        TelefoneRepository repository = Mockito.mock(TelefoneRepository.class);
        TelefoneService service = new TelefoneService(repository);

        TelefoneCadastro telefoneCadastro = new TelefoneCadastro("11", "999999999", "CELULAR");
        DadosClienteCadastro clienteCadastro = new DadosClienteCadastro("12345678901", "Cliente Teste", null, null);
        Cliente cliente = new Cliente(clienteCadastro);

        Telefone telefone = new Telefone();
        telefone.setDdd(telefoneCadastro.ddd());
        telefone.setNumero(telefoneCadastro.numero());
        telefone.setTipo(telefoneCadastro.tipo());
        telefone.setCliente(cliente);

        Mockito.when(repository.save(Mockito.any(Telefone.class))).thenReturn(telefone);

        // Act
        DadosTelefoneDto result = service.cadastrarTelefone(telefoneCadastro, cliente);

        // Assert
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Telefone.class));
        assertEquals(telefone.getDdd(), result.ddd());
        assertEquals(telefone.getNumero(), result.numero());
    }
}
