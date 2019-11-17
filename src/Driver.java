import java.time.Duration;
import java.time.LocalTime;

import menus.MenuHelpers;
import menus.Menus;
import objectBuilder.GsonHelpers;
import records.Record;
import maps.Map;
import maps.MapList;
import menus.MapsMenu;

import static java.lang.System.exit;


public class Driver {

    public static void main(String[] args) {
        GsonHelpers.registerAllTypes();
        MapList maps;
        if (args.length == 0) {
            maps = new MapList();
        } else {
            //TODO load from input file
            maps = new MapList();
        }

        MapsMenu.mapsMenu(maps);
    }
}


