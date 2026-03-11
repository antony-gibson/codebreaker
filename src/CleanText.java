import java.util.Scanner;

public class CleanText {
    private String userInput;
    private String newUserInput;
    private String strippedUserInput;
    private String onlyTextInput;
    private Scanner input = new Scanner(System.in); //takes user's input

    public String getInput(){
        userInput = input.nextLine();
        newUserInput = userInput.trim().toUpperCase();
        strippedUserInput = newUserInput.replaceAll("\\s", ""); //double slash allows all whitespace to be removed. It doesn't work without the double slash - if it ain't broke don't fix it.
        onlyTextInput = strippedUserInput.replaceAll("[^A-Z, 0-9]", ""); //^ is 'not', so anything that isn't a letter or number gets removed
        System.out.println(newUserInput); //shows user capitalised input minus spaces before or after words
        System.out.println(strippedUserInput); //shows user capitalised input with no spaces at all
        System.out.println(onlyTextInput);
        return onlyTextInput;
    }
}
