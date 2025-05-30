package br.com.teste.Aplicacao.mapper;

import br.com.teste.Aplicacao.dto.ProdutoDTO;
import br.com.teste.Aplicacao.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getNome(),
                produto.getAltura(),
                produto.getLargura(),
                produto.getComprimento()
        );
    }

    public static Produto toEntity(ProdutoDTO dto) {
        return new Produto(
                dto.getNome(),
                dto.getAltura(),
                dto.getLargura(),
                dto.getComprimento()
        );
    }

    public static List<ProdutoDTO> toDTOList(List<Produto> produtos) {
        return produtos.stream().map(ProdutoMapper::toDTO).collect(Collectors.toList());
    }

    public static List<Produto> toEntityList(List<ProdutoDTO> dtos) {
        return dtos.stream().map(ProdutoMapper::toEntity).collect(Collectors.toList());
    }
}

