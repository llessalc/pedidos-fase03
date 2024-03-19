package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.core.usecase.PedidoService;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosValorDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(description = "Lista todos os pedidos")
    @GetMapping
    public ResponseEntity<List<DadosPedidosPainelDto>> listarPedidos(){
        List<DadosPedidosPainelDto> pedidos = service.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @Operation(description = "Inicia Checkout")
    @PostMapping("/checkout")
    public ResponseEntity<Long> incluirPedido(@RequestBody @Valid DadosPedidosEntrada dto){
        DadosPedidosDto dadosPedidosDto = service.inserirPedidoFila(dto);

        return ResponseEntity.ok(dadosPedidosDto.getId());
    }

    @Operation(description = "Recebe aprovação de pagamento e pedido é transferido para cozinha")
    @PostMapping("/confirmacao-pagamento/{id}")
    @Transactional
    public ResponseEntity<DadosPedidosDto> recebePagamento(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.recebePagamento(id));
    }

    @Operation(description = "Lista pedido")
    @GetMapping("/{id}")
    public ResponseEntity<DadosPedidosValorDto> listarPedido(@PathVariable Long id){
        return ResponseEntity.ok(service.buscaPedido(id));
    }
}

