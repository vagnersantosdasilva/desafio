package com.petize.pedido_service.controller;

import com.petize.pedido_service.service.ProdutoService;
import com.petize.pedido_service.service.dto.produto.ProdutoCreate;
import com.petize.pedido_service.service.dto.produto.ProdutoResponse;
import com.petize.pedido_service.service.dto.produto.ProdutoUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto-service")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping("/produto")
    public ResponseEntity<?> createProduto(@RequestBody @Valid ProdutoCreate produto, UriComponentsBuilder uriComponentsBuilder){
        ProdutoResponse produtoResponse = produtoService.create(produto);
        var uri = uriComponentsBuilder.path("produto/{id}").buildAndExpand(produtoResponse.id()).toUri();
        return ResponseEntity.created(uri).body(produtoResponse);
    }

    @GetMapping("/produto")
    public ResponseEntity<?> findAllProdutos() {
        List<ProdutoResponse> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoResponse> findProdutoById(@PathVariable Integer id) {
        ProdutoResponse produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<ProdutoResponse> updateProduto(@PathVariable Integer id, @RequestBody @Valid ProdutoUpdate produtoUpdate) {

        ProdutoResponse produtoResponse = produtoService.update(id, produtoUpdate);
        return ResponseEntity.ok(produtoResponse);

    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity deleteProduto(@PathVariable Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();

    }

}