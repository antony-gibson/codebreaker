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
            case 0: //Exit Program
                System.exit(0);
                break;
            case 1: //Pick a Cipher
                displayCipherMenu();
                break;
            case 2: //Display Other Menu Options
                displayMainMenu();
                break;
            default:
                System.err.println("Please choose one of the available options. Try again.");
                displayMenu();
                break;
        }
    }

    /**
     * displays main menu, calls menuActions() to carry out menu functions
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
            case 0: //Exit Program
                System.exit(0);
                break;
            case 1: //Caesar Cipher
                userCipherChoice = 1;
                cipherKeyFileName = "caesar-key.txt";
                break;
            case 2: //Keyed Caesar Cipher
                userCipherChoice = 2;
                cipherKeyFileName = "keyed-caesar-key.txt";
                break;
            case 3: //Vigenere Cipher
                userCipherChoice = 3;
                cipherKeyFileName = "vigenere-key.txt";
                break;
            case 4: //Other Menu Options
                displayMainMenu();
                break;
            default:
                System.err.println("Please choose one of the available options. Try again.");
                displayCipherMenu();
                break;
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
            case 0: //If user has not chosen a cipher
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
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                displayMainMenu();
                break;
        }

        if (userInput.isEmpty()) { //Checks user has entered a value in the key file
            System.err.println("Please enter a key before continuing.");
            editKey();
        }

        //Checks user intends to overwrite current key file contents
        System.out.println("Press 1 to save, press 0 to go back.");
        System.out.println("***This will overwrite any existing file contents.***");
        int userSelection = CleanText.getNumberInput();

        switch (userSelection) {
            case 0:
                displayMainMenu();
                break;
            case 1:
                Cipher.writeToFile(cipherKeyFileName, userInput);
                break;
            default:
                System.err.println("Please choose one of the available options. Try again.");
                editKey();
                break;
        }
    }

    /**
     * displays the key file to user
     */
    public void displayKey() {
        //Checks user has chosen a cipher
        if (cipherKeyFileName == null) {
            System.err.println("Please select a cipher before continuing.");
            displayCipherMenu();
        }

        System.out.println("Here is the current key: " + cipherKeyFileName);
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
        //Checks user has entered a file
        if (chosenTextFile == null) {
            System.err.println("Please enter a plaintext file before continuing.");
            displayMainMenu();
        }

        String preparedPlainText = CleanText.fileStringInput(chosenTextFile);
        Cipher.writeToFile("prep.txt", preparedPlainText);
        String preppedOutput = Cipher.readFile("prep.txt");
        System.out.println("Prepared Plaintext File: " + preppedOutput);
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

        switch (userCipherChoice) {
            case 0: //If user hasn't chosen a cipher
                System.err.println("Please enter a cipher before trying to encrypt a file.");
                displayCipherMenu();
            case 1: //Caesar Cipher
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                cipherOutput = caesarCipher.caesarEncrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 2: //Keyed Caesar Cipher
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                cipherOutput = keyedCaesarCipher.keyedCaesarEncrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 3: //Vigenere Cipher
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                cipherOutput = vigenereCipher.vigenereEncrypt(cipherKeyFileName, chosenTextFile);
                break;
            default:
                System.err.println("An error has occured. Please try again.");
                displayMainMenu();
                break;
        }
    }

    /**
     * calls save to file methods of user's chosen cipher
     * @param contentsToSave is the contents passed into this method to save to the specified file
     */
    public void saveToFile(String contentsToSave) {
        //Checks user has chosen a cipher
        if (userCipherChoice == 0) {
            System.err.println("Please enter a cipher before continuing.");
            displayCipherMenu();
        }

        //Checks user has encrypted or decrypted a file before attempting to save
        if (contentsToSave == null) {
            System.err.println("Please encrypt or decrypt a file before trying to save.");
            displayMainMenu();
        }

        System.out.println("Press 1 to save, press 0 to go back.");
        System.out.println("***This will overwrite any existing file contents***");
        int userSelection = CleanText.getNumberInput();

        switch (userSelection) {
            case 0:
                displayMainMenu();
                break;
            case 1:
                switch (userCipherChoice) {
                    case 0:
                        System.err.println("Please enter a cipher before continuing.");
                        displayCipherMenu();
                        break;
                    case 1:
                        caesarCipher.caesarSave(contentsToSave);
                        break;
                    case 2:
                        keyedCaesarCipher.keyedCaesarSave(contentsToSave);
                        break;
                    case 3:
                        vigenereCipher.vigenereSave(contentsToSave);
                        break;
                    default:
                        System.err.println("An error has occurred. Please try again.");
                        displayMainMenu();
                }
                break;
            default:
                System.err.println("Please choose one of the available options. Try again.");
                saveToFile(contentsToSave);
                break;
        }
    }

    /**
     * displays cipher text to the user
     */
    public void displayCipherTextFile() {
        //Checks user has encrypted a file before trying to display the ciphertext output
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
        //Takes output of user's chosen file by calling fileOutput method in CleanText, prevents user having to re-enter the file name
        String fileOutput = CleanText.fileOutput();

        //Checks user has chosen a cipher and entered a file to decrypt
        if (fileOutput == null && userCipherChoice == 0) {
            System.err.println("Please enter a cipher before trying to decrypt a file.");
            displayCipherMenu();
        } else if (fileOutput == null) {
            CleanText.getFileContents();
        }

        switch (userCipherChoice) {
            case 0: //If user has not chosen a cipher
                System.err.println("Please enter a cipher before trying to decrypt a file.");
                displayCipherMenu();
            case 1: //Caesar Cipher
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                plainTextOutput = caesarCipher.caesarDecrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 2: //Keyed Caesar Cipher
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                plainTextOutput = keyedCaesarCipher.keyedCaesarDecrypt(cipherKeyFileName, chosenTextFile);
                break;
            case 3: //Vigenere Cipher
                checkCipherKeyFile(cipherKeyFileName);
                checkChosenTextFile(chosenTextFile);
                plainTextOutput = vigenereCipher.vigenereDecrypt(cipherKeyFileName, chosenTextFile);
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                displayMainMenu();
                break;
        }
        System.out.println("Decrypted File: " + plainTextOutput);
    }

    /**
     * each case corresponds to the numbers the user inputs in the main menu, calls the relevant methods depending on task.
     */
    public void menuActions() {

        switch (userMainMenuInput) {
            case 0: //Exit Program
                System.exit(0);
                break;
            case 1: //Choose a Cipher
                displayCipherMenu();
                break;
            case 2: //Edit the Key
                editKey();
                displayMainMenu();
                break;
            case 3: //Display the Key
                displayKey();
                displayMainMenu();
                break;
            case 4: //Input a Plaintext File
                enterFile();
                displayMainMenu();
                break;
            case 5: //Display the Prepared Plaintext File
                displayPreparedPlaintextFile();
                displayMainMenu();
                break;
            case 6: //Encrypt the File
                encryptFile();
                displayMainMenu();
                break;
            case 7: //Display the Ciphertext
                displayCipherTextFile();
                displayMainMenu();
                break;
            case 8: //Save Ciphertext to File
                saveToFile(cipherOutput);
                displayMainMenu();
                break;
            case 9: //Input Ciphertext File to Decrypt
                enterFile();
                displayMainMenu();
                break;
            case 10: //Decrypt and Display the Ciphertext File
                decryptFile();
                saveToFile(plainTextOutput);
                displayMainMenu();
                break;
            default:
                System.err.println("Please pick one of the displayed numbers.");
                displayMainMenu();
        }
    }

    /**
     * Starts the Program
     */
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
    }
}
