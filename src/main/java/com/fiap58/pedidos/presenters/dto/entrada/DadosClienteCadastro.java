package com.fiap58.pedidos.presenters.dto.entrada;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.core.domain.entity.Telefone;

import java.util.List;

public record DadosClienteCadastro(String cpf,
                                   String nome,
                                   List<EnderecoCadastro> enderecos,
                                   List<TelefoneCadastro> telefones) {
}
