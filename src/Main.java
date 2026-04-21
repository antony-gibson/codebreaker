import java.util.Scanner;

public class Main {
    private int userMenuInput;
    private int userMainMenuInput;
    private int userCipherInput;
    private int userCipherChoice;
    private String userInput;
    private String plaintextFileName;
    private String cipherOutput;
    private String plainTextOutput;
    private Scanner input = new Scanner(System.in); //takes user's input
    private String userSaveFileName;
    private String chosenFile;
    private String cipherKeyFileName;
    CleanText cleanText = new CleanText();
    CaesarCipher caesarCipher = new CaesarCipher();
    KeyedCaesarCipher keyedCaesarCipher = new KeyedCaesarCipher();


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
        userMenuInput = getNumberInput();

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
                System.out.println("To continue, press 2. To change your mind, press 1. To exit, press 0.");
                int userSubMenuInput = getNumberInput();

                switch (userSubMenuInput) {
                    case 0:
                        System.exit(0);
                        break;
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
        userMainMenuInput = getNumberInput();
        menuActions();
    }

    public void displayCipherMenu(){
        cipherMenu();
        userCipherInput = getNumberInput();

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
        Cipher cipher = new Cipher();
        System.out.println("Enter new key: ");
        userInput = getCipherInput();
        cipher.writeToFile(cipherKeyFileName, userInput);
    }

    public void displayKey() {
        System.out.println("Here is the file contents: ");
        System.out.println(cleanText.readFile(cipherKeyFileName));
    }

    public void enterFile() {
        chosenFile = cleanText.getFileContents();
        System.out.println("To continue, select another item from the menu.");
    }

    public void displayPreparedPlaintextFile() {
        System.out.println(cleanText.fileStringInput());
    }

    public void encryptFile() {
        //chosenFile is declared at the top of Main

        switch (userCipherChoice) {
            case 1:
                String caesarFileContents = cleanText.readFile(cipherKeyFileName);
                int caesarShift = cleanText.fileNumberInput(caesarFileContents);
                cipherOutput = caesarCipher.encrypt(chosenFile, caesarShift);
                break;
            case 2:
                String keyedFileContents = cleanText.readFile(cipherKeyFileName);
                int keyedShift = cleanText.fileNumberInput(keyedFileContents);
                String keyWord = cleanText.fileStringInput();
                cipherOutput = keyedCaesarCipher.encrypt(keyWord, keyedShift, chosenFile);
                break;
            case 3:
                //add in vigenere here
                break;
            default:
                System.err.println("An error has occurred. Please try again.");
                System.exit(1);
        }
    }

    public void saveFile(String contentsSaved){
        Cipher cipher = new Cipher();
        System.out.println("Please enter the filename to save to: ");
        userSaveFileName = input.nextLine();
        cipher.writeToFile(userSaveFileName, contentsSaved);
    }

    public void displayCipherTextFile() {
        System.out.println(cipherOutput);
    }

    public void decryptFile() {
        String fileOutput = cleanText.fileOutput();

        switch (userCipherChoice) {
            case 1:
                plainTextOutput = caesarCipher.decrypt(fileOutput);
                System.out.println(plainTextOutput);
                break;
            case 2:
                //add in keyed here
                break;
            case 3:
                //add in vigenere here
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
