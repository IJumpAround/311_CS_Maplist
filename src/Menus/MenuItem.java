package Menus;
enum options {ADD_MAP,ADD_ZONE,ADD_RECORD,DELETE_MAP,DELETE_ZONE,DELETE_RECORD, VIEW,SEARCH_MAPS,SEARCH_ZONES,
    SEARCH_RECORDS,NEXT,PREVIOUS,NONE,EXIT,VIEW_ZONES,VIEW_RECORDS}

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
