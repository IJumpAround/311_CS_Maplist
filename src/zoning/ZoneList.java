package zoning;

import ABC.MenuList;
import java.util.ArrayList;

public class ZoneList extends MenuList {
    ArrayList<Zone> zones;

    public ZoneList() {
        super();
        zones = new ArrayList<>();
    }

    public ZoneList(ArrayList<Zone> zones) {
        super();
        this.zones = zones;
    }

}
