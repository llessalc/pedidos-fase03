package com.fiap58.pedidos.presenters.dto.entrada;

public record EnderecoCadastro(String rua,
                               String numero,
                               String cidade,
                               String estado,
                               String complemento) {
}
