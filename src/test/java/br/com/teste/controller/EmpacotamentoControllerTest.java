package br.com.teste.controller;

import br.com.teste.Aplicacao.controller.EmpacotamentoController;
import br.com.teste.Aplicacao.dto.CaixaEmpacotadaDTO;
import br.com.teste.Aplicacao.model.Pedido;
import br.com.teste.Aplicacao.repository.PedidoRepository;
import br.com.teste.Aplicacao.repository.ProdutoRepository;
import br.com.teste.Aplicacao.service.EmpacotamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpacotamentoController.class)
public class EmpacotamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpacotamentoService empacotamentoService;

    @MockBean
    private PedidoRepository pedidoRepository;

    @MockBean
    private ProdutoRepository produtoRepository;

    @Test
    void deveRetornarOkNoTeste() throws Exception {
        mockMvc.perform(get("/api/empacotamento/teste"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    void deveProcessarUmPedido() throws Exception {
        String pedidoId = "pedido001";
        Pedido pedidoMock = new Pedido(pedidoId, List.of());

        when(pedidoRepository.findAll()).thenReturn(List.of(pedidoMock));

        CaixaEmpacotadaDTO dtoMock = new CaixaEmpacotadaDTO();
        when(empacotamentoService.processarPedido(pedidoMock)).thenReturn(dtoMock);

        mockMvc.perform(get("/api/empacotamento/processar/{arg}", pedidoId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        // Aqui você pode usar jsonPath se quiser validar campos específicos do DTO
    }

    @Test
    void deveEmpacotarTodosOsPedidos() throws Exception {
        Pedido pedidoMock = new Pedido("pedido001", List.of());

        when(pedidoRepository.findAll()).thenReturn(List.of(pedidoMock));

        List<CaixaEmpacotadaDTO> listaDtoMock = List.of(new CaixaEmpacotadaDTO());
        when(empacotamentoService.processarMaisUmPedido(List.of(pedidoMock))).thenReturn(listaDtoMock);

        mockMvc.perform(get("/api/empacotamento/processar-todos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
