package menus.menuclasses;

/**
 * Represents a choice in a given menu.
 * Each choice has three properties:
 *  A number the user will enter to choose it
 *  Text that will be displayed on the menu
 *  An enum representing the choice for easy comparison
 */
class MenuItem {
    private final int number;             //number the user should enter to choose this item, will be displayed before the name
    private final String name;            //Identifying text displayed in the menu
    private final optionsEnum identifier; //enum representing the option


    /**
     * Constructor
     * @param number number choice
     * @param name text shown
     * @param identifier enum
     */
    MenuItem(int number, String name, optionsEnum identifier) {
        this.number = number;
        this.name = name;
        this.identifier = identifier;
    }

    /**
     * getter for number choice
     * @return int value of choice
     */
    int getNumber() {
        return number;
    }

    /**
     * getter for enum identifier
     * @return optionsEnum
     */
    optionsEnum getIdentifier() {
        return identifier;
    }

    /**
     * Text that will be displayed on the menu
     * @return string
     */
    @Override
    public String toString() {
        return number + ") " + name;
    }
}
