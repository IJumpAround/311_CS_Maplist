package maps;

import records.RecordList;
import records.WR;
import zoning.ZoneList;


/**
 * Maps are the courses that the players compete on in bhop. Each map has a start and end zone, and potentially
 * a start and end zone for a bonus. Start and end zones are not required by this program.
 * Every time a player completes a map, their completion time is either added to a list of records, or their current
 * record is updated with the new time. (Record insertion is implemented, updating is not)
 * <p>
 * Container class that encompasses Zones and Records.
 * Contains a RecordList and ZoneList to manage information about zones and records.
 * Holds other information relevant to a map such as its difficulty and number of completions.
 */
public class Map {
    private String mapName;     //name of the map
    private short tier;         //difficulty of the map (scale 1-10)
    private int completions;    //total unique completions
    private RecordList records; //holder for all records on a map
    private ZoneList zones;     //holder for all zones on a map

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

//    /**
//     * Parameter constructor
//     * @param mapName name of the map
//     * @param tier  difficulty of the map (1-10)
//     * @param completions number of people who have completed this map
//     * @param records list of records
//     * @param zones list of zones
//     */
//    public Map(String mapName, short tier, int completions, RecordList records, ZoneList zones) {
//        this.mapName = mapName;
//        this.tier = tier;
//        this.completions = completions;
//        this.records = records;
//        this.zones = zones;
//    }

    /**
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
     *
     * @return
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * Setter for mapname
     *
     * @param mapName name of map
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    /**
     * Getter for tier
     *
     * @return difficulty of map (1-10)
     */
    public short getTier() {
        return tier;
    }

    /**
     * Setter for tier
     *
     * @param tier difficulty 1-10
     */
    public void setTier(short tier) {
        this.tier = tier;
    }

    /**
     * Getter for completions
     *
     * @return number of completions (records)
     */
    public int getCompletions() {
        refreshStats();
        return completions;
    }

    /**
     * Getter for records
     *
     * @return RecordList of records
     */
    public RecordList getRecords() {
        return records;
    }

    /**
     * Setter for records (not a deep copy)
     *
     * @param records RecordsList of records
     */
    public void setRecords(RecordList records) {
        this.records = records;
    }

    /**
     * Before I learned about GSON my plan was to use toString() to create the file for input/output
     * It would have been better to do the pretty printing in tostring instead of seperate functions.
     *
     * @return
     */
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
     * Formatted output of map information. The format is meant for display to the user on the menu interface
     *
     * @return a pretty string
     */
    String toPrettyString() {
        refreshStats();
        String wr;
        if (records.getCount() > 0)
            wr = records.getWRFormatted();
        else
            wr = "N/A";
        return String.format(
                "Map Name:    %-15s\n"
                        + "WR:          %-15s\n"
                        + "Tier:        %-15d\n"
                        + "Completions: %-15d\n"
                        + "Zones:       %-15d\n"
                , mapName, wr, tier, completions, zones.getCount());
    }

    /**
     * Getter for ZoneList of zones
     *
     * @return ZoneList
     */
    public ZoneList getZones() {
        return zones;
    }

    /**
     * update completions from records count
     */
    private void refreshStats() {
        completions = records.getCount();
    }

    public int getReplayID() {
        records.setCursor(0);
        return ((WR) records.getSelectedItem()).getReplayID();
    }
}
