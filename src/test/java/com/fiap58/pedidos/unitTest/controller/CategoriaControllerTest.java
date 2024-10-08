package com.fiap58.pedidos.unitTest.controller;

import com.fiap58.pedidos.gateway.impl.QueueConsumer;
import com.fiap58.pedidos.gateway.impl.QueuePublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap58.pedidos.controller.CategoriaController;
import com.fiap58.pedidos.core.services.CategoriaService;
import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

        @Mock
        private ICategoriaService service;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @InjectMocks
        private CategoriaController controller;

        @MockBean
        private QueuePublisher queuePublisher;

        @MockBean
        private QueueConsumer queueConsumer;



        @BeforeEach
        public void setup() {
                mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        }

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

                CategoriaDtoEntrada categoriaDto = new CategoriaDtoEntrada("Categoria Teste");
                DadosCategoriaDto savedCategoriaDto = new DadosCategoriaDto(1L, "CategoriaTeste");
                // preencha o objeto dadosCate
                Mockito.when(service.cadastrarCategoria(categoriaDto)).thenReturn(savedCategoriaDto);

                mockMvc.perform(
                                MockMvcRequestBuilders.post("/categoria")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(new ObjectMapper().writeValueAsString(categoriaDto)))
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }

        @Test
        void testCadastraCategoriaNullFailed() throws Exception {
                ICategoriaService service = Mockito.mock(CategoriaService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();

                CategoriaDtoEntrada categoriaDto = new CategoriaDtoEntrada("Categoria Teste");
                DadosCategoriaDto savedCategoriaDto = new DadosCategoriaDto(1L, "CategoriaTeste");
                // preencha o objeto dadosCate
                Mockito.when(service.cadastrarCategoria(categoriaDto)).thenReturn(null);

                mockMvc.perform(
                        MockMvcRequestBuilders.post("/categoria")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(categoriaDto)))
                        .andExpect(MockMvcResultMatchers.status().isNotFound());

        }

        @Test
        void testCadastraCategoriaFailureTest() throws Exception {
                ICategoriaService service = Mockito.mock(CategoriaService.class);
                mockMvc = MockMvcBuilders.standaloneSetup(new CategoriaController(service)).build();

                DadosCategoriaDto dadosCategoriaDto = new DadosCategoriaDto(1L, "Categoria Teste");
                // preencha o objeto dadosCate
                given(service.cadastrarCategoria(any(CategoriaDtoEntrada.class))).willThrow(new RuntimeException());

                String json = objectMapper.writeValueAsString(dadosCategoriaDto);

                mockMvc.perform(MockMvcRequestBuilders.post("/categoria")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
        }

}
