import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaDeCompra {

    public void main() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            Produto[] produtos = inicializarProdutos();
            Carrinho carrinho = new Carrinho();

            int categoriaCliente = 0;
            boolean entradaValida = false;

            try {
                System.out.println("Selecione a categoria de cliente:");
                System.out.println("1. Normal");
                System.out.println("2. Premium");
                System.out.println("3. Socio");
                System.out.print("Digite o numero correspondente: ");
                categoriaCliente = scanner.nextInt();

                // Validação para garantir que a categoria está entre 1 e 3
                if (categoriaCliente < 1 || categoriaCliente > 3) {
                    throw new InputMismatchException("Categoria invalida.");
                } else {
                    entradaValida = true; // Entrada válida se não houver exceções
                }

            } catch (InputMismatchException e) {
                
				System.out.println("Entrada invalida! Por favor, insira um numero entre 1 e 3.");
                scanner.next(); // Limpa o buffer do scanner para evitar loop infinito
            } finally {
                System.out.println("Processamento da categoria de cliente concluido.");
            }

            if (entradaValida) {
                Cliente cliente = Cliente.criarCliente(categoriaCliente);

                boolean continuarAdicionandoProdutos;
                do {
                    mostrarProdutos(produtos);
                    int escolha = escolherProduto(scanner, produtos.length);
                    if (escolha >= 1 && escolha <= produtos.length) {
                        carrinho.adicionarProduto(produtos[escolha - 1]);
                    } else {
                        System.out.println("Escolha invalida.");
                    }
                    continuarAdicionandoProdutos = continuarComprando(scanner);

                } while (continuarAdicionandoProdutos);

                double total = carrinho.calcularTotal();
                total = cliente.aplicarDesconto(total);
                System.out.println("Total a pagar (com desconto): " + total + "Mt");

                finalizarCompra(scanner);
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // Fechar o scanner no bloco finally para garantir que sempre seja fechado
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private Produto[] inicializarProdutos() {
        return new Produto[] {
            new Produto("Samsung", 1000.00),
            new Produto("Apple", 2000.00),
            new Produto("Xiaomi", 800.00),
            new Produto("Motorola", 600.00)
        };
    }

    private void mostrarProdutos(Produto[] produtos) {
        System.out.println("Marcas disponiveis:");
        for (int i = 0; i < produtos.length; i++) {
            System.out.println((i + 1) + ". " + produtos[i].getMarca() + " - " + produtos[i].getPreco() + "Mt");
        }
    }

    private int escolherProduto(Scanner scanner, int numeroDeProdutos) {
        System.out.print("Escolha o numero do produto para adicionar ao carrinho: ");
        return scanner.nextInt();
    }

    private  boolean continuarComprando(Scanner scanner) {
        System.out.print("Deseja adicionar outro produto ao carrinho? (s/n): ");
        char resposta = scanner.next().toLowerCase().charAt(0);
        return resposta == 's';
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
                System.out.println("Opcao invalida. Escolha 's' para sim ou 'n' para nao.");
            }
        }
    }
}
