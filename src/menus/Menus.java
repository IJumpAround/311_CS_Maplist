package menus;

import menus.menuclasses.MenuClass;
import menus.menuclasses.optionsEnum;

import java.util.Scanner;

/**
 * Class to hold static instances of each MenuClass type.
 * Initializes all menus and their choices.
 * setupMenus() must be called before attempting to use any of the contained menus.
 */
public  class Menus {
    static MenuClass mapMenu;                           //Menu for maps
    static MenuClass zoneMenu;                          //Menu for zones
    static MenuClass recordMenu;                        //Menu for records
    static MenuClass mapSubMenu;                        //Menu for choosing between zones or records
    static final Scanner reader = new Scanner(System.in);     //Reader opens stdin for user input
    public static String status = "";                   //Set to display a status message after events

    /**
     * Initialize all menus and their options
     */
    static void setupMenus() {
        mapMenu = new MenuClass("Map Management");
        zoneMenu = new MenuClass("Zone Management");
        recordMenu = new MenuClass("Record Management");
        mapSubMenu = new MenuClass("Select an entry type");

        //Setup Maps menu
        mapMenu.addMenuItem(1,"Add Map", optionsEnum.ADD_MAP);
        mapMenu.addMenuItem(2,"Delete Map", optionsEnum.DELETE_MAP);
        mapMenu.addMenuItem(3,"View/Modify", optionsEnum.VIEW);
        mapMenu.addMenuItem(4,"Search Maps", optionsEnum.SEARCH_MAPS);
        mapMenu.addMenuItem(5,"Previous", optionsEnum.PREVIOUS);
        mapMenu.addMenuItem(6,"Next", optionsEnum.NEXT);
        mapMenu.addMenuItem(7,"Save", optionsEnum.WRITE);
        mapMenu.addMenuItem(8,"Load", optionsEnum.LOAD);
        mapMenu.addMenuItem(0,"Exit", optionsEnum.EXIT);

        //Setup zones menu
        zoneMenu.addMenuItem(1,"Add Zone", optionsEnum.ADD_ZONE);
        zoneMenu.addMenuItem(2,"Delete Zone", optionsEnum.DELETE_ZONE);
        zoneMenu.addMenuItem(3,"Set Target", optionsEnum.SET_TARGET);
        zoneMenu.addMenuItem(4,"Search Zones", optionsEnum.SEARCH_ZONES);
        zoneMenu.addMenuItem(5,"Previous", optionsEnum.PREVIOUS);
        zoneMenu.addMenuItem(6,"Next", optionsEnum.NEXT);
        zoneMenu.addMenuItem(0,"Back", optionsEnum.EXIT);

        //Setup records menu
        recordMenu.addMenuItem(1,"Add Record", optionsEnum.ADD_RECORD);
        recordMenu.addMenuItem(2,"Delete Record", optionsEnum.DELETE_RECORD);
        recordMenu.addMenuItem(3,"Search Records", optionsEnum.SEARCH_RECORDS);
        recordMenu.addMenuItem(4,"Previous", optionsEnum.PREVIOUS);
        recordMenu.addMenuItem(5,"Next", optionsEnum.NEXT);
        recordMenu.addMenuItem(0,"Back", optionsEnum.EXIT);

        mapSubMenu.addMenuItem(1,"Zones", optionsEnum.VIEW_ZONES);
        mapSubMenu.addMenuItem(2, "Records", optionsEnum.VIEW_RECORDS);
        mapSubMenu.addMenuItem(0,"Back", optionsEnum.EXIT);
    }


}
