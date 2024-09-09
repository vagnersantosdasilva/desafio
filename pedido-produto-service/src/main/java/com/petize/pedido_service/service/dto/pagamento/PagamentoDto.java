package com.petize.pedido_service.service.dto.pagamento;

import java.math.BigDecimal;

public record PagamentoDto(BigDecimal valor, Integer pedidoId, StatusPagamento status) {
}
