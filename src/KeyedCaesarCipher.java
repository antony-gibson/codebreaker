public class KeyedCaesarCipher extends Cipher{

    public String createKeyedAlphabet (String keyWord) {
        String keyedAlphabet = "";

        //removes duplicate letters within the keyword
        for (int i = 0; i < keyWord.length(); i++) { //for length of the key word
            char keyWordChar = keyWord.charAt(i); //make each letter a character
            int currentPosition = keyedAlphabet.indexOf(keyWordChar);
            if (currentPosition == -1) { //return -1 if the letter isn't in the new alphabet: -1 because 0 would be first position in the alphabet rather than null
                keyedAlphabet += keyWordChar; //add letter to alphabet
            }
        }

        //removes duplicate letters within the alphabet
        for (int i = 0; i < 26; i++) { //circles through all letters of the alphabet, checks if they are in the keyedAlphabet, then adds them if they are not
            char letter = (char)(i + 'A'); //adds ASCII value for 'A' to the current value to get to the ASCII values of the alphabet
            int currentPosition = keyedAlphabet.indexOf(letter);
            if (currentPosition == -1) { //if the letter currently being checked isn't in the alphabet, return -1
                keyedAlphabet += letter; //add current letter to alphabet
            }
        }

        return keyedAlphabet; //return the newly-created keyed alphabet
    }

    public String shiftedKeyedAlphabet (String keyedAlphabet, int shift) {
        String output = ""; //output initialised as empty

        for (int i = 0; i < 26; i++) { //for each letter of the alphabet
            int currentPosition = i; //converts each letter to a number in the normal alphabet
            int movementValue = currentPosition + shift + 26;
            int wrappedMovementValue = movementValue % 26;
            char charOutput = keyedAlphabet.charAt(wrappedMovementValue);
            output += charOutput;
        }

        return output; //give output from shifting the keyed alphabet
    }

    public String encrypt (String keyWord, int shift, String userInput) {
        String keyedAlphabet = createKeyedAlphabet(keyWord); //calls method to create keyed alphabet
        String shiftedKeyedAlphabet = shiftedKeyedAlphabet(keyedAlphabet, shift); //calls method to shift this new keyed alphabet

        String cipherOutput = ""; //cipher output variable initialised as empty
        for (int i = 0; i < userInput.length(); i++) { //for the length of the string the user wants encoding
            char charInput = userInput.charAt(i); //convert each letter in turn to be a character

            int alphabetPosition = charInput - 'A'; //subtracts ASCII value of 'A' from input, to give the position within the alphabet (from 0-25).
            char newAlphabetPosition = shiftedKeyedAlphabet.charAt(alphabetPosition);
            cipherOutput += newAlphabetPosition; //takes the letter at that position in the new, shifted and keyed alphabet and appends to cipher output variable
        }

        return cipherOutput; //returns cipher output variable to user
    }
    //to decrypt, reverse the shift then substitute each position with the same position in the normal alphabet (I think)


}
