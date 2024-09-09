package com.petize.pagamento_service.service.dto;

import com.petize.pagamento_service.models.Status;

import java.math.BigDecimal;

public record PagamentoDTO(
        BigDecimal valor,
        String tipo,
        Status status,
        Integer pedidoId
) {
}
