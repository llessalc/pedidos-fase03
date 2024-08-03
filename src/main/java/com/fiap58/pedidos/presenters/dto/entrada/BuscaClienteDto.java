package com.fiap58.pedidos.presenters.dto.entrada;

public record BuscaClienteDto (
        String cpf,
        String nome,
        boolean excluirCliente
){
}
