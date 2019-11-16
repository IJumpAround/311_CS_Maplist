package menus.menuclasses;

import java.util.Scanner;

public  class Menus {
    public static MenuClass mapMenu;
    public static MenuClass zoneMenu;
    public static MenuClass recordMenu;
    public static MenuClass mapSubMenu;
    static Scanner reader = new Scanner(System.in);
    public static String status = "";

    public static void setupMenus() {
        mapMenu = new MenuClass("Map Management");
        zoneMenu = new MenuClass("Zone Management");
        recordMenu = new MenuClass("Record Management");
        mapSubMenu = new MenuClass("Select an entry type");

        //Setup Maps menu
        mapMenu.addItem(1,"Add Map", optionsEnum.ADD_MAP);
        mapMenu.addItem(2,"Delete Map", optionsEnum.DELETE_MAP);
        mapMenu.addItem(3,"View/Modify", optionsEnum.VIEW);
        mapMenu.addItem(4,"Search Maps", optionsEnum.SEARCH_MAPS);
        mapMenu.addItem(5,"Next", optionsEnum.NEXT);
        mapMenu.addItem(6,"Previous", optionsEnum.PREVIOUS);
        mapMenu.addItem(7,"Save", optionsEnum.WRITE);
        mapMenu.addItem(8,"Load", optionsEnum.LOAD);
        mapMenu.addItem(0,"Exit", optionsEnum.EXIT);

        //Setup zones menu
        zoneMenu.addItem(1,"Add Zone", optionsEnum.ADD_ZONE);
        zoneMenu.addItem(2,"Delete Zone", optionsEnum.DELETE_ZONE);
        zoneMenu.addItem(3,"View/Modify", optionsEnum.VIEW);
        zoneMenu.addItem(4,"Search Zones", optionsEnum.SEARCH_ZONES);
        zoneMenu.addItem(5,"Next", optionsEnum.NEXT);
        zoneMenu.addItem(6,"Previous", optionsEnum.PREVIOUS);
        zoneMenu.addItem(0,"Exit", optionsEnum.EXIT);

        //Setup records menu
        recordMenu.addItem(1,"Add Record", optionsEnum.ADD_RECORD);
        recordMenu.addItem(2,"Delete Record", optionsEnum.DELETE_RECORD);
        recordMenu.addItem(3,"View/Modify", optionsEnum.VIEW);
        recordMenu.addItem(4,"Search Records", optionsEnum.SEARCH_RECORDS);
        recordMenu.addItem(5,"Next", optionsEnum.NEXT);
        recordMenu.addItem(6,"Previous", optionsEnum.PREVIOUS);
        recordMenu.addItem(0,"Exit", optionsEnum.EXIT);

        mapSubMenu.addItem(1,"Zones", optionsEnum.VIEW_ZONES);
        mapSubMenu.addItem(2, "Records", optionsEnum.VIEW_RECORDS);
        mapSubMenu.addItem(0,"Exit", optionsEnum.EXIT);
    }

    /**
     * Get the user's input for a menu option
     * @return -1 if invalid input, and the number otherwise
     */
    public static int getMenuChoice() {
        String line;
        int option = -1;

        System.out.print(">");
        if(reader.hasNext()) {
            line = reader.nextLine().strip();

            try {
                option = Integer.parseInt(line);
            }
            catch(NumberFormatException e) {
                option = -1;
            }
        }

        return option;
    }

    /**
     * Prompt for a string input.
     * Continues to prompt until the user enters a string.
     * IE: Doesn't allow just pressing enter without text
     * @return the entered string
     */
    public static String promptForString() {
        String line= "";

        while(line.compareTo("") == 0) {
            System.out.print(">");
            if(reader.hasNext()) {
                line = reader.nextLine().strip();
            }
        }
        return line;
    }

    /**
     * Prompt the user to enter a number.
     * Continues to prompt until they enter a valid number
     * @return the number entered
     */
    public static int promptForNumber() {
        String input;
        int value = -1;


        while(value == -1) {
            System.out.print(">");

            if(reader.hasNext()) {
                input = reader.nextLine().strip();
                try {
                    value = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    value = -1;
                }
            }
        }
        return value;
    }

    /**
     * Prompt the user for three integer values each separated by a space
     * Store the values into a 3 length array and return the array
     * @return int[3]
     */
    public static long[] promptForCoords() {
        String input = "";
        long[] coords = {0,0,0};
        boolean done = false;

        while(!done) {
            System.out.print(">");

            if(reader.hasNext()) {
                input = reader.nextLine().replace('(',' ').replace(')',' ').strip();
                String[] s = input.split("\\s",3);

                //Parse split input into ints
                try {
                    coords[0] = Long.parseLong(s[0]);
                    coords[1] = Long.parseLong(s[1]);
                    coords[2] = Long.parseLong(s[2]);
                    done = true;
                }
                catch (NumberFormatException e) {
                    System.out.println("Enter three numbers space separated");
                }
            }

        }
        return coords;
    }



}
