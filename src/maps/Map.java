package maps;
import java.util.ArrayList;
import records.Record;
import zoning.Zone;
import zoning.ZoneList;


public class Map {
    private String mapName;
    private short tier;
    private int completions;
    private ArrayList<Record> records;
    private ZoneList zones;

    /**
     * Default constructor
     */
    public Map() {
        this.mapName = "Null";
        this.tier = 0;
        this.completions = 0;
        this.records = new ArrayList<>();
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
    public Map(String mapName, short tier, int completions, ArrayList<Record> records, ZoneList zones) {
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
        this.records = new ArrayList<>();
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
        return completions;
    }

    public void setCompletions(int completions) {
        this.completions = completions;
    }

    public ArrayList<Record> getRecords() {
        return records;
    }

    /**
     * Retrieve a record by place
     * @param place
     * @return either the record is returned or null is returned if not found
     */
    public Record GetRecordByPlace(int place) {
        for(Record rec: this.records) {
            if(rec.getPlace() == place)
                return rec;
        }
        return null;
    }

    /**
     * Retrieve a record by SteamID of the completing player.
     * @param id
     * @return either the record is returned or null is returned if not found
     */
    public Record GetRecordByPlayer(String id) {
        for(Record rec: this.records) {
            if(rec.getSteamID().compareTo(id) == 0)
                return rec;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Map{" +
                "mapName='" + mapName + '\'' +
                ", tier=" + tier +
                ", completions=" + completions +
                ", records=" + records +
                ", zones=" + zones +
                '}';
    }

    public String toPrettyString() {
        String wr;
        if(records.size() > 0)
            wr = records.get(0).formattedTime();
        else
            wr = "N/A";
        return String.format(
                 "Map Name:    %-15s\n"
                +"WR:          %-15s\n"
                +"Tier:        %-15d\n"
                +"Completions: %-15d\n"
                ,mapName,wr,tier,completions);
//
    }

    public ZoneList getZones() {
        return zones;
    }

    public void setZones(ZoneList zones) {
        this.zones = zones;
    }
}
