package br.com.teste.Aplicacao.model;

import java.util.ArrayList;
import java.util.List;


public class Caixa {
    private String tipo;
    private double altura;
    private double largura;
    private double comprimento;
    private List<Produto> produtos = new ArrayList<>();

    public Caixa(String s, int i, int i1, int i2) {
    }

    public Caixa() {

    }

    public double getVolume() {
        return altura * largura * comprimento;
    }

    public double getVolumeOcupado() {
        return produtos.stream().mapToDouble(Produto::getVolume).sum();
    }

    public double getVolumeDisponivel() {
        return getVolume() - getVolumeOcupado();
    }

    public boolean adicionarProduto(Produto produto) {
        if (produto.getVolume() <= getVolumeDisponivel()) {
            produtos.add(produto);
            return true;
        }
        return false;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}