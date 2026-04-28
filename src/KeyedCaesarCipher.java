public class KeyedCaesarCipher extends Cipher{

    public String createKeyedAlphabet (String keyWord) {
        String keyedAlphabet = "";
        StringBuilder keyedAlphabetString = new StringBuilder(keyedAlphabet);

        //removes duplicate letters within user-specified key word
        for (int i = 0; i < keyWord.length(); i++) {
            char keyWordChar = keyWord.charAt(i);
            int firstInstance = keyedAlphabet.indexOf(keyWordChar);

            if (firstInstance < 0) { //if letter isn't already in alphabet, add it
                keyedAlphabetString.append(keyWordChar);
            }
        }

        //removes duplicate letters within the alphabet once key word has been appended
        for (int i = 0; i < 26; i++) {
            char currentLetter = (char)(i+'A'); //adding A gives ASCII value of current position in alphabet
            int firstInstance = keyedAlphabet.indexOf(currentLetter);

            if (firstInstance < 0) { //if letter isn't already in alphabet, add it
                keyedAlphabetString.append(currentLetter);
            }
        }

        return keyedAlphabet;
    }

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

        return output;
    }

    public String encrypt (String keyWord, int shift, String userInput) {
        String keyedAlphabet = createKeyedAlphabet(keyWord);
        String shiftedKeyedAlphabet = shiftedKeyedAlphabet(keyedAlphabet, shift);

        String cipherOutput = "";
        StringBuilder cipherStringOutput = new StringBuilder(cipherOutput);

        for (int i = 0; i < userInput.length(); i++) {
            char charInput = userInput.charAt(i);

            int alphabetPosition = charInput - 'A'; //subtracts ASCII value of 'A' from input, to give the position within the alphabet (from 0-25).
            char newAlphabetPosition = shiftedKeyedAlphabet.charAt(alphabetPosition);
            cipherStringOutput.append(newAlphabetPosition); //takes the letter at that position in the new, shifted and keyed alphabet and appends to cipher output variable
        }

        return cipherOutput;
    }

    public String decrypt (String keyWord, int shift, String userEncryptedInput) {
        String keyedAlphabet = createKeyedAlphabet(keyWord);
        String shiftedKeyedAlphabet = shiftedKeyedAlphabet(keyedAlphabet, shift);
        String plainTextOutput = "";
        StringBuilder plainTextOutputString = new StringBuilder(plainTextOutput);


        for (int i = 0; i < userEncryptedInput.length(); i++) {
            char charInput = userEncryptedInput.charAt(i);

            //finds position in shifted alphabet of encrypted letter
            int encryptedAlphabetPosition = shiftedKeyedAlphabet.indexOf(charInput);

            if (encryptedAlphabetPosition < 0) {
                System.err.println("Could not decrypt one or more characters. Please try again.");
            }

            //matches position in normal alphabet of encrypted character.
            char unencryptedCharacter = alphabet.charAt(encryptedAlphabetPosition);
            plainTextOutputString.append(unencryptedCharacter);
        }
        return plainTextOutput;
    }

}
