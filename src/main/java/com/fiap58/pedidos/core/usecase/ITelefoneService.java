package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosTelefoneDto;

public interface ITelefoneService {

    DadosTelefoneDto cadastrarTelefone(TelefoneCadastro telefoneCadastro, Cliente cliente);

}