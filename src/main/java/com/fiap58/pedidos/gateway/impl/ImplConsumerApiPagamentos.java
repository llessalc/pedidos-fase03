package com.fiap58.pedidos.gateway.impl;

import com.fiap58.pedidos.gateway.ConsumerApiPagamentos;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImplConsumerApiPagamentos implements ConsumerApiPagamentos {

    @Autowired
    private Environment environment;


    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void acionaCriarPagamento(Long id) {
        String pagamentos = environment.getProperty("pagamento.service");
        String url_padrao = String.format("http://%s:8081/gerenciamento-pagamento", pagamentos);
        StringBuilder urlBuilder = new StringBuilder();
        String url = urlBuilder.append(url_padrao).append("/criar-pagamento/pedido/").append(id).toString();

        restTemplate.postForEntity(url, null, String.class);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
