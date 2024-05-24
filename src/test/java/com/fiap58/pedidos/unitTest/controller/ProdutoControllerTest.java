package com.fiap58.pedidos.unitTest.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fiap58.pedidos.core.domain.entity.Categoria;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IProdutoService service;

    private final String ENDPOINT_API_PEDIDO_INSERIR = "http://localhost:8080/produto";
    private final String ENDPOINT_API_PEDIDO_ATUALIZAR = "http://localhost:8080/produto/{idProduto}";
    private final String ENDPOINT_API_PEDIDO_DELETAR = "http://localhost:8080/produto/{idProduto}";

    private Produto produto;
    private Categoria categoria;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria("lanche");
        produto = new Produto("Produto1", "Descricao 1", new BigDecimal("10.00"));
        produto.setIdProduto(100L);
        produto.setCategoria(categoria);
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

    @Test
    void testInlcuirProduto() throws Exception {
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        DadosProdutoDtoEntrada dto = new DadosProdutoDtoEntrada(1L, "Teste", "Teste", new BigDecimal(10));
        DadosProdutoDto dadosProdutoDto = new DadosProdutoDto(produto);

        when(service.inserirProduto(any(DadosProdutoDtoEntrada.class))).thenReturn(dadosProdutoDto);

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_API_PEDIDO_INSERIR)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service, times(1)).inserirProduto(any(DadosProdutoDtoEntrada.class));
    }

    @Test
    void testInlcuirProdutoFalha() throws Exception {
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        DadosProdutoDtoEntrada dto = new DadosProdutoDtoEntrada(1L, "Teste", "Teste", new BigDecimal(10));

        when(service.inserirProduto(any(DadosProdutoDtoEntrada.class))).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_API_PEDIDO_INSERIR)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(service, times(1)).inserirProduto(any(DadosProdutoDtoEntrada.class));
    }

    @Test
    void testAtualizarProduto() throws Exception {
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        String idProduto = "1";
        when(service.updateProduto(anyLong(), any(DadosProdutoDto.class))).thenReturn(produto);
        DadosProdutoDto dadosProdutoDto = new DadosProdutoDto(produto);

        mockMvc.perform(MockMvcRequestBuilders.patch(ENDPOINT_API_PEDIDO_ATUALIZAR, idProduto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dadosProdutoDto))).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        verify(service, times(1)).updateProduto(anyLong(), any(DadosProdutoDto.class));
    }

    @Test
    void testAtualizarProdutoComErro() throws Exception {
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        String idProduto = "1";
        when(service.updateProduto(anyLong(), any(DadosProdutoDto.class))).thenThrow(new RuntimeException());
        DadosProdutoDto dadosProdutoDto = new DadosProdutoDto(produto);

        mockMvc.perform(MockMvcRequestBuilders.patch(ENDPOINT_API_PEDIDO_ATUALIZAR, idProduto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dadosProdutoDto)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
        verify(service, times(1)).updateProduto(anyLong(), any(DadosProdutoDto.class));
    }

    @Test
    void testDeletarProduto() throws Exception {
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        String idProduto = "1";

        doNothing().when(service).deleteProduto(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_API_PEDIDO_DELETAR, idProduto)
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(service, times(1)).deleteProduto(anyLong());
    }

    @Test
    void testDeletarProdutoFailed() throws Exception {
        IProdutoService service = Mockito.mock(ProdutoService.class);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ProdutoController(service)).build();
        String idProduto = "1";
        doThrow(RuntimeException.class).when(service).deleteProduto(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT_API_PEDIDO_DELETAR, idProduto)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(service, times(1)).deleteProduto(anyLong());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
