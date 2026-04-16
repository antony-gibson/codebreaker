import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CleanText {
    private String userInput;
    private String newUserInput;
    private int userNumberInput;
    private Scanner input = new Scanner(System.in); //takes user's input
    private String userFileNameInput;
    private String strippedUserInput;
    private String onlyTextInput;
    private int onlyNumberInput;
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
        cleanFileOutput = fileStringInput();
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

    public String fileStringInput(){
        strippedUserInput = fileOutput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        onlyTextInput = strippedUserInput.replaceAll("[^A-Z]", ""); //^ is 'not', so anything that isn't a letter gets removed
        return onlyTextInput;
    }

    public int fileNumberInput(String fileOutput){
        onlyNumberInput= Integer.parseInt(fileOutput.replaceAll("\\s", "")); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        return onlyNumberInput;
    }

    public String getInput(){
        userInput = input.nextLine().trim().toUpperCase();
        return userInput;
    }

    public String getCipherInput(){
        getInput();
        newUserInput = userInput.replaceAll("\\s", "");
        newUserInput = userInput.replaceAll("[^A-Z]", "");
        return newUserInput;
    }

    public int getNumberInput(){
        getInput();
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
