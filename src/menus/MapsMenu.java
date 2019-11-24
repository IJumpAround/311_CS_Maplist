package menus;

import objectBuilder.ReadObject;
import objectBuilder.WriteObject;
import exceptions.DuplicateEntryException;
import maps.Map;
import maps.MapList;
import menus.menuclasses.optionsEnum;
import records.WR;

import java.util.Arrays;

import static menus.MenuHelpers.*;
import static menus.ZonesMenu.zonesMenu;
import static menus.RecordsMenu.recordsMenu;

public class MapsMenu {

    /**
     * Initial goal was to make this generic so that we didn't need 3 copies of what is essentially the same
     * code, but ran out of time.
     * Essentially the driver class for the mapsmenu interface
     * @param maps List of all maps in the program
     */
    public static void mapsMenu(MapList maps) {
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

            //Call execute. Load will return a maplist if it is called, any other possibility returns null
            MapList temp = mapsExecute(maps, choice);
            if(temp != null) {
                maps = temp;    //load in the new mapList if necessary
            }
            //Clear screen before end of loop, print any error messages here so they show at the top of screen
            clearScreen();
            if(Menus.status.compareTo("") != 0) {
                System.out.println(Menus.status);
                Menus.status = "";
            }
        }
    }

    /**
     * Executes the chosen command entered by the user.
     * @param maps maplist
     * @param choice menu choice the user made
     * @return Almost alway returns null. Will only return a maplist when the user chooses to load from a file
     */
    private static MapList mapsExecute(MapList maps, optionsEnum choice) {

        switch (choice){

            case ADD_MAP:
                addMapPrompt(maps);
                break;
            case DELETE_MAP:
                deleteMapPrompt(maps);
                break;
            case VIEW:
                //Bring up zone/record submenu for the current map
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
            case WRITE:
                //Prompt for a filename then begin the save process
                String filename = promptForString("Enter a filename","ObjectGson.gson");
                WriteObject.writeObject(filename,maps);
                break;
            case LOAD: {
                //prompt the user to load from a file then begin loading from the file
                //Returns the loaded mapList to the calling function
                String proceed = promptWithOptions(Arrays.asList("y","n"),"Loading from a file will overwrite the current program data, continue?");
                if(proceed.compareTo("y") == 0) {
                    int replayGen = WR.getReplayIDGen();
                    WR.setReplayIDGen(0);
                    String fname = promptForString("Enter the filename to load from", "ObjectGson.gson");
                    MapList loaded = ReadObject.loadObject(fname);
                    if(loaded == null)
                        WR.setReplayIDGen(replayGen);
                    else
                        Menus.status = "[Info]: Loaded from " + fname;
                    return loaded;
                }
            }
            case EXIT:
            case NONE:
                break;
        }
        return null;
    }

    /**
     * Prompt the user for information required to make a map, then add it to the list of maps.
     * @param maps map list
     */
    private static void addMapPrompt(MapList maps) {
        String name;
        short tier = 0;
        clearScreen();
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
     * Delete the currently selected map.
     * Prompts the user to make sure they want to delete the selected map
     * @param maps maplist
     */
    private static void deleteMapPrompt(MapList maps) {
        if(maps.getCount() == 0)
            return;
        String response = "";


        Map curr = maps.getSelectedMap();

        while(response.compareTo("y") != 0 && response.compareTo("n") != 0) {
            clearScreen();
            System.out.println("Are you sure you want to delete " + maps.getSelectedMap().getMapName() + "? \n"
                    + "Note this will delete all " + curr.getZones().getCount() + " zones "
                    + "and " + curr.getCompletions() + " records for this map (y/n): ");
            response = MenuHelpers.promptForString().toLowerCase();
        }

        //Delete map otherwise do nothing
        if(response.compareTo("y") == 0) {
            maps.deleteMap();
        }
    }

    /**
     * When selecting an individual map, choose to edit either zones or records
     * @param map to show information about
     */
    private static void mapsSubMenu(Map map) {
        optionsEnum choice;

        //Clear screen and print options for this submenu
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

    /**
     * Search the maplist for a name entered by the user.
     * @param maps list of maps
     */
    private static void searchMaps(MapList maps) {
        int index;
        String response;

        clearScreen();
        System.out.println("Enter the mapname you want to search for");
        response = MenuHelpers.promptForString();
        index = maps.findMapByName(response);

        //Set a status message if not found
        if(index == -1) {
            Menus.status = "Map: " + response + " was not found";
        }
        else //otherwise select the found map
            maps.setCursor(index);
    }

}
