import java.io.IOException;
/**
 * Cipher program that supports encryption and decryption using:
 * - caesar cipher
 * - keyed caesar cipher
 * - vigenere cipher
 *
 * @author Antony Gibson
 * @since 11th March 2026
 */
public class Main {
    private int userMainMenuInput;
    private int userCipherChoice = 0;
    private String cipherOutput;
    private String plainTextOutput;
    private String chosenTextFile;
    private String cipherKeyFileName;
    CaesarCipher caesarCipher = new CaesarCipher();
    KeyedCaesarCipher keyedCaesarCipher = new KeyedCaesarCipher();
    VigenereCipher vigenereCipher = new VigenereCipher();

    /**
     * Holds the sub menu
     */
    public void subMenu(){
        System.out.println("1. Pick your Cipher");
        System.out.println("2. Display Other Menu Options");
        System.out.println("0. Exit Program");
    }

    /**
     * Holds main menu, called from most places in Main
     */
    public void mainMenu(){
        System.out.println("1. Pick your Cipher");
        System.out.println("2. Edit Key");
        System.out.println("3. Display Key");
        System.out.println("4. Input Plaintext File");
        System.out.println("5. Display Prepared Plaintext File");
        System.out.println("6. Encrypt File");
        System.out.println("7. Display Encrypted File");
        System.out.println("8. Save Encrypted File");
        System.out.println("9. Input Ciphertext File");
        System.out.println("10. Decrypt and Save Ciphertext File");
        System.out.println("0. Exit Program");
    }

    /**
     * holds cipher menu, used by users to pick desired cipher
     */
    public void cipherMenu(){
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Keyed Caesar Cipher");
        System.out.println("3. Vigenere Cipher");
        System.out.println("4. Display Other Menu Options");
        System.out.println("0. Exit Program");
    }

    /**
     * used to display submenu and carry out user's inputs
     */
    public void displayMenu() {
        subMenu();
        int userMenuInput = CleanText.getNumberInput();

        switch (userMenuInput) {
            case 0:
                System.exit(0);
                break;
            case 1:
                displayCipherMenu();
                break;
            case 2:
                System.out.println("");
                System.out.println("***You will still need to pick a Cipher before making changes***");
                System.out.println("To continue, press 2. To change your mind, press 1.");
                int userSubMenuInput = CleanText.getNumberInput();

                switch (userSubMenuInput) {
                    case 1:
                        displayCipherMenu();
                        break;
                    case 2:
                        displayMainMenu();
                        break;
                    default:
                        System.err.println("Please try again.");
                        displayMenu();
                }
            default:
                System.err.println("Please enter an available option.");
                displayMenu();
                break;
        }
    }

    /**
     * displays main menu, calls menuActions() to carry out functions
     */
    public void displayMainMenu() {
        mainMenu();
        userMainMenuInput = CleanText.getNumberInput();
        menuActions();
    }

    /**
     * displays cipher menu, assigns name of cipher key file depending on choice
     */
    public void displayCipherMenu(){
        cipherMenu();
        int userCipherInput = CleanText.getNumberInput();

        switch (userCipherInput) {
            case 0:
                System.exit(0);
                break;
            case 1:
                userCipherChoice = 1;
                cipherKeyFileName = "caesar-key.txt";
                break;
            case 2:
                userCipherChoice = 2;
                cipherKeyFileName = "keyed-caesar-key.txt";
                break;
            case 3:
                userCipherChoice = 3;
                cipherKeyFileName = "vigenere-key.txt";
                break;
            case 4:
                displayMenu();
                break;
            default:
                System.err.println("Please choose one of the available options. Try again.");
                displayCipherMenu();
        }

        System.out.println("");
        System.out.println("Cipher Selected. Now pick what you're going to do with it.");
        displayMainMenu();
    }

    /**
     * used to edit the key file
     */
    public void editKey(){
        String userInput = "";

        switch (userCipherChoice) {
            case 0:
                System.err.println("Please enter a cipher before continuing.");
                displayCipherMenu();
                break;
            case 1: //Caesar Cipher
                System.out.println("Please enter the shift value to encrypt with.");
                userInput = CleanText.getKeyInput();
                break;
            case 2: //Keyed Caesar Cipher
                System.out.println("Please enter the word to use as a key, followed by the shift value you would like.");
                userInput = CleanText.getKeyInput();
                break;
            case 3: //Vigenere Cipher
                System.out.println("Please enter the word to use as a key.");
                userInput = CleanText.getKeyInput();
        }

        if (userInput.isEmpty()) {
            System.err.println("Please enter a key before continuing.");
            editKey();
        }

        System.out.println("Are you sure? ***This will overwrite any existing file contents.***");
        System.out.println("Press 1 to save, press 0 to go back.");
        int userSelection = CleanText.getNumberInput();

        if (userSelection == 1) {
            Cipher.writeToFile(cipherKeyFileName, userInput);
        } else if (userSelection == 0) {
            displayMainMenu();
        }
    }

    /**
     * displays the key file to user
     */
    public void displayKey() {
        if (cipherKeyFileName == null) {
            System.err.println("Please select a cipher before continuing.");
            displayCipherMenu();
        }

        System.out.println("Here is the current key: ");
        System.out.println(Cipher.readFile(cipherKeyFileName));
    }

