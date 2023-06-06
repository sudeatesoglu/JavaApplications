import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SubstitutionCipher cipher = new SubstitutionCipher(3);
        String plainText = readPlainTextFromFile("plainText.txt");

        if (plainText != null) {
            String encodedText = cipher.encode(plainText);
            writeEncodedTextToFile(encodedText, "encodedText.txt");
        }
    }

    public static String readPlainTextFromFile(String path) {
        StringBuilder plainText = new StringBuilder();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                plainText.append(scan.nextLine()).append(" ");
            }
            scan.close();
            return plainText.toString();
        } catch (FileNotFoundException e) {
            System.err.println("Invalid! File is not found: " + e.getMessage());
        }
        return null;
    }

    public static void writeEncodedTextToFile(String encodedText, String path) {
        File file = new File(path);
        try {
            // FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(file);
            pw.print(encodedText);
            pw.flush();
            pw.close();
            // fos.close();
            System.out.println("Encoded text has been written to file: " + path);
        } catch (FileNotFoundException e) {
            System.err.println("Invalid! File is not found: " + e.getMessage());
        }
    }
}
