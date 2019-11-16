package menus;

import ABC.ABCSelectionList;
import menus.menuclasses.Menus;

import java.util.ArrayList;

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
            input = Menus.promptForString();
        }
        return input;
    }


}
