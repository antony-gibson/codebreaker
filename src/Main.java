public class Main {
    private String userMenuInput;
    private String userCipherInput;

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

    public String getNumberInput(){
        CleanText cleanText = new CleanText();
        String userNumberInput = cleanText.getNumberInput();
        return userNumberInput;
    }

    public void mainMenu(){
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
    }

    public void cipherMenu(){
        System.out.println("Welcome Back: We Meet Again. Same again, pick the number relating to the Cipher that you want.");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Keyed Caesar Cipher");
        System.out.println("3. Vigenere Cipher");
    }

    public void displayMenu(){
        mainMenu();
        userMenuInput = getNumberInput();
        System.out.println("You picked: " + userMenuInput + ".");
        menuActions();
        //return userMenuInput;
    }

    public void displayCipherMenu(){
        cipherMenu();
        userCipherInput = getNumberInput();
        System.out.println("You picked: " + userCipherInput + ". Outstanding Choice.");
        //return userCipherInput;
    }

    public void menuActions(){

       if (userMenuInput.equals("0")){
            System.exit(0);
       } else if (userMenuInput.equals("1")){
           displayCipherMenu();
       } else {
           System.out.println("L bozo");
       }

    }
    //below is what allows this file to be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
        main.getUserInput();
        main.menuActions();
        main.displayCipherMenu();
    }
}
