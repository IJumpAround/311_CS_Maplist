package Menus;

import Menus.MenuClasses.ABCSelectionList;

public class MenuHelpers {

    public static void outputCurrentItem(ABCSelectionList list) {
        String type = list.getType();
        System.out.println("\n\nCurrently selected item: " + type);
        System.out.println("Number of " + type + "s: " + list.getCount());
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
}
