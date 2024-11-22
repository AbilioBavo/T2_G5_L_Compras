public abstract class Produto {
    private String marca;
    private double preco;

    public Produto(String marca, double preco) {
        this.marca = marca;
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public double getPreco() {
        return preco;
    }
}
