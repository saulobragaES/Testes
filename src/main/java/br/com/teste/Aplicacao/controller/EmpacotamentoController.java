package br.com.teste.Aplicacao.controller;


import br.com.teste.Aplicacao.dto.CaixaEmpacotadaDTO;
import br.com.teste.Aplicacao.repository.PedidoRepository;
import br.com.teste.Aplicacao.repository.ProdutoRepository;
import br.com.teste.Aplicacao.model.Pedido;

import br.com.teste.Aplicacao.service.EmpacotamentoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/empacotamento")
public class EmpacotamentoController {

    @PostConstruct
    public void init() {
        System.out.println("EmpacotamentoController iniciado com sucesso!");
    }

    @Autowired
    private EmpacotamentoService empacotamentoService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private  ProdutoRepository produtoRepository;
    private static final Logger logger = Logger.getLogger(EmpacotamentoController.class.getName());



    @GetMapping("/teste")
    public String teste() {
        logger.info("Chamou POST /api/empacotamento/teste");
        return "ok";
    }

    @GetMapping("/processar/{arg}")
    public CaixaEmpacotadaDTO processarUmPedido(@PathVariable("arg") String arg) {

        Pedido pedido = pedidoRepository.findAll().stream()
                .filter(p -> p.getId().equals(arg))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + arg));

        return empacotamentoService.processarPedido(pedido);

    }


    @GetMapping("/processar-todos")
    public List<CaixaEmpacotadaDTO> empacotarTodos() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        return empacotamentoService.processarMaisUmPedido(pedidos);

    }

}
