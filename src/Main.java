public class Main {
    private int userMenuInput;
    private int userCipherInput;
    private int userCipherChoice;
    private String userInput;

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

    public int getNumberInput(){
        CleanText cleanText = new CleanText();
        int userNumberInput = cleanText.getNumberInput();
        return userNumberInput;
    }

    public void mainMenu(){
        System.out.println("1. Pick your Cipher");
        System.out.println("2. Edit Key");
        System.out.println("3. Display Key");
        System.out.println("4. Input Plaintext File");
        System.out.println("5. Display Prepared Plaintext File");
        System.out.println("6. Encrypt File");
        System.out.println("7. Display Cipher Text");
        System.out.println("8. Save Cipher Text");
        System.out.println("9. Input Ciphertext File");
        System.out.println("10. Decrypt Ciphertext File");
        System.out.println("0. Exit Program");
    }

    public void cipherMenu(){
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Keyed Caesar Cipher");
        System.out.println("3. Vigenere Cipher");
        System.out.println("4. Display The Other Menu Options");
        System.out.println("0. Exit Program");
    }

    public void displayMenu(){
        System.out.println("");
        mainMenu();
        userMenuInput = getNumberInput();
        menuActions();
    }

    public void displayCipherMenu(){
        System.out.println("");
        cipherMenu();
        userCipherInput = getNumberInput();

        if (userCipherInput == 0){
            System.exit(0);
        } else if (userCipherInput == 1 || userCipherInput == 2 || userCipherInput == 3) {
            userCipherChoice = userCipherInput;
        } else if (userCipherInput == 4) {
            displayMenu();
        } else {
            System.out.println("An Error has occured. Please try again.");
            System.exit(1);
        }

        System.out.println("");
        System.out.println("Cipher Selected. Now pick what you're going to do with it.");
        displayMenu();
    }

    public void menuActions() {
        Cipher cipher = new Cipher();

        switch (userMenuInput) {
            case 0:
                System.exit(0);
                break;
            case 1:
                displayCipherMenu();
                break;
            case 2:
                System.out.println("Enter key to use: ");
                userInput = getUserInput();

                if (userCipherChoice == 1){
                    cipher.writeToFile("caesar-key.txt", userInput);
                } else if (userCipherChoice == 2){
                    cipher.writeToFile("keyed-caesar-key.txt", userInput);
                } else if (userCipherChoice == 3){
                    cipher.writeToFile("vigenere-key.txt", userInput);
                } else {
                    System.out.println("An error has occurred. Please try again.");
                    System.exit(1);
                }
                displayMenu();
                break;
            case 3:
                System.out.println("Here is the key in the file: ");

                if (userCipherChoice == 1){
                    cipher.readFile("caesar-key.txt");
                } else if (userCipherChoice == 2){
                    cipher.readFile("keyed-caesar-key.txt");
                } else if (userCipherChoice == 3){
                    cipher.readFile("vigenere-key.txt");
                } else {
                    System.out.println("An error has occurred. Please try again.");
                    System.exit(1);
                }
                displayMenu();
                break;
            default:
                System.out.println("L bozo. I haven't implemented this yet >:(");
        }
    }

    //initialises the functions to run: without this, Main.java cannot be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
        main.getUserInput();
        main.menuActions();
        main.displayCipherMenu();
    }
}
