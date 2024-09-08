package com.petize.pedido_service.service.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoPedido(@NotNull
                            Integer id,
                            @NotBlank
                            String nome,
                            @NotBlank
                            String descricao,
                            @NotNull
                            BigDecimal preco,
                            @NotNull
                            Integer quantidade
) {
}
