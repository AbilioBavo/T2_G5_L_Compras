public class Cliente {
    private String tipo;
    private double desconto;

    public Cliente(String tipo, double desconto) {
        this.tipo = tipo;
        this.desconto = desconto;
    }

    public double aplicarDesconto(double total) {
        return total - (total * desconto / 100);
    }

    public static Cliente criarCliente(int categoriaCliente) {
        switch (categoriaCliente) {
            case 2:
                return new Cliente("Premium", 25);
            case 3:
                return new Cliente("Socio", 50);
            default:
                return new Cliente("Normal", 0);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public double getDesconto() {
        return desconto;
    }
}































