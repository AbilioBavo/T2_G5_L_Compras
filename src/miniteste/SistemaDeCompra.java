import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaDeCompra {

    public void main() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            Produto[] produtos = inicializarProdutos();
            Carrinho carrinho = new Carrinho();

            boolean continuarAdicionandoProdutos;
            do {
                mostrarCategorias();
                int categoriaEscolhida = escolherCategoria(scanner);

                Produto[] produtosFiltrados = filtrarProdutosPorCategoria(produtos, categoriaEscolhida);
                if (produtosFiltrados != null) {
                    mostrarProdutos(produtosFiltrados);
                    int escolhaProduto = escolherProduto(scanner, produtosFiltrados.length);
                    if (escolhaProduto >= 1 && escolhaProduto <= produtosFiltrados.length) {
                        carrinho.adicionarProduto(produtosFiltrados[escolhaProduto - 1]);
                    } else {
                        System.out.println("Escolha inválida.");
                    }
                } else {
                    System.out.println("Categoria inválida.");
                }

                continuarAdicionandoProdutos = continuarComprando(scanner);
            } while (continuarAdicionandoProdutos);

            // Pergunta o tipo de cliente ao final
            int categoriaCliente = selecionarCategoriaCliente(scanner);
            if (categoriaCliente != -1) {
                Cliente cliente = Cliente.criarCliente(categoriaCliente);

                double total = carrinho.calcularTotal();
                total = cliente.aplicarDesconto(total);
                System.out.println("Resumo da compra:");
                carrinho.mostrarProdutosNoCarrinho();
                System.out.println("Total a pagar (com desconto): " + total + "Mt");

                finalizarCompra(scanner);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private Produto[] inicializarProdutos() {
        return new Produto[] {
                new Smartwatch("Apple Watch", 1500.00),
                new Smartwatch("Samsung Galaxy Watch", 1200.00),
                new TV("LG OLED", 5000.00),
                new TV("Samsung QLED", 4500.00),
                new Smartphone("iPhone 14", 2000.00),
                new Smartphone("Samsung Galaxy S23", 1800.00)
        };
    }

    private void mostrarCategorias() {
        System.out.println("Categorias disponíveis:");
        System.out.println("1. Smartwatch");
        System.out.println("2. TV");
        System.out.println("3. Smartphone");
    }

    private Produto[] filtrarProdutosPorCategoria(Produto[] produtos, int categoriaEscolhida) {
        switch (categoriaEscolhida) {
            case 1:
                return new Produto[] {
                        new Smartwatch("Apple Watch", 1500.00),
                        new Smartwatch("Samsung Galaxy Watch", 1200.00)
                };
            case 2:
                return new Produto[] {
                        new TV("LG OLED", 5000.00),
                        new TV("Samsung QLED", 4500.00)
                };
            case 3:
                return new Produto[] {
                        new Smartphone("iPhone 14", 2000.00),
                        new Smartphone("Samsung Galaxy S23", 1800.00)
                };
            default:
                return null;
        }
    }

    private int escolherCategoria(Scanner scanner) {
        System.out.print("Escolha o número da categoria: ");
        return scanner.nextInt();
    }

    private void mostrarProdutos(Produto[] produtos) {
        System.out.println("Produtos disponíveis:");
        for (int i = 0; i < produtos.length; i++) {
            System.out.println((i + 1) + ". " + produtos[i].getMarca() + " - " + produtos[i].getPreco() + "Mt");
        }
    }

    private int escolherProduto(Scanner scanner, int numeroDeProdutos) {
        System.out.print("Escolha o número do produto para adicionar ao carrinho: ");
        return scanner.nextInt();
    }

    private boolean continuarComprando(Scanner scanner) {
        System.out.print("Deseja adicionar outro produto ao carrinho? (s/n): ");
        char resposta = scanner.next().toLowerCase().charAt(0);
        return resposta == 's';
    }

    private int selecionarCategoriaCliente(Scanner scanner) {
        System.out.println("Selecione a categoria de cliente:");
        System.out.println("1. Normal");
        System.out.println("2. Premium");
        System.out.println("3. Sócio");
        System.out.print("Digite o número correspondente: ");
        try {
            int categoriaCliente = scanner.nextInt();
            if (categoriaCliente >= 1 && categoriaCliente <= 3) {
                return categoriaCliente;
            } else {
                System.out.println("Categoria inválida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida.");
            scanner.next();
        }
        return -1;
    }

    private void finalizarCompra(Scanner scanner) {
        boolean compraFinalizada = false;
        while (!compraFinalizada) {
            System.out.print("Deseja finalizar a compra? (s/n): ");
            char finalizarCompra = scanner.next().toLowerCase().charAt(0);
            if (finalizarCompra == 's') {
                System.out.println("Compra finalizada com sucesso!");
                compraFinalizada = true;
            } else if (finalizarCompra == 'n') {
                System.out.println("Compra cancelada.");
                compraFinalizada = true;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }
}
