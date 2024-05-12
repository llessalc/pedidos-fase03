package com.fiap58.pedidos.unitTest.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap58.pedidos.controller.CategoriaController;
import com.fiap58.pedidos.controller.PedidoController;
import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.services.CategoriaService;
import com.fiap58.pedidos.core.services.PedidoService;
import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)

public class CategoriaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoriaService service;

    @Test
    public void getCategoriaSuccessTest() throws Exception {

        ICategoriaService service = Mockito.mock(CategoriaService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        DadosCategoriaDto categoria = new DadosCategoriaDto(1L, "Categoria Teste");
        given(service.retornarCategoria(1L)).willReturn(categoria);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/categoria/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCategoriaFailureTest() throws Exception {
        ICategoriaService service = Mockito.mock(CategoriaService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        given(service.retornarCategoria(1L)).willThrow(new RuntimeException());
        mockMvc.perform(
                MockMvcRequestBuilders.get("/categoria/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testDeletarCategoria() throws Exception {
        ICategoriaService service = Mockito.mock(CategoriaService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        doNothing().when(service).deletarCategoria(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/categoria/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testDeletarCategoriaFailureTest() throws Exception {
        ICategoriaService service = Mockito.mock(CategoriaService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        doThrow(new RuntimeException()).when(service).deletarCategoria(anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/categoria/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    void testCadastraCategoria() throws Exception {
        ICategoriaService service = Mockito.mock(CategoriaService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        DadosCategoriaDto dadosCategoriaDto = new DadosCategoriaDto(1L, "Categoria Teste");
        // preencha o objeto dadosCate
        given(service.cadastrarCategoria(any(CategoriaDtoEntrada.class))).willReturn(dadosCategoriaDto);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dadosCategoriaDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testCadastraCategoriaFailureTest() throws Exception {
        ICategoriaService service = Mockito.mock(CategoriaService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();
        DadosCategoriaDto dadosCategoriaDto = new DadosCategoriaDto(1L, "Categoria Teste");
        // preencha o objeto dadosCate
        given(service.cadastrarCategoria(any(CategoriaDtoEntrada.class))).willThrow(new RuntimeException());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dadosCategoriaDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}
