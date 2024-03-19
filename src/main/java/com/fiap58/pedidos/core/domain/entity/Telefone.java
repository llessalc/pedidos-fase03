package com.fiap58.pedidos.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "Telefones")
@Getter
@Setter
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TELEFONE")
    private Long idTelefone;

    @JsonIgnoreProperties("telefone")
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @Column(name = "DDD", nullable = false, length = 2)
    private String ddd;

    @Column(name = "NUMERO", nullable = false, length = 10)
    private String numero;

    @Column(name = "TIPO", length = 20)
    private String tipo;

    @Column(name = "CRIADO_EM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant criadoEm;

    @Column(name = "ATUALIZADO_EM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant atualizadoEm;

    @Column(name = "DELETADO_EM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant deletadoEm;

    public Telefone() {
    }

    public Telefone(TelefoneCadastro telefoneCadastro) {
        this.criadoEm = Instant.now();
        this.ddd = telefoneCadastro.ddd();
        this.numero = telefoneCadastro.numero();
        this.tipo = telefoneCadastro.tipo();
    }
}
