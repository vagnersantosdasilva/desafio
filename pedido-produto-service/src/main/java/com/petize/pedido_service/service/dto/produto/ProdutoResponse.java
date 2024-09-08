package com.petize.pedido_service.service.dto.produto;

import java.math.BigDecimal;

public record ProdutoResponse(Integer id, String nome, String descricao, BigDecimal preco) {
}
