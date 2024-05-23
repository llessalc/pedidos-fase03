package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.usecase.IProdutoService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final IProdutoService service;

    public ProdutoController(IProdutoService _service) {
        this.service = _service;
    }

    @Operation(description = "Busca produto por Id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable long id) {
        try {
            return ResponseEntity.ok(service.retornaProduto(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Lista todos os produtos disponíveis")
    @GetMapping("/list")
    public ResponseEntity<List<?>> listarProdutos() {
        try {
            List<DadosProdutoDto> lDadosProdutoDtos = service.retornaListaProdutos();
            return ResponseEntity.ok(lDadosProdutoDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Busca produtos de uma determinada categoria")
    @GetMapping("/buscaPorCat/{nomeCategoria}")
    public ResponseEntity<List<?>> listarProdutosPorCategoria(@PathVariable String nomeCategoria) {
        try {
            List<DadosProdutoDto> lDadosProdutoDtos = service.retornaListaProdutosCategoria(nomeCategoria);
            return ResponseEntity.ok(lDadosProdutoDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Faz a inclusão de um novo produto")
    @PostMapping
    @Transactional
    public ResponseEntity<?> incluirProduto(@RequestBody @Valid DadosProdutoDtoEntrada dto) {
        try {
            DadosProdutoDto dadosProdutoDto = service.inserirProduto(dto);
            return ResponseEntity.ok(dadosProdutoDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Atualiza um produto existente")
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizarProduto(@PathVariable Long id, @RequestBody @Valid DadosProdutoDto dto) {
        try {
            service.updateProduto(id, dto);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @Operation(description = "Deleta um produto")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        try {
            service.deleteProduto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
