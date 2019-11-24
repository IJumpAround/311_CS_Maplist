package maps;
import exceptions.DuplicateEntryException;
import abc.ABCSelectionList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Extends ABCSelection list to hold map objects
 * Uses an arraylist to store map objects
 * Uses cursor functionality to select modify and display information.
 */
public class MapList extends ABCSelectionList implements Iterable<Map>{
    private ArrayList<Map> maps;        //all maps in the program
    private ArrayList<String> names;    //Names of all maps

    /**
     * Default constructor
     */
    public MapList() {
        super();
        initializeType();
        maps = new ArrayList<>();
        names = new ArrayList<>();
    }

    /**
     * Implements prettyCurrentItem() to display information about the selected map
     * @return the formatted string or a blank string if there are no maps
     */
    @Override
    public String prettyCurrentItem() {
        String str = "";

        if(maps.size() > 0) {
            if(!isCursorInbounds())
                clampCursor();
            Map curr = getSelectedMap();
            str =  curr.toPrettyString();
        }
        return str;
    }

    /**
     * Set map type
     */
    @Override
    protected void initializeType() {
        typeName = "Map";
    }

    /**
     * Return the map the cursor is currently pointing at.
     * @return Map
     */
    public Map getSelectedMap() {
        if(isCursorInbounds())
            return maps.get(cursor);
        else
            return null;
    }

    /**
     * Adds a new map to the maplist.
     * @param name name of the map being added
     * @param tier difficulty of the map being added (1-10)
     * @throws DuplicateEntryException duplicate map names are not allowed
     */
    public void addMap(String name, short tier) throws DuplicateEntryException {
        if(!mapNameExists(name)) {
            maps.add(new Map(name, tier));
            names.add(name);
            count++;
        }
        else
            throw new DuplicateEntryException("[Error]: " + name + " already exists");
    }

    /**
     * Does the given mapname already exist?
     * @param name name to search for
     * @return boolean
     */
    private boolean mapNameExists(String name) {

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
     * @return name of map to search for
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

    /**
     * So maps can be looped over using for each
     * @return Iterator
     */
    @Override
    public Iterator<Map> iterator() {
        return maps.iterator();
    }
}
