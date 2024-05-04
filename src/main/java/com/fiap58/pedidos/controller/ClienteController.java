package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @Operation(description = "Faz a inserção de um novo cliente")
    @PostMapping("/inserir")
    public ResponseEntity<DadosClienteDto> cadastrarCliente(@RequestBody DadosClienteCadastro cliente) {
        DadosClienteDto dadosClienteDto = service.cadastrarCliente(cliente);
        if (dadosClienteDto == null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(service.retornaClienteCpf(cliente.cpf()));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(dadosClienteDto);
        }
    }

    @Operation(description = "Lista todos os clientes")
    @GetMapping("/list")
    public ResponseEntity<List<DadosClienteDto>> listarClientes() {
        List<DadosClienteDto> clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(description = "Busca um cliente por Id")
    @GetMapping("/{id}")
    public ResponseEntity<DadosClienteDto> buscarCliente(@PathVariable Long id) {
        DadosClienteDto cliente = service.retornaClienteId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Busca um cliente por CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<DadosClienteDto> buscarClientePorCpf(@PathVariable String cpf) {
        DadosClienteDto cliente = service.retornaClienteCpf(cpf);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
