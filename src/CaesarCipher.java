public class CaesarCipher extends Cipher {

    //take user input from file, having already been run through cleanText.
    //also implement a piece asking for the shift value to use for this cipher
    private String userInput;
    private String cipherOutput;
    StringBuilder output = new StringBuilder();

    public String encrypt (String userInput, int shift) {

        for (int i = 0; i < userInput.length(); i++) {
            char charUserInput = userInput.charAt(i);

            char encrypted = (char)((charUserInput - 'A' + shift) % 26 + 'A');

            output.append(encrypted);
        }

        cipherOutput = output.toString();
        return cipherOutput;
    }


}
