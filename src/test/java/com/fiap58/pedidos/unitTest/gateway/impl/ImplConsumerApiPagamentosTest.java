package com.fiap58.pedidos.unitTest.gateway.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fiap58.pedidos.gateway.impl.ImplConsumerApiPagamentos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImplConsumerApiPagamentosTest {

    private ImplConsumerApiPagamentos consumerApiPagamentos;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        consumerApiPagamentos = new ImplConsumerApiPagamentos();
        consumerApiPagamentos.setRestTemplate(restTemplate);
    }

    @Test
    void testAcionaCriarPagamento() {
        Long id = 1L;
        String url = "http://null:8081/gerenciamento-pagamento/criar-pagamento/pedido/1";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        when(restTemplate.postForEntity(url, null, String.class)).thenReturn(responseEntity);

        consumerApiPagamentos.acionaCriarPagamento(id);

        verify(restTemplate).postForEntity(url, null, String.class);
    }
}