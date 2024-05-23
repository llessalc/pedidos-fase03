package com.fiap58.pedidos.core.services;

import com.fiap58.pedidos.gateway.CategoriaRepository;
import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria buscarCategoria(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public DadosCategoriaDto retornarCategoria(Long id) {
        return mapperCategoria(buscarCategoria(id));
    }

    private DadosCategoriaDto mapperCategoria(Categoria categoria) {
        return new DadosCategoriaDto(categoria);
    }

    @Override
    public DadosCategoriaDto cadastrarCategoria(CategoriaDtoEntrada dto) {
        for (Categoria categoria : recuperaCategoriasVigentes(recuperaCategorias())) {
            if (categoria.getNomeCategoria().equals(dto.nomeCategoria()) &&
                    categoria.getDeletadoEm() == null)
                return null;
        }
        Categoria categoriaSalva = repository.save(new Categoria(dto.nomeCategoria()));
        return mapperCategoria(categoriaSalva);
    }

    private List<Categoria> recuperaCategorias() {
        return repository.findAll();
    }

    private List<Categoria> recuperaCategoriasVigentes(List<Categoria> categorias) {
        return categorias.stream()
                .filter(categoria -> categoria.getDeletadoEm() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarCategoria(Long id) {
        Categoria categoria = repository.getReferenceById(id);
        categoria.setAtualizadoEm(Instant.now());
        categoria.setDeletadoEm(Instant.now());
        repository.save(categoria);
    }
}
