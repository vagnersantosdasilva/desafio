package com.petize.pedido_service.service.util;

import com.petize.pedido_service.models.Pedido;
import com.petize.pedido_service.service.dto.pedido.PedidoCreate;
import com.petize.pedido_service.service.dto.pedido.PedidoResponse;
import com.petize.pedido_service.service.dto.pedido.PedidoUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);
    Pedido toEntity(PedidoCreate pedidoCreate);
    Pedido toUpdateEntity (PedidoUpdate pedidoUpdate);
    PedidoResponse toDto(Pedido pedido);
}
