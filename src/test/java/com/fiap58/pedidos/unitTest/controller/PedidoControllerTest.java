package com.fiap58.pedidos.unitTest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap58.pedidos.controller.ClienteController;
import com.fiap58.pedidos.controller.PedidoController;
import com.fiap58.pedidos.core.services.ClienteService;
import com.fiap58.pedidos.core.services.PedidoService;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.ProdutoCarrinho;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosValorDto;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given; // Import the necessary class from Mockito library
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Mock
        private IPedidoService pedidoService;

        @Autowired
        private ObjectMapper objectMapper;

        @BeforeEach
        public void setup() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        public void listarPedidosTest_VariosItensNaLista() throws Exception {

                IPedidoService pedidoService = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();
                // Mock the service response
                List<DadosPedidosPainelDto> pedidos = Arrays.asList(
                                new DadosPedidosPainelDto(),
                                new DadosPedidosPainelDto());
                Mockito.when(pedidoService.listarPedidos()).thenReturn(pedidos);

                // Perform the request and assert the response
                mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(pedidos.size()));
        }

        @Test
        public void listarPedidosTest_ComUmPedido() throws Exception {
                // Create a pedido
                DadosPedidosPainelDto pedido = new DadosPedidosPainelDto();
                // Set the properties of the pedido...

                IPedidoService pedidoService = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();

                // Configure the mock to return a list with one pedido
                Mockito.when(pedidoService.listarPedidos()).thenReturn(Arrays.asList(pedido));

                // Perform the request and assert the response
                mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));
        }

        @Test
        public void listarPedidosTest_ComListaEmBranco() throws Exception {

                IPedidoService pedidoService = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();

                Mockito.when(pedidoService.listarPedidos()).thenReturn(Collections.emptyList());

                // Perform the request and assert the response
                mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                                .andExpect(MockMvcResultMatchers.status().isNoContent());
        }

        @Test
        public void listarPedidosTest_ComException() throws Exception {
                IPedidoService pedidoService = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();

                // Mock the service to throw an exception
                Mockito.when(pedidoService.listarPedidos()).thenThrow(new RuntimeException("Test exception"));

                // Perform the request and assert the response
                mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
        }

        @Test
        public void testListarPedido() throws Exception {
                long id = 1L;
                DadosPedidosValorDto dto = new DadosPedidosValorDto();
                dto.setId(id);

                given(pedidoService.buscaPedido(id)).willReturn(dto);

                mockMvc.perform(MockMvcRequestBuilders.get("/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
        }

        @Test
        void testListarPedidoSuccessTest() throws Exception {
                // Mock the service
                IPedidoService service = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();

                DadosPedidosValorDto dadosPedidosDto = new DadosPedidosValorDto();
                // Set up the service to return the output data when called with the input data
                when(service.buscaPedido(1L)).thenReturn(dadosPedidosDto);

                // Make a POST request to the controller and check the response
                mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        void testListarPedidoFailedTest() throws Exception {
                // Mock the service
                IPedidoService service = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();

                // Set up the service to return the output data when called with the input data
                when(service.buscaPedido(1L)).thenThrow(new RuntimeException());

                // Make a POST request to the controller and check the response
                mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        void testRecebePagamentoSucessTest() throws Exception {
                // Mock the service
                IPedidoService service = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();

                DadosPedidosDto dadosPedidoDto = new DadosPedidosDto();
                // Set up the service to return the output data when called with the input data
                when(service.recebePagamento(1L)).thenReturn(dadosPedidoDto);

                // Make a POST request to the controller and check the response
                mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/confirmacao-pagamento/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        void testRecebePagamentoFailedTest() throws Exception {
                // Mock the service
                IPedidoService service = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();

                // Set up the service to return the output data when called with the input data
                when(service.recebePagamento(1L)).thenThrow(new RuntimeException());

                // Make a POST request to the controller and check the response
                mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/confirmacao-pagamento/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(
                                                MockMvcResultMatchers.status().isBadRequest());
        }

        @Test
        void testIncluirPedidoSucessTest() throws Exception {
                // Mock the service
                IPedidoService service = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();

                List<ProdutoCarrinho> carrinho = new ArrayList<>();
                DadosPedidosDto dadosPedidosDto = new DadosPedidosDto();
                DadosPedidosEntrada dadosPedidosEntrada = new DadosPedidosEntrada(carrinho);
                // Set up the service to return the output data when called with the input data
                when(service.inserirPedidoFila(any(DadosPedidosEntrada.class))).thenReturn(dadosPedidosDto);

                String json = objectMapper.writeValueAsString(dadosPedidosEntrada);

                // Make a POST request to the controller and check the response
                mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(
                                                MockMvcResultMatchers.status().isOk());
        }

        @Test
        void testIncluirPedidoFailedTest() throws Exception {
                // Mock the service
                IPedidoService service = Mockito.mock(PedidoService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(service)).build();

                List<ProdutoCarrinho> carrinho = new ArrayList<>();
                DadosPedidosEntrada dadosPedidosEntrada = new DadosPedidosEntrada(carrinho);
                // Set up the service to return the output data when called with the input data
                when(service.inserirPedidoFila(any(DadosPedidosEntrada.class))).thenThrow(new RuntimeException());

                String json = objectMapper.writeValueAsString(dadosPedidosEntrada);

                // Make a POST request to the controller and check the response
                mockMvc.perform(MockMvcRequestBuilders.post("/pedidos/checkout")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(
                                                MockMvcResultMatchers.status().isBadRequest());
        }

}
