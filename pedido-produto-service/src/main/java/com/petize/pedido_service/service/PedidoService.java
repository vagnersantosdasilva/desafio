package com.petize.pedido_service.service;

import com.petize.pedido_service.models.ItemPedido;
import com.petize.pedido_service.models.Pedido;
import com.petize.pedido_service.models.Produto;
import com.petize.pedido_service.models.Status;
import com.petize.pedido_service.repository.PedidoRepository;
import com.petize.pedido_service.repository.ProdutoRepository;
import com.petize.pedido_service.service.dto.pedido.PedidoCreate;
import com.petize.pedido_service.service.dto.pedido.PedidoResponse;
import com.petize.pedido_service.service.dto.pedido.PedidoStatusUpdate;
import com.petize.pedido_service.service.dto.produto.ProdutoPedido;
import com.petize.pedido_service.service.util.PedidoMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;


    @Transactional
    public PedidoResponse create(PedidoCreate pedidoCreate) {
        // Cria a entidade Pedido a partir do DTO
        Pedido novoPedido = new Pedido();
        novoPedido.setDataPedido(LocalDateTime.now());
        novoPedido.setStatus(Status.PENDENTE);

        // Converte cada ProdutoUpdate em ItemPedido e associa ao Pedido
        List<ItemPedido> itens = pedidoCreate.itens().stream().map(produtoUpdate -> {
            // Busca o produto pelo ID
            Produto produto = produtoRepository.findById(produtoUpdate.id())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + produtoUpdate.id()));

            // Cria o ItemPedido e associa ao Produto e Pedido
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setPedido(novoPedido);
            itemPedido.setQuantidade(produtoUpdate.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());

            return itemPedido;
        }).collect(Collectors.toList());

        // Associa a lista de itens ao Pedido
        novoPedido.setItens(itens);

        // Salva o Pedido e os Itens associados
        Pedido pedidoSalvo = pedidoRepository.save(novoPedido);

        return convertPedido(pedidoSalvo);
    }

    private PedidoResponse convertPedido(Pedido pedido){
        List<ProdutoPedido> produtoPedidos = pedido.getItens().stream().map(itemPedido -> {
            Produto produto = itemPedido.getProduto();
            return new ProdutoPedido(
                    produto.getId(),
                    produto.getNome(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    itemPedido.getQuantidade()
            );
        }).collect(Collectors.toList());

        // Cria o PedidoResponse
        PedidoResponse pedidoResponse = new PedidoResponse(
                pedido.getId(),
                pedido.getDataPedido(),
                produtoPedidos,
                pedido.getStatus()
        );

        return pedidoResponse;
    }


    public List<PedidoResponse> findAll() {
        return pedidoRepository.findAll().stream()
                .map(e->convertPedido(e))
                .collect(Collectors.toList());
    }


    public PedidoResponse findById(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        return convertPedido(pedido);
    }

    @Transactional
    public PedidoResponse updateStatus(Integer id, PedidoStatusUpdate statusUpdate) {

        Pedido pedido = pedidoRepository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(statusUpdate.status());
        pedidoRepository.atualizaStatus(statusUpdate.status(), pedido);
        return convertPedido(pedidoRepository.save(pedido));
    }

    public void aprovaPagamentoPedido(Integer id) {

        Pedido pedido = pedidoRepository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(Status.CONCLUIDO);
        pedidoRepository.atualizaStatus(Status.CONCLUIDO, pedido);
    }

    public void delete(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        pedidoRepository.delete(pedido);
    }
}
