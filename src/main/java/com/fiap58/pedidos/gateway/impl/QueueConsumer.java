package com.fiap58.pedidos.gateway.impl;

import com.fiap58.pedidos.adapter.PagamentoMsgToPagamentoDto;
import com.fiap58.pedidos.core.services.PedidoService;
import com.fiap58.pedidos.gateway.IQueueConsumer;
import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class QueueConsumer implements IQueueConsumer {

    @Autowired
    private PedidoService service;

    @Override
    @SqsListener(value="${events.queue-confirmacao-pagamento}", pollTimeoutSeconds="20")
    public void listen(String message) throws Exception {
        System.out.println("Mensagem recebida da queue: "+message);
        PagamentoDto pagamentoDto = PagamentoMsgToPagamentoDto.pagamentoToPagamentoDto(message);
        Long idPedido = pagamentoDto.idPedido();
        service.atualizarPedido(idPedido, true);
    }

    @Override
    @SqsListener(value="${events.queue-pagamento-cancelado}", pollTimeoutSeconds="20")
    public void cancelamento(String message) throws Exception {
        System.out.println("Mensagem recebida da queue: "+message);
        PagamentoDto pagamentoDto = PagamentoMsgToPagamentoDto.pagamentoToPagamentoDto(message);
        Long idPedido = pagamentoDto.idPedido();
        service.excluiPedido(idPedido);
    }

}
