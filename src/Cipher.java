import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Cipher {


    public static String readFile(String fileName) {
        File file = new File(fileName); //new File object
        StringBuilder fileContentString = new StringBuilder();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine(); //contents of current line stored in line variable
                line = line.trim().toUpperCase(); //line contents are made uppercase and spaces are stripped
                fileContentString.append(line); //line contents are appended to file content string
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
            System.err.println("Please try again.");
            System.exit(1);
        }
        return fileContentString.toString();
    }

    public static void writeToFile(String fileName, String content) {
        createFile(fileName); //creates a new file
        try (FileWriter write = new FileWriter(fileName)) { //new FileWriter object created
            write.write(content); //FileWriter object writes content (passed into method) to the file
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
            System.err.println("Please try again.");
            System.exit(1);
        }
    }

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName); //File object initialised
            if (file.createNewFile()) { //if there isn't a file with the given name that already exists, one is created
                System.out.println("File created: " + fileName);
            } else {
                System.out.println("File already exists: no new file created. This process is still working as intended.");
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
            System.err.println("Please try again.");
            System.exit(1);
        }
    }
}
