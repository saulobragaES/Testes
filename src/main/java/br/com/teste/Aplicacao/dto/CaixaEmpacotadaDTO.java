package br.com.teste.Aplicacao.dto;

import java.util.List;

public class CaixaEmpacotadaDTO {
    private String tipo;
    private List<ProdutoDTO> produtos;

    public CaixaEmpacotadaDTO() {}

    public CaixaEmpacotadaDTO(String tipo, List<ProdutoDTO> produtos) {
        this.tipo = tipo;
        this.produtos = produtos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }

}
