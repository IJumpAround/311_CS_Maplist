package maps;
import Menus.MenuList;
import Records.Record;
import zoning.Zone;

import java.util.ArrayList;

public class MapList extends MenuList {
    //private int cursor;
    private ArrayList<Map> maps;
    private ArrayList<String> names;

    public MapList() {
        super();
        maps = new ArrayList<>();
        names = new ArrayList<>();
    }

    @Override
    public String prettyCurrentItem() {
        String str = "";

        if(maps.size() > 0) {
            Map curr = getSelectedMap();
            str =  curr.toPrettyString();
        }
        return str;
    }

    public MapList(ArrayList<Map> maps) {
        super(maps.size());
        this.maps = maps;
        names = new ArrayList<>();

        for (Map map: maps) {
            names.add(map.getMapName());
        }
    }


    /**
     * Return the map the cursor is currently pointing at.
     * @return Map
     */
    public Map getSelectedMap() {
        return maps.get(cursor);
    }

    /**
     * Wrapper for _addMap()
     */
    public void addMap(String name, short tier, int completions, ArrayList<Record> records, ArrayList<Zone> zones) {
        _addMap(name, tier, completions, records, zones);
    }

    /**
     * Wrapper for addMap()
     */
    public void addMap(String name) {
        _addMap(name,(short)0,0,null,null);
    }


    /**
     * Add a map to the program
     * @param name name of the map being added
     * @param tier the difficulty tier (1-10)
     * @param completions the number of completions
     * @param records list of records
     * @param zones list of zones
     */
    private void _addMap(String name, short tier, int completions, ArrayList<Record> records, ArrayList<Zone> zones) {
        //something something add map
        String n = "name";
        names.add(n);

    }

    public ArrayList<String> getMapNames() {
        return names;
    }


}
