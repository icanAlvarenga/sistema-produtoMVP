
package br.ufes.sistema.produtomvp.seeder;

import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.ProdutoRepository;

public class Seeder {

    public static void executar(ProdutoRepository repository) {

        // Categorias
        Categoria bebidas = new Categoria(1, "Bebidas", 30.0);
        Categoria alimentos = new Categoria(2, "Alimentos", 25.0);
        Categoria higiene = new Categoria(3, "Higiene", 40.0);

        // Produtos
        Produto cocaCola = new Produto(
                1,
                "Coca-Cola",
                5.00,
                bebidas
        );

        Produto suco = new Produto(
                2,
                "Suco de Laranja",
                4.00,
                bebidas
        );

        Produto arroz = new Produto(
                3,
                "Arroz",
                20.00,
                alimentos
        );

        Produto feijao = new Produto(
                4,
                "Feijão",
                8.00,
                alimentos
        );

        Produto sabonete = new Produto(
                5,
                "Sabonete",
                3.50,
                higiene
        );

        // Adicionando os produtos ao Repository
        repository.adicionarProduto(cocaCola);
        repository.adicionarProduto(suco);
        repository.adicionarProduto(arroz);
        repository.adicionarProduto(feijao);
        repository.adicionarProduto(sabonete);
    }
}