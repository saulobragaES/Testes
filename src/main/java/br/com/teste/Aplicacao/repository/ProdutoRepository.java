package br.com.teste.Aplicacao.repository;

import br.com.teste.Aplicacao.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProdutoRepository {

    public List<Produto> findAll() {
        return Arrays.asList(
                new Produto("Produto 01", 10.0, 15.0, 3.0),
                new Produto("Produto 02", 20.0, 25.0, 5.0),
                new Produto("Produto 03", 40.0, 30.0, 20.0),
                new Produto("Produto 04", 60.0, 70.0, 80.0)
        );
    }
}
