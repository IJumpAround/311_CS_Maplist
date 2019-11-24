import objectBuilder.GsonHelpers;
import maps.MapList;
import menus.MapsMenu;

class Driver {

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


