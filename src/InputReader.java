import java.util.Scanner;

public class InputReader {
    static Scanner reader = new Scanner(System.in);

    /**
     * Get the user's input for a menu option
     * @return -1 if invalid input, and the number otherwise
     */
    public static int getMenuChoice() {
        String line;
        int option = -1;
        if(reader.hasNext()) {
            line = reader.nextLine().strip();

            try {
                option = Integer.parseInt(line);
            }
            catch(NumberFormatException e) {
                option = -1;
            }
        }
        return option;
    }

}
