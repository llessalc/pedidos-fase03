package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosValorDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final IPedidoService service;

    public PedidoController(IPedidoService _service) {
        this.service = _service;
    }

    @Operation(description = "Lista todos os pedidos")
    @GetMapping
    public ResponseEntity<List<DadosPedidosPainelDto>> listartodosPedidos() {
        try {
            List<DadosPedidosPainelDto> pedidos = service.listarPedidos();

            if (pedidos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<DadosPedidosPainelDto>>(pedidos, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception message and return a ResponseEntity with a custom error
            // message
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Operation(description = "Inicia Checkout")
    @PostMapping("/checkout")
    public ResponseEntity<Long> incluirPedido(@RequestBody @Valid DadosPedidosEntrada dto) {
        try {
            DadosPedidosDto dadosPedidosDto = service.inserirPedidoFila(dto);

            return ResponseEntity.ok(dadosPedidosDto.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Recebe aprovação de pagamento e pedido é transferido para cozinha")
    @PostMapping("/confirmacao-pagamento/{id}")
    @Transactional
    public ResponseEntity<DadosPedidosDto> recebePagamento(@PathVariable Long id) throws Exception {
        try {
            DadosPedidosDto objTarget = service.recebePagamento(id);
            return ResponseEntity.ok(objTarget);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(description = "Lista pedido")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPedido(@PathVariable Long id) {
        try {
            DadosPedidosValorDto objTarget = service.buscaPedido(id);
            return ResponseEntity.ok(objTarget);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
