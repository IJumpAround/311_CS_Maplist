package Menus;

import java.util.Scanner;

public  class Menus {
    public static MenuClass mapMenu;
    public static MenuClass zoneMenu;
    public static MenuClass recordMenu;
    public static MenuClass mapSubMenu;
    static Scanner reader = new Scanner(System.in);

    public static void setupMenus() {
        mapMenu = new MenuClass();
        zoneMenu = new MenuClass();
        recordMenu = new MenuClass();

        //Setup Maps menu
        mapMenu.addItem(1,"Add Map",options.ADD_MAP);
        mapMenu.addItem(2,"Delete Map",options.DELETE_MAP);
        mapMenu.addItem(3,"View/Modify",options.VIEW);
        mapMenu.addItem(4,"Search Maps",options.SEARCH_MAPS);
        mapMenu.addItem(5,"Next",options.NEXT);
        mapMenu.addItem(6,"Previous",options.PREVIOUS);
        mapMenu.addItem(0,"Exit",options.EXIT);

        //Setup zones menu
        zoneMenu.addItem(1,"Add Zone",options.ADD_ZONE);
        zoneMenu.addItem(2,"Delete Zone",options.DELETE_ZONE);
        zoneMenu.addItem(3,"View/Modify",options.VIEW);
        zoneMenu.addItem(4,"Search Zones",options.SEARCH_ZONES);
        zoneMenu.addItem(5,"Next",options.NEXT);
        zoneMenu.addItem(6,"Previous",options.PREVIOUS);
        zoneMenu.addItem(0,"Exit",options.EXIT);

        //Setup records menu
        recordMenu.addItem(1,"Add Record",options.ADD_RECORD);
        recordMenu.addItem(2,"Delete Record",options.DELETE_RECORD);
        recordMenu.addItem(3,"View/Modify",options.VIEW);
        recordMenu.addItem(4,"Search Records",options.SEARCH_RECORDS);
        recordMenu.addItem(5,"Next",options.NEXT);
        recordMenu.addItem(6,"Previous",options.PREVIOUS);
        recordMenu.addItem(0,"Exit",options.EXIT);

        mapSubMenu.addItem(1,"Zones",options.VIEW_ZONES);
        mapSubMenu.addItem(2,"Records",options.VIEW_RECORDS);
        mapSubMenu.addItem(0,"Exit",options.EXIT);
    }

    /**
     * Get the user's input for a menu option
     * @return -1 if invalid input, and the number otherwise
     */
    public static int getMenuChoice() {
        String line;
        int option = -1;
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

}
