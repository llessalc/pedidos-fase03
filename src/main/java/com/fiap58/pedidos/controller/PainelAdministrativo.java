package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adm")
public class PainelAdministrativo {

    private final IPedidoService service;

    public PainelAdministrativo(IPedidoService _service) {
        this.service = _service;
    }

    @Operation(description = "Atualiza o status do pedido")
    @PatchMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id) {
        try {
            DadosPedidosDto data = service.atualizarPedido(id, false);
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(description = "Define tempo de espera pedido")
    @PostMapping("/define-tempo-espera/{id}")
    @Transactional
    public ResponseEntity<?> atualizarTempoEspera(@PathVariable Long id,
            @RequestBody long tempoEspera) {

        try {
            if (tempoEspera < 0L) {
                throw new IllegalArgumentException("Tempo de espera não pode ser zerado e nem negativo");
            }

            Pedido pedido = service.retornaPedido(id);
            return ResponseEntity.ok(service.defineTempoEspera(pedido, tempoEspera));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}
