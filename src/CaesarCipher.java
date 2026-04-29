/**
 * Subclass of Cipher superclass, handling the Caesar Cipher.
 *
 * @author Antony Gibson
 * @since 11th March 2026
 */

public class CaesarCipher extends Cipher {

    /**
     * takes plaintext input and a shift value, and encrypts into the ciphertext output
     * @param fileOutput is the plaintext from the text file inputted by the user
     * @param shiftValue is the shift value in the key file edited by the user
     * @return cipherStringOutput, the final ciphertext after encrypting all plaintext letters
     */
    public String encrypt(String fileOutput, int shiftValue) {
        String cipherOutput = "";
        StringBuilder cipherStringOutput = new StringBuilder(cipherOutput);

        for (int i = 0; i < fileOutput.length(); i++) {
            char charInput = fileOutput.charAt(i);

            int alphabetPosition = charInput - 'A'; //subtracts ASCII value of 'A' from input, to give the position within the alphabet (from 0-25).
            int shiftedAlphabetPosition = alphabetPosition + shiftValue + 26; //adds the shift value to the position in the alphabet, then adds 26 to account for adding negative numbers (negatives are a left shift, positives are a right shift).
            char encrypted = (char) (shiftedAlphabetPosition % 26 + 'A'); //modulo by 26 to ensure values are between 0 and 26, and then add back on the ASCII value of 'A' to return the ASCII value of the new shifted letter.

            cipherStringOutput.append(encrypted);
        }

        return cipherStringOutput.toString();
    }

    /**
     * decrypts the ciphertext by reversing the process, and shifting by the inverse of the encryption shift
     * @param fileOutput is the plaintext from the text file inputted by the user
     * @param shiftValue is the shift value in the key file edited by the user
     * @return the result of running the encrypt with a negative shift (to reverse the shift performed during encryption)
     */
    public String decrypt(String fileOutput, int shiftValue) {
        return encrypt(fileOutput, -shiftValue); //does the same thing but shifts the opposite way, hence -shift, so gives the original input.
    }
}