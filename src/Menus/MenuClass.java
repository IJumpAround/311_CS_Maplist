package Menus;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuClass {
    protected ArrayList<MenuItem> items;
    protected int choice;

    public MenuClass() {
        items = new ArrayList<>();
    }

    public MenuClass(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public void DisplayMenu() {
        for(MenuItem item: items) {
            System.out.println(item.toString());
        }
    }

    /**
     * Uses the scanner object in Menus to prompt the user for input.
     * The int returned is converted to an enum and the enum is returned
     * @return option enum
     */
    public options getInput() {
        int option = Menus.getMenuChoice();
        for(MenuItem item: items) {
            if(item.getNumber() == option)
                return item.getIdentifier();
        }
        return options.NONE;
    }

    public void addItem(int index, String name, options identifier) {
        items.add(new MenuItem(index, name, identifier));
    }
}
