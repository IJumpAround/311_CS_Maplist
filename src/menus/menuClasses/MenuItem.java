package menus.menuClasses;

public class MenuItem {
    private int number;
    private String name;
    private options identifier;

    public MenuItem() {
        number = -1;
        name = "";
        identifier = options.NONE;
    }

    public MenuItem(int number, String name, options identifier) {
        this.number = number;
        this.name = name;
        this.identifier = identifier;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public options getIdentifier() {
        return identifier;
    }

    public void setIdentifier(options identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return number + ") " + name;
    }
}
