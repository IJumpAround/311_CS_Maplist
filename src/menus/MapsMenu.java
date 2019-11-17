package menus;

import objectBuilder.ReadObject;
import objectBuilder.WriteObject;
import exceptions.DuplicateEntryException;
import maps.Map;
import maps.MapList;
import menus.menuclasses.optionsEnum;

import static menus.MenuHelpers.clearScreen;
import static menus.MenuHelpers.outputCurrentItem;
import static menus.ZonesMenu.zonesMenu;
import static menus.RecordsMenu.recordsMenu;

public class MapsMenu {

    /**
     * Initial goal was to make this generic so that we didn't need 3 copies of what is essentially the same
     * code, but ran out of time.
     * @param maps
     * @return
     */
    public static int mapsMenu(MapList maps) {
        optionsEnum choice;
        Menus.setupMenus();
        clearScreen();

        /**
         * Main loop for menu options
         */
        while (Menus.mapMenu.getMenuChoice() != optionsEnum.EXIT) {
            Menus.mapMenu.DisplayMenu();
            outputCurrentItem(maps);
            choice = Menus.mapMenu.promptForMenuChoice();

            //Call execute, load will return a maplist if it is called any other possibility returns null
            MapList temp = mapsExecute(maps, choice);
            if(temp != null)
                maps = temp;

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

    public static MapList mapsExecute(MapList maps, optionsEnum choice) {

        switch (choice){

            case ADD_MAP:
                addMapPrompt(maps);
                break;
            case DELETE_MAP:
                deleteMapPrompt(maps);
                break;
            case VIEW:
                Map curr = maps.getSelectedMap();
                if(curr != null)
                    mapsSubMenu(maps.getSelectedMap());
                else
                    Menus.status = "Map list is empty";
                break;
            case SEARCH_MAPS:
                searchMaps(maps);
                break;
            case NEXT:
                maps.next();
                break;
            case PREVIOUS:
                maps.prev();
                break;
            case EXIT:
                break;
            case NONE:
                break;
            case WRITE:
                WriteObject.test(maps);
                break;
            case LOAD:
                 return ReadObject.loadObject("ObjectGson.gson");


        }
        return null;
    }

    /**
     * Prompt the user for a mapname and add a new map to the maplist
     * @param maps map list
     */
    public static void addMapPrompt(MapList maps) {
        String name;
        short tier = 0;
        clearScreen();
        //System.out.println("Enter the map name: ");
        name = MenuHelpers.promptForString("Enter the map name");

        while(tier < 1 || tier > 10) {
            System.out.println("Enter the map Tier (1-10): ");
            tier = (short) MenuHelpers.promptForNumber();
        }
        try {
            maps.addMap(name, tier);
        } catch (DuplicateEntryException e) {
            Menus.status = e.getMessage();
        }
    }

    /**
     * Delete the currently selected map
     * @param maps
     */
    public static void deleteMapPrompt(MapList maps) {
        String response = "";

        if(maps.getCount() == 0)
            return;

        Map curr = maps.getSelectedMap();

        while(response.compareTo("y") != 0 && response.compareTo("n") != 0) {
            clearScreen();
            System.out.println("Are you sure you want to delete " + maps.getSelectedMap().getMapName() + "? \n"
                    + "Note this will delete all " + curr.getZones().getCount() + " zones "
                    + "and " + curr.getCompletions() + "records for this map (y/n): ");
            response = MenuHelpers.promptForString().toLowerCase();
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
        optionsEnum choice;

        //Clear screen and print options for this submenu
        //exit, zones or records
        clearScreen();
        Menus.mapSubMenu.DisplayMenu();
        choice = Menus.mapSubMenu.promptForMenuChoice();

        switch (choice) {
            case VIEW_RECORDS:
                recordsMenu(map.getRecords(),map.getTier());
                break;
            case VIEW_ZONES:
                zonesMenu(map.getZones());
                break;
            case EXIT:
                break;
        }
    }

    public static void searchMaps(MapList maps) {
        int index;
        String response = "";

        clearScreen();
        System.out.println("Enter the mapname you want to search form");
        response = MenuHelpers.promptForString();
        index = maps.findMapByName(response);

        maps.setCursor(index);
        if(index == -1) {
            Menus.status = "Map: " + response + " was not found";
        }


    }
}
