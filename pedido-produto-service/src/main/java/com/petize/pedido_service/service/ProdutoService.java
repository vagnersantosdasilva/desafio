package com.petize.pedido_service.service;

import com.petize.pedido_service.models.Produto;
import com.petize.pedido_service.repository.ProdutoRepository;
import com.petize.pedido_service.service.dto.produto.ProdutoCreate;
import com.petize.pedido_service.service.dto.produto.ProdutoResponse;
import com.petize.pedido_service.service.dto.produto.ProdutoUpdate;
import com.petize.pedido_service.service.util.ProdutoMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponse create(ProdutoCreate produtoCreate){

        Produto novoProduto = ProdutoMapper.INSTANCE.toEntity(produtoCreate);

        //validacoes.forEach(v-> v.validar(novoProduto.getCategoria(), novoProduto.getIdEspecificacao()));

        return ProdutoMapper.INSTANCE.toDto(produtoRepository.save(novoProduto));
    }

    public List<ProdutoResponse> findAll() {
        return produtoRepository.findAll().stream()
                .map(ProdutoMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponse update(Integer id, ProdutoUpdate produtoUpdate) {
        produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        return ProdutoMapper.INSTANCE.toDto(produtoRepository.save(ProdutoMapper.INSTANCE.toUpdateEntity(produtoUpdate)));
    }

    public ProdutoResponse findById(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return ProdutoMapper.INSTANCE.toDto(produto);

    }

    public void delete(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produtoRepository.delete(produto);
    }

}
