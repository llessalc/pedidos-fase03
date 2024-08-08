package com.fiap58.pedidos.core.usecase;

import java.util.List;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.presenters.dto.entrada.BuscaClienteDto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteExclusaoDto;

public interface IClienteService {

    Cliente cadastrarCliente(Cliente cliente);

    DadosClienteDto cadastrarCliente(DadosClienteCadastro dto);

    DadosClienteDto retornaClienteCpf(String cpf);

    DadosClienteDto retornaClienteId(Long id);

    Cliente buscarClientePorCpf(String cpf);

    Cliente buscarClientePorId(Long id);

    List<DadosClienteDto> listarClientes();

    DadosClienteExclusaoDto excluirCliente(BuscaClienteDto dto) throws Exception;

    Cliente buscarClientePorNome(String nome);
}