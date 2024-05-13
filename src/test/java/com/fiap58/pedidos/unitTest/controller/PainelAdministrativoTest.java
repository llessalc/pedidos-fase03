package com.fiap58.pedidos.unitTest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fiap58.pedidos.controller.PainelAdministrativo;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PainelAdministrativo.class)
public class PainelAdministrativoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPedidoService service;

    @Test
    public void testAtualizarStatusSucess() throws Exception {
        // Arrange
        Long id = 1L;

        DadosPedidosDto dadosPedidosDto = new DadosPedidosDto();
        // configure o objeto dadosPedidosDto conforme necessário
        when(service.atualizarPedido(any(Long.class), any(Boolean.class))).thenReturn(dadosPedidosDto);

        // Act & Assert
        mockMvc.perform(patch("/adm/atualizar/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public void testAtualizarStatusFailed() throws Exception {
        Long id = 1L;

        when(service.atualizarPedido(any(Long.class), any(Boolean.class))).thenThrow(new RuntimeException());
        mockMvc.perform(patch("/adm/atualizar/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAtualizarTempoEspera() throws Exception {
        // Arrange
        Long id = 1L;
        long tempoEspera = 30L;
        DadosPedidosPainelDto dadosPedidosPainelDto = new DadosPedidosPainelDto();
        Pedido pedido = new Pedido();
        dadosPedidosPainelDto.setTempoEspera(tempoEspera);

        when(service.retornaPedido(any(Long.class))).thenReturn(pedido);
        when(service.defineTempoEspera(any(Pedido.class), any(Long.class))).thenReturn(dadosPedidosPainelDto);

        // Act & Assert
        mockMvc.perform(post("/adm/define-tempo-espera/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(tempoEspera)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAtualizarTempoEsperaFailed() throws Exception {
        // Arrange
        Long id = 1L;
        long tempoEspera = -9L;

        // Act & Assert
        mockMvc.perform(post("/adm/define-tempo-espera/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(tempoEspera)))
                .andExpect(status().isBadRequest());
    }
}