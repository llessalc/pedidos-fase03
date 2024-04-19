package com.fiap58.pedidos.presenters.dto.entrada;

import java.util.List;

public record DadosClienteCadastro(String cpf,
        String nome,
        List<EnderecoCadastro> enderecos,
        List<TelefoneCadastro> telefones) {
}
