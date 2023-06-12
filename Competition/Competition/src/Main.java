import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private Zavod race;

    public static void main(String[] args) {
        boolean endOfProgram;
        try {
            do {
                printMenu();
                int selection = loadSelection();
                endOfProgram = handleSelection(selection);
            } while (!endOfProgram);
        } catch (Exception ex) {
            System.out.println("Neocekavana chyba " + ex.getMessage());
            ex.printStackTrace();
        } catch (Error er) {
            System.out.println("Neocekavana systemova chyba " + er.getMessage());
            er.printStackTrace();
        }
        System.out.println("Koncim ...");
    }

    private static void printMenu() {
        System.out.println("Hlavni menu programu\n"+
        "1: Nacist soubor registrace\n"+
        "2: Nacist soubor startu\n"+
        "3: Nacist soubor cile\n"+
        "4: Zobrazit seznam zavodniku s daty\n"+
        "5: Zobrazeni vysledkove listiny\n"+
        "6: Ulozeni vysledkove listiny do souboru\n"+
        "0: Konec programu");

    }

    private static int loadSelection() {
        int selection;
        try {
            selection = sc.nextInt();
        } catch (InputMismatchException ex) {
            selection = -1;
        }
        sc.nextLine();
        return selection;
    }

    private static boolean handleSelection(int selection) {
        switch (selection) {
            case 0:
                return true;
            case 1:
                loadRegistrationFile();
                break;
            case 2:
                loadStartFile();
                break;
            case 3:
                loadFinishFile();
                break;
            case 4:
                showRacers();
                break;
            case 5:
                showResults();
                break;
            case 6:
                saveResults();
                break;
            default:
                System.out.println("Neplatna volba");
                break;
        }
        return false;
    }

    private static void loadRegistrationFile() {
        String res = IOService.readFile("./Competition/src/data/registrace.txt");
        System.out.println(res);
    }

    private static void loadStartFile() {
        String res = IOService.readFile("./Competition/src/data/start.txt");
        System.out.println(res);
    }

    private static void loadFinishFile() {
        String res = IOService.readFile("./Competition/src/data/cil.txt");
        System.out.println(res);
    }

    private static void showRacers() {
        Zavod race = new Zavod();
        for (Zavodnik zavodnik : race.getRacers()) {
            System.out.println(zavodnik);
        }
    }

    private static void showResults() {
        Zavod race = new Zavod();
        String[] results = race.getResults();
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%s", results[i]);
        }
    }

    private static void saveResults() {
        Zavod race = new Zavod();
        String[] results = race.getResults();
        IOService.writeFile("./Competition/src/data/vysledkovaListina.txt", "", false);
        for (int i = 0; i < results.length; i++) {
            IOService.writeFile("./Competition/src/data/vysledkovaListina.txt", results[i], true);
        }
    }

}
