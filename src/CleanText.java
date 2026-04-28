import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CleanText {
    private static String userInput;
    private static int userNumberInput;
    private static String cleanFileOutput;
    private static Scanner input = new Scanner(System.in); //takes user's input

    public static String getFileContents(){
        System.out.println("Please enter the file name: ");
        String userFileNameInput = input.nextLine();
        fileCheck(userFileNameInput);
        String fileOutput = Cipher.readFile(userFileNameInput);
        cleanFileOutput = fileStringInput(fileOutput);
        return cleanFileOutput;
    }

    public static void fileCheck(String userFileNameInput) {
        File file = new File(userFileNameInput);

        while (!userFileNameInput.endsWith(".txt") && !file.exists() && !file.canRead()) {
            System.err.println("File error. Program could not access file, please try again.");
            System.exit(1);
        }
    }

    public static String fileOutput(){
        return cleanFileOutput;
    }

    public static String fileStringInput(String fileInput){
        String stripped = fileInput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        String onlyText = stripped.replaceAll("[^A-Z]", ""); //^ is 'not', so anything that isn't a letter gets removed
        return onlyText;
    }

    public static int fileNumberInput(String fileInput){
        String stripped = fileInput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        String onlyNumbers = stripped.replaceAll("[^0-9]", "");

        if (onlyNumbers.isEmpty()) {
            System.err.println("No number specified.");
            return -1;
        }

        return Integer.parseInt(onlyNumbers);

    }

    public static String getInput(){
        userInput = input.nextLine();
        return userInput;
    }

    public static String getKeyInput() {
        String userInput = getInput();
        userInput = userInput.trim().toUpperCase();
        return userInput;
    }

    public static int getNumberInput(){
        String newInput = getKeyInput();
        try {
            userNumberInput = Integer.parseInt(userInput.replaceAll("\\s", ""));
            return userNumberInput;
        } catch (NumberFormatException error) {
            System.err.println("An Error has occurred. Please try again.");
            System.exit(1);
        }
        return userNumberInput;
    }
}
