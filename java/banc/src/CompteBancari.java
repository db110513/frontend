
import java.util.ArrayList;
import java.util.List;

public class CompteBancari {

    private String numeroCompte;
    private double saldo;
    private List<String> historial;

    public CompteBancari(String numeroCompte, double saldoInicial) {
        this.numeroCompte = numeroCompte;
        this.saldo = saldoInicial;
        this.historial = new ArrayList<>();
    }

    public void dipositar(double quantitat) {
        saldo += quantitat;
        historial.add("\nDipòsit: " + quantitat);
    }

    public boolean retirar(double quantitat) {
        if (quantitat > saldo) {
            System.out.println("\nSaldo insuficient.");
            return false;
        }
        saldo -= quantitat;
        historial.add("\nRetirada: " + quantitat);
        return true;
    }

    public boolean transferir(CompteBancari destinatari, double quantitat) {
        if (quantitat > saldo) {
            System.out.println("\nSaldo insuficient per transferir.");
            return false;
        }
        this.retirar(quantitat);
        destinatari.dipositar(quantitat);
        historial.add("\nTransferència de " + quantitat + " a compte " + destinatari.getNumeroCompte());
        return true;
    }

    public void consultarSaldo() {
        System.out.println("\nSaldo actual: " + saldo);
    }

    public void mostrarHistorial() {
        System.out.println("\nHistorial de transaccions:");
        for (String transaccio : historial) {
            System.out.println(transaccio);
        }
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }
}
