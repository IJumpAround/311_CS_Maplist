package menus.menuclasses;

import menus.MenuHelpers;

import java.util.ArrayList;

public class MenuClass {
    protected String title;
    protected ArrayList<MenuItem> items;
    //protected int choice;
    protected optionsEnum menuChoice;

    //Constructors
    public MenuClass(String title) {
        this.title = title;
        items = new ArrayList<>();
        menuChoice = optionsEnum.NONE;
    }

    public MenuClass(String title, ArrayList<MenuItem> items) {
        this.title = title;
        this.items = items;
        menuChoice = optionsEnum.NONE;
    }

    /**
     * Reset choice to NONE
     */
    public void clearChoice() {
        this.menuChoice = optionsEnum.NONE;
    }

    /**
     * Display all menu items and its title
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
     * @return option enum
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

    public void addItem(int index, String name, optionsEnum identifier) {
        items.add(new MenuItem(index, name, identifier));
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public optionsEnum getMenuChoice() {
        return menuChoice;
    }

    public void setMenuChoice(optionsEnum menuChoice) {
        this.menuChoice = menuChoice;
    }
}
