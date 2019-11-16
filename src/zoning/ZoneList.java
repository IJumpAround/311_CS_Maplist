package zoning;

import exceptions.DuplicateEntryException;
import ABC.ABCSelectionList;
import java.util.ArrayList;

public class ZoneList extends ABCSelectionList {
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
        return zones.get(cursor);
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
        if(count > 0) {
            zones.remove(cursor);
            count--;
            clampCursor();
        }
    }

}
