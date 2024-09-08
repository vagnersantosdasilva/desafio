package com.petize.pedido_service.service.dto.pedido;

import com.petize.pedido_service.models.ItemPedido;
import com.petize.pedido_service.models.Status;
import com.petize.pedido_service.service.dto.produto.ProdutoCreate;
import com.petize.pedido_service.service.dto.produto.ProdutoPedido;
import com.petize.pedido_service.service.dto.produto.ProdutoUpdate;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoCreate(
                           LocalDateTime dataPedido,
                           @NotNull
                           List<ProdutoPedido> itens,

                           Status status)  {
}
