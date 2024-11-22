public class ClienteNormal extends Cliente {
    @Override
    public double aplicarDesconto(double total) {
        System.out.println("Cliente Normal: Sem desconto aplicado.");
        return total;
    }
}
