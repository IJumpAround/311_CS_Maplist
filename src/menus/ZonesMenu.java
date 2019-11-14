package menus;

import menus.menuClasses.options;
import zoning.Coordinate;
import zoning.Zone;
import zoning.ZoneList;
import exceptions.DuplicateEntryException;
import menus.menuClasses.Menus;

import static menus.MenuHelpers.clearScreen;
import static menus.MenuHelpers.outputCurrentItem;

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
                //deleteMapPrompt(zones);
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


        //Add the zone, print an error if this name is a duplicate
        try {
            zones.addZone(new Zone(name, c1, c2, zHeight));
        }
        catch (DuplicateEntryException e) {
            Menus.status = e.getMessage();
        }

    }
}
