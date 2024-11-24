
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banc banc = new Banc();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\nBenvingut al simulador bancari!");
            System.out.println("1. Afegir client");
            System.out.println("2. Crear compte bancari");
            System.out.println("3. Realitzar dipòsit");
            System.out.println("4. Realitzar retirada");
            System.out.println("5. Realitzar transferència");
            System.out.println("6. Consultar saldo i historial");
            System.out.println("7. Sortir");
            System.out.print("Trieu una opció: ");

            int opcio = scanner.nextInt();
            scanner.nextLine();

            switch (opcio) {
                case 1:
                    System.out.print("Introduïu el nom del client: ");
                    String nomClient = scanner.nextLine();
                    System.out.print("Introduïu el DNI del client: ");
                    String dniClient = scanner.nextLine();
                    banc.afegirClient(nomClient, dniClient);
                    break;

                case 2:
                    System.out.print("Introduïu el DNI del client per crear el compte: ");
                    String dniCompte = scanner.nextLine();
                    System.out.print("Introduïu el número del compte: ");
                    String numeroCompte = scanner.nextLine();
                    System.out.print("Introduïu el saldo inicial: ");
                    double saldoInicial = scanner.nextDouble();
                    banc.afegirCompteBancari(dniCompte, numeroCompte, saldoInicial);
                    break;

                case 3:
                    System.out.print("Introduïu el número del compte per dipositar: ");
                    String compteDiposit = scanner.nextLine();
                    System.out.print("Introduïu la quantitat a dipositar: ");
                    double quantitatDiposit = scanner.nextDouble();
                    banc.dipositar(compteDiposit, quantitatDiposit);
                    break;

                case 4:
                    System.out.print("Introduïu el número del compte per retirar: ");
                    String compteRetirada = scanner.nextLine();
                    System.out.print("Introduïu la quantitat a retirar: ");
                    double quantitatRetirada = scanner.nextDouble();
                    banc.retirar(compteRetirada, quantitatRetirada);
                    break;

                case 5:
                    System.out.print("Introduïu el número del compte d'origen: ");
                    String compteOrigen = scanner.nextLine();
                    System.out.print("Introduïu el número del compte de destinació: ");
                    String compteDestinacio = scanner.nextLine();
                    System.out.print("Introduïu la quantitat a transferir: ");
                    double quantitatTransferencia = scanner.nextDouble();
                    banc.transferir(compteOrigen, compteDestinacio, quantitatTransferencia);
                    break;

                case 6:
                    System.out.print("Introduïu el número del compte per consultar el saldo: ");
                    String compteConsulta = scanner.nextLine();
                    banc.mostrarInformacioCompte(compteConsulta);
                    break;

                case 7:
                    System.out.println("Fins la propera!!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opció invàlida. Torna a intentar-ho.");
            }
        }
    }
}
