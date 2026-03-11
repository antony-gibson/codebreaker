public class Main {
    //private String userInput;
    //take inspo from Application.java of MonsterAndTreasuresV4
    //public Main(String userInput) {
    //}

    public String getUserInput(){
        CleanText cleanText = new CleanText();
        System.out.print("Enter your Cipher: ");
        cleanText.toString();
        System.out.print("You chose ");
        System.out.println(cleanText.toString());
    }

    public static void main(String[] args){
        Main main = new Main(args[0]);
        main.getUserInput();
    }


}
