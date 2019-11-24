package zoning;

import exceptions.DuplicateEntryException;
import abc.ABCSelectionList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Child class of ABCSelectionList
 * This class encompasses an arraylist of zones and provides and interface to them.
 */
public class ZoneList extends ABCSelectionList implements Iterable<Zone> {
    private ArrayList<Zone> zones;  //Zones managed by this list

    /**
     * Default constructor
     */
    public ZoneList() {
        super();
        initializeType();
        zones = new ArrayList<>();
    }

    /**
     * Create a formatted string for the currently selected zone
     * @return formatted string
     */
    @Override
    public String prettyCurrentItem() {
        String str = "";

        if(zones.size() > 0) {
            Zone curr = getSelectedZone();
            str =  curr.prettyZone();
        }
        return str;
    }

    /**
     * Set type
     */
    @Override
    protected void initializeType() {
        typeName = "Zone";
    }

    /**
     * Return the currently selected zone from the list
     * @return Zone
     */
    public Zone getSelectedZone() {
        if(isCursorInbounds()) {
            return zones.get(cursor);
        }
        else
            clampCursor();
        return null;
    }

    /**
     * Add a zone to the list
     * @param z zone to add
     * @throws DuplicateEntryException duplicate zone IDs not allowed
     */
    public void addZone(Zone z) throws DuplicateEntryException {
        if(findByID(z.getID()) == -1) {
            zones.add(z);
            count++;
        }
        else
            throw new DuplicateEntryException("ID: " + z.getID() + " already exists");
    }

    /**
     * Return index of a zone if it exists in the list.
     * @param id string id
     * @return index or -1 if not found
     */
    public int findByID(String id) {
        for(int i = 0; i < zones.size(); i++) {
            if(zones.get(i).getID().compareTo(id) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Remove the currently selected zone.
     */
    public void deleteZone() {
        if(count > 0 && isCursorInbounds()) {
            zones.remove(cursor);
            count--;
            clampCursor();
        }
    }

    @Override
    public Iterator<Zone> iterator() {
        return zones.iterator();
    }
}
