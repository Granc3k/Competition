import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

public class Zavod {
    private Zavodnik[] racers;

    public Zavod() {
        racers = loadRacers();
    }

    public Zavod(Zavodnik[] racers) {
        this.racers = racers;
    }

    public Zavodnik[] getRacers() {
        return racers;
    }

    private Zavodnik[] loadRacers() {
        racers = new Zavodnik[0];
        String registrationFile = IOService.readFile("./Competition/src/data/registrace.txt");
        String[] registrationLines = registrationFile.split("\n");
        String startFile = IOService.readFile("./Competition/src/data/start.txt");
        String[] startLines = startFile.split("\n");
        String finishFile = IOService.readFile("./Competition/src/data/cil.txt");
        String[] finishLines = finishFile.split("\n");
        racers = new Zavodnik[registrationLines.length];

        for (int i = 0; i < registrationLines.length; i++) {
            racers[i] = parseRegistration(registrationLines[i]);
        }

        for (int i = 0; i < startLines.length; i++) {
            String[] startAttrs = startLines[i].split(",");
            String startNumber = startAttrs[0];
            Zavodnik racer = findRacerByStartNumber(startNumber);
            racer.setStartTime(LocalTime.parse(startAttrs[1], DateTimeFormatter.ofPattern("HH:mm:ss")));
        }

        for (int i = 0; i < finishLines.length; i++) {
            String[] finishAttrs = finishLines[i].split(",");
            String startNumber = finishAttrs[0];
            Zavodnik racer = findRacerByStartNumber(startNumber);
            racer.setFinishTime(LocalTime.parse(finishAttrs[1], DateTimeFormatter.ofPattern("HH:mm:ss")));
        }

        return racers;
    }

    private Zavodnik parseRegistration(String line) {
        String[] racerAttrs = line.split(",");
        String name = racerAttrs[0];
        String surname = racerAttrs[1];
        String startNumber = racerAttrs[2];
        int birthYear = Integer.parseInt(racerAttrs[3]);
        Gender gender = Gender.valueOf(racerAttrs[4]);
        Zavodnik zavodnik = new Zavodnik(name, surname, startNumber, birthYear, gender);
        return zavodnik;
    }

    private Zavodnik findRacerByStartNumber(String startNumber) {
        for (Zavodnik zavodnik : racers) {
            if (zavodnik.getStartNumber().equals(startNumber))
                return zavodnik;
        }
        return null;
    }

    public String[] getResults() {
        Zavodnik[] sortedRacers = sortRacers();
        String[] results = new String[sortedRacers.length];

        for (int i = 0; i < sortedRacers.length; i++) {
            results[i] = String.format("%d. %s s casem %o s%n", i + 1, sortedRacers[i].getFullName(),
                    sortedRacers[i].getResultTime());
        }

        return results;
    }

    private Zavodnik[] sortRacers() {
        Zavodnik[] sortedRacers = racers.clone();
        Collections.sort(Arrays.asList(sortedRacers));
        return sortedRacers;
    }
}
