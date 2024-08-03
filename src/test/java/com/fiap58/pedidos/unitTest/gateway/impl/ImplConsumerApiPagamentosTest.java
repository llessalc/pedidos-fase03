package com.fiap58.pedidos.unitTest.gateway.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fiap58.pedidos.gateway.impl.ImplConsumerApiPagamentos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ImplConsumerApiPagamentosTest {

    private ImplConsumerApiPagamentos consumerApiPagamentos;
    @Mock
    private Environment environment;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testAcionaCriarPagamento() {
//        consumerApiPagamentos = new ImplConsumerApiPagamentos(environment, restTemplate);
//        consumerApiPagamentos.setRestTemplate(restTemplate);
//        Long id = 1L;
//        String url = "http://localhost:8081/gerenciamento-pagamento/criar-pagamento/pedido/1";
//        when(environment.getProperty(anyString())).thenReturn("localhost");
//
//        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
//        when(restTemplate.postForEntity(url, null, String.class)).thenReturn(responseEntity);
//
//        consumerApiPagamentos.acionaCriarPagamento(id);
//        verify(restTemplate, times(1)).postForEntity(anyString(), any(), any());
    }
}