package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.usecase.ICategoriaService;
import com.fiap58.pedidos.presenters.dto.entrada.CategoriaDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosCategoriaDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

        return ResponseEntity.ok(service.retornarCategoria(id));
    }

    @Operation(description = "Cadastra nova categoria")
    @PostMapping()
    @Transactional
    public ResponseEntity<DadosCategoriaDto> cadastraCategoria(@RequestBody @Valid CategoriaDtoEntrada dto){
        DadosCategoriaDto categoria = service.cadastrarCategoria(dto);
        if (categoria != null){
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Excluir categoria")
    @DeleteMapping("/{id}")
    @Transactional
    public void deletarCategoria(@PathVariable Long id){
        service.deletarCategoria(id);
    }
}
