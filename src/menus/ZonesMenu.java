package menus;

import exceptions.DuplicateEntryException;
import menus.menuclasses.optionsEnum;
import zoning.*;
import zoning.enums.teleportType;
import zoning.enums.timerType;

import java.util.Arrays;

import static menus.MenuHelpers.*;

/**
 * Similar structure and logic to mapsmenu
 */
class ZonesMenu {

    /**
     * Display an interface for manipulating zones.
     * @param zones list of zones from this map
     */
    static void zonesMenu(ZoneList zones) {
        optionsEnum choice;
        clearScreen();

        //Leaving via exit then re-entering leave choice set to EXIT. The loop exits immediately in that case
        Menus.zoneMenu.clearChoice();

        //main loop for zones
        while (Menus.zoneMenu.getMenuChoice() != optionsEnum.EXIT) {
            Menus.zoneMenu.DisplayMenu();
            outputCurrentItem(zones);
            choice = Menus.zoneMenu.promptForMenuChoice();
            zonesExecute(zones, choice);

            //Clear screen before end of loop, print any error messages here so they show at the top of screen
            clearScreen();
            if (Menus.status.compareTo("") != 0) {
                System.out.println(Menus.status);
                Menus.status = "";
            }
        }
    }

    /**
     * Execute logic for whatever choices are available in this menu.
     * @param zones zones being operated on
     * @param choice choice made the user
     */
    private static void zonesExecute(ZoneList zones, optionsEnum choice) {

        switch (choice) {

            case ADD_ZONE:
                addZonePrompt(zones);
                break;
            case DELETE_ZONE:
                deleteZonePrompt(zones);
                break;
            case SEARCH_ZONES:
                searchZones(zones);
                break;
            case NEXT:
                zones.next();
                break;
            case PREVIOUS:
                zones.prev();
                break;
            case SET_TARGET:
                setTarget(zones);
                break;
            case EXIT:
            case NONE:
                break;
        }
    }

    /**
     * Zones are created as rectangular cuboids.
     * The user is asked for two coordinates, two opposite corners of the objects base.
     * The user is then asked to provide the height of the zone from which the remaining points can be determined.
     * @param zones ZoneList new zone is being added to
     */
    private static void addZonePrompt(ZoneList zones) {
        Zone zoneToAdd;
        boolean zMatch = false;
        String name;
        String type = "";
        int zHeight;

        clearScreen();
        System.out.println("Enter a name or identifier for this zone: ");
        name = promptForString();

        //Determine type of zone
        while (type.compareTo("timer") != 0 && type.compareTo("teleport") != 0) {
            System.out.println("Zone type: (timer|teleport)");
            type = promptForString();
        }


        //Prompts specific to each zone type (teleport or timer)
        String source;
        String input;
        timerType tType = null;
        teleportType teleType = null;
        boolean isStart = false;
        if (type.compareTo("timer") == 0) {
            //timer
            input = promptWithOptions(Arrays.asList("start", "end"), "Is this a start or end zone");
            isStart = input.compareTo("start") == 0;
            input = promptWithOptions(Arrays.asList("main", "bonus"), "Is this a main or bonus zone");
            tType = (input.compareTo("main") == 0) ? timerType.MAIN : timerType.BONUS;
        } else {
            //teleporter
            source = promptWithOptions(Arrays.asList("src", "dest"), "Is this the source or destination teleporter");
            teleType = source.equals("src") ? teleportType.SOURCE : teleportType.DESTINATION;
        }

        Coordinate c1, c2;
        long[] temp1 = {0, 0, 0};
        long[] temp2 = {0, 0, 0};

        //Get 2 coordinates
        while (!zMatch) {
            System.out.println("Create a zone by entering opposite corners of the base of the cuboid");
            System.out.println("z values must match for both corners");
            System.out.println("Enter the first coordinate: (x y z) ");
            temp1 = promptForCoords();

            System.out.println("Enter the second coordinate: (x y z) ");
            temp2 = promptForCoords();

            //Check z values match
            if (temp1[2] == temp2[2]) {
                zMatch = true;
            } else
                System.out.println("[Error]: Z values did not match");


        }

        c1 = new Coordinate(temp1[0], temp1[1], temp1[2]);
        c2 = new Coordinate(temp2[0], temp2[1], temp2[2]);

        //Get height
        System.out.println("Enter the height of this zone: ");
        zHeight = promptForNumber();

        //Default to 1
        zHeight = (zHeight == 0) ? 1 : zHeight;

        //Choose which instance of base class to instantiate
        if (type.compareTo("timer") == 0)
            zoneToAdd = new TimerZone(name, c1, c2, zHeight, tType, isStart);
        else
            zoneToAdd = new TeleportZone(name, c1, c2, zHeight, teleType, "");

        //Add the zone, print an error if this name is a duplicate
        try {
            zones.addZone(zoneToAdd);
        } catch (DuplicateEntryException e) {
            Menus.status = e.getMessage();
        }

    }

    /**
     * Prompt to delete a zone.
     * @param zones zonelist
     */
    private static void deleteZonePrompt(ZoneList zones) {
        if (zones.getCount() == 0)
            return;

        //Make sure they want to delete this zone
        String response = "";
        while (response.compareTo("y") != 0 && response.compareTo("n") != 0) {
            clearScreen();
            System.out.println("Are you sure you want to delete " + zones.getSelectedZone().getID()
                    + " (y|n)");
            response = promptForString().toLowerCase();
        }

        //Delete zone otherwise do nothing
        if (response.equals("y")) {

            Zone curr = zones.getSelectedZone();

            //Item being deleted is a destination zone find the zone that targets it and set its dest to nothing
            if (curr instanceof TeleportZone && ((TeleportZone) curr).getTeleType() == teleportType.DESTINATION) {
                for (Zone z : zones) {
                    if (z instanceof TeleportZone && ((TeleportZone) z).getTarget().equals(curr.getID())) {
                        ((TeleportZone) z).setTarget("");
                    }
                }
            }
            zones.deleteZone();
        }
    }

    /**
     * Search the list of zones by name
     * @param zones list to search
     */
    private static void searchZones(ZoneList zones) {
        int index;
        System.out.println("Enter the ID of the zone you wish to find");
        String input = promptForString();

        if ((index = zones.findByID(input)) != -1) {
            zones.setCursor(index);

        } else
            Menus.status = "ID: " + input + " was not found";
    }

    /**
     * Set the target of a teleport source zone
     * Does not allow targeting non dest type Teleport zones as destination
     * @param zones zonelist being modified
     */
    private static void setTarget(ZoneList zones) {
        Zone curr = zones.getSelectedZone();

        //Teleportzone is source
        if (curr instanceof TeleportZone && ((TeleportZone) curr).getTeleType() == teleportType.SOURCE) {
            String name = MenuHelpers.promptForString("Enter the name of the zone you wish to target");
            int index = zones.findByID(name);
            //Zone exists
            if (index != -1) {
                //shenanigans to select target zone
                int here = zones.getCursor();
                zones.setCursor(index);
                //target is telezone and a destination
                if(zones.getSelectedZone() instanceof TeleportZone && ((TeleportZone) zones.getSelectedZone()).getTeleType() == teleportType.DESTINATION) {
                    ((TeleportZone) curr).setTarget(name);
                }
                else
                    Menus.status = "[Error]: Can only target TeleportZones that are of type Destination";
                zones.setCursor(here);
            } else
                Menus.status = "[Error]: Cannot target " + name + " as it does not exist";
        } else
            Menus.status = "[Error]: Can only add target to TeleportZones that are of type Source";
    }
}
