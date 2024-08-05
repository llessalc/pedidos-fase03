package com.fiap58.pedidos.unitTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.services.PedidoService;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.gateway.impl.QueueConsumer;
import com.fiap58.pedidos.gateway.impl.QueuePublisher;
import com.fiap58.pedidos.presenters.dto.entrada.*;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteExclusaoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fiap58.pedidos.controller.ClienteController;
import com.fiap58.pedidos.core.services.ClienteService;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Mock
    private IClienteService service;

    @Mock
    private IPedidoService pedidoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClienteController clienteController;

    @MockBean
    private QueuePublisher queuePublisher;

    @MockBean
    private QueueConsumer queueConsumer;

    @BeforeEach
    public void setup() {
        // Mock the service
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testCadastrarClienteSuccessTest() throws Exception {
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

        String cpf = "12345678900";
        Mockito.when(service.retornaClienteCpf(cpf)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/cpf/" + cpf)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testExcluiCliente() throws Exception {
        List<PagamentoDto> pagamentoDtos =  new ArrayList<>();
        pagamentoDtos.add(new PagamentoDto(1L,1L,"Teste","Teste",BigDecimal.ZERO,
                "Criado",null,null,null));
        BuscaClienteDto clienteDto = new BuscaClienteDto("123456789", null, true);

        when(pedidoService.retornaPedidosCliente(any(BuscaClienteDto.class))).thenReturn(pagamentoDtos);
        doNothing().when(pedidoService).excluirPagamentosCliente(pagamentoDtos);
        when(service.excluirCliente(any(BuscaClienteDto.class))).thenReturn(null);

        // Convert the input data to JSON
        String json = objectMapper.writeValueAsString(clienteDto);

        // Make a POST request to the controller and check the response
        mockMvc.perform(MockMvcRequestBuilders.post("/cliente/excluir")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testExcluiClienteDadosNotNull() throws Exception {
        List<PagamentoDto> pagamentoDtos =  new ArrayList<>();
        pagamentoDtos.add(new PagamentoDto(1L,1L,"Teste","Teste",BigDecimal.ZERO,
                "Criado",null,null,null));
        BuscaClienteDto clienteDto = new BuscaClienteDto("123456789", null, false);
        DadosClienteExclusaoDto dados = new DadosClienteExclusaoDto("1234", "Teste", null, null, null);

        when(pedidoService.retornaPedidosCliente(any(BuscaClienteDto.class))).thenReturn(pagamentoDtos);
        when(service.excluirCliente(any(BuscaClienteDto.class))).thenReturn(dados);

        // Convert the input data to JSON
        String json = objectMapper.writeValueAsString(clienteDto);

        // Make a POST request to the controller and check the response
        mockMvc.perform(MockMvcRequestBuilders.post("/cliente/excluir")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testExcluiClienteDadosNull() throws Exception {
        List<PagamentoDto> pagamentoDtos =  new ArrayList<>();
        pagamentoDtos.add(new PagamentoDto(1L,1L,"Teste","Teste",BigDecimal.ZERO,
                "Criado",null,null,null));
        when(pedidoService.retornaPedidosCliente(any(BuscaClienteDto.class))).thenReturn(pagamentoDtos);
        BuscaClienteDto clienteDto = new BuscaClienteDto("123456789", null, false);
        when(service.excluirCliente(any(BuscaClienteDto.class))).thenReturn(null);

        // Convert the input data to JSON
        String json = objectMapper.writeValueAsString(clienteDto);

        // Make a POST request to the controller and check the response
        mockMvc.perform(MockMvcRequestBuilders.post("/cliente/excluir")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(
                        MockMvcResultMatchers.status().isNotFound());
    }
}
