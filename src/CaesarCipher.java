public class CaesarCipher extends Cipher {

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

    public String decrypt(String fileOutput, int shiftValue) {
        return encrypt(fileOutput, -shiftValue); //does the same thing but shifts the opposite way, hence -shift, so gives the original input.
    }
}