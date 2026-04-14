import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Cipher {

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
                System.out.println("File already exists: no new file created. This process is still working as intended.");
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }
}
