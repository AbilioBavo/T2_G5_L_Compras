package miniteste;


/*
Nomes:
Abilio Viano Bavo
Arlindo Lazaro Cau Jr
Jofrey Seuane
Nataniel Andrade Uqueio
Tomas Rafael Jr
*/

import java.util.ArrayList;
import java.util.Scanner;

public class Compras {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Produto[] produtos = inicializarProdutos();
        ArrayList<Produto> carrinho = new ArrayList<>(); 
        double total = 0;

        int categoriaCliente = selecionarCategoria(scanner); 
        double desconto = calcularDesconto(categoriaCliente); 

        boolean continuarAdicionandoProdutos = true;
        do {
            mostrarProdutos(produtos); 
            int escolha = escolherProduto(scanner); 
            if (escolha >= 1 && escolha <= produtos.length) {
                adicionarAoCarrinho(carrinho, produtos[escolha - 1]); 
                total += produtos[escolha - 1].preco; // Atualiza o total da compra
                System.out.println(produtos[escolha - 1].marca + " adicionado ao carrinho.");
            } else {
                System.out.println("Escolha invalida.");
            }

            continuarAdicionandoProdutos = continuarComprando(scanner); // Pergunta se o usuário deseja continuar comprando

        } while (continuarAdicionandoProdutos);

        total = aplicarDesconto(total, desconto); // Aplica o desconto ao total da compra
        System.out.println("Total a pagar: Mt" + total);

        finalizarCompra(scanner); // Finaliza a compra

        scanner.close();
    }

    // Inicializa a lista de produtos com marcas e preços
    private static Produto[] inicializarProdutos() {
        return new Produto[] {
            new Produto("Samsung", 1000.00),
            new Produto("Apple", 2000.00),
            new Produto("Xiaomi", 800.00),
            new Produto("Motorola", 600.00)
        };
    }

    // Solicita ao usuário a categoria do cliente e retorna a escolha
    private static int selecionarCategoria(Scanner scanner) {
        System.out.println("Selecione a categoria de cliente:");
        System.out.println("1. Normal");
        System.out.println("2. Premium");
        System.out.println("3. Socio");
        System.out.print("Digite o numero correspondente: ");
        return scanner.nextInt();
    }

    // Calcula o desconto com base na categoria do cliente
    private static double calcularDesconto(int categoriaCliente) {
        switch (categoriaCliente) {
            case 2: return 25;
            case 3: return 50;
            default: return 0;
        }
    }

    // Exibe a lista de produtos disponíveis para o usuário
    private static void mostrarProdutos(Produto[] produtos) {
        System.out.println("Marcas disponiveis:");
        for (int i = 0; i < produtos.length; i++) {
            System.out.println((i + 1) + ". " + produtos[i].marca + " - Mt" + produtos[i].preco);
        }
    }

    // Solicita ao usuário a escolha do produto para adicionar ao carrinho
    private static int escolherProduto(Scanner scanner) {
        System.out.print("Escolha o numero do produto para adicionar ao carrinho: ");
        return scanner.nextInt();
    }

    // Adiciona o produto selecionado ao carrinho
    private static void adicionarAoCarrinho(ArrayList<Produto> carrinho, Produto produto) {
        carrinho.add(produto);
    }

    // Pergunta ao usuário se ele deseja continuar comprando
    private static boolean continuarComprando(Scanner scanner) {
        System.out.print("Deseja adicionar outro produto ao carrinho? (s/n): ");
        char resposta = scanner.next().toLowerCase().charAt(0);
        return resposta == 's';
    }

    // Aplica o desconto ao total da compra
    private static double aplicarDesconto(double total, double desconto) {
        if (desconto > 0) {
            total -= total * desconto / 100;
            System.out.println("Desconto de " + desconto + "% aplicado.");
        } else {
            System.out.println("Nenhum desconto aplicado.");
        }
        return total;
    }

    // Solicita ao usuário se deseja finalizar a compra e processa a resposta
    private static void finalizarCompra(Scanner scanner) {
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
