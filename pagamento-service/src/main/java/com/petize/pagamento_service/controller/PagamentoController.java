package com.petize.pagamento_service.controller;

import com.petize.pagamento_service.service.dto.PagamentoDTO;
import jakarta.validation.Valid;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/pagamento-service")
public class PagamentoController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/pagamento")
    public ResponseEntity<PagamentoDTO> create(@RequestBody  PagamentoDTO dto, UriComponentsBuilder uriBuilder) {
        //PagamentoDTO pagamento = service.criarPagamento(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(1).toUri();
        Message message = new Message(("Criei um pagamento para  o pedido de id " + dto.pedidoId()).getBytes());
        rabbitTemplate.send("pagamento.concluido", message);
        return ResponseEntity.created(endereco).body(dto);
    }
}
