
public class Main {
    private int userMainMenuInput;
    private int userCipherChoice;
    private String cipherOutput;
    private String plainTextOutput;
    private String chosenTextFile;
    private String cipherKeyFileName;
    CaesarCipher caesarCipher = new CaesarCipher();
    KeyedCaesarCipher keyedCaesarCipher = new KeyedCaesarCipher();
    VigenereCipher vigenereCipher = new VigenereCipher();

    public void subMenu(){
        System.out.println("1. Pick your Cipher");
        System.out.println("2. Display Other Menu Options");
        System.out.println("0. Exit Program");
    }

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

    public void cipherMenu(){
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Keyed Caesar Cipher");
        System.out.println("3. Vigenere Cipher");
        System.out.println("4. Display Other Menu Options");
        System.out.println("0. Exit Program");
    }

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
                System.out.println("***You will still need to pick a Cipher before making changes, are you sure you want to continue?***");
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
                        System.err.println("An error has occurred in sub-menu. Please try again.");
                        System.exit(1);
                }
            default:
                System.err.println("An error has occurred. Please try again.");
                System.exit(1);
                break;
        }
    }

    public void displayMainMenu() {
        mainMenu();
        userMainMenuInput = CleanText.getNumberInput();
        menuActions();
    }

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
                System.err.println("Something went wrong. Please try again.");
                System.exit(1);
        }

        System.out.println("");
        System.out.println("Cipher Selected. Now pick what you're going to do with it.");
        displayMainMenu();
    }

    public void editKey(){
        System.out.println("Enter new key: ");
        String userInput = CleanText.getKeyInput();

        System.out.println("Are you sure? ***This will overwrite any existing file contents.***");
        System.out.println("Press 1 to save, press 0 to go back.");
        int userSelection = CleanText.getNumberInput();

        if (userSelection == 1) {
            Cipher.writeToFile(cipherKeyFileName, userInput);
        } else if (userSelection == 0) {
            displayMainMenu();
        }
    }

    public void displayKey() {
        System.out.println("Here is the file contents: ");
        System.out.println(Cipher.readFile(cipherKeyFileName));
    }

    public void enterFile() {
        chosenTextFile = CleanText.getFileContents();
        System.out.println("To continue, select another item from the menu.");
    }

    public void displayPreparedPlaintextFile() {
        String preparedPlainText = CleanText.fileStringInput(chosenTextFile);
        Cipher.writeToFile("prep.txt", preparedPlainText);
        String preppedOutput = Cipher.readFile("prep.txt");
        System.out.println(preppedOutput);
    }

    public void encryptFile() {
        //chosenTextFile is declared at the top of Main

        switch (userCipherChoice) {
            case 1:
                int shiftValue = CleanText.fileNumberInput(Cipher.readFile(cipherKeyFileName));
                cipherOutput = caesarCipher.encrypt(chosenTextFile, shiftValue);
                break;
            case 2:
                String keyedFileContents = Cipher.readFile(cipherKeyFileName);
                int keyedShift = CleanText.fileNumberInput(keyedFileContents);
                String keyedCaesarKeyWord = CleanText.fileStringInput(keyedFileContents);
                cipherOutput = keyedCaesarCipher.encrypt(keyedCaesarKeyWord, keyedShift, chosenTextFile);
                break;
            case 3:
                String vigenereKeyWord = CleanText.fileStringInput(Cipher.readFile(cipherKeyFileName));
                if (vigenereKeyWord.isEmpty()) {
                    System.err.println("Please enter a key word in the edit key section of the menu.");
                    displayMainMenu();
                } else {
                    cipherOutput = vigenereCipher.encrypt(chosenTextFile, vigenereKeyWord);
                }
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                System.exit(1);
        }
    }

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

    public void displayCipherTextFile() {
        System.out.println(cipherOutput);
    }

    public void decryptFile() {
        String fileOutput = CleanText.fileOutput();

        switch (userCipherChoice) {
            case 1:
                int shiftValue = CleanText.fileNumberInput(Cipher.readFile(cipherKeyFileName));
                plainTextOutput = caesarCipher.decrypt(fileOutput, shiftValue);
                System.out.println(plainTextOutput);
                break;
            case 2:
                String keyedFileContents = Cipher.readFile(cipherKeyFileName);
                int keyedShift = CleanText.fileNumberInput(keyedFileContents);
                String keyWord = CleanText.fileStringInput(keyedFileContents);
                plainTextOutput = keyedCaesarCipher.decrypt(keyWord, keyedShift, fileOutput);
                System.out.println(plainTextOutput);
                break;
            case 3:
                String vigenereKeyWord = CleanText.fileStringInput(Cipher.readFile(cipherKeyFileName));
                if (vigenereKeyWord.isEmpty()) {
                    System.err.println("Please enter a key word in the edit key section of the menu.");
                    displayMainMenu();
                } else {
                    plainTextOutput = vigenereCipher.decrypt(fileOutput, vigenereKeyWord);
                }
                System.out.println(plainTextOutput);
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                System.exit(1);
                break;
        }
    }

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
                System.err.println("Please pick one of the displayed numbers. Your choice was not within the range permitted. If this is a program error, try again.");
                displayMainMenu();
        }
    }

    //initialises the functions to run: without this, Main.java cannot be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.displayMenu();
    }
}
