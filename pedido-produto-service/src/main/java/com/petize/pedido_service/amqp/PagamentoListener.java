package com.petize.pedido_service.amqp;

import com.petize.pedido_service.service.PedidoService;
import com.petize.pedido_service.service.dto.pagamento.PagamentoDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = "pagamento.concluido")
    public void recebeMensagem(PagamentoDto pagamento) {
        String mensagem = """
                 Dados do pagamento:
                 Número do pedido: %s
                 Valor R$: %s
                 Status: %s
                """.formatted(pagamento.pedidoId(),
                pagamento.valor(),
                pagamento.status());
        System.out.println("Recebi a mensagem " + mensagem);

        // Verifica se o status do pagamento é "CONFIRMADO"
        if ("CONFIRMADO".equalsIgnoreCase(pagamento.status().toString())) {
            // Chama o serviço para atualizar o status do pedido
            pedidoService.aprovaPagamentoPedido(pagamento.pedidoId());
            System.out.println("Status do pedido atualizado para CONCLUIDO.");
        } else {
            System.out.println("Pagamento não confirmado, nenhuma ação realizada.");
        }
    }
}