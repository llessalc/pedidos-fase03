package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.gateway.TelefoneRepository;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosTelefoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository repository;

    public DadosTelefoneDto cadastrarTelefone(TelefoneCadastro telefoneCadastro, Cliente cliente){
        Telefone telefone = new Telefone(telefoneCadastro);
        telefone.setCliente(cliente);
        Telefone telefoneSalvo = repository.save(telefone);
        return mapperTelefoneDto(telefoneSalvo);
    }

    private DadosTelefoneDto mapperTelefoneDto(Telefone telefone){
        return new DadosTelefoneDto(telefone);
    }
}
