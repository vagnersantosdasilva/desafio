package com.petize.pedido_service.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> itens = new ArrayList<>();

    @Column(name = "status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


}
