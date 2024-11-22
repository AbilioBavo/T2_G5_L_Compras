public abstract class Cliente {
    public abstract double aplicarDesconto(double total);

    public static Cliente criarCliente(int categoria) {
        switch (categoria) {
            case 1:
                return new ClienteNormal();
            case 2:
                return new ClientePremium();
            case 3:
                return new ClienteSocio();
            default:
                return null;
        }
    }
}
