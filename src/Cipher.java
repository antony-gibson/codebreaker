import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Superclass that is extended by all three cipher subclasses, and houses file handling for each of the ciphers
 *
 * @author Antony Gibson
 * @since 11th March 2026
 */
public class Cipher {

    /**
     * reads file specified by user and outputs the contents in one string
     * @param fileName takes the name inputted by the user of the file to be used
     * @return fileContentString, which is the StringBuilder output of appending all lines of the file together
     */
    public static String readFile(String fileName) {
        File file = new File(fileName);
        StringBuilder fileContentString = new StringBuilder();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                line = line.trim().toUpperCase();
                fileContentString.append(line); //each line appended to fileContentString string
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
            System.err.println("Please try again.");
            System.exit(1);
        }
        return fileContentString.toString();
    }

    /**
     * saves to a file specified by user
     * @param contentsSaved is the contents to be saved to the file
     */
    public void saveFile(String contentsSaved){
        System.out.println("Please enter the filename to save to: ");
        String userSaveFileName = CleanText.getInput();

        writeToFile(userSaveFileName, contentsSaved);
    }

    /**
     * writes information passed into this method to a file chosen by the user
     * @param fileName takes the name inputted by the user of the file to be used
     * @param content is the information to be written to the file
     */
    public static void writeToFile(String fileName, String content) {
        createFile(fileName); //uses createFile to check if file already exists, if not then creates file with specified name
        try (FileWriter write = new FileWriter(fileName)) {
            write.write(content);
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
            System.err.println("Please try again.");
            System.exit(1);
        }
    }

    /**
     * creates a file if one with the specified name doesn't already exist
     * @param fileName takes the name inputted by the user of the file to be used
     */
    public static void createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException error) {
                System.err.println("An Error has occurred: " + error.getMessage());
            }
            System.out.println("File created: " + fileName);
        } else if (file.exists()) {
            System.out.println("Saved to File.");
        } else {
            System.err.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }
}
