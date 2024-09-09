package com.petize.pagamento_service.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Pagamento {

    private Long id;

    private BigDecimal valor;


    private String nome;


    private String numero;


    private String expiracao;


    private String codigo;


    private Status status;


    private Long pedidoId;


    private Long formaDePagamentoId;
}
