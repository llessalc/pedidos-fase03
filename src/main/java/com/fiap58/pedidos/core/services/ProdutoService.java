package com.fiap58.pedidos.core.services;

import com.fiap58.pedidos.gateway.ProdutoRepository;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.core.usecase.IProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fiap58.pedidos.core.specifications.ProdutoSpecification.temCategoria;

@Service
public class ProdutoService implements IProdutoService {

    private final ProdutoRepository repository;

    private final ICategoriaService categoriaService;

    public ProdutoService(ProdutoRepository repository, ICategoriaService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    @Override
    public Produto buscarProduto(long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public DadosProdutoDto retornaProduto(long id) {
        Produto produto = verificaVigenciaProduto(buscarProduto(id));
        if(produto != null){
            return mapperDadosProdutoDto(produto);
        }
        return null;
    }

    @Override
    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    @Override
    public List<DadosProdutoDto> retornaListaProdutos() {
        List<Produto> produtos = produtosVigentes(listarProdutos());
        if(produtos.size() != 0){
            return mapperListaProdutoDto(produtos);
        }
        return null;

    }

    @Override
    public List<DadosProdutoDto> retornaListaProdutosCategoria(String nomeCategoria) {
        List<Produto> produtos = produtosVigentes(buscarProdutoPorCategoria(nomeCategoria));
        if(produtos != null){
            return mapperListaProdutoDto(produtos);
        }
        return null;
    }

    private List<DadosProdutoDto> mapperListaProdutoDto(List<Produto> produtoList) {
        return produtoList.stream().filter(produto -> produto.getDeletadoEm() == null).map(this::mapperDadosProdutoDto)
                .collect(Collectors.toList());
    }

    @Override
    public DadosProdutoDto inserirProduto(DadosProdutoDtoEntrada dto) {
        Produto produto = new Produto(dto.nome(), dto.descricao(), dto.precoAtual());
        var categoria = categoriaService.buscarCategoria(dto.idCategoria());
        produto.setCategoria(categoria);

        Produto produtoSalvo = repository.save(produto);

        return mapperProdutoDto(produtoSalvo);
    }

    private DadosProdutoDto mapperProdutoDto(Produto produto) {
        return new DadosProdutoDto(produto);
    }

    @Override
    public void deleteProduto(Long id) {
        Produto produto = buscarProduto(id);
        produto.setAtualizadoEm(Instant.now());
        produto.setDeletadoEm(Instant.now());
        repository.save(produto);
    }

    @Override
    public Produto updateProduto(Long id, DadosProdutoDto dto) {
        // Por enquanto, so atualiza nome e preço.
        Produto produto = buscarProduto(id);
        produto.setNome(dto.nome().isEmpty() ? produto.getNome() : dto.nome());
        produto.setPrecoAtual(dto.precoAtual() == null ? produto.getPrecoAtual() : dto.precoAtual());
        produto.setAtualizadoEm(Instant.now());

        repository.save(produto);

        return produto;
    }

    private List<Produto> buscarProdutoPorCategoria(String nomeCategoria) {
        return repository.findAll(temCategoria(nomeCategoria));
    }

    private DadosProdutoDto mapperDadosProdutoDto(Produto produto) {
        return new DadosProdutoDto(produto);
    }

    private Produto verificaVigenciaProduto(Produto produto) {
        if (produto.getDeletadoEm() == null)
            return produto;
        else
            return null;
    }

    private List<Produto> produtosVigentes(List<Produto> produtos) {
        List<Produto> produtosVig = new ArrayList<>();
        for (Produto produto : produtos) {
            if (verificaVigenciaProduto(produto) != null)
                produtosVig.add(produto);
        }
        return produtosVig;
    }
}
