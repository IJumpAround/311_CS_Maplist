import ABC.MenuList;
import Records.Record;
import maps.Map;
import maps.MapList;
import zoning.ZoneList;
import java.time.LocalTime;


public class Driver {

    public static void main(String[] args) {
        MapList maps;
        if(args.length == 0) {
            maps = new MapList();
        }
        else {
            //TODO load from input file
            maps = new MapList();
        }

        recordTests();
        menuDriver(maps);

    }

    public static void recordTests() {
        Record test = new Record();
        test.setTime(LocalTime.now());

        System.out.println(test.formattedTime());
    }

    public static void menuDriver(MapList list) {
        int option = -1;


        while(option != 0) {
           option = MapsMenu(list);
            //TODO finish other options
           switch (option) {
               case 0:
                   break;

           }
           clearScreen();
        }
    }

    public static int MapsMenu(MapList maps) {
        int choice;
        System.out.println("Maps");
        System.out.println("1) Add Map");
        System.out.println("2) Delete Map");
        System.out.println("3) View/Modify");
        System.out.println("4) Search");
        System.out.println("5) Next");
        System.out.println("6) Previous");
        System.out.println("0) Exit");

        choice = InputReader.getMenuChoice();
        return choice;
    }

    /**
     * Menu show when selecting a single map
     * @param map selected map
     * @return menu choice
     */
    public static int mapSubmenu(Map map) {
        int choice;
        System.out.println("Map: " + map.getMapName());
        System.out.println("1) Zones");
        System.out.println("2) Records");
        System.out.println("\n");
        System.out.println(map.toPrettyString());

        choice = InputReader.getMenuChoice();
        return choice;
    }

    public static int ZonesMenu(ZoneList zones) {
        int choice;
        System.out.println("Zones");
        System.out.println("1) Add Map");
        System.out.println("2) Delete Map");
        System.out.println("3) View/Modify");
        System.out.println("4) Search");
        System.out.println("5) Next");
        return -1;
    }

    /**
     * Nice terminal refresh courtesy of stack overflow
     * @author Satish
     * https://stackoverflow.com/questions/2979383/java-clear-the-console
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}


