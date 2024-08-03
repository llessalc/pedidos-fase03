package com.fiap58.pedidos.presenters.dto.saida;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosClienteExclusaoDto{
    private String cpf;
    private String nome;
    private List<EnderecoCadastro> enderecos;
    private List<TelefoneCadastro> telefones;
    private List<PagamentoDto> pagamentoDtos;

    public DadosClienteExclusaoDto(Cliente cliente, List<EnderecoCadastro> enderecos,
                                   List<TelefoneCadastro> telefones) {
        this(cliente.getCpf(), cliente.getNome(), enderecos, telefones, null);
    }

}
