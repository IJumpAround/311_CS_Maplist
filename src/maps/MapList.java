package maps;
import exceptions.DuplicateEntryException;
import abc.ABCSelectionList;
import records.Record;
import zoning.Zone;

import java.util.ArrayList;

public class MapList extends ABCSelectionList {
    //private int cursor;
    private ArrayList<Map> maps;
    private ArrayList<String> names;

    public MapList() {
        super();
        maps = new ArrayList<>();
        names = new ArrayList<>();
    }

    public MapList(ArrayList<Map> maps) {
        super(maps.size());
        this.maps = maps;
        names = new ArrayList<>();

        for (Map map: maps) {
            names.add(map.getMapName());
        }
    }


    public MapList(MapList target) {
        super(target);
        this.maps = new ArrayList<Map>(target.maps);
        this.names = new ArrayList<String>(target.names);
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

    @Override
    protected void initializeType() {
        type = "Map";
    }

    /**
     * Return the map the cursor is currently pointing at.
     * @return Map
     */
    public Map getSelectedMap() {
        if(count > 0)
            return maps.get(cursor);
        else
            return null;
    }

    /**
     * Wrapper for _addMap()
     */
    public void addMap(String name, short tier, int completions, ArrayList<Record> records, ArrayList<Zone> zones) throws DuplicateEntryException {
        _addMap(name, tier, completions, records, zones);
    }

    /**
     * Wrapper for addMap()
     */
    public void addMap(String name, short tier) throws DuplicateEntryException {
        _addMap(name,tier,0,null,null);
    }


    /**
     * Add a map to the program
     * @param name name of the map being added
     * @param tier the difficulty tier (1-10)
     * @param completions the number of completions
     * @param records list of records
     * @param zones list of zones
     */
    private void _addMap(String name, short tier, int completions, ArrayList<Record> records, ArrayList<Zone> zones) throws DuplicateEntryException {
        //something something add map
        if(!mapNameExists(name)) {
            maps.add(new Map(name, tier));
            names.add(name);
            count++;
        }
        else
            throw new DuplicateEntryException("[Error]: " + name + " already exists");

    }

    public ArrayList<String> getMapNames() {
        return names;
    }

    /**
     * Does the given mapname already exist?
     * @param name
     * @return
     */
    public boolean mapNameExists(String name) {

        for(String n: names) {
            if(n.compareTo(name) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete the currently selected map
     */
    public void deleteMap() {
        if(maps.size() > 0) {
            maps.remove(cursor);
            count--;
            clampCursor();
        }
    }

    /**
     * Search for a map by its name and select it.
     * @param name String mapname
     * @return
     */
    public int findMapByName(String name) {
        Map curr;
        for(int i = 0; i < count; i++) {
            curr = maps.get(i);
            if(name.compareTo(curr.getMapName()) == 0)
                return i;
        }
        return -1;
    }
}
