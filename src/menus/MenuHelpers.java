package menus;

import abc.ABCSelectionList;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Various helper methods for prompting the user during menu interaction
 */
public class MenuHelpers {

    /**
     * Prints most of the selection information seen on the interface
     * @param list list of objects for the current menu
     */
    static void outputCurrentItem(ABCSelectionList list) {
        String cursor = (list.getCount() > 0) ? String.valueOf(list.getCursor() + 1) : "0"; //convert from 0 index to 1 for display
        String max = (list.getCount() > 0) ? String.valueOf(list.getCount()) : "0";

        String type = list.getTypeName();
        System.out.println("\n\n" + type);
        System.out.println("Current Selection: " + cursor + " of " + max);
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
     *
     * @param options list of options the user should enter
     * @param prompt  prompt to show the user
     * @return returns the string they entered
     */
    public static String promptWithOptions(List<String> options, String prompt) {
        String input = "";
        String optionsFormatter = "(" + String.join("|", options) + ")";


        while (!options.contains(input)) {
            System.out.println(prompt + " " + optionsFormatter);
            System.out.print(">");
            input = promptForString();
        }
        return input;
    }


    /**
     * Get the user's input for a menu option
     *
     * @return -1 if invalid input, and the number otherwise
     */
    public static int getMenuChoice() {
        String line;
        int option = -1;

        System.out.print(">");
        if (Menus.reader.hasNext()) {
            line = Menus.reader.nextLine().strip();

            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                option = -1;
            }
        }

        return option;
    }

    /**
     * Prompt for a string input.
     * Continues to prompt until the user enters a string.
     * IE: Doesn't allow just pressing enter without text
     *
     * @return the entered string
     */
    static String promptForString(String prompt) {
        String line = "";

        System.out.println(prompt);
        while (line.compareTo("") == 0) {
            System.out.print(">");
            if (Menus.reader.hasNext()) {
                line = Menus.reader.nextLine().strip();
            }
        }
        return line;
    }

    /**
     * First implementation, others are better.
     * @return string entered by user
     */
    static String promptForString() {
        String line = "";

        while (line.compareTo("") == 0) {
            System.out.print(">");
            if (Menus.reader.hasNext()) {
                line = Menus.reader.nextLine().strip();
            }
        }
        return line;
    }

    /**
     * Variant of string prompt. Pass in the prompt the user should see.
     * Allows a default option if the user chooses nothing.
     *
     * @param prompt        prompt to display to user
     * @param defaultOption default choice
     * @return either user input or default
     */
    static String promptForString(String prompt, String defaultOption) {
        String line;
        System.out.println(prompt + " (default=" + defaultOption + ")");

        System.out.print(">");
        // if (Menus.reader.()) {
        line = Menus.reader.nextLine().strip();
        //}

        if (line.compareTo("") == 0)
            return defaultOption;
        return line;
    }

    /**
     * Prompt the user to enter a number.
     * Continues to prompt until they enter a valid number
     *
     * @return the number entered
     */
    //TODO comma in number still parses IE 39, -> 39.  0
    static int promptForNumber() {
        String input;
        int value = -1;


        while (value == -1) {
            System.out.print(">");

            if (Menus.reader.hasNext()) {
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
     * Prompt for a number from the user and display a prompt specified by the caller
     *
     * @param prompt optional prompt to display to the user
     * @return int
     */
    static int promptForNumber(String prompt) {
        String input;
        int value = -1;

        System.out.println(prompt);
        while (value == -1) {
            System.out.print(">");

            if (Menus.reader.hasNext()) {
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
     *(x y z) coordinates
     * @return int[3]
     */
    static long[] promptForCoords() {
        String input;
        long[] coords = {0, 0, 0};
        boolean done = false;

        while (!done) {
            System.out.print(">");

            if (Menus.reader.hasNext()) {
                //strip out parenthesis if the user decided to enter them
                input = Menus.reader.nextLine().replace('(', ' ').replace(')', ' ').strip();
                String[] s = input.split("\\s", 3);

                //Parse split input into ints
                try {
                    coords[0] = Long.parseLong(s[0]);
                    coords[1] = Long.parseLong(s[1]);
                    coords[2] = Long.parseLong(s[2]);
                    done = true;
                } catch (Exception e) {
                    System.out.println("Enter three numbers space separated");
                }
            }

        }
        return coords;
    }

    /**
     * Prompts for the completion time of a user when adding a record to a map
     * @return the string converted to a Duration
     */
    static Duration promptForTime() {
        String input;

        Duration time;
        System.out.println("Enter the player's time (hh:mm:ss.SSS)");
        System.out.println("EX: 1:03:44.256");

        //loops until correct imput calls break;
        while (true) {
            System.out.print(">");

            if (Menus.reader.hasNext()) {
                input = Menus.reader.nextLine().strip();

                //Reformat the input to match ISO standard
                int colonCount = countOccurrences(input, ':');
                if (colonCount == 2) {
                    input = input.replaceFirst(":", "H");
                    input = input.replace(':', 'M');
                } else if (colonCount == 1) {
                    input = input.replace(':', 'M');
                }
                input = "PT" + input + "S";

                //Parse the input to a duration
                try {
                    time = Duration.parse(input);
                    break;
                } catch (DateTimeParseException dtp) {
                    System.out.println("Make sure the format is correct");
                }
            }
        }
        return time;
    }

    /**
     * Count number of occurrences of a character c in the input string
     * @param input string to check
     * @param c     target char
     * @return count
     */
    private static int countOccurrences(String input, char c) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /**
     * Prompt for a yes/no question and convert it to boolean
     *
     * @param prompt prompt to display
     * @return true if yes was chosen, false otherwise
     */
    static boolean promptForComfirmation(String prompt) {
        String line;

        line = promptWithOptions(Arrays.asList("y", "n"), prompt);

        return (line.compareTo("y") == 0);
    }
}
