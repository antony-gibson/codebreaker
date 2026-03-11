import java.util.Scanner;

public class CleanText {
    private String userInput;
    private Scanner input; //takes user's input

    public String toString(){
        userInput = input.nextLine();
        return userInput.trim().toUpperCase();
    }
}
