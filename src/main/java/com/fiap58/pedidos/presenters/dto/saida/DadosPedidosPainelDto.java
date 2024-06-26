package com.fiap58.pedidos.presenters.dto.saida;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
public class DadosPedidosPainelDto {
    @JsonIgnore
    private Long id;
    private List<ProdutoCarrinhoSaida> produtos;
    private String nomeCliente = "";
    private Instant dataPedido;
    private StatusPedido status;
    private long tempoEspera;

    public void setTempoEspera(long tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    private Erro erro;

    public static class Erro {
        private String mensagemErro;

        public String getMensagemErro() {
            return mensagemErro;
        }

        public void setMensagemErro(String mensagemErro) {
            this.mensagemErro = mensagemErro;
        }

    }

    public DadosPedidosPainelDto(DadosPedidosDto dadosPedidosDto, long tempoEspera) {
        this.id = dadosPedidosDto.getId();
        this.produtos = dadosPedidosDto.getProdutos();
        this.nomeCliente = dadosPedidosDto.getNomeCliente();
        this.dataPedido = dadosPedidosDto.getDataPedido();
        this.status = dadosPedidosDto.getStatus();
        this.tempoEspera = tempoEspera;
    }
}
