import java.util.Scanner;

public class CleanText {
    private String userInput;
    private String newUserInput;
    private String strippedUserInput;
    private String onlyTextInput;
    private int onlyNumberInput;
    private Scanner input = new Scanner(System.in); //takes user's input

    public String getInput(){
        userInput = input.nextLine();
        newUserInput = userInput.trim().toUpperCase();
        return userInput;
    }

    public String getCipherInput(){
        getInput();
        strippedUserInput = newUserInput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        onlyTextInput = strippedUserInput.replaceAll("[^A-Z]", ""); //^ is 'not', so anything that isn't a letter or number gets removed
        return onlyTextInput;
    }

    public int getNumberInput(){
        getInput();
        onlyNumberInput= Integer.parseInt(newUserInput.replaceAll("\\s", "")); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        return onlyNumberInput;
    }

}
