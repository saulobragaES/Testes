package br.com.teste.Aplicacao.model;

import java.util.List;

public class Pedido {

    private String id;
    private List<Produto> produtos;


    public Pedido(String id, List<Produto> produtos) {
        this.id = id;
        this.produtos = produtos;
    }


    public Pedido() {
    }

    public <T> Pedido(long l, String pedido001, List<T> asList) {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }


}
