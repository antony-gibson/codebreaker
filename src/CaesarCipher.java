/**
 * Subclass of Cipher superclass, handling the Caesar Cipher.
 *
 * @author Antony Gibson
 * @since 11th March 2026
 */
public class CaesarCipher extends Cipher {

    /**
     * takes plaintext input and key file name to call encrypt function
     * @param fileName is name of key file
     * @param chosenTextFile is user's file to encrypt
     * @return caesarCipher, the result of calling the encrypt function
     */
    public String caesarEncrypt(String fileName, String chosenTextFile) {
        int shiftValue = CleanText.fileNumberInput(super.readFile(fileName)); //reads shift value from key file
        String caesarCipher = encrypt(chosenTextFile, shiftValue);
        return caesarCipher;
    }

    /**
     * takes ciphertext input and key file name to call decrypt function
     * @param fileName is name of key file
     * @param chosenTextFile is name of user's file to decrypt
     * @return caesarCipher, the result of calling the decrypt function
     */
    public String caesarDecrypt(String fileName, String chosenTextFile) {
        int shiftValue = CleanText.fileNumberInput(super.readFile(fileName)); //reads shift value from key file
        String caesarCipher = decrypt(chosenTextFile, shiftValue);
        return caesarCipher;
    }

    /**
     * takes contents to save to file then calls saveFile method
     * @param contentsToSave is the contents passed into this method to save to the specified file
     */
    public void caesarSave(String contentsToSave) {
        super.saveFile(contentsToSave);
    }

    /**
     * takes plaintext input and a shift value, and encrypts into the ciphertext output
     * @param fileOutput is the plaintext input
     * @param shiftValue is the shift value input
     * @return cipherStringOutput, the final ciphertext after encrypting all plaintext letters
     */
    public String encrypt(String fileOutput, int shiftValue) {
        String cipherOutput = "";
        StringBuilder cipherStringOutput = new StringBuilder(cipherOutput);

        for (int i = 0; i < fileOutput.length(); i++) {
            char charInput = fileOutput.charAt(i);

            int alphabetPosition = charInput - 'A'; //subtracts ASCII value of 'A' to give position within alphabet (0-25)
            int shiftedAlphabetPosition = alphabetPosition + shiftValue + 26; //adds the shift value to the position in the alphabet, then adds 26 to account for adding negative numbers
            char encrypted = (char) (shiftedAlphabetPosition % 26 + 'A'); //modulo 26 ensures values between 0 and 26, add back the ASCII value of 'A' to return the ASCII value of the new shifted letter.

            cipherStringOutput.append(encrypted);
        }

        return cipherStringOutput.toString();
    }

    /**
     * decrypts the ciphertext by reversing the process, and shifting by the inverse of the encryption shift
     * @param fileOutput is the plaintext from the text file inputted by the user
     * @param shiftValue is the shift value in the key file edited by the user
     * @return the result of running encrypt with a negative shift (to reverse the shift performed during encryption)
     */
    public String decrypt(String fileOutput, int shiftValue) {
        return encrypt(fileOutput, -shiftValue); //does the same thing but shifts the opposite way, hence -shift, so gives the original input.
    }
}