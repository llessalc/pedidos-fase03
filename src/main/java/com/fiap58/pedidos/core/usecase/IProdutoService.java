package com.fiap58.pedidos.core.usecase;

import java.util.List;

import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;

public interface IProdutoService {

    Produto buscarProduto(long id);

    DadosProdutoDto retornaProduto(long id);

    List<Produto> listarProdutos();

    List<DadosProdutoDto> retornaListaProdutos();

    List<DadosProdutoDto> retornaListaProdutosCategoria(String nomeCategoria);

    DadosProdutoDto inserirProduto(DadosProdutoDtoEntrada dto);

    void deleteProduto(Long id);

    Produto updateProduto(Long id, DadosProdutoDto dto);

}