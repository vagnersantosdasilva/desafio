package com.petize.pedido_service.service.dto.pedido;

import com.petize.pedido_service.service.dto.produto.ProdutoResponse;

public record ItemPedidoDto(Integer id, Integer quantidade, ProdutoResponse produtoResponse ) {
}
