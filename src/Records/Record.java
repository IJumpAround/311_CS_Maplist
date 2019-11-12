package Records;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Holds information about a single completion on a given map
 */
public class Record implements Comparable<Record> {
    protected String playerName;
    protected String steamID;
    protected LocalDateTime dateOfRun;
    protected float points;
    private LocalTime time;
    private int place;

    public Record() {
        this.playerName = "";
        this.steamID = "";
        this.dateOfRun = null;
        this.points = 0;
        this.time = null;
        this.place = 0;
    }

    public Record(String playerName, String steamID, LocalDateTime dateOfRun, float points, LocalTime time, int place) {
        this.playerName = playerName;
        this.steamID = steamID;
        this.dateOfRun = dateOfRun;
        this.points = points;
        this.time = time;
        this.place = place;
    }

    @Override
    public String toString() {
        return "Record{" +
                "playerName='" + playerName + '\'' +
                ", steamID='" + steamID + '\'' +
                ", dateOfRun=" + dateOfRun +
                ", points=" + points +
                ", time=" + time +
                ", place=" + place +
                '}';
    }

    /**
     * overriden to allow sorting based on time
     * @param rec
     * @return
     */
    @Override
    public int compareTo(Record rec) {
        return this.time.compareTo(rec.time);
    }




    //Getters and setters

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSteamID() {
        return steamID;
    }

    public void setSteamID(String steamID) {
        this.steamID = steamID;
    }

    public LocalDateTime getDateOfRun() {
        return dateOfRun;
    }

    public void setDateOfRun(LocalDateTime dateOfRun) {
        this.dateOfRun = dateOfRun;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
