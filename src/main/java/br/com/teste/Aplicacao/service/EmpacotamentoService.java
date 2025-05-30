package br.com.teste.Aplicacao.service;

import br.com.teste.Aplicacao.dto.PedidoDTO;
import br.com.teste.Aplicacao.dto.ProdutoDTO;
import br.com.teste.Aplicacao.model.Caixa;
import br.com.teste.Aplicacao.dto.CaixaEmpacotadaDTO;
import br.com.teste.Aplicacao.mapper.ProdutoMapper;
import br.com.teste.Aplicacao.model.Pedido;
import br.com.teste.Aplicacao.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpacotamentoService {

    private final List<Caixa> tiposDeCaixa;

    public EmpacotamentoService() {
        tiposDeCaixa = List.of(
                criarCaixa("Caixa 1", 30.0, 40.0, 80.0),
                criarCaixa("Caixa 2", 80.0, 50.0, 40.0),
                criarCaixa("Caixa 3", 50.0, 80.0, 60.0)
        );
    }

    private Caixa criarCaixa(String tipo, double altura, double largura, double comprimento) {
        Caixa caixa = new Caixa();
        caixa.setTipo(tipo);
        caixa.setAltura(altura);
        caixa.setLargura(largura);
        caixa.setComprimento(comprimento);
        caixa.setProdutos(new ArrayList<>());
        return caixa;
    }

    public List<CaixaEmpacotadaDTO> processarMaisUmPedido(List<Pedido> pedidos) {
        List<CaixaEmpacotadaDTO> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            List<Caixa> caixas = processarPedidoEmpacontado(pedido);
            resultado.addAll(converterCaixasParaDTO(caixas));
        }
        return resultado;
    }

    public CaixaEmpacotadaDTO processarPedido(Pedido pedido) {
        List<Caixa> caixas = processarPedidoEmpacontado(pedido); // método que empacota e retorna caixas (List<Caixa>)
        List<CaixaEmpacotadaDTO> caixasDTO = converterCaixasParaDTO(caixas);

        if (caixasDTO.isEmpty()) {
            throw new RuntimeException("Caixa alguma foi gerada para o pedido.");
        }

        return caixasDTO.get(0);
    }

    private Pedido converterPedidoDTOParaPedido(PedidoDTO pedidoDTO) {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoDTO pDTO : pedidoDTO.getProdutos()) {
            produtos.add(ProdutoMapper.toEntity(pDTO));
        }
        Pedido pedido = new Pedido();
        pedido.setId(pedidoDTO.getId());
        pedido.setProdutos(produtos);
        return pedido;
    }


    private List<Caixa> processarPedidoEmpacontado(Pedido pedido) {
        List<Produto> restos = new ArrayList<>(pedido.getProdutos());
        List<Caixa> usadas = new ArrayList<>();

        while (!restos.isEmpty()) {
            boolean encaixouAlgum = false;

            for (Caixa tipo : tiposDeCaixa) {
                Caixa caixa = criarCaixa(
                        tipo.getTipo(),
                        tipo.getAltura(),
                        tipo.getLargura(),
                        tipo.getComprimento()
                );

                List<Produto> paraRemover = new ArrayList<>();
                for (Produto prod : restos) {
                    if (caixa.adicionarProduto(prod)) {
                        paraRemover.add(prod);
                    }
                }

                if (!caixa.getProdutos().isEmpty()) {
                    usadas.add(caixa);
                    restos.removeAll(paraRemover);
                    encaixouAlgum = true;
                    break;
                }
            }

            if (!encaixouAlgum) {
                throw new RuntimeException(
                        "Nenhum produto foi colocado na caixa."
                );
            }
        }

        return usadas;
    }


    public List<CaixaEmpacotadaDTO> converterCaixasParaDTO(List<Caixa> caixas) {
        List<CaixaEmpacotadaDTO> list = new ArrayList<>();
        for (Caixa c : caixas) {
            List<ProdutoDTO> prodsDto = ProdutoMapper.toDTOList(c.getProdutos());
            list.add(new CaixaEmpacotadaDTO(c.getTipo(), prodsDto));
        }
        return list;
    }
}