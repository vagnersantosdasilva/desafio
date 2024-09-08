package com.petize.pedido_service.service.util;

import com.petize.pedido_service.models.ItemPedido;

import com.petize.pedido_service.service.dto.pedido.ItemPedidoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ItemPedidoMapper {
    @Mapping(source = "produto", target = "produtoResponse")
    ItemPedidoDto toDto(ItemPedido itemPedido);
}
