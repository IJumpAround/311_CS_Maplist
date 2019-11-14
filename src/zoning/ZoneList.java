package zoning;

import Menus.MenuList;
import java.util.ArrayList;

public class ZoneList extends MenuList {
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

    public ZoneList(ArrayList<Zone> zones) {
        super();
        this.zones = zones;
    }

    public Zone getSelectedZone() {
        return zones.get(cursor);
    }

}