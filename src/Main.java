public class Main {

    public String getUserInput(){
        CleanText cleanText = new CleanText();
        System.out.print("Enter your Cipher: ");
        String userInput = cleanText.getInput();
        System.out.print("You chose ");
        System.out.println(userInput);
        return userInput;
    }

    public static void main(String[] args){
        Main main = new Main();
        main.getUserInput();
    }
}
