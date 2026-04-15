
public class Main {
    private int userMenuInput;
    private int userCipherInput;
    private int userCipherChoice;
    private String userInput;
    private String plaintextFileName;
    CleanText cleanText = new CleanText();


    public String getUserFileInput(){
        String userInput = cleanText.getFileContents();
        return userInput;
    }

    public String getInput(){
        String userInput = cleanText.getInput();
        return userInput;
    }

    public String getCipherInput(){
        String userCipherInput = cleanText.getCipherInput();
        return userCipherInput;
    }

    public int getNumberInput(){
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
            System.out.println("An Error has occurred. Please try again.");
            System.exit(1);
        }

        System.out.println("");
        System.out.println("Cipher Selected. Now pick what you're going to do with it.");
        displayMenu();
    }

    public void editKey(){
        Cipher cipher = new Cipher();
        System.out.println("Enter new key: ");
        userInput = getCipherInput();

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
    }

    public void displayKey() {
        System.out.println("Here is the key in the file: ");

        if (userCipherChoice == 1){
            cleanText.readFile("caesar-key.txt");
        } else if (userCipherChoice == 2){
            cleanText.readFile("keyed-caesar-key.txt");
        } else if (userCipherChoice == 3){
            cleanText.readFile("vigenere-key.txt");
        } else {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }

    public void enterPlaintextFile() {
        cleanText.getFileContents();
        System.out.println("To continue, select another item from the menu.");
    }

    public void displayPreparedPlaintextFile() {
        cleanText.fileStringInput();
    }

    public void encryptFile() {
        String fileName = cleanText.userFileName();

        if (userCipherChoice == 1){
            CaesarCipher cipher = new CaesarCipher();
            System.out.println("Please enter the number of spaces to shift your input: ");
            int shift = cleanText.getNumberInput();
            cipher.encrypt(fileName, shift);
        } else if (userCipherChoice == 2){
            //do the same for Keyed Caesar Cipher
        } else if (userCipherChoice == 3){
            //same again for vigenere
        } else {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }

    public void menuActions() {

        switch (userMenuInput) {
            case 0: //exit
                System.exit(0);
                break;
            case 1: //choose the cipher
                displayCipherMenu();
                break;
            case 2: //edit the key
                editKey();
                displayMenu();
                break;
            case 3: //display the key
                displayKey();
                displayMenu();
                break;
            case 4: //input plaintext file
                enterPlaintextFile();
                displayMenu();
                break;
            case 5: //display prepared plaintext file
                displayPreparedPlaintextFile();
                displayMenu();
                break;
            case 6: //encrypt the file using the chosen cipher
                encryptFile();
                displayMenu();
                break;
            case 7: //display encrypted cipher output
                displayMenu();
                break;
            case 8: //save cipher text to file
                displayMenu();
                break;
            case 9: //input ciphertext file to decrypt
                displayMenu();
                break;
            case 10: //decrypt ciphertext file
                displayMenu();
                break;
            default:
                System.out.println("Please pick one of the displayed numbers. Your choice was not within the range permitted. If this is a program error, exit and try again.");
        }
    }

    //initialises the functions to run: without this, Main.java cannot be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
        main.getInput();
        main.menuActions();
        main.displayCipherMenu();
        main.getUserFileInput();
        main.getCipherInput();

    }
}
