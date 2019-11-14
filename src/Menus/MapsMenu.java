package Menus;

import Exceptions.DuplicateMapException;
import Menus.MenuClasses.Menus;
import Menus.MenuClasses.options;
import maps.Map;
import maps.MapList;

import static Menus.MenuHelpers.clearScreen;
import static Menus.MenuHelpers.outputCurrentItem;

public class MapsMenu {

    /**
     * Initial goal was to make this generic so that we didn't need 3 copies of what is essentially the same
     * code, but ran out of time.
     * @param maps
     * @return
     */
    public static int mapsMenu(MapList maps) {
        options choice;
        Menus.setupMenus();
        clearScreen();

        /**
         * Main loop for menu options
         */
        while (Menus.mapMenu.getMenuChoice() != options.EXIT) {
            Menus.mapMenu.DisplayMenu();
            outputCurrentItem(maps);
            choice = Menus.mapMenu.promptForMenuChoice();
            MapsExecute(maps, choice);

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

    public static int MapsExecute(MapList maps, options choice) {

        switch (choice){

            case ADD_MAP:
                addMapPrompt(maps);
                break;
            case DELETE_MAP:
                deleteMapPrompt(maps);
                break;
            case VIEW:
                mapsSubMenu(maps.getSelectedMap());
                break;
            case SEARCH_MAPS:
                break;
            case NEXT:
                break;
            case PREVIOUS:
                break;
            case EXIT:
                break;
            case NONE:
                break;
        }
        return -1;
    }

    /**
     * Prompt the user for a mapname and add a new map to the maplist
     * @param maps map list
     */
    public static void addMapPrompt(MapList maps) {
        String name;
        short tier = 0;
        clearScreen();
        System.out.println("Enter the map name: ");
        name = Menus.promptForString();

        while(tier < 1 || tier > 10) {
            System.out.println("Enter the map Tier (1-10): ");
            tier = (short)Menus.promptForNumber();
        }
        try {
            maps.addMap(name, tier);
        } catch (DuplicateMapException e) {
            Menus.status = e.getMessage();
        }
    }

    /**
     * Delete the currently selected map
     * @param maps
     */
    public static void deleteMapPrompt(MapList maps) {
        String response = "";
        Map curr = maps.getSelectedMap();

        while(response.compareTo("y") != 0 && response.compareTo("n") != 0) {
            clearScreen();
            System.out.println("Are you sure you want to delete " + maps.getSelectedMap().getMapName() + "? \n"
                    + "Note this will delete all " + curr.getZones().size() + " zones "
                    + "and " + curr.getCompletions() + " for this map (y/n): ");
            response = Menus.promptForString().toLowerCase();
        }

        //Delete map otherwise do nothing
        if(response.compareTo("y") == 0) {
            maps.deleteMap();
        }
    }

    /**
     * When selecting an individual map, choose to edit either zones or records
     * @param map
     */
    public static void mapsSubMenu(Map map) {
        options choice;

        //Clear screen and print options for this submenu
        //exit, zones or records
        clearScreen();
        Menus.mapSubMenu.DisplayMenu();
        choice = Menus.mapSubMenu.promptForMenuChoice();

        switch (choice) {
            case VIEW_RECORDS:

                break;
            case VIEW_ZONES:
                break;
            case EXIT:
                break;
        }
    }
}
