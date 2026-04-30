import java.util.Scanner;
import java.io.File;

/**
 * Utility class that sanitises inputs, both from a file and from console
 *
 * @author Antony Gibson
 * @since 11th March 2026
 */
public class CleanText {
    private static String cleanFileOutput;
    private static Scanner input = new Scanner(System.in); //takes user's input

    /**
     * gets contents of user-specified file, checks file exists and can be read, then converts to prepared plaintext
     * @return the prepared plain text of the file contents
     */
    public static String getFileContents(){
        System.out.println("Please enter the file name: ");
        String userFileNameInput = input.nextLine();
        fileCheck(userFileNameInput);
        String fileOutput = Cipher.readFile(userFileNameInput);

        //Checks file is not empty
        if (fileOutput.isEmpty()) {
            System.err.println("This file is empty.");
            System.exit(1);
        }

        cleanFileOutput = fileStringInput(fileOutput);
        return cleanFileOutput;
    }

    /**
     * @param userFileNameInput takes user input for the name of the file they want to use
     * checks chosen file is a text file, exists and can be read by the program
     */
    public static void fileCheck(String userFileNameInput) {
        File file = new File(userFileNameInput);

        if (!userFileNameInput.endsWith(".txt") && !file.exists() && !file.canRead()) {
            System.err.println("File error. Program could not access file, please try again.");
            System.exit(1);
        }
    }

    /**
     * @return cleanFileOutput to Main to be used in the cipher decryption calls
     */
    public static String fileOutput(){
        return cleanFileOutput;
    }

    /**
     * takes file output, converts to prepared plaintext
     * @param fileInput is the plain text output of a file
     * @return onlyText, the prepared plaintext of the file
     */
    public static String fileStringInput(String fileInput){
        String stripped = fileInput.replaceAll("\\s", "");
        String onlyText = stripped.replaceAll("[^A-Z]", "");
        return onlyText;
    }

    /**
     * takes file output, converts to just integer values
     * @param fileInput is plaintext output of a file
     * @return onlyNumbers, only the integer values within a file
     */
    public static int fileNumberInput(String fileInput){
        String stripped = fileInput.replaceAll("\\s", "");
        String onlyNumbers = stripped.replaceAll("[^0-9]", "");

        //Checks that the file actually contained any numbers
        if (onlyNumbers.isEmpty()) {
            System.err.println("No number specified.");
            return -1; //to prevent a null variable being returned
        }
        return Integer.parseInt(onlyNumbers);
    }

    /**
     * gets user's typed input into console
     * @return userInput, the output from the scanner that took the user's typed input
     */
    public static String getInput(){
        String userInput = input.nextLine();
        return userInput;
    }

    /**
     * gets user input and trims whitespace from each end, and makes caps
     * @return userInput, user's typed console input with the trim and caps applied
     */
    public static String getKeyInput() {
        String userInput = getInput();
        userInput = userInput.trim().toUpperCase();
        return userInput;
    }

    /**
     * removes whitespace from number inputs into the code. Calls NumberFormatException error if it goes wrong.
     * @return userNumberInput, integers but with whitespace removed
     */
    public static int getNumberInput(){
        int userNumberInput = -1; //to prevent a null variable being returned
        String newInput = getKeyInput();

        try {
            userNumberInput = Integer.parseInt(newInput.replaceAll("[^0-9]", ""));
            return userNumberInput;
        } catch (NumberFormatException error) {
            System.err.println("An error has occurred. " + error.getMessage());
        }
        return userNumberInput;
    }
}
