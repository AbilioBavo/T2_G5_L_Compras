import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> produtos;

    public Carrinho() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println(produto.getMarca() + " foi adicionado ao carrinho.");
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public void mostrarProdutosNoCarrinho() {
        if (produtos.isEmpty()) {
            System.out.println("O carrinho está vazio.");
        } else {
            System.out.println("Produtos no carrinho:");
            for (Produto produto : produtos) {
                System.out.println("- " + produto.getMarca() + " - " + produto.getPreco() + "Mt");
            }
        }
    }
}
