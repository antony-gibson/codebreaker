public class KeyedCaesarCipher extends Cipher{

    public String createKeyedAlphabet (String keyWord) {
        String keyedAlphabet = "";

        //removes duplicate letters within the keyword
        for (int i = 0; i < keyWord.length(); i++) {
            char keyWordChar = keyWord.charAt(i);
            if (keyedAlphabet.indexOf(keyWordChar) == -1) { //-1 because 0 would be first position in the alphabet
                keyedAlphabet += keyWordChar;
            }
        }

        //removes duplicate letters within the alphabet
        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) { //circles through all letters of the alphabet, checks if they are in the keyedAlphabet, then adds them if they are not
            if (keyedAlphabet.indexOf(alphabet) == -1) {
                keyedAlphabet += alphabet;
            }
        }

        return keyedAlphabet;
    }

    public String shiftedKeyedAlphabet (String keyedAlphabet, int shift) {
        String output = "";

        for (int i = 0; i < 26; i++) {
            int currentPosition = i; //converts each letter to a number in the normal alphabet
            int shifted = (currentPosition + shift + 26) % 26; //shifts the letter: +26 stops incorrect negatives, %26 allows the wraparound
            output += keyedAlphabet.charAt(shifted); //take the letter at the new position in the alphabet
            //so, the new keyed alphabet (with key word at beginning). pick a letter in this alphabet, move through this alphabet for *shift* steps, and outputs the letter it lands on
        }

        return output;
    }

    public String encrypt (String keyWord, int shift, String userInput) {
        String keyedAlphabet = createKeyedAlphabet(keyWord);
        String shiftedKeyedAlphabet = shiftedKeyedAlphabet(keyedAlphabet, shift);

        String cipherOutput = "";
        for (int i = 0; i < userInput.length(); i++) {
            char charInput = userInput.charAt(i);

            char alphabetPosition = (char) (charInput - 'A'); //subtracts ASCII value of 'A' from input, to give the position within the alphabet (from 0-25).
            cipherOutput += shiftedKeyedAlphabet.charAt(alphabetPosition);
        }

        return cipherOutput;
    }

    //.indexOf() returns the position of the first occurrence of the specified character in a string

    //take word as input from keyedcaesarkey.txt or whatever
    //take shift as input, same file as above
    //add word to start of alphabet
    //remove duplicate letters from the word
    //remove duplicate letters from the alphabet
    //shift this new alphabet by the shift value
    //take input to encrypt
    //do a caesar cipher on this new alphabet with the input
    //to decrypt, reverse the shift then substitute each position with the same position in the normal alphabet (I think)


}
