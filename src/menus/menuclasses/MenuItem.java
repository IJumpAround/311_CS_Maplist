package menus.menuclasses;

public class MenuItem {
    private int number;
    private String name;
    private optionsEnum identifier;

    public MenuItem() {
        number = -1;
        name = "";
        identifier = optionsEnum.NONE;
    }

    public MenuItem(int number, String name, optionsEnum identifier) {
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

    public optionsEnum getIdentifier() {
        return identifier;
    }

    public void setIdentifier(optionsEnum identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return number + ") " + name;
    }
}
