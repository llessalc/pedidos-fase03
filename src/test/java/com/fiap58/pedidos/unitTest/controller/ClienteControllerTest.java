package com.fiap58.pedidos.unitTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fiap58.pedidos.controller.ClienteController;
import com.fiap58.pedidos.core.services.ClienteService;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IClienteService service;

    @BeforeEach
    public void setup() {
        // Mock the service

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarClienteSuccessTest() throws Exception {
        // Mock the service
        IClienteService service = Mockito.mock(ClienteService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();

        // Create the input and output data
        List<EnderecoCadastro> enderecos = new ArrayList<>();
        List<TelefoneCadastro> telefones = new ArrayList<>();
        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro("12345678900", "Nome do Cliente",
                enderecos, telefones);
        DadosClienteDto dadosClienteDto = new DadosClienteDto(1L, "0000", "Nome do Cliente");

        // Set up the service to return the output data when called with the input data
        when(service.cadastrarCliente(any(DadosClienteCadastro.class))).thenReturn(dadosClienteDto);

        // Convert the input data to JSON
        String json = objectMapper.writeValueAsString(dadosClienteCadastro);

        // Make a POST request to the controller and check the response
        mockMvc.perform(MockMvcRequestBuilders.post("/cliente/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(
                        MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testCadastrarClienteFailedTest() throws Exception {
        // Mock the service
        IClienteService service = Mockito.mock(ClienteService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();

        // Create the input and output data
        List<EnderecoCadastro> enderecos = new ArrayList<>();
        List<TelefoneCadastro> telefones = new ArrayList<>();
        DadosClienteCadastro dadosClienteCadastro = new DadosClienteCadastro("12345678900", "Nome do Cliente",
                enderecos, telefones);

        // Set up the service to return the output data when called with the input data
        when(service.cadastrarCliente(any(DadosClienteCadastro.class))).thenReturn(null);

        // Convert the input data to JSON
        String json = objectMapper.writeValueAsString(dadosClienteCadastro);

        // Make a POST request to the controller and check the response
        mockMvc.perform(MockMvcRequestBuilders.post("/cliente/inserir")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(
                        MockMvcResultMatchers.status().isFound());
    }

    @Test
    void testListClienteSuccessTest() throws Exception {
        // Mock the service
        IClienteService service = Mockito.mock(IClienteService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();
        DadosClienteDto dadosClienteCadastro = new DadosClienteDto(1l, "12345678900", "Nome do Cliente");
        DadosClienteDto dadosClienteCadastro1 = new DadosClienteDto(2l, "123456789000", "Nome do Cliente 1");
        // Create the output data
        List<DadosClienteDto> clientes = Arrays.asList(
                dadosClienteCadastro,
                dadosClienteCadastro1);

        // Set up the service to return the output data
        Mockito.when(service.listarClientes()).thenReturn(clientes);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(clientes)));
    }

    @Test
    void testListIdClienteFailedTest() throws Exception {

        // Mock the service
        IClienteService service = Mockito.mock(IClienteService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();
        // Create the output data
        List<DadosClienteDto> clientes = Arrays.asList();

        // Set up the service to return the output data
        Mockito.when(service.listarClientes()).thenReturn(clientes);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testBuscarClientesSuccessTest() throws Exception {

        IClienteService service = Mockito.mock(IClienteService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();

        // Create the output data
        DadosClienteDto dadosClienteDto = new DadosClienteDto(1l, "0000", "fulano");

        // Set up the service to return the output data
        Long id = 1L; // Replace with the ID you want to test
        Mockito.when(service.retornaClienteId(id)).thenReturn(dadosClienteDto);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testBuscarClientesFailedTest() throws Exception {
        IClienteService service = Mockito.mock(IClienteService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();

        // Set up the service to return the output data
        Long id = 1L; // Replace with the ID you want to test
        Mockito.when(service.retornaClienteId(id)).thenReturn(null);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testbuscarClientePorCpfSucessTest() throws Exception {
        IClienteService service = Mockito.mock(IClienteService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();

        DadosClienteDto dadosClienteCadastro = new DadosClienteDto(1l, "12345678900", "Nome do Cliente");
        // Set up the service to return the output data
        String cpf = "12345678900"; // Replace with the ID you want to test
        Mockito.when(service.retornaClienteCpf(cpf)).thenReturn(dadosClienteCadastro);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/cpf/" + cpf)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testbuscarClientePorCpfFailedTest() throws Exception {
        IClienteService service = Mockito.mock(IClienteService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ClienteController(service)).build();

        String cpf = "12345678900"; 
        Mockito.when(service.retornaClienteCpf(cpf)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/cpf/" + cpf)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
