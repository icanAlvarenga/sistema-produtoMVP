package br.ufes.sistema.produtomvp.seeder;

import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.ICategoriaRepository;
import br.ufes.sistema.produtomvp.repository.IHistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seeder {

    public static void executar(ICategoriaRepository categoriaRepository,
                                IProdutoRepository produtoRepository,
                                IHistoricoPrecoRepository historicoPrecoRepository) {

        // 12.1 Categorias iniciais
        Categoria educacao = new Categoria(categoriaRepository.proximoId(), "Educação", 25.00);
        Categoria papelaria = new Categoria(categoriaRepository.proximoId(), "Papelaria", 30.00);
        Categoria alimentacao = new Categoria(categoriaRepository.proximoId(), "Alimentação", 22.00);
        Categoria lazer = new Categoria(categoriaRepository.proximoId(), "Lazer", 35.00);
        Categoria entretenimento = new Categoria(categoriaRepository.proximoId(), "Entretenimento", 40.00);
        Categoria higiene = new Categoria(categoriaRepository.proximoId(), "Higiene", 28.00);
        Categoria limpeza = new Categoria(categoriaRepository.proximoId(), "Limpeza", 25.00);

        categoriaRepository.adicionar(educacao);
        categoriaRepository.adicionar(papelaria);
        categoriaRepository.adicionar(alimentacao);
        categoriaRepository.adicionar(lazer);
        categoriaRepository.adicionar(entretenimento);
        categoriaRepository.adicionar(higiene);
        categoriaRepository.adicionar(limpeza);

        // 12.2 Produtos iniciais
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(produtoRepository.proximoId(), "Livro didático", 45.00, educacao));
        produtos.add(new Produto(produtoRepository.proximoId(), "Livro paradidático", 30.00, educacao));
        produtos.add(new Produto(produtoRepository.proximoId(), "Mochila escolar", 70.00, educacao));

        produtos.add(new Produto(produtoRepository.proximoId(), "Caderno universitário", 16.00, papelaria));
        produtos.add(new Produto(produtoRepository.proximoId(), "Lápis grafite HB", 1.20, papelaria));
        produtos.add(new Produto(produtoRepository.proximoId(), "Caneta esferográfica azul", 2.20, papelaria));
        produtos.add(new Produto(produtoRepository.proximoId(), "Borracha branca", 1.00, papelaria));
        produtos.add(new Produto(produtoRepository.proximoId(), "Apontador com depósito", 3.50, papelaria));

        produtos.add(new Produto(produtoRepository.proximoId(), "Jogo de tabuleiro", 55.00, lazer));
        produtos.add(new Produto(produtoRepository.proximoId(), "Bola recreativa", 40.00, lazer));
        produtos.add(new Produto(produtoRepository.proximoId(), "Quebra-cabeça 500 peças", 35.00, lazer));

        produtos.add(new Produto(produtoRepository.proximoId(), "Fone de ouvido", 48.00, entretenimento));
        produtos.add(new Produto(produtoRepository.proximoId(), "Caixa de som portátil", 80.00, entretenimento));
        produtos.add(new Produto(produtoRepository.proximoId(), "Revista de passatempos", 12.00, entretenimento));

        produtos.add(new Produto(produtoRepository.proximoId(), "Biscoito integral", 5.50, alimentacao));
        produtos.add(new Produto(produtoRepository.proximoId(), "Suco de uva 1 L", 9.00, alimentacao));
        produtos.add(new Produto(produtoRepository.proximoId(), "Barra de cereal", 3.20, alimentacao));

        produtos.add(new Produto(produtoRepository.proximoId(), "Sabonete", 2.80, higiene));
        produtos.add(new Produto(produtoRepository.proximoId(), "Creme dental", 5.50, higiene));

        produtos.add(new Produto(produtoRepository.proximoId(), "Detergente líquido", 2.60, limpeza));
        produtos.add(new Produto(produtoRepository.proximoId(), "Esponja multiuso", 1.70, limpeza));

        // 12.3 Estado inicial do histórico (10 dias atrás)
        LocalDate dataInicial = LocalDate.now().minusDays(10);

        for (Produto produto : produtos) {
            double margem = produto.getCategoria().getPercLucro();
            double precoVenda = produto.getPrecoDeCusto() * (1.0 + (margem / 100.0));
            double precoVendaArredondado = BigDecimal.valueOf(precoVenda)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            produto.atualizarCalculo(margem, precoVendaArredondado);
            produtoRepository.adicionarProduto(produto);

            HistoricoPreco historico = new HistoricoPreco(
                    historicoPrecoRepository.proximoId(),
                    produto,
                    dataInicial,
                    margem,
                    precoVendaArredondado
            );
            historicoPrecoRepository.adicionar(historico);
        }
    }
}
