package com.fiap58.pedidos.core.domain.entity;

public enum StatusPedido {
    RECEBIDO(1,"Recebido"),
    EM_PREPARACAO(2, "Em Preparação"),
    PRONTO(3, "Pronto"),
    FINALIZADO(4, "Finalizado");

    private final int valor;
    private final String status;

    StatusPedido(int valor, String status){
        this.valor = valor;
        this.status = status;
    }

    public int getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }
}
