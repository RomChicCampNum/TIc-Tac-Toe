import java.util.InputMismatchException;
import java.util.Scanner;


public class InteractionUtilisateur {
    private Scanner scanner;

    public InteractionUtilisateur() {
        scanner = new Scanner(System.in);
    }

    public int askForInt(String question, int min, int max) {
        int value = -1;
        while (true) {
            try {
                System.out.println(question + " ");
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    break;
                }
                System.out.println("Veuillez entrer un nombre entre " + min + " et " + max + ".");
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                scanner.next(); // Ignore l'entrée invalide
            }
        }
        return value;
    }

    public Player[] choosePlayers() {
        System.out.println("Choisissez la configuration des joueurs :");
        System.out.println("1. Humain contre Humain");
        System.out.println("2. Humain contre IA");
        System.out.println("3. IA contre IA");

        int choice = askForInt("Votre choix :", 1, 3);

        Player playerX, playerO;

        switch (choice) {
            case 1:
                playerX = new HumanPlayer(" X ", this);
                playerO = new HumanPlayer(" O ", this);
                break;
            case 2:
                playerX = new HumanPlayer(" X ", this);
                playerO = new ArtificialPlayer(" O ");
                break;
            case 3:
                playerX = new ArtificialPlayer(" X ");
                playerO = new ArtificialPlayer(" O ");
                break;
            default:
                System.out.println("Choix invalide, par défaut Humain contre IA.");
                playerX = new HumanPlayer(" X ", this);
                playerO = new ArtificialPlayer(" O ");
        }

        return new Player[]{playerX, playerO};
    }

    public void close() {
        scanner.close();
    }
}
