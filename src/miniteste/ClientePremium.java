public class ClientePremium extends Cliente {
    @Override
    public double aplicarDesconto(double total) {
        double desconto = total * 0.25;
        System.out.println("Cliente Premium: Desconto de 25% aplicado.");
        return total - desconto;
    }
}
