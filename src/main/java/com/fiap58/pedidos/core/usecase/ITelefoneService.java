package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosTelefoneDto;

import java.util.List;

public interface ITelefoneService {

    DadosTelefoneDto cadastrarTelefone(TelefoneCadastro telefoneCadastro, Cliente cliente);
    void excluirTelefoneCliente(Long idCliente);
    List<Telefone> listaTelefoneCliente(Long idCliente);

}