package com.fiap58.pedidos.gateway.impl;

import com.fiap58.pedidos.gateway.ConsumerApiPagamentos;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImplConsumerApiPagamentos implements ConsumerApiPagamentos {

    String pagamento_service = System.getenv("pagamento_service");
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void acionaCriarPagamento(Long id) {

        String url_padrao = String.format("http://%s:8081/gerenciamento-pagamento", this.pagamento_service);
        StringBuilder urlBuilder = new StringBuilder();
        String url = urlBuilder.append(url_padrao).append("/criar-pagamento/pedido/").append(id).toString();

        restTemplate.postForEntity(url, null, String.class);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
