package records;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Holds information about a single completion on a given map.
 * Implements compareTo for sorting
 */
public class Record implements Comparable<Record> {

    String playerName;          //Name of the player whos record this is
    String steamID;             //SteamID of the player
    LocalDateTime dateOfRun;    //Date the run happened
    float points;               //Points earned from this time
    private Duration time;      //Actual time
    private int place;          //Map rank


    /**
     * Default constructor
     */
    public Record() {
        this.playerName = "";
        this.steamID = "";
        this.dateOfRun = null;
        this.points = 0;
        this.time = null;
        this.place = 0;
    }

    /**
     * Alternate constructor
     * @param playerName name
     * @param steamID steamid
     * @param dateOfRun date
     * @param points score
     * @param time run time
     * @param place map rank
     */
    public Record(String playerName, String steamID, LocalDateTime dateOfRun, float points, Duration time, int place) {
        this.playerName = playerName;
        this.steamID = steamID;
        this.dateOfRun = dateOfRun;
        this.points = points;
        this.time = time;
        this.place = place;
    }

    /**
     * Copy constructor
     * @param record target record to copy
     */
    public Record(Record record) {
        steamID = record.steamID;
        playerName = record.playerName;
        dateOfRun = record.dateOfRun;
        points = record.points;
        time = record.time;
        place = record.place;
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
     * @param rec record we are comparing this to
     * @return < 0 if less, 0 if equal, > 0 if larger
     */
    @Override
    public int compareTo(Record rec) {
        return this.time.compareTo(rec.time);
    }

    /**
     * Formats the duration for display
     * @return string formatted time
     */
    String formattedTime() {
        long totalSeconds = time.getSeconds();
        int nanos = time.getNano();

        long hours, minutes, seconds, millis;

        hours = Duration.of(totalSeconds, ChronoUnit.SECONDS).toHours();
        minutes = Duration.of(totalSeconds, ChronoUnit.SECONDS).toMinutes() % 60;
        seconds = Duration.of(totalSeconds, ChronoUnit.SECONDS).toSeconds() % 60;
        millis = Duration.of(nanos, ChronoUnit.NANOS).toMillis();


        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }

    /**
     * Formats the entire record for display
     * Calls formattedTime()
     * @return formatted string
     */
    public String prettyRecord() {
        String date = dateOfRun.format( DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return String.format(
                "Player:      %-15s\n"
                        + "Place:       %-15d\n"
                        + "Date Of Run: %-15s\n"
                        + "Points:      %-15.2f\n"
                        + "Steam_ID:    %-15s\n"
                , playerName,  place, date, points, steamID);


    }

    //Getters and setters
    public String getPlayerName() {
        return playerName;
    }

    void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    String getSteamID() {
        return steamID;
    }

    void setSteamID(String steamID) {
        this.steamID = steamID;
    }

    public LocalDateTime getDateOfRun() {
        return dateOfRun;
    }

    void setDateOfRun(LocalDateTime dateOfRun) {
        this.dateOfRun = dateOfRun;
    }

    float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    Duration getTime() {
        return time;
    }

    void setTime(Duration time) {
        this.time = time;
    }

    int getPlace() {
        return place;
    }

    void setPlace(int place) {
        this.place = place;
    }
}
