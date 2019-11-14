package menus.menuClasses;

import java.util.ArrayList;

public class MenuClass {
    protected String title;
    protected ArrayList<MenuItem> items;
    //protected int choice;
    protected options menuChoice;

    //Constructors
    public MenuClass(String title) {
        this.title = title;
        items = new ArrayList<>();
        menuChoice = options.NONE;
    }

    public MenuClass(String title, ArrayList<MenuItem> items) {
        this.title = title;
        this.items = items;
        menuChoice = options.NONE;
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
    public options promptForMenuChoice() {
        int option = Menus.getMenuChoice();
        for(MenuItem item: items) {
            if(item.getNumber() == option) {
                menuChoice = item.getIdentifier();
                return menuChoice;
            }
        }
        menuChoice = options.NONE;
        return menuChoice;
    }

    public void addItem(int index, String name, options identifier) {
        items.add(new MenuItem(index, name, identifier));
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public options getMenuChoice() {
        return menuChoice;
    }

    public void setMenuChoice(options menuChoice) {
        this.menuChoice = menuChoice;
    }
}
