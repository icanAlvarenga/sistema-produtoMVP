package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import java.util.List;

public interface ICalculoMargemLucroView {
    String getDataCalculo();
    void setDataCalculo(String data);
    void setDatasDisponiveis(List<String> datas);
    void exibirProdutosCalculados(List<HistoricoPreco> historicos);
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagem);
    void abrir();
    void fechar();
    void aoCalcular(Runnable acao);
    void aoFechar(Runnable acao);
}
