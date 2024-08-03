package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosEnderecoDto;

public interface IEnderecoService {

    DadosEnderecoDto cadstrarEndereco(EnderecoCadastro enderecoCadastro, Cliente cliente);

}