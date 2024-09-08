package com.petize.pedido_service.service.util;

import com.petize.pedido_service.models.Produto;
import com.petize.pedido_service.service.dto.produto.ProdutoCreate;
import com.petize.pedido_service.service.dto.produto.ProdutoResponse;
import com.petize.pedido_service.service.dto.produto.ProdutoUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {
    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
    Produto toEntity(ProdutoCreate produtoCreateDto);
    Produto toUpdateEntity (ProdutoUpdate produtoUpdateDto);
    ProdutoResponse toDto(Produto produto);
}
