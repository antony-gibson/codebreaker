import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Cipher {

    public void readFile(String fileName) {
        createFile(fileName);
        File file = new File(fileName);
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                System.out.println("THIS IS A TEST. REMOVE BEFORE SUBMISSION");
                System.out.println(line);
                System.out.println("\n");
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }

    public void writeToFile(String fileName, String content) {
        createFile(fileName);
        try (FileWriter write = new FileWriter(fileName)) {
            write.write(content);
            System.out.println("Successfully wrote to the file. THIS IS A TEST, REMOVE THIS BEFORE SUBMISSION.");
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }

    public void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }



}
