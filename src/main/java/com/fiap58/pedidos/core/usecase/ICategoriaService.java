package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;

public interface ICategoriaService {

    Categoria buscarCategoria(Long id);

    DadosCategoriaDto retornarCategoria(Long id);

    DadosCategoriaDto cadastrarCategoria(CategoriaDtoEntrada dto);

    void deletarCategoria(Long id);

}