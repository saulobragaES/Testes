package br.com.teste.Aplicacao.repository;

import br.com.teste.Aplicacao.model.Caixa;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CaixaRepository {

    public List<Caixa> findAll() {
        return Arrays.asList(
                new Caixa("Caixa 1", 30, 40, 80),
                new Caixa("Caixa 2", 80, 50, 40),
                new Caixa("Caixa 3", 50, 80, 60)
        );
    }
}

