package com.petize.pedido_service.controller;


import com.petize.pedido_service.service.PedidoService;
import com.petize.pedido_service.service.dto.pedido.PedidoCreate;
import com.petize.pedido_service.service.dto.pedido.PedidoResponse;
import com.petize.pedido_service.service.dto.pedido.PedidoStatusUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedido-service")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("/pedido")
    public List<PedidoResponse> listarTodos() {
        return service.findAll();
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<PedidoResponse> listarPorId(@PathVariable @NotNull Integer id) {
        return  ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/pedido")
    public ResponseEntity<PedidoResponse> realizaPedido(@RequestBody @Valid PedidoCreate dto, UriComponentsBuilder uriBuilder) {
        PedidoResponse pedidoRealizado = service.create(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.id()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/pedido/{id}/status")
    public ResponseEntity<PedidoResponse> atualizaStatus(@PathVariable Integer id, @RequestBody PedidoStatusUpdate status){
        PedidoResponse dto = service.updateStatus(id, status);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/pedido/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Integer id) {
        service.aprovaPagamentoPedido(id);

        return ResponseEntity.ok().build();

    }
}