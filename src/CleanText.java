import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CleanText {
    private String userInput;
    private String newUserInput;
    private int userNumberInput;
    private Scanner input = new Scanner(System.in); //takes user's input
    private String userFileNameInput;
    private int onlyNumberOutput;
    private String line;
    private String fileOutput;
    private String cleanFileOutput;

    public String readFile(String fileName) {
        File file = new File(fileName);
        String fileContents = "";
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                line = line.trim().toUpperCase();
                fileContents += line;
            }
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
        return fileContents;
    }

    public String getFileContents(){
        System.out.println("Please enter the file name: ");
        userFileNameInput = input.nextLine();
        fileCheck(userFileNameInput);
        fileOutput = readFile(userFileNameInput);
        cleanFileOutput = fileStringInput(fileOutput);
        return cleanFileOutput;
    }

    public void fileCheck(String userFileNameInput) {
        File file = new File(userFileNameInput);

        while (!userFileNameInput.endsWith(".txt") && !file.exists() && !file.canRead()) {
            System.out.println("An error has occured. Please try again.");
            System.exit(1);
        }
    }

    public String fileOutput(){
        return cleanFileOutput;
    }

    public String fileStringInput(String fileInput){
        String stripped = fileInput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        String onlyText = stripped.replaceAll("[^A-Z]", ""); //^ is 'not', so anything that isn't a letter gets removed
        return onlyText;
    }

    public int fileNumberInput(String fileInput){
        String stripped = fileInput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        String onlyNumbers = stripped.replaceAll("[^0-9]", "");

        if (onlyNumbers.isEmpty()) {
            System.err.println("Error in file number input: only numbers is empty.");
            return -1;
        }

        onlyNumberOutput = Integer.parseInt(onlyNumbers);
        return onlyNumberOutput;
    }

    public String getInput(){
        userInput = input.nextLine();
        return userInput;
    }

    public String getKeyInput() {
        String userInput = getInput();
        userInput = userInput.trim().toUpperCase();
        return userInput;
    }

    public String getCipherInput(){
        getInput();
        newUserInput = userInput.trim().toUpperCase();
        newUserInput = userInput.replaceAll("\\s", "");
        newUserInput = userInput.replaceAll("[^A-Z]", "");
        return newUserInput;
    }

    public int getNumberInput(){
        getInput();
        String newInput = userInput.trim().toUpperCase();
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
