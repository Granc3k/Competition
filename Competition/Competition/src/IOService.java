import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class IOService {
    public static String readFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(
                new FileReader(fileName, StandardCharsets.UTF_8))) {
            String stringCurrentLine;
            while ((stringCurrentLine = bfr.readLine()) != null) {
                sb.append(stringCurrentLine);
                sb.append("\n");
            }
        } catch (IOException ex) {
            System.out.println("Chyba pri cteni souboru " + ex.getMessage());
        }
        return sb.toString();
    }

    public static void writeFile(String fileName, String content, boolean append) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8, append))) {
            bfw.write(content);
        } catch (IOException ex) {
            System.out.println("Chyba pri zapisu do souboru " + ex.getMessage());
        }
    }
}
