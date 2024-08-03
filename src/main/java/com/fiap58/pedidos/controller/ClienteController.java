package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.Telefone;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IEnderecoService;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.core.usecase.ITelefoneService;
import com.fiap58.pedidos.presenters.dto.entrada.BuscaClienteDto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.PagamentoDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;

import com.fiap58.pedidos.presenters.dto.saida.DadosClienteExclusaoDto;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final IClienteService service;


    private final IEnderecoService enderecoService;
    private final ITelefoneService telefoneService;
    private final IPedidoService pedidoService;

    public ClienteController(IClienteService _service, IEnderecoService enderecoService, ITelefoneService telefoneService, IPedidoService pedidoService) {
        this.service = _service;
        this.enderecoService = enderecoService;
        this.telefoneService = telefoneService;
        this.pedidoService = pedidoService;
    }

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
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(clientes);
        }
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


    @PostMapping("/excluir")
    public ResponseEntity<DadosClienteExclusaoDto> inativarDadosCliente(@RequestBody BuscaClienteDto cliente) throws Exception {
        List<PagamentoDto> pagamentoDtos = pedidoService.retornaPedidosCliente(cliente);
        DadosClienteExclusaoDto dadosClienteExclusaoDto;

        if(cliente.excluirCliente()){

            pedidoService.excluirPagamentosCliente(pagamentoDtos);
            dadosClienteExclusaoDto = service.excluirCliente(cliente);


            return ResponseEntity.ok().body(null);
        }
        dadosClienteExclusaoDto = service.excluirCliente(cliente);
        if(dadosClienteExclusaoDto != null) {
            dadosClienteExclusaoDto.setPagamentoDtos(pagamentoDtos);
            return ResponseEntity.ok().body(dadosClienteExclusaoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("enderecos/{id}")
    public ResponseEntity<List<Endereco>> listarEnderecoCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(enderecoService.listarEnderecoCliente(id));
    }

    @GetMapping("telefones/{id}")
    public ResponseEntity<List<Telefone>> listarTelefonesCliente(@PathVariable Long id){
        return ResponseEntity.ok().body(telefoneService.listaTelefoneCliente(id));
    }

}
