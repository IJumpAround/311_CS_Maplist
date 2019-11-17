package maps;

import records.RecordList;
import zoning.ZoneList;


public class Map {
    private String mapName;
    private short tier;
    private int completions;
    private RecordList records;
    private ZoneList zones;

    /**
     * Default constructor
     */
    public Map() {
        this.mapName = "Null";
        this.tier = 0;
        this.completions = 0;
        this.records = new RecordList();
        this.zones = new ZoneList();

    }

    /**
     * Parameter constructor
     * @param mapName name of the map
     * @param tier  difficulty of the map (1-10)
     * @param completions number of people who have completed this map
     * @param records list of records
     * @param zones list of zones
     */
    public Map(String mapName, short tier, int completions, RecordList records, ZoneList zones) {
        this.mapName = mapName;
        this.tier = tier;
        this.completions = completions;
        this.records = records;
        this.zones = zones;
    }

    /**
     * Name constructor
     * @param mapName
     */
    public Map(String mapName, short tier) {
        this.mapName = mapName;
        this.tier = tier;
        this.completions = 0;
        this.records = new RecordList();
        this.zones = new ZoneList();
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public short getTier() {
        return tier;
    }

    public void setTier(short tier) {
        this.tier = tier;
    }

    public int getCompletions() {
        refreshStats();
        return completions;
    }

    public void setCompletions(int completions) {
        this.completions = completions;
    }

    public RecordList getRecords() {
        return records;
    }


    @Override
    public String toString() {
        refreshStats();
        return "Map{" +
                "mapName='" + mapName + '\'' +
                ", tier=" + tier +
                ", completions=" + completions +
                ", records=" + records +
                ", zones=" + zones +
                '}';
    }

    public String toPrettyString() {
        refreshStats();
        String wr;
        if(records.getCount() > 0)
            wr = records.getWRFormatted();
        else
            wr = "N/A";
        return String.format(
                 "Map Name:    %-15s\n"
                +"WR:          %-15s\n"
                +"Tier:        %-15d\n"
                +"Completions: %-15d\n"
                +"Zones:       %-15d\n"
                ,mapName,wr,tier,completions,zones.getCount());
//
    }

    public ZoneList getZones() {
        return zones;
    }

    public void setZones(ZoneList zones) {
        this.zones = zones;
    }

    private void refreshStats() {
        completions = records.getCount();
    }
}
