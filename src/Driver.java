import java.time.Duration;
import java.time.LocalTime;

import menus.MenuHelpers;
import menus.Menus;
import records.Record;
import maps.Map;
import maps.MapList;
import menus.MapsMenu;

import static java.lang.System.exit;


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

//        recordTests();
//        menuDriver(maps);
        //MapsMenu(maps);
        MapsMenu.mapsMenu(maps);
    }

    public static void menuClassTest() {
        Menus.setupMenus();
        Menus.mapMenu.DisplayMenu();
        Menus.recordMenu.DisplayMenu();
        Menus.zoneMenu.DisplayMenu();

    }



//    public static void menuDriver(MapList list) {
//        int option = -1;
//
//
//        while(option != 0) {
//           option = MapsMenu(list);
//            //TODO finish other options
//           switch (option) {
//               case 0:
//                   break;
//
//           }
//
//
//           clearScreen();
//        }
//    }


    public static void timerPromptTest() {
        Menus.setupMenus();
        Duration time;

        time = MenuHelpers.promptForTime();
        System.out.println(time);
    }








}


