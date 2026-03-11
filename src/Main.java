public class Main {

    public String getUserInput(){
        CleanText cleanText = new CleanText();
        System.out.print("Enter your Cipher: ");
        String userInput = cleanText.getInput();
        System.out.print("You chose ");
        System.out.println(userInput);
        return userInput;
    }


    //below is what allows this file to be run by IntelliJ
    public static void main(String[] args){
        Main main = new Main();
        main.getUserInput();
    }
}
