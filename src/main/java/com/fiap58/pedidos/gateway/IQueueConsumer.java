package com.fiap58.pedidos.gateway;

public interface IQueueConsumer {

    void listen(String message) throws Exception;

    void cancelamento(String message) throws Exception;
}
