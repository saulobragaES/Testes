package br.com.teste.service;

import br.com.teste.Aplicacao.dto.CaixaEmpacotadaDTO;
import br.com.teste.Aplicacao.model.Pedido;
import br.com.teste.Aplicacao.model.Produto;
import br.com.teste.Aplicacao.service.EmpacotamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpacotamentoServiceTest {

    private EmpacotamentoService empacotamentoService;

    @BeforeEach
    void setUp() {
        empacotamentoService = new EmpacotamentoService();
    }

    @Test
    void deveEmpacotarPedidoComSucesso() {
        // Arrange
        Produto produto1 = new Produto("Produto 01", 10.0, 15.0, 3.0);
        Produto produto2 = new Produto("Produto 02", 20.0, 25.0, 5.0);
        Produto produto3 = new Produto("Produto 03", 40.0, 30.0, 20.0);
        Produto produto4 = new Produto("Produto 04", 60.0, 70.0, 80.0);

        Pedido pedido = new Pedido("pedido1", List.of(produto1, produto2, produto3, produto4));

        // Act
        List<CaixaEmpacotadaDTO> caixas = empacotamentoService.processarMaisUmPedido(List.of(pedido));

        // Assert
        assertNotNull(caixas);
        assertFalse(caixas.isEmpty(), "A lista de caixas não deve estar vazia");

        int totalProdutosEmpacotados = caixas.stream()
                .mapToInt(c -> c.getProdutos().size())
                .sum();

        assertEquals(4, totalProdutosEmpacotados, "Todos os produtos devem ser empacotados");

        caixas.forEach(caixa -> {
            assertNotNull(caixa.getTipo(), "Tipo da caixa não pode ser nulo");
            assertFalse(caixa.getProdutos().isEmpty(), "Cada caixa deve ter pelo menos um produto");
        });
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoCabeEmNenhumaCaixa() {
        // Arrange
        Produto produtoMuitoGrande = new Produto("Produto Gigante", 200.0, 200.0, 200.0);
        Pedido pedido = new Pedido("pedido2", List.of(produtoMuitoGrande));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            empacotamentoService.processarPedido(pedido);
        });

        assertEquals("Não foi possível encaixar alguns produtos em nenhuma caixa.", exception.getMessage());
    }
}
