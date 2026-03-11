import java.util.Scanner;

public class CleanText {
    private String userInput;
    private String newUserInput;
    private String strippedUserInput;
    private Scanner input = new Scanner(System.in); //takes user's input

    public String getInput(){
        userInput = input.nextLine();
        newUserInput = userInput.trim().toUpperCase();
        strippedUserInput = newUserInput.replaceAll("\\s", "");
        System.out.println(newUserInput); //shows user capitalised input minus spaces before or after words
        System.out.println(strippedUserInput); //shows user capitalised input with no spaces at all
        return strippedUserInput;
    }

    //onlyTextInput = strippedUserInput.replaceAll("[^A-Z]", "");
    //I think this is regex for only letters in user input? not sure if I need this as of right now so it can stay commented out.
}
