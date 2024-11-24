import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banc banc = new Banc();
        Scanner scanner = new Scanner(System.in);
        String arxiuMoviments = "moviments_banc.txt"; // Nom del fitxer

        while (true) {
            System.out.println("\nBenvingut al banc DBG !");
            System.out.println("\n\t1. Afegir client");
            System.out.println("\t2. Crear compte bancari");
            System.out.println("\t3. Realitzar dipòsit");
            System.out.println("\t4. Realitzar retirada");
            System.out.println("\t5. Realitzar transferència");
            System.out.println("\t6. Consultar saldo i historial");
            System.out.println("\t7. Sortir");

            int opcio = -1;
            while (opcio < 1 || opcio > 7) {
                System.out.print("\n\tTria una opció: ");
                try {
                    opcio = scanner.nextInt();
                    scanner.nextLine();
                    if (opcio < 1 || opcio > 7) {
                        System.out.println("\nOpció incorrecta, torna a intentar-ho.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("\nIntrodueix un número entre 1 i 7.");
                    scanner.nextLine();
                }
            }

            switch (opcio) {
                case 1:
                    System.out.print("\nIntrodueix el nom del client: ");
                    String nomClient = scanner.nextLine();

                    System.out.print("\nIntrodueix el DNI del client: ");
                    String dniClient = scanner.nextLine();
                    banc.afegirClient(nomClient, dniClient);
                    escriureMoviment(arxiuMoviments, "\nAfegit client: " + nomClient + " amb DNI " + dniClient);
                    break;

                case 2:
                    System.out.print("\nIntrodueix el DNI del client per crear el compte: ");
                    String dniCompte = scanner.nextLine();

                    System.out.print("\nIntrodueix el número del compte: ");
                    String numeroCompte = scanner.nextLine();

                    System.out.print("\nIntrodueix el saldo inicial: ");
                    double saldoInicial = scanner.nextDouble();
                    scanner.nextLine();

                    banc.afegirCompteBancari(dniCompte, numeroCompte, saldoInicial);
                    escriureMoviment(arxiuMoviments, "\nCompte creat per client DNI " + dniCompte + " amb número de compte " + numeroCompte + " i saldo inicial " + saldoInicial);
                    break;

                case 3:
                    System.out.print("\nIntrodueix el número del compte per dipositar: ");
                    String compteDiposit = scanner.nextLine();

                    System.out.print("\nIntrodueix la quantitat a dipositar: ");
                    double quantitatDiposit = scanner.nextDouble();
                    scanner.nextLine();

                    banc.dipositar(compteDiposit, quantitatDiposit);
                    escriureMoviment(arxiuMoviments, "\nDipòsit de " + quantitatDiposit + " a compte " + compteDiposit);
                    break;

                case 4:
                    System.out.print("\nIntrodueix el número del compte per retirar: ");
                    String compteRetirada = scanner.nextLine();

                    System.out.print("\nIntrodueix la quantitat a retirar: ");
                    double quantitatRetirada = scanner.nextDouble();
                    scanner.nextLine();

                    banc.retirar(compteRetirada, quantitatRetirada);
                    escriureMoviment(arxiuMoviments, "\nRetirada de " + quantitatRetirada + " de compte " + compteRetirada);
                    break;

                case 5:
                    System.out.print("\nIntrodueix el número del compte d'origen: ");
                    String compteOrigen = scanner.nextLine();

                    System.out.print("\nIntrodueix el número del compte de destinació: ");
                    String compteDestinacio = scanner.nextLine();

                    System.out.print("\nIntrodueix la quantitat a transferir: ");
                    double quantitatTransferencia = scanner.nextDouble();
                    scanner.nextLine();

                    banc.transferir(compteOrigen, compteDestinacio, quantitatTransferencia);
                    escriureMoviment(arxiuMoviments, "\nTransferència de " + quantitatTransferencia + " des de compte " + compteOrigen + " a compte " + compteDestinacio);
                    break;

                case 6:
                    System.out.print("\nIntrodueix el número del compte per consultar el saldo: ");
                    String compteConsulta = scanner.nextLine();

                    banc.mostrarInformacioCompte(compteConsulta);
                    break;

                case 7:
                    System.out.println("\nFins la propera!!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nOpció incorrecta, torna a intentar-ho.");
            }
        }
    }

    private static void escriureMoviment(String arxiuMoviments, String moviment) {
        try (FileWriter fileWriter = new FileWriter(arxiuMoviments, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(moviment);
        }

        catch (IOException e) {
            System.out.println("\nNo s'ha pogut escriure al fitxer: " + e.getMessage());
        }
    }
}
