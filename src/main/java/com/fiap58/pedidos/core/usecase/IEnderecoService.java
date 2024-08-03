package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosEnderecoDto;

import java.util.List;

public interface IEnderecoService {

    DadosEnderecoDto cadstrarEndereco(EnderecoCadastro enderecoCadastro, Cliente cliente);

    void excluirEndereco(Long idCliente);
    List<Endereco> listarEnderecoCliente(Long idCliente);

}