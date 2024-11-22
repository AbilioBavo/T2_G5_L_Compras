public class ClienteSocio extends Cliente {
    @Override
    public double aplicarDesconto(double total) {
        double desconto = total * 0.50;
        System.out.println("Cliente Sócio: Desconto de 50% aplicado.");
        return total - desconto;
    }
}
