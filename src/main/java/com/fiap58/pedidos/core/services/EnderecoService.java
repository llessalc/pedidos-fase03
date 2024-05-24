package com.fiap58.pedidos.core.services;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.core.usecase.IEnderecoService;
import com.fiap58.pedidos.gateway.EnderecoRepository;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosEnderecoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService implements IEnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    @Override
    public DadosEnderecoDto cadstrarEndereco(EnderecoCadastro enderecoCadastro, Cliente cliente) {
        Endereco endereco = new Endereco(enderecoCadastro);
        endereco.setCliente(cliente);
        Endereco enderecoSalvo = repository.save(endereco);
        return new DadosEnderecoDto(enderecoSalvo);
    }

}
