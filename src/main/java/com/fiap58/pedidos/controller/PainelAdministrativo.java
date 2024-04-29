package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosPainelDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adm")
public class PainelAdministrativo {

    @Autowired
    private IPedidoService service;

    @Operation(description = "Atualiza o status do pedido")
    @PatchMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<DadosPedidosDto> atualizarStatus(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.atualizarPedido(id, false));
    }

    @Operation(description = "Define tempo de espera pedido")
    @PostMapping("/define-tempo-espera/{id}")
    @Transactional
    public ResponseEntity<DadosPedidosPainelDto> atualizarTempoEspera(@PathVariable Long id,
                                                                      @RequestBody long tempoEspera){
        Pedido pedido = service.retornaPedido(id);
        return ResponseEntity.ok(service.defineTempoEspera(pedido, tempoEspera));
    }

}
