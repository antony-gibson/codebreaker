import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Cipher {

    public void writeToFile(String fileName, String content) {
        createFile(fileName);
        try (FileWriter write = new FileWriter(fileName)) {
            write.write(content);
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }

    public void createFile(String fileName) {
        CleanText cleanText = new CleanText();
        try {
            System.out.println("Are you sure? ***This will overwrite any existing file contents.***");
            System.out.println("Press 1 to continue, press 0 to exit.");
            int userSelection = cleanText.getNumberInput();

            if (userSelection == 1) {
                File file = new File(fileName);
                if (file.createNewFile()) {
                    System.out.println("File created: " + fileName);
                } else {
                    System.out.println("File already exists: no new file created. This process is still working as intended.");
                }
            } else if (userSelection == 0) {
                System.exit(0);
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }
}
