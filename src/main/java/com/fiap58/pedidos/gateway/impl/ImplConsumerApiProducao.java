package com.fiap58.pedidos.gateway.impl;

import com.fiap58.pedidos.gateway.ConsumerApiPagamentos;
import com.fiap58.pedidos.gateway.ConsumerApiProducao;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoSaida;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImplConsumerApiProducao implements ConsumerApiProducao {

    @Autowired
    private Environment environment;


    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void acionaCriarPedidoProducao(DadosPedidoSaida pedidoSaida) {
        String producao = environment.getProperty("producao.service");
        String url_padrao = String.format("http://%s:8080", producao);
        StringBuilder urlBuilder = new StringBuilder();
        String url = urlBuilder.append(url_padrao).append("/pedidoProducao/adicionaPedido").toString();
        restTemplate.postForEntity(url, pedidoSaida, String.class);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
