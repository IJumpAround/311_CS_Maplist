package maps;
import java.util.ArrayList;
import Records.Record;
import zoning.Zone;


public class Map {
    private String mapName;
    private short tier;
    private int completions;
    private ArrayList<Record> records;
    private ArrayList<Zone> zones;

    /**
     * Default constructor
     */
    public Map() {
        this.mapName = "Null";
        this.tier = 0;
        this.completions = 0;
        this.records = new ArrayList<>();
        this.zones = new ArrayList<>();

    }

    /**
     * Parameter constructor
     * @param mapName name of the map
     * @param tier  difficulty of the map (1-10)
     * @param completions number of people who have completed this map
     * @param records list of records
     * @param zones list of zones
     */
    public Map(String mapName, short tier, int completions, ArrayList<Record> records, ArrayList<Zone> zones) {
        this.mapName = mapName;
        this.tier = tier;
        this.completions = completions;
        this.records = records;
        this.zones = zones;
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

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }
}
