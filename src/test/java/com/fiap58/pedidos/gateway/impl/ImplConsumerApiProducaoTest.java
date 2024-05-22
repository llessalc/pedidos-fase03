package com.fiap58.pedidos.gateway.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ImplConsumerApiProducaoTest {

    private ImplConsumerApiProducao consumerApiProducao;
    @Mock
    private Environment environment;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void acionaCriarPedidoProducao() {
        consumerApiProducao = new ImplConsumerApiProducao(environment, restTemplate);
        consumerApiProducao.setRestTemplate(restTemplate);
        Long id = 1L;
        String url = "http://localhost:8080/pedidoProducao/adicionaPedido";
        when(environment.getProperty(anyString())).thenReturn("localhost");

        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.postForEntity(url, null, String.class)).thenReturn(responseEntity);

        consumerApiProducao.acionaCriarPedidoProducao(null);
        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
    }
}