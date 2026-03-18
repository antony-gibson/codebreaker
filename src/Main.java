public class Main {
    private String userMenuInput;

    public String getUserInput(){
        CleanText cleanText = new CleanText();
        String userInput = cleanText.getInput();
        return userInput;
    }

    public String getCipherInput(){
        CleanText cleanText = new CleanText();
        String userCipherInput = cleanText.getCipherInput();
        return userCipherInput;
    }

    public String displayMenu(){
        System.out.print("Ello Ello ELlo. Welcome.");
        System.out.println("Please enter the number relating to the choice you are picking:");
        System.out.println("1. Pick your Cipher");
        System.out.println("2. Edit Key");
        System.out.println("3. Display Key");
        System.out.println("4. Input a file");
        System.out.println("5. Display File");
        System.out.println("6. Encrypt File");
        System.out.println("7. Display Cipher Text");
        System.out.println("8. Save Cipher Text");
        System.out.println("9. Input Encrypted File");
        System.out.println("10. Decrypt Encrypted File");
        System.out.println("0. Exit Program");
        userMenuInput = getUserInput();
        System.out.println("You picked: " + userMenuInput + ". Good Move.");
        return userMenuInput;
    }

    //below is what allows this file to be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
        main.getUserInput();
    }
}
