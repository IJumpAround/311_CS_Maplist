package Menus;

import Menus.MenuClasses.options;
import zoning.ZoneList;
import Exceptions.DuplicateMapException;
import Menus.MenuClasses.Menus;
import Menus.MenuClasses.options;

import static Menus.MenuHelpers.clearScreen;
import static Menus.MenuHelpers.outputCurrentItem;

public class ZonesMenu {
    public static int zonesMenu(ZoneList zones) {
        options choice;
        Menus.setupMenus();
        clearScreen();

        /**
         * Main loop for menu options
         */
        while (Menus.recordMenu.getMenuChoice() != options.EXIT) {
            Menus.recordMenu.DisplayMenu();
            outputCurrentItem(zones);
            choice = Menus.recordMenu.promptForMenuChoice();
            MapsExecute(zones, choice);

            //Clear screen before end of loop, print any error messages here so they show at the top of screen
            clearScreen();
            if(Menus.status.compareTo("") != 0) {
                System.out.println(Menus.status);
                Menus.status = "";
            }

        }
        return 0;
        //retur n choice;
    }

    public static int MapsExecute(ZoneList zones, options choice) {

        switch (choice){

            case ADD_ZONE:
                addZonePrompt(zones);
                break;
            case DELETE_ZONE:
                deleteMapPrompt(zones);
                break;
            case VIEW:
                zonesSubMenu(zones.getSelectedMap());
                break;
            case SEARCH_ZONES:
                searchMaps(zones);
                break;
            case NEXT:
                zones.next();
                break;
            case PREVIOUS:
                zones.prev();
                break;
            case EXIT:
                break;
            case NONE:
                break;
        }
        return -1;
    }

    public static void addZonePrompt(ZoneList zones) {
        String name;
        String type = "";
        clearScreen();

        while(type.compareTo("timer") != 0 && type.compareTo("teleport") != 0) {
            System.out.println("Zone type: (timer|teleport)");
            type = Menus.promptForString();
        }

//        while()
//        System.out.println("Create a zone by entering opposite corners of the base of the cuboid");
//        System.out.println("Enter two coordinates: (x y z)");
//        System.out.println("z values must match for both corners");
//        name = Menus.promptForString();
//
//        while(tier < 1 || tier > 10) {
//            System.out.println("Enter the map Tier (1-10): ");
//            tier = (short)Menus.promptForNumber();
//        }
//        try {
//            maps.addMap(name, tier);
//        } catch (DuplicateMapException e) {
//            Menus.status = e.getMessage();
//        }
    }
}