    /**
     * prompts user to enter a file name
     */
    public void enterFile() {
        chosenTextFile = CleanText.getFileContents();
        System.out.println("To continue, select another item from the menu.");
    }

    /**
     * saves to and reads prepared plaintext from prep.txt file
     */
    public void displayPreparedPlaintextFile() {
        String preparedPlainText = CleanText.fileStringInput(chosenTextFile);
        Cipher.writeToFile("prep.txt", preparedPlainText);
        String preppedOutput = Cipher.readFile("prep.txt");
        System.out.println(preppedOutput);
    }

    /**
     * checks user has actually chosen a file to use with the ciphers
     * @param chosenTextFile is the text file chosen by user to take input from to enter into ciphers
     */
    public void checkChosenTextFile(String chosenTextFile){
        if (chosenTextFile == null) {
            System.err.println("Please enter a file to encrypt or decrypt.");
            enterFile();
        }
    }

    /**
     * checks user has entered a key in the key file to use with the ciphers
     * @param cipherKeyFile is the name of the key file depending on the cipher chosen by the user
     */
    public void checkCipherKeyFile(String cipherKeyFile){
        if (cipherKeyFile == null) {
            System.err.println("Please enter a key in the key file.");
            editKey();
        }
    }

    /**
     * encrypts user-chosen file based on the chosen cipher.
     */
    public void encryptFile() {
        //chosenTextFile is declared at the top of Main

        switch (userCipherChoice) {
            case 0:
                System.err.println("Please enter a cipher before trying to encrypt a file.");
                displayCipherMenu();
            case 1:
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                cipherOutput = caesarCipher.caesarEncrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 2:
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                cipherOutput = keyedCaesarCipher.keyedCaesarEncrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 3:
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                cipherOutput = vigenereCipher.vigenereEncrypt(cipherKeyFileName, chosenTextFile);
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                System.exit(1);
        }
    }

    /**
     * saves to a file specified by user
     * @param contentsSaved is the contents to be saved to the file
     */
    public void saveFile(String contentsSaved){
        System.out.println("Please enter the filename to save to: ");
        String userSaveFileName = CleanText.getInput();

        System.out.println("***This will overwrite any existing file contents.***");
        System.out.println("Press 1 to save, press 0 to go back.");
        int userSelection = CleanText.getNumberInput();

        if (userSelection == 1) {
            Cipher.writeToFile(userSaveFileName, contentsSaved);
        } else if (userSelection == 0) {
            displayMainMenu();
        }
    }

    /**
     * displays cipher text to the user
     */
    public void displayCipherTextFile() {
        if (cipherOutput == null) {
            System.err.println("Please encrypt a file before trying to display it.");
            displayMainMenu();
        } else {
            System.out.println(cipherOutput);
        }
    }

    /**
     * decrypts user-specified file according to which cipher the user chose
     */
    public void decryptFile() {
        String fileOutput = CleanText.fileOutput();

        if (fileOutput == null && userCipherChoice == 0) {
            System.err.println("Please enter a cipher before trying to decrypt a file.");
            displayCipherMenu();
        } else if (fileOutput == null) {
            CleanText.getFileContents();
        }

        switch (userCipherChoice) {
            case 0:
                System.err.println("Please enter a cipher before trying to decrypt a file.");
                displayCipherMenu();
            case 1:
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                plainTextOutput = caesarCipher.caesarDecrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 2:
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                plainTextOutput = keyedCaesarCipher.keyedCaesarDecrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 3:
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                plainTextOutput = vigenereCipher.vigenereDecrypt(cipherKeyFileName, chosenTextFile);
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                System.exit(1);
                break;
        }
        System.out.println("Decrypted File: " + plainTextOutput);
    }

    /**
     * each case corresponds to the numbers the user inputs in the main menu, calls the relevant methods depending on task.
     */
    public void menuActions() {

        switch (userMainMenuInput) {
            case 0: //exit
                System.exit(0);
                break;
            case 1: //choose the cipher
                displayCipherMenu();
                break;
            case 2: //edit the key
                editKey();
                displayMainMenu();
                break;
            case 3: //display the key
                displayKey();
                displayMainMenu();
                break;
            case 4: //input plaintext file
                enterFile();
                displayMainMenu();
                break;
            case 5: //display prepared plaintext file
                displayPreparedPlaintextFile();
                displayMainMenu();
                break;
            case 6: //encrypt the file using the chosen cipher
                encryptFile();
                displayMainMenu();
                break;
            case 7: //display encrypted cipher output
                displayCipherTextFile();
                displayMainMenu();
                break;
            case 8: //save cipher text to file
                saveFile(cipherOutput);
                displayMainMenu();
                break;
            case 9: //input ciphertext file to decrypt
                enterFile();
                displayMainMenu();
                break;
            case 10: //decrypt ciphertext file
                decryptFile();
                saveFile(plainTextOutput);
                displayMainMenu();
                break;
            default:
                System.err.println("Please pick one of the displayed numbers.");
                displayMainMenu();
        }
    }

    /**
     * runs Main
     */
    //initialises the functions to run: without this, Main.java cannot be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
    }
}
