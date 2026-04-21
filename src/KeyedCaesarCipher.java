public class KeyedCaesarCipher extends Cipher{

    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String createKeyedAlphabet (String keyWord) {
        String keyedAlphabet = "";
        for (int i = 0; i < keyWord.length(); i++) {
            char alphabetChar = keyWord.charAt(i);
            if (keyedAlphabet.indexOf(alphabetChar) == -1) {
                keyedAlphabet += alphabetChar;
            }
        }

        for (char alphabetChar = 'A'; alphabetChar <= 'Z'; alphabetChar++) {
            if (keyedAlphabet.indexOf(alphabetChar) == -1) {
                keyedAlphabet += alphabetChar;
            }
        }

        return keyedAlphabet;
    }

    public String encrypt (String keyValue) {

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
