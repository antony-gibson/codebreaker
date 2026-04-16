public class CaesarCipher extends Cipher {

    public int cipherShift;
    public String encrypt(String fileOutput, int shift) {

        cipherShift = shift; //stores shift value in variable
        String cipherOutput = "";

        for (int i = 0; i < fileOutput.length(); i++) {
            char charInput = fileOutput.charAt(i);

            char encrypted = (char) (charInput - 'A'); //subtracts ASCII value of 'A' from input, to give the position within the alphabet (from 0-25).
            encrypted = (char) (encrypted + shift + 26); //adds the shift value to the position in the alphabet, then adds 26 to account for adding negative numbers (negatives are a left shift, positives are a right shift).
            encrypted = (char) (encrypted % 26 + 'A'); //modulo by 26 to ensure values are between 0 and 26, and then add back on the ASCII value of 'A' to return the ASCII value of the new shifted letter.

            cipherOutput += encrypted;
        }

        return cipherOutput;
    }

    public String decrypt(String fileOutput) {
        CleanText cleanText = new CleanText();
        int newShift = 0;

        if (cipherShift == 0) {
            System.out.println("Please enter the shift value.");
            newShift = cleanText.getNumberInput();
        } else if (cipherShift != 0) {
            System.out.println("You encrypted the file with the shift value " + cipherShift);
            System.out.println("To decrypt with this value, press 1. To input your own value, press 2. To exit, press 0.");
            int userInput = cleanText.getNumberInput();

            if (userInput == 1) {
                newShift = cipherShift;
            } else if (userInput == 2) {
                System.out.println("Please enter the shift number.");
                newShift = cleanText.getNumberInput();
            } else if (userInput == 0) {
                System.exit(0);
            }
        }

        String plainTextOutput = encrypt(fileOutput, -newShift); //does the same thing but shifts the opposite way, hence -shift, so gives the original input.
        return plainTextOutput;
    }
}