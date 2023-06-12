import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Zavodnik implements Comparable<Zavodnik> {
    private String name;
    private String surname;
    private String startNumber;
    private int birthYear;
    private Gender gender;
    private LocalTime startTime;
    private LocalTime finishTime;
    private int resultTime;

    public Zavodnik(String name, String surname, String startNumber, int birthYear, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.startNumber = startNumber;
        this.birthYear = birthYear;
        this.gender = gender;
        this.startTime = null;
        this.finishTime = null;
        recalculateResultTime();
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
        recalculateResultTime();
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
        recalculateResultTime();
    }

    public int getResultTime() {
        return resultTime;
    }

    private void recalculateResultTime() {
        if (this.startTime != null && this.finishTime != null)
            this.resultTime = (int) Duration.between(startTime, finishTime).toSeconds();
        else
            this.resultTime = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(startNumber);
        sb.append(" ");
        sb.append(name);
        sb.append(" ");
        sb.append(surname);
        sb.append(" ");
        sb.append(birthYear);
        sb.append(" ");
        sb.append(gender.toString());
        sb.append(" ");
        sb.append(startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        sb.append(" ");
        sb.append(finishTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        sb.append(" ");
        sb.append(resultTime);
        return sb.toString();
    }

    @Override
    public int compareTo(Zavodnik z) {
        return this.startNumber.compareTo(z.startNumber);
    }
}
