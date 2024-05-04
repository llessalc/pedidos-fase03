package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.usecase.IProdutoService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private IProdutoService service;

    @Operation(description = "Busca produto por Id")
    @GetMapping("/{id}")
    public ResponseEntity<DadosProdutoDto> buscarProduto(@PathVariable long id) {
        return ResponseEntity.ok(service.retornaProduto(id));
    }

    @Operation(description = "Lista todos os produtos disponíveis")
    @GetMapping("/list")
    public List<DadosProdutoDto> listarProdutos() {
        return service.retornaListaProdutos();
    }

    @Operation(description = "Busca produtos de uma determinada categoria")
    @GetMapping("/buscaPorCat/{nomeCategoria}")
    public List<DadosProdutoDto> listarProdutosPorCategoria(@PathVariable String nomeCategoria) {
        return service.retornaListaProdutosCategoria(nomeCategoria);
    }

    @Operation(description = "Faz a inclusão de um novo produto")
    @PostMapping
    @Transactional
    public ResponseEntity<DadosProdutoDto> incluirProduto(@RequestBody @Valid DadosProdutoDtoEntrada dto) {

        DadosProdutoDto dadosProdutoDto = service.inserirProduto(dto);
        return ResponseEntity.ok(dadosProdutoDto);
    }

    @Operation(description = "Atualiza um produto existente")
    @PatchMapping("/{id}")
    @Transactional
    public void atualizarProduto(@PathVariable Long id, @RequestBody @Valid DadosProdutoDto dto) {

        service.updateProduto(id, dto);

    }

    @Operation(description = "Deleta um produto")
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteProduto(@PathVariable Long id) {

        service.deleteProduto(id);
    }

}
