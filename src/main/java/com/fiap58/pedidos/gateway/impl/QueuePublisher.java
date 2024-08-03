package com.fiap58.pedidos.gateway.impl;

import com.fiap58.pedidos.adapter.InstantAdapter;
import com.fiap58.pedidos.gateway.IQueuePublisher;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoPagamento;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidoSaida;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.PedidoFinalizadoDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class QueuePublisher implements IQueuePublisher {

    @Autowired
    private SqsTemplate sqsTemplate;

    @Value("${events.queue-pedidos-criados}")
    private String queuePedidosCriados;

    @Value("${events.queue-producao}")
    private String queuePagamentosConfirmados;

    @Value("${events.queue-pedido-finalizado}")
    private String queuePedidoFinalizado;

    @Override
    public void publicarPedidoCriado(DadosPedidoPagamento dadosPedidosDto){
        Gson gson = new GsonBuilder().registerTypeAdapter(Instant.class, new InstantAdapter()).create();
        String dadosPedidoMsg = gson.toJson(dadosPedidosDto);

        System.out.printf("Enviando pedido à queue %s: %s", queuePedidosCriados, dadosPedidoMsg);

        sqsTemplate.send(to -> to.queue(queuePedidosCriados)
                .payload(dadosPedidoMsg)
        );
    }

    @Override
    public void publicarPedidoProducao(DadosPedidoSaida dadosPedidoProducao){
        Gson gson = new Gson();
        String dadosPedidoMsg = gson.toJson(dadosPedidoProducao);

        System.out.printf("Enviando pedido à queue %s: %s", queuePagamentosConfirmados, dadosPedidoMsg);

        sqsTemplate.send(to -> to.queue(queuePagamentosConfirmados)
                .payload(dadosPedidoMsg)
        );
    }

    @Override
    public void publicarFinalizacaoPedido(PedidoFinalizadoDto pedidoFinalizadoDto) {
        Gson gson = new Gson();
        String pedidoFinalizadoMsg = gson.toJson(pedidoFinalizadoDto);

        System.out.printf("Enviando pedido à queue %s: %s", queuePedidoFinalizado, pedidoFinalizadoMsg);

        sqsTemplate.send(to -> to.queue(queuePedidoFinalizado)
                .payload(pedidoFinalizadoMsg)
        );
    }

}
