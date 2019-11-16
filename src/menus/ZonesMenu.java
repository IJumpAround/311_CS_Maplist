package menus;

import menus.menuClasses.options;
import zoning.*;
import exceptions.DuplicateEntryException;
import menus.menuClasses.Menus;

import java.util.ArrayList;
import java.util.Arrays;

import static menus.MenuHelpers.clearScreen;
import static menus.MenuHelpers.outputCurrentItem;
import static menus.MenuHelpers.promptForOptions;

public class ZonesMenu {
    public static int zonesMenu(ZoneList zones) {
        options choice;
        clearScreen();

        /**
         * Main loop for menu options
         */
        while (Menus.zoneMenu.getMenuChoice() != options.EXIT) {
            Menus.zoneMenu.DisplayMenu();
            outputCurrentItem(zones);
            choice = Menus.zoneMenu.promptForMenuChoice();
            zonesExecute(zones, choice);

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

    public static int zonesExecute(ZoneList zones, options choice) {

        switch (choice){

            case ADD_ZONE:
                addZonePrompt(zones);
                break;
            case DELETE_ZONE:
                deleteZonePrompt(zones);
                break;
            case VIEW:
                //zonesSubMenu(zones.getSelectedMap());
                break;
            case SEARCH_ZONES:
                //searchMaps(zones);
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

    /**
     * Zones are created as rectangular cuboids.
     * The user is asked for two coordinates, two opposite corners of the objects base.
     * The user is then asked to provide the height of the zone from which the remaining points can be determined.
     * @param zones
     */
    public static void addZonePrompt(ZoneList zones) {
        Zone zoneToAdd;
        boolean zMatch = false;
        String name = "";
        String type = "";
        clearScreen();
        int zHeight = 0;

        System.out.println("Enter a name or identifier for this zone: ");
        name = Menus.promptForString();

        //Determine type of zone
        while(type.compareTo("timer") != 0 && type.compareTo("teleport") != 0) {
            System.out.println("Zone type: (timer|teleport)");
            type = Menus.promptForString();
        }



        //Prompts specific to each zone type
        String source = "";
        String input = "";
        timerType tType = null;
        teleportType teleType;
        boolean isStart = false;
        if(type.compareTo("timer") == 0) {
            input = promptForOptions(new ArrayList<>(Arrays.asList("start", "end")), "Is this a start or end zone");
            isStart = input.compareTo("start") == 0;
            input = promptForOptions(new ArrayList<>(Arrays.asList("main", "bonus")), "Is this a main or bonus zone");
            tType = (input.compareTo("main") == 0) ? timerType.MAIN : timerType.BONUS;
        }
        else {
           source = promptForOptions(new ArrayList<>(Arrays.asList("source","destination")), "Is this the source or destination teleporter");
           teleType = source.compareTo("source") == 0 ? teleportType.SOURCE : teleportType.DESTINATION;
        }

        Coordinate c1,c2;
        long[] temp1 = {0,0,0};
        long[] temp2 = {0,0,0};

        //Get 2 coordinates
        while(!zMatch) {

            System.out.println("Create a zone by entering opposite corners of the base of the cuboid");
            System.out.println("z values must match for both corners");
            System.out.println("Enter the first coordinate: (x y z) ");
            temp1 = Menus.promptForCoords();

            System.out.println("Enter the second coordinate: (x y z) ");
            temp2 = Menus.promptForCoords();

            if(temp1[2] == temp2[2]) {
                zMatch = true;
            }
            else
                System.out.println("[Error]: Z values did not match");


        }

        c1 = new Coordinate(temp1[0],temp1[1],temp1[2]);
        c2 = new Coordinate(temp2[0],temp2[1],temp2[2]);

        //Get height
        System.out.println("Enter the height of this zone: ");
        zHeight = Menus.promptForNumber();

        //Default to 1
        zHeight = (zHeight == 0) ? 1 : zHeight;

        //TODO update constructors in child classes
        if(type.compareTo("timer") == 0)
            zoneToAdd = new TimerZone(name,c1,c2,zHeight,tType,isStart);
        else
            zoneToAdd = new TeleportZone(name,c1,c2,zHeight);

        //Add the zone, print an error if this name is a duplicate
        try {
            zones.addZone(zoneToAdd);
        }
        catch (DuplicateEntryException e) {
            Menus.status = e.getMessage();
        }

    }

    /**
     * Prompt to delete a zone.
     * @param zones
     */
    public static void deleteZonePrompt(ZoneList zones) {
        if(zones.getCount() == 0)
            return;

        String response = "";
        while(response.compareTo("y") != 0 && response.compareTo("n") != 0) {
            clearScreen();
            System.out.println("Are you sure you want to delete " + zones.getSelectedZone().getID()
            +" (y|n)");
            response = Menus.promptForString().toLowerCase();
        }

        //Delete zone otherwise do nothing
        if(response.compareTo("y") == 0) {
            zones.deleteZone();
        }
    }
}
