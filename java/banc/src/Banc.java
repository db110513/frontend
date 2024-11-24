
import java.util.HashMap;
import java.util.Map;

public class Banc {
    private Map<String, Client> clients;
    private Map<String, CompteBancari> comptes;

    public Banc() {
        this.clients = new HashMap<>();
        this.comptes = new HashMap<>();
    }

    public void afegirClient(String nom, String dni) {
        Client client = new Client(nom, dni);
        clients.put(dni, client);
        System.out.println("Client afegit: " + nom);
    }

    public void afegirCompteBancari(String dni, String numeroCompte, double saldoInicial) {
        Client client = clients.get(dni);
        if (client != null) {
            CompteBancari compte = new CompteBancari(numeroCompte, saldoInicial);
            comptes.put(numeroCompte, compte);
            System.out.println("Compte creat per al client " + client.getNom());
        }
        else {
            System.out.println("Client no trobat.");
        }
    }

    public void mostrarInformacioClient(String dni) {
        Client client = clients.get(dni);
        if (client != null) {
            System.out.println(client);
        }
        else {
            System.out.println("Client no trobat.");
        }
    }

    public void mostrarInformacioCompte(String numeroCompte) {
        CompteBancari compte = comptes.get(numeroCompte);
        if (compte != null) {
            compte.consultarSaldo();
            compte.mostrarHistorial();
        } else {
            System.out.println("Compte no trobat.");
        }
    }



    public void dipositar(String numeroCompte, double quantitat) {
        CompteBancari compte = comptes.get(numeroCompte);
        if (compte != null) {
            compte.dipositar(quantitat);
        }
    }

    public void retirar(String numeroCompte, double quantitat) {
        CompteBancari compte = comptes.get(numeroCompte);
        if (compte != null) {
            compte.retirar(quantitat);
        }
    }

    public void transferir(String numeroCompteOrige, String numeroCompteDestinatari, double quantitat) {
        CompteBancari compteOrige = comptes.get(numeroCompteOrige);
        CompteBancari compteDestinatari = comptes.get(numeroCompteDestinatari);
        if (compteOrige != null && compteDestinatari != null) {
            compteOrige.transferir(compteDestinatari, quantitat);
        }
    }
}
