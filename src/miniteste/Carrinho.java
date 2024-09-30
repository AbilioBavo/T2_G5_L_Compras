import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Produto> produtos;

    public Carrinho() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        System.out.println(produto.getMarca() + " adicionado ao carrinho.");
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public void mostrarCarrinho() {
        System.out.println("Produtos no carrinho:");
        for (Produto produto : produtos) {
            System.out.println(produto.getMarca() + " - " + produto.getPreco() + "Mt");
        }
    }
}









































