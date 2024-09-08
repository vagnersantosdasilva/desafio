package com.petize.pedido_service.service.dto.pedido;

import com.petize.pedido_service.models.ItemPedido;
import com.petize.pedido_service.models.Status;
import com.petize.pedido_service.service.dto.produto.ProdutoPedido;
import com.petize.pedido_service.service.dto.produto.ProdutoResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Integer id, LocalDateTime dataPedido, List<ProdutoPedido> itens, Status status) {
}
