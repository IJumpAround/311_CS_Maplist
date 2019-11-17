package menus;

import menus.menuclasses.MenuClass;
import menus.menuclasses.optionsEnum;

import java.util.Scanner;

/**
 * Class to hold static instances of each MenuClass type.
 * setupMenus() must be called before attempting to use any of the contained menus.
 */
public  class Menus {
    public static MenuClass mapMenu;
    public static MenuClass zoneMenu;
    public static MenuClass recordMenu;
    public static MenuClass mapSubMenu;
    static Scanner reader = new Scanner(System.in);
    public static String status = "";

    /**
     * Create menu items for each menu
     */
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
        mapMenu.addItem(5,"Previous", optionsEnum.PREVIOUS);
        mapMenu.addItem(6,"Next", optionsEnum.NEXT);
        mapMenu.addItem(7,"Save", optionsEnum.WRITE);
        mapMenu.addItem(8,"Load", optionsEnum.LOAD);
        mapMenu.addItem(0,"Exit", optionsEnum.EXIT);

        //Setup zones menu
        zoneMenu.addItem(1,"Add Zone", optionsEnum.ADD_ZONE);
        zoneMenu.addItem(2,"Delete Zone", optionsEnum.DELETE_ZONE);
        zoneMenu.addItem(3,"Set Target", optionsEnum.SET_TARGET);
        zoneMenu.addItem(4,"Search Zones", optionsEnum.SEARCH_ZONES);
        zoneMenu.addItem(5,"Previous", optionsEnum.PREVIOUS);
        zoneMenu.addItem(6,"Next", optionsEnum.NEXT);
        zoneMenu.addItem(0,"Back", optionsEnum.EXIT);

        //Setup records menu
        recordMenu.addItem(1,"Add Record", optionsEnum.ADD_RECORD);
        recordMenu.addItem(2,"Delete Record", optionsEnum.DELETE_RECORD);
        recordMenu.addItem(3,"Search Records", optionsEnum.SEARCH_RECORDS);
        recordMenu.addItem(4,"Previous", optionsEnum.PREVIOUS);
        recordMenu.addItem(5,"Next", optionsEnum.NEXT);
        recordMenu.addItem(0,"Back", optionsEnum.EXIT);

        mapSubMenu.addItem(1,"Zones", optionsEnum.VIEW_ZONES);
        mapSubMenu.addItem(2, "Records", optionsEnum.VIEW_RECORDS);
        mapSubMenu.addItem(0,"Back", optionsEnum.EXIT);
    }


}
