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
    CleanText cleanText = new CleanText();
    CaesarCipher caesarCipher = new CaesarCipher();



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

    public void displayMenu(){
        subMenu();
        userMenuInput = getNumberInput();

        if (userMenuInput == 1) {
            displayCipherMenu();
        } else if (userMenuInput == 2) {
            System.out.println("");
            System.out.println("***You will still need to pick a Cipher before making changes, are you sure you want to continue?***");
            System.out.println("To continue, press 2. To change your mind, press 1. To exit, press 0.");
            int userSubMenuInput = getNumberInput();

            if (userSubMenuInput == 1) {
                displayCipherMenu();
            } else if (userSubMenuInput == 2) {
                displayMainMenu();
            } else if (userSubMenuInput == 0) {
                System.exit(0);
            } else {
                System.out.println("An error has occured. Please try again.");
                System.exit(1);
            }

        } else if (userMenuInput == 0) {
            System.exit(0);
        } else {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
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
        displayMainMenu();
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
            System.out.println(cleanText.readFile("caesar-key.txt"));
        } else if (userCipherChoice == 2){
            System.out.println(cleanText.readFile("keyed-caesar-key.txt"));
        } else if (userCipherChoice == 3){
            System.out.println(cleanText.readFile("vigenere-key.txt"));
        } else {
            System.out.println("An error has occurred. Please try again.");
            System.exit(1);
        }
    }

    public void enterFile() {
        cleanText.getFileContents();
        System.out.println("To continue, select another item from the menu.");
    }

    public void displayPreparedPlaintextFile() {
        System.out.println(cleanText.fileStringInput());
    }

    public void encryptFile() {
        String fileOutput = cleanText.fileOutput();

        if (userCipherChoice == 1){
            System.out.println("Please enter the number of spaces to shift your input: ");
            int shift = cleanText.getNumberInput();
            cipherOutput = caesarCipher.encrypt(fileOutput, shift); //cipher, because caesar cipher is initialised above
        } else if (userCipherChoice == 2){
            //do the same for Keyed Caesar Cipher
        } else if (userCipherChoice == 3){
            //same again for vigenere
        } else {
            System.out.println("An error has occurred. Please try again.");
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

        if (userCipherChoice == 1){
            plainTextOutput = caesarCipher.decrypt(fileOutput);
            System.out.println(plainTextOutput);
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
