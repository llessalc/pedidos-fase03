package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private ICategoriaService service;

    public CategoriaController(ICategoriaService _service) {
        this.service = _service;
    }

    @Operation(description = "Busca categoria por Id")
    @GetMapping("/{id}")
    public ResponseEntity<DadosCategoriaDto> getCategoria(@PathVariable long id) {

        try {
            DadosCategoriaDto categoria = service.retornarCategoria(id);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Cadastra nova categoria")
    @PostMapping()
    @Transactional
    public ResponseEntity<DadosCategoriaDto> cadastraCategoria(@RequestBody @Valid CategoriaDtoEntrada dto) {
        try {
            DadosCategoriaDto categoria = service.cadastrarCategoria(dto);
            if (categoria != null) {
                return ResponseEntity.ok(categoria);
            } else {
                throw new Exception("Categoria nao cadastrada");
            }
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(description = "Excluir categoria")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        try {
            service.deletarCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
