package zoning;

import Menus.MenuClasses.ABCSelectionList;
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

}
