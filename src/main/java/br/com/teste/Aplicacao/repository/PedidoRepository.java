package br.com.teste.Aplicacao.repository;

import br.com.teste.Aplicacao.model.Pedido;
import br.com.teste.Aplicacao.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class PedidoRepository {

    public List<Pedido> findAll() {
        Produto produto1 = new Produto("Caixa Barbear", 10.0, 15.0, 3.0);
        Produto produto2 = new Produto("Caixa Pasta Dente", 20.0, 25.0, 5.0);
        Produto produto3 = new Produto("Caixa Sabonete", 40.0, 30.0, 20.0);

        Pedido pedido1 = new Pedido("pedido001", Arrays.asList(produto1, produto2));
        Pedido pedido2 = new Pedido("pedido002", Arrays.asList(produto3));

        return Arrays.asList(pedido1, pedido2);
    }

    public Pedido findById(String arg) {
        Produto produto1 = new Produto("Caixa Barbear", 10.0, 15.0, 3.0);
        Produto produto2 = new Produto("Caixa Pasta Dente", 20.0, 25.0, 5.0);
        Produto produto3 = new Produto("Caixa Sabonete", 40.0, 30.0, 20.0);

        Pedido pedido1 = new Pedido(1L, "pedido001", Arrays.asList(produto1, produto2));
        Pedido pedido2 = new Pedido(2L, "pedido002", Arrays.asList(produto3));

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        return pedidos.stream()
                .filter(p -> p.getId().equals(arg))
                .findFirst()
                .orElse(null);
    }


}
