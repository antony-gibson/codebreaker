public class VigenereCipher extends Cipher{
    CaesarCipher caesarCipher = new CaesarCipher();
    private String keyWord;

    public void keyLengthCalculation(String plainText) {
        StringBuilder keyWordString = new StringBuilder(keyWord);

        int differenceValue = plainText.length() - keyWord.length();

        if (differenceValue > 0) { //if plainText is longer than key to encrypt with
            int i = 0;
            int length = keyWord.length(); //sets start of i as 0, gets length for loop below

            for (int j = differenceValue; j > 0; j--) { //loops for the size of the difference in length
                char keyWordChar = keyWord.charAt(i);
                keyWordString.append(keyWordChar);
                i++;
                if (i == length) { //resets to beginning of key word once end of key word reached
                    i = 0;
                }
            }
        } else if (differenceValue < 0) { //if key is longer than plainText
            int length = keyWordString.length();
            for (int i = length - 1; i >= plainText.length(); i--) {
                keyWordString.deleteCharAt(i); //deletes excess characters starting at the end of the key word
            }
        }
        this.keyWord = keyWordString.toString(); //assigns to the global variable at the top, rather than stringBuilder
    }


    public String encrypt(String plainText, String userKeyWord) {
        StringBuilder cipherOutput = new StringBuilder();

        keyWord = userKeyWord; //sets as global variable, to be used in keyLengthCalculation
        keyLengthCalculation(plainText);


        for (int i = 0; i < plainText.length(); i++) {
            char keyWordChar = keyWord.charAt(i);

            int alphabetIndex = keyWordChar - 'A';

            String cipherLetter = caesarCipher.encrypt(String.valueOf(plainText.charAt(i)), alphabetIndex);
            cipherOutput.append(cipherLetter);
        }
        return cipherOutput.toString();
    }

    public String decrypt(String cipherText, String userKeyWord) {
        StringBuilder plainTextOutput = new StringBuilder();

        keyWord = userKeyWord;
        keyLengthCalculation(cipherText); //called again to make sure key is same length as cipher

        for (int i = 0; i < cipherText.length(); i++) {
            char keyWordChar = userKeyWord.charAt(i);
            int shiftValue = keyWordChar - 'A'; //determine shift applied to each letter of the cipherText

            String plainTextLetter = caesarCipher.decrypt(String.valueOf(cipherText.charAt(i)), shiftValue); //shift backwards by each shift value to get plainText
            plainTextOutput.append(plainTextLetter);
        }
        return plainTextOutput.toString();
    }
}
