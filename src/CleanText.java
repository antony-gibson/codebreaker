import java.util.Scanner;

public class CleanText {
    private String userInput;
    private Scanner input = new Scanner(System.in); //takes user's input

    public String getInput(){
        userInput = input.nextLine();
        return userInput.trim().toUpperCase();
    }
}
