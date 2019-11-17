package zoning;

import exceptions.DuplicateEntryException;
import abc.ABCSelectionList;
import java.util.ArrayList;
import java.util.Iterator;

public class ZoneList extends ABCSelectionList implements Iterable<Zone> {
    ArrayList<Zone> zones;

    public ZoneList() {
        super();
        zones = new ArrayList<>();
    }

    @Override
    public String prettyCurrentItem() {
        String str = "";

        if(zones.size() > 0) {
            Zone curr = getSelectedZone();
            str =  curr.prettyZone();
        }
        return str;
    }

    @Override
    protected void initializeType() {
        type = "Zone";
    }

    public ZoneList(ArrayList<Zone> zones) {
        super();
        this.zones = zones;
    }

    public Zone getSelectedZone() {
        if(isCursorInbounds()) {
            return zones.get(cursor);
        }
        else
            clampCursor();
        return null;
    }

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
