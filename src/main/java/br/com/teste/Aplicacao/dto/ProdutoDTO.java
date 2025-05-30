package br.com.teste.Aplicacao.dto;

public class ProdutoDTO {
    private String nome;
    private double altura;
    private double largura;
    private double comprimento;

    public ProdutoDTO() {}

    public ProdutoDTO(String nome, double altura, double largura, double comprimento) {
        this.nome = nome;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }
    public double getLargura() { return largura; }
    public void setLargura(double largura) { this.largura = largura; }
    public double getComprimento() { return comprimento; }
    public void setComprimento(double comprimento) { this.comprimento = comprimento; }
}
