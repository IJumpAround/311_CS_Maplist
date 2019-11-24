package menus.menuclasses;

import menus.MenuHelpers;

import java.util.ArrayList;

/**
 * This class is used to display & organize menus. It stores a list of MenuItems which
 * represent all the choices that are valid for that menu.
 * Can convert user input into a valid choice for a menu
 */
public class MenuClass {
    private String title;               //Title to display when this menu is active
    private ArrayList<MenuItem> items;  //All possible choices for this menu
    private optionsEnum menuChoice;     //Most recent choice the user has made

    /**
     * Creates a menu with the given title and sets the choice to none
     * @param title
     */
    public MenuClass(String title) {
        this.title = title;
        items = new ArrayList<>();
        menuChoice = optionsEnum.NONE;
    }

    /**
     * Reset choice to NONE
     */
    public void clearChoice() {
        this.menuChoice = optionsEnum.NONE;
    }

    /**
     * Display all menu items and the title
     */
    public void DisplayMenu() {
        System.out.println(title);
        for(MenuItem item: items) {
            System.out.println(item.toString());
        }
    }

    /**
     * Uses the scanner object in Menus to prompt the user for input.
     * The int returned is converted to an enum and the enum is returned
     * @return option enum from the chosen item. None if the choice is invalid
     */
    public optionsEnum promptForMenuChoice() {
        int option = MenuHelpers.getMenuChoice();
        for(MenuItem item: items) {
            if(item.getNumber() == option) {
                menuChoice = item.getIdentifier();
                return menuChoice;
            }
        }
        menuChoice = optionsEnum.NONE;
        return menuChoice;
    }

    /**
     * Adds a new choice to the list of menu choices.
     * No error checking for duplicate choices in a menu
     * @param index number the user should enter to select this item
     * @param name menu item text to be displayed
     * @param identifier enum representing the choice
     */
    public void addMenuItem(int index, String name, optionsEnum identifier) {
        items.add(new MenuItem(index, name, identifier));
    }

    /**
     * Returns enum of the currently selected item
     * @return enum
     */
    public optionsEnum getMenuChoice() {
        return menuChoice;
    }

}
