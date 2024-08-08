package com.fiap58.pedidos.core.services;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.core.usecase.ITelefoneService;
import com.fiap58.pedidos.gateway.TelefoneRepository;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosTelefoneDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TelefoneService implements ITelefoneService {

    private final TelefoneRepository repository;

    public TelefoneService(TelefoneRepository _repository) {
        this.repository = _repository;
    }

    @Override
    public DadosTelefoneDto cadastrarTelefone(TelefoneCadastro telefoneCadastro, Cliente cliente) {

        Telefone telefone = new Telefone(telefoneCadastro);
        telefone.setCliente(cliente);
        return mapperTelefoneDto(repository.save(telefone));
    }

    @Override
    public void excluirTelefoneCliente(Long idCliente) {
        List<Telefone> listaTelefoneCliente = repository.findByIdCliente(idCliente);
        for (Telefone telefone : listaTelefoneCliente) {
            telefone.setAtualizadoEm(Instant.now());
            telefone.setDeletadoEm(Instant.now());
            repository.save(telefone);
        }
    }

    @Override
    public List<Telefone> listaTelefoneCliente(Long idCliente) {
        return repository.findByIdCliente(idCliente);
    }


    private DadosTelefoneDto mapperTelefoneDto(Telefone telefone) {

        return new DadosTelefoneDto(telefone);
    }
}
