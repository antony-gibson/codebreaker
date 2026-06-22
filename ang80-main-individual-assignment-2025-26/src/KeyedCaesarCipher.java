/**
 * Subclass of Cipher superclass, handling the Keyed Caesar Cipher.
 *
 * @author Antony Gibson
 * @since 11th March 2026
 */
public class KeyedCaesarCipher extends Cipher{

    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * takes plaintext input and key file name to call encrypt function
     * @param fileName is name of key file
     * @param chosenTextFile is name of user's file to encrypt
     * @return keyedCaesar, the result of calling the encrypt function
     */
    public String keyedCaesarEncrypt(String fileName, String chosenTextFile) {
        int shiftValue = CleanText.fileNumberInput(super.readFile(fileName)); //reads shift value from key file
        String keyWord = CleanText.fileStringInput(super.readFile(fileName)); //reads key word from key file
        String keyedCaesar = encrypt(keyWord, shiftValue, chosenTextFile);
        return keyedCaesar;
    }

    /**
     * takes ciphertext input and key file name to call decrypt function
     * @param fileName is name of key file
     * @param chosenTextFile is name of user's file to encrypt
     * @return keyedCaesar, the result of calling the decrypt function
     */
    public String keyedCaesarDecrypt(String fileName, String chosenTextFile) {
        int shiftValue = CleanText.fileNumberInput(super.readFile(fileName)); //reads shift value from key file
        String keyWord = CleanText.fileStringInput(super.readFile(fileName)); //reads key word from key file
        String keyedCaesar = decrypt(keyWord, shiftValue, chosenTextFile);
        return keyedCaesar;
    }

    /**
     * takes contents to save to file then calls saveFile method
     * @param contentsToSave is the contents passed into this method to save to the specified file
     */
    public void keyedCaesarSave(String contentsToSave) {
        super.saveFile(contentsToSave);
    }

    /**
     * creates the keyed alphabet used in encryption
     * @param keyWord is the word chosen by user to use as the key
     * @return keyedAlphabetString, the outcome of removing duplicate letters from key and adding remaining letters of alphabet onto the end
     */
    public String createKeyedAlphabet (String keyWord) {
        String keyedAlphabet = "";
        StringBuilder keyedAlphabetString = new StringBuilder(keyedAlphabet);

        //removes duplicate letters within user-specified key word
        for (int i = 0; i < keyWord.length(); i++) {
            char keyWordChar = keyWord.charAt(i);
            int firstInstance = keyedAlphabetString.indexOf(String.valueOf(keyWordChar));

            if (firstInstance < 0) { //if letter isn't already in the word, append to string
                keyedAlphabetString.append(keyWordChar);
            }
        }

        //removes duplicate letters within the alphabet once key word has been appended
        for (int i = 0; i < 26; i++) {
            char currentLetter = (char)(i+'A'); //adding A gives ASCII value of current position in alphabet
            int firstInstance = keyedAlphabetString.indexOf(String.valueOf(currentLetter));

            if (firstInstance < 0) { //if letter isn't already in string, add it
                keyedAlphabetString.append(currentLetter);
            }
        }
        return keyedAlphabetString.toString();
    }

    /**
     * shifts each letter of the keyed alphabet by the shift value
     * @param keyedAlphabet is the keyed alphabet created above, in createKeyedAlphabet()
     * @param shift is the shift value inputted by user into the key file
     * @return outputString, the shifted keyed alphabet to be used for encryption
     */
    public String shiftedKeyedAlphabet (String keyedAlphabet, int shift) {
        String output = "";
        StringBuilder outputString = new StringBuilder(output);

        //for each letter of the alphabet
        for (int i = 0; i < 26; i++) {
            int currentPosition = i;
            int shiftedPosition = currentPosition + shift + 26; //+26 accounts for negative shifts
            int wrappedShiftPosition = shiftedPosition % 26; //if number is above 26, wraps round to start from 0 again

            char charOutput = keyedAlphabet.charAt(wrappedShiftPosition); //take character from new position in the keyed alphabet
            outputString.append(charOutput);
        }
        return outputString.toString();
    }

    /**
     * encrypts user plaintext into ciphertext
     * @param keyWord is word chosen by user as key in key file
     * @param shift is shift value chosen by user in key file
     * @param userInput is plaintext from user-inputted file
     * @return cipherStringOutput, the result of encrypting the plaintext into ciphertext
     */
    public String encrypt (String keyWord, int shift, String userInput) {
        String keyedAlphabet = createKeyedAlphabet(keyWord);
        String shiftedKeyedAlphabet = shiftedKeyedAlphabet(keyedAlphabet, shift);

        String cipherOutput = "";
        StringBuilder cipherStringOutput = new StringBuilder(cipherOutput);

        for (int i = 0; i < userInput.length(); i++) {
            char charInput = userInput.charAt(i);

            int alphabetPosition = charInput - 'A'; //subtracts ASCII value of 'A' from input, to give the position within the alphabet (from 0-25).
            char newAlphabetPosition = shiftedKeyedAlphabet.charAt(alphabetPosition); //takes letter at same position in shifted and keyed alphabet
            cipherStringOutput.append(newAlphabetPosition);
        }
        return cipherStringOutput.toString();
    }

    /**
     * decrypts user ciphertext into plaintext by reconstructing shifted keyed alphabet, finding ciphertext letters and returning letters in same position in normal alphabet
     * @param keyWord is word chosen by user as key in key file
     * @param shift is shift value chosen by user in key file
     * @param userEncryptedInput is cipher text from file chosen by user
     * @return plainTextOutputString, the result of decrypting
     */
    public String decrypt (String keyWord, int shift, String userEncryptedInput) {
        String keyedAlphabet = createKeyedAlphabet(keyWord);
        String shiftedKeyedAlphabet = shiftedKeyedAlphabet(keyedAlphabet, shift);
        String plainTextOutput = "";
        StringBuilder plainTextOutputString = new StringBuilder(plainTextOutput);


        for (int i = 0; i < userEncryptedInput.length(); i++) {
            char charInput = userEncryptedInput.charAt(i);

            //finds position in shifted alphabet of encrypted letter
            int encryptedAlphabetPosition = shiftedKeyedAlphabet.indexOf(charInput);

            if (encryptedAlphabetPosition < 0) { //Makes sure all characters can be found in the new shifted keyed alphabet
                System.err.println("Could not decrypt one or more characters. Please try again.");
                System.exit(1);
            }

            //takes position of encrypted letter in shifted alphabet, returns letter at same position in normal alphabet
            char unencryptedCharacter = alphabet.charAt(encryptedAlphabetPosition);
            plainTextOutputString.append(unencryptedCharacter);
        }
        return plainTextOutputString.toString();
    }

}
