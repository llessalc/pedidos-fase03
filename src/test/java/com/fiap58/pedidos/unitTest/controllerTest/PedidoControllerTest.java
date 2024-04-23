package com.fiap58.pedidos.unitTest.controllerTest;

import com.fiap58.pedidos.controller.PedidoController;
import com.fiap58.pedidos.core.usecase.PedidoService;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PedidoService pedidoService;


    @BeforeEach
    public void setup() {
        // Mock the service
 
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarPedidosTest_VariosItensNaLista() throws Exception {

        PedidoService pedidoService = Mockito.mock(PedidoService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();
        // Mock the service response
        List<DadosPedidosPainelDto> pedidos = Arrays.asList(
                new DadosPedidosPainelDto(),
                new DadosPedidosPainelDto()
        );
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

    PedidoService pedidoService = Mockito.mock(PedidoService.class);
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

        PedidoService pedidoService = Mockito.mock(PedidoService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();
        
        Mockito.when(pedidoService.listarPedidos()).thenReturn(Collections.emptyList());

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void listarPedidosTest_ComException() throws Exception {
        PedidoService pedidoService = Mockito.mock(PedidoService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PedidoController(pedidoService)).build();

        // Mock the service to throw an exception
        Mockito.when(pedidoService.listarPedidos()).thenThrow(new RuntimeException("Test exception"));

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos"))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    // // Add more test methods for other controller methods

}
