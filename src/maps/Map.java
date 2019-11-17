package maps;

import records.RecordList;
import zoning.ZoneList;


/**
 * Container class that encompasses Zones and Records.
 * Contains a RecordList and ZoneList to manage information about zones and records.
 * Holds other information relevant to a map such as its difficulty and number of completions.
 */
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
     *
     * @param mapName
     * @param tier
     */
    public Map(String mapName, short tier) {
        this.mapName = mapName;
        this.tier = tier;
        this.completions = 0;
        this.records = new RecordList();
        this.zones = new ZoneList();
    }

    /**
     * Getter for mapname
     * @return
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Setter for mapname
     * @param mapName
     */
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

    /**
     * Formatted output of map information. The format is meant for display.
     * @return
     */
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

    /**
     * update completions from records count
     */
    private void refreshStats() {
        completions = records.getCount();
    }
}
