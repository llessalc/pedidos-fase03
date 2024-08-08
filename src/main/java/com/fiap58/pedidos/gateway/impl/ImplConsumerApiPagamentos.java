package com.fiap58.pedidos.gateway.impl;

import com.fiap58.pedidos.gateway.ConsumerApiPagamentos;
import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImplConsumerApiPagamentos implements ConsumerApiPagamentos {

    @Autowired
    private Environment environment;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public PagamentoDto acionaListarPagamentoId(Long idPagamento) {

        String pagamentos = environment.getProperty("pagamento.service");
        String url_padrao = String.format("http://%s:8081/gerenciamento-pagamento", pagamentos);
        StringBuilder urlBuilder = new StringBuilder();
        String url = urlBuilder.append(url_padrao).append("/pagamento?id=").append(idPagamento).toString();
        System.out.println(url);

        ResponseEntity<PagamentoDto> response = restTemplate.getForEntity(url, PagamentoDto.class);
        return response.getBody();
    }

    @Override
    public void acionaExcluirPagamento(List<PagamentoDto> pagamentosDto) {
        String pagamentos = environment.getProperty("pagamento.service");
        String url_padrao = String.format("http://%s:8081/gerenciamento-pagamento", pagamentos);
        StringBuilder urlBuilder = new StringBuilder();
        String url = urlBuilder.append(url_padrao).append("/cancelar-pagamento-automatico").toString();
        System.out.println(url);

        restTemplate.postForEntity(url, pagamentosDto, String.class);
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
