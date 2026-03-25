import java.util.Scanner;

public class Cipher {

    public void readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Below is the file content. This is a check, BEFORE SUBMISSION REMOVE ABILITY TO PRINT FILE OUTPUT. ");
            String fileOutput = reader.readLine(); //reads first line of file

            while (fileOutput != null) {
                System.out.println(fileOutput);
                fileOutput = reader.readLine(); //reads the next line
            }

        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }

    }

    public void writeToFile(String fileName, String content) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(fileName))) {
            write.write(content);
            System.out.println("Successfully wrote to the file. THIS IS A TEST, REMOVE THIS BEFORE SUBMISSION.");
        } catch (IOException error) {
            System.err.println("An Error has occurred: " + error.getMessage());
        }
    }
}
