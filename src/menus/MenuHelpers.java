package menus;

import ABC.ABCSelectionList;

import java.util.ArrayList;

/**
 * Various helper methods for prompting the user during menu interaction
 */
public class MenuHelpers {

    public static void outputCurrentItem(ABCSelectionList list) {
        String cursor = (list.getCount() > 0) ? String.valueOf(list.getCursor()+1) : "0";
        String max = (list.getCount()>0) ? String.valueOf(list.getCount()) : "0";

        String type = list.getType();
        System.out.println("\n\n" + type);
        System.out.println("Current Selection: " + cursor + " of " + max);
        //System.out.println("Number of " + type + "s: " + list.getCount());
        System.out.println(list.prettyCurrentItem());
    }

    /**
     * Nice terminal refresh courtesy of stack overflow
     * @author Satish
     * https://stackoverflow.com/questions/2979383/java-clear-the-console
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Provided a list of options and a prompt, format the prompt
     * and continue to prompt the user until they enter a value contained in options
     * @param options list of options the user should enter
     * @param prompt  prompt to show the user
     * @return returns the string they entered
     */
    public static String promptForOptions(ArrayList<String> options, String prompt) {
        String input ="";
        String optionsFormatter = options.toString();
        optionsFormatter = optionsFormatter.replace(',', '|').replace('[', '(').replace(']',')');

        while(!options.contains(input)) {
            System.out.println(prompt + " " + optionsFormatter);
            System.out.print(">");
            input = promptForString();
        }
        return input;
    }


    /**
     * Get the user's input for a menu option
     * @return -1 if invalid input, and the number otherwise
     */
    public static int getMenuChoice() {
        String line;
        int option = -1;

        System.out.print(">");
        if(Menus.reader.hasNext()) {
            line = Menus.reader.nextLine().strip();

            try {
                option = Integer.parseInt(line);
            }
            catch(NumberFormatException e) {
                option = -1;
            }
        }

        return option;
    }

    /**
     * Prompt for a string input.
     * Continues to prompt until the user enters a string.
     * IE: Doesn't allow just pressing enter without text
     * @return the entered string
     */
    public static String promptForString() {
        String line= "";

        while(line.compareTo("") == 0) {
            System.out.print(">");
            if(Menus.reader.hasNext()) {
                line = Menus.reader.nextLine().strip();
            }
        }
        return line;
    }

    /**
     * Prompt the user to enter a number.
     * Continues to prompt until they enter a valid number
     * @return the number entered
     */
    public static int promptForNumber() {
        String input;
        int value = -1;


        while(value == -1) {
            System.out.print(">");

            if(Menus.reader.hasNext()) {
                input = Menus.reader.nextLine().strip();
                try {
                    value = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    value = -1;
                }
            }
        }
        return value;
    }

    /**
     * Prompt the user for three integer values each separated by a space
     * Store the values into a 3 length array and return the array
     * @return int[3]
     */
    public static long[] promptForCoords() {
        String input = "";
        long[] coords = {0,0,0};
        boolean done = false;

        while(!done) {
            System.out.print(">");

            if(Menus.reader.hasNext()) {
                input = Menus.reader.nextLine().replace('(',' ').replace(')',' ').strip();
                String[] s = input.split("\\s",3);

                //Parse split input into ints
                try {
                    coords[0] = Long.parseLong(s[0]);
                    coords[1] = Long.parseLong(s[1]);
                    coords[2] = Long.parseLong(s[2]);
                    done = true;
                }
                catch (NumberFormatException e) {
                    System.out.println("Enter three numbers space separated");
                }
            }

        }
        return coords;
    }
}
