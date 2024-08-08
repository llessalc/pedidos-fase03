package com.fiap58.pedidos.unitTest.gateway.impl;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;

class ImplConsumerApiProducaoTest {


    @Mock
    private Environment environment;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

//    @Test
//    void acionaCriarPedidoProducao() {
//        Long id = 1L;
//        String url = "http://localhost:8080/pedidoProducao/adicionaPedido";
//        when(environment.getProperty(anyString())).thenReturn("localhost");
//
//        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        when(restTemplate.postForEntity(url, null, String.class)).thenReturn(responseEntity);
//
//        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
//    }
}