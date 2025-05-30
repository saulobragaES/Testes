package br.com.teste.Aplicacao.dto;

import br.com.teste.Aplicacao.mapper.ProdutoMapper;
import br.com.teste.Aplicacao.model.Pedido;
import br.com.teste.Aplicacao.model.Produto;

import java.util.ArrayList;
import java.util.List;



public class PedidoDTO {
    private String id;
    private List<ProdutoDTO> produtos;

    public PedidoDTO() {
    }

    public PedidoDTO(String id, List<ProdutoDTO> produtos) {
        this.id = id;
        this.produtos = produtos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoDTO produtoDTO : pedidoDTO.getProdutos()) {
            Produto produto = ProdutoMapper.toEntity(produtoDTO);
            produtos.add(produto);
        }
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setProdutos(produtos);
        return pedido;
    }


}
