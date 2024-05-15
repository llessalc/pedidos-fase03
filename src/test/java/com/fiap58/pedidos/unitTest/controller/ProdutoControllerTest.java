package com.fiap58.pedidos.unitTest.controller;

import java.math.BigDecimal;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap58.pedidos.controller.ClienteController;
import com.fiap58.pedidos.controller.ProdutoController;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.services.ProdutoService;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IProdutoService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IProdutoService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAtualizarProduto() throws Exception {
        // Implement the test here
    }

    @Test
    void testBuscarProdutoSucess() throws Exception {
        // Mock the service
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        BigDecimal bd1 = new BigDecimal("10.0");

        DadosProdutoDto dadosProdutoDto = new DadosProdutoDto("Fulano de tal", "Categoria Teste", "Categoria", bd1);

        // Set up the service to return the output data
        given(service.retornaProduto(1L)).willReturn(dadosProdutoDto);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/produto/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testBuscarProdutoFailed() throws Exception {
        // Mock the service
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();

        // Set up the service to return the output data
        given(service.retornaProduto(1L)).willThrow(new RuntimeException());

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/produto/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testListarProdutosSucess() throws Exception {
        // Mock the service
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        BigDecimal bd1 = new BigDecimal("10.0");

        List<DadosProdutoDto> lDadosProdutoDtos = new ArrayList<>();

        // Set up the service to return the output data
        given(service.retornaListaProdutos()).willReturn(lDadosProdutoDtos);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/produto/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testListarProdutosFailed() throws Exception {
        // Mock the service
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();

        // Set up the service to return the output data
        given(service.retornaListaProdutos()).willThrow(new RuntimeException());

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/produto/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testListarProdutosPorCategoriaSucess() throws Exception {
        // Mock the service
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();

        List<DadosProdutoDto> lDadosProdutoDtos = new ArrayList<>();

        // Set up the service to return the output data
        given(service.retornaListaProdutosCategoria("teste10")).willReturn(lDadosProdutoDtos);

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/produto/buscaPorCat/teste10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testListarProdutosPorCategoriaFailed() throws Exception {
        // Mock the service
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();

        // Set up the service to return the output data
        given(service.retornaListaProdutosCategoria("teste10")).willThrow(new RuntimeException());

        // Perform the GET request and check the response
        mockMvc.perform(MockMvcRequestBuilders.get("/produto/buscaPorCat/teste10")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
