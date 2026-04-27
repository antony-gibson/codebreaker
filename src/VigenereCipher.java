public class VigenereCipher extends Cipher{
    String keyWord;
    StringBuilder keyWordString = new StringBuilder(keyWord);

    public void keyLengthCalculation(String plainText, String keyWord) {

        int differenceValue = plainText.length() - keyWord.length();

        if (differenceValue > 0) { //if plainText is longer than key to encrypt with
            for (int j = differenceValue; j == 0; j--) { //loops for the size of the difference in length
                for (int i = 0; i < keyWord.length(); i++) { //loops through key word
                    char keyWordChar = keyWord.charAt(i);
                    keyWordString.append(keyWordChar); //adds each letter of the key word in turn until length of key is the same as plainText
                }
            }
        } else if (differenceValue < 0) { //if key is longer than plainText
            for (int i = differenceValue; i == 0; i++) { //loops through key word
                keyWordString.deleteCharAt(i); //removes all characters beyond the end of the plainText input
            }
        }
    }


    public String encrypt(String plainText, String userKeyWord) {
        CaesarCipher caesarCipher = new CaesarCipher();
        StringBuilder cipherOutput = new StringBuilder();

        keyWord = userKeyWord; //sets as global variable
        keyLengthCalculation(plainText, keyWord);


        //check if vigenere works: then figure out decryption


        for (int i = 0; i < plainText.length(); i++) {
            char keyWordChar = keyWordString.charAt(i);

            int alphabetIndex = keyWordChar - 'A';

            String cipherLetter = caesarCipher.encrypt(String.valueOf(keyWordChar), alphabetIndex);
            cipherOutput.append(cipherLetter);
        }
        return cipherOutput.toString(); //should use default toString() method inherited? I hope...
    }



}
