package br.ufes.sistema.produtomvp.seeder;

import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Pedido;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.model.Usuario;
import br.ufes.sistema.produtomvp.repository.ICategoriaRepository;
import br.ufes.sistema.produtomvp.repository.IClienteRepository;
import br.ufes.sistema.produtomvp.repository.IHistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.IPedidoRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;
import br.ufes.sistema.produtomvp.repository.IUsuarioRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seeder {

    public static void executar(ICategoriaRepository categoriaRepository,
                                IProdutoRepository produtoRepository,
                                IHistoricoPrecoRepository historicoPrecoRepository,
                                IClienteRepository clienteRepository,
                                IUsuarioRepository usuarioRepository,
                                IPedidoRepository pedidoRepository) {

        // 1. Categorias iniciais (Documento de Referência 12.1)
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

        // 2. Produtos iniciais (Documento de Referência 12.2)
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

        // 3. Estado inicial do histórico (10 dias atrás)
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

        // 4. Clientes iniciais (Solicitação de Mudança #1 - Figura 3)
        Cliente c1 = new Cliente(clienteRepository.proximoId(), "Ana Souza", "Rua das Flores, 123", "Centro", "Alegre", "ES", "PRATA", 245.80);
        Cliente c2 = new Cliente(clienteRepository.proximoId(), "Bruno Lima", "Rua Central, 45", "Vila do Sul", "Alegre", "ES", "PRATA", 120.00);
        Cliente c3 = new Cliente(clienteRepository.proximoId(), "Carlos Mendes", "Av. Principal, 200", "Centro", "Guaçuí", "ES", "PRATA", 450.30);
        Cliente c4 = new Cliente(clienteRepository.proximoId(), "Daniela Rocha", "Rua Nova, 12", "Nova Alegre", "Alegre", "ES", "PRATA", 89.90);
        Cliente c5 = new Cliente(clienteRepository.proximoId(), "Eduardo Silva", "Rua São José, 78", "São Miguel", "Guaçuí", "ES", "PRATA", 310.20);
        Cliente c6 = new Cliente(clienteRepository.proximoId(), "Fernanda Costa", "Rua do Comércio, 55", "Centro", "Alegre", "ES", "PRATA", 560.75);
        Cliente c7 = new Cliente(clienteRepository.proximoId(), "Gabriel Oliveira", "Travessa Café, 10", "Vila do Café", "Alegre", "ES", "PRATA", 75.40);
        Cliente c8 = new Cliente(clienteRepository.proximoId(), "Juliana Santos", "Rua da Paz, 99", "Centro", "Guaçuí", "ES", "PRATA", 220.00);
        Cliente c9 = new Cliente(clienteRepository.proximoId(), "Marcos Ribeiro", "Rua da Colina, 33", "Cachoeirinha", "Alegre", "ES", "PRATA", 180.50);
        Cliente c10 = new Cliente(clienteRepository.proximoId(), "Patrícia Almeida", "Av. Beira Rio, 150", "Bela Vista", "Guaçuí", "ES", "PRATA", 99.00);

        // Clientes adicionais para suportar todos os usuários da Figura 4
        Cliente c11 = new Cliente(clienteRepository.proximoId(), "Carla Mendes", "Rua das Palmeiras, 80", "Centro", "Alegre", "ES", "PRATA", 0.0);
        Cliente c12 = new Cliente(clienteRepository.proximoId(), "Mariana Costa", "Rua Vitória, 15", "Centro", "Alegre", "ES", "PRATA", 0.0);
        Cliente c13 = new Cliente(clienteRepository.proximoId(), "Paulo Victor", "Rua Minas Gerais, 40", "Centro", "Guaçuí", "ES", "PRATA", 0.0);

        clienteRepository.adicionar(c1);
        clienteRepository.adicionar(c2);
        clienteRepository.adicionar(c3);
        clienteRepository.adicionar(c4);
        clienteRepository.adicionar(c5);
        clienteRepository.adicionar(c6);
        clienteRepository.adicionar(c7);
        clienteRepository.adicionar(c8);
        clienteRepository.adicionar(c9);
        clienteRepository.adicionar(c10);
        clienteRepository.adicionar(c11);
        clienteRepository.adicionar(c12);
        clienteRepository.adicionar(c13);

        // 5. Usuários iniciais (Solicitação de Mudança #1 - Figura 4)
        Usuario uAdmin = new Usuario(usuarioRepository.proximoId(), "Administrador do Sistema", "admin@pocdelivery.com", "admin", "admin123", "Administrador", "Habilitado", null);
        Usuario uFernanda = new Usuario(usuarioRepository.proximoId(), "Fernanda Alves", "fernanda@pocdelivery.com", "fernanda", "123456", "Atendente", "Habilitado", null);
        Usuario uRicardo = new Usuario(usuarioRepository.proximoId(), "Ricardo Nascimento", "ricardo@pocdelivery.com", "ricardo", "123456", "Atendente", "Habilitado", null);
        Usuario uAna = new Usuario(usuarioRepository.proximoId(), "Ana Souza", "ana@cliente.com", "anasouza", "123456", "Cliente", "Habilitado", c1);
        Usuario uBruno = new Usuario(usuarioRepository.proximoId(), "Bruno Lima", "bruno@cliente.com", "brunolima", "123456", "Cliente", "Habilitado", c2);
        Usuario uCarla = new Usuario(usuarioRepository.proximoId(), "Carla Mendes", "carla@cliente.com", "carlamendes", "123456", "Cliente", "Habilitado", c11);
        Usuario uDiego = new Usuario(usuarioRepository.proximoId(), "Diego Rocha", "diego@pocdelivery.com", "diego", "123456", "Atendente", "Habilitado", null);
        Usuario uMariana = new Usuario(usuarioRepository.proximoId(), "Mariana Costa", "mariana@cliente.com", "marianac", "123456", "Cliente", "Habilitado", c12);
        Usuario uPaulo = new Usuario(usuarioRepository.proximoId(), "Paulo Victor", "paulo@cliente.com", "paulovictor", "123456", "Cliente", "Habilitado", c13);
        Usuario uJuliana = new Usuario(usuarioRepository.proximoId(), "Juliana Pereira", "juliana@pocdelivery.com", "juliana", "123456", "Atendente", "Habilitado", null);

        usuarioRepository.adicionar(uAdmin);
        usuarioRepository.adicionar(uFernanda);
        usuarioRepository.adicionar(uRicardo);
        usuarioRepository.adicionar(uAna);
        usuarioRepository.adicionar(uBruno);
        usuarioRepository.adicionar(uCarla);
        usuarioRepository.adicionar(uDiego);
        usuarioRepository.adicionar(uMariana);
        usuarioRepository.adicionar(uPaulo);
        usuarioRepository.adicionar(uJuliana);

        // 6. Pedidos para o dashboard (Solicitação de Mudança #1 - Figura 2)
        if (pedidoRepository != null) {
            pedidoRepository.adicionar(new Pedido(2001, "15/04/2025 14:32", "Ana Souza", "Em preparo", "Pix", 45.90));
            pedidoRepository.adicionar(new Pedido(2002, "15/04/2025 13:18", "Bruno Lima", "Saiu para entrega", "Cartão de Crédito", 62.50));
            pedidoRepository.adicionar(new Pedido(2003, "15/04/2025 12:45", "Carla Mendes", "Entregue", "Pix", 38.70));
            pedidoRepository.adicionar(new Pedido(2004, "14/04/2025 20:17", "Daniel Rocha", "Em preparo", "Dinheiro", 79.90));
            pedidoRepository.adicionar(new Pedido(2005, "14/04/2025 19:03", "Eduardo Silva", "Cancelado", "Cartão de Débito", 55.20));
            pedidoRepository.adicionar(new Pedido(2006, "14/04/2025 18:27", "Fernanda Costa", "Entregue", "Pix", 88.40));
            pedidoRepository.adicionar(new Pedido(2007, "13/04/2025 21:15", "Gabriel Oliveira", "Saiu para entrega", "Cartão de Crédito", 67.80));
            pedidoRepository.adicionar(new Pedido(2008, "13/04/2025 20:01", "Juliana Santos", "Entregue", "Dinheiro", 49.90));
            pedidoRepository.adicionar(new Pedido(2009, "13/04/2025 19:33", "Marcos Ribeiro", "Em preparo", "Pix", 73.60));
            pedidoRepository.adicionar(new Pedido(2010, "13/04/2025 18:20", "Patrícia Almeida", "Entregue", "Cartão de Crédito", 59.30));
        }
    }
}
