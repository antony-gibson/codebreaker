public class CaesarCipher extends Cipher {

    //take user input from file, having already been run through cleanText.
    //also implement a piece asking for the shift value to use for this cipher
    private String cipherOutput;

    public String encrypt (String fileName, int shift) {

        for (int i = 0; i < fileName.length(); i++) {
            char charUserInput = fileName.charAt(i);

            char encrypted = (char)((charUserInput - 'A' + shift) % 26 + 'A');

            cipherOutput += encrypted;
        }

        return cipherOutput;
    }


}
