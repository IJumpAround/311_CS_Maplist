package records;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Holds information about a single completion on a given map
 */
public class Record implements Comparable<Record> {
    protected String playerName;
    protected String steamID;
    protected LocalDateTime dateOfRun;
    protected float points;
    private Duration time;
    private int place;

    public Record() {
        this.playerName = "";
        this.steamID = "";
        this.dateOfRun = null;
        this.points = 0;
        this.time = null;
        this.place = 0;
    }

    public Record(String playerName, String steamID, LocalDateTime dateOfRun, float points, Duration time, int place) {
        this.playerName = playerName;
        this.steamID = steamID;
        this.dateOfRun = dateOfRun;
        this.points = points;
        this.time = time;
        this.place = place;
    }

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
     *
     * @param rec
     * @return
     */
    @Override
    public int compareTo(Record rec) {
        return this.time.compareTo(rec.time);
    }

    public String formattedTime() {
        long totalSeconds = time.getSeconds();
        int nanos = time.getNano();

        long hours, minutes, seconds, millis;

        hours = Duration.of(totalSeconds, ChronoUnit.SECONDS).toHours();
        minutes = Duration.of(totalSeconds, ChronoUnit.SECONDS).toMinutes() % 60;
        seconds = Duration.of(totalSeconds, ChronoUnit.SECONDS).toSeconds() % 3600;
        millis = Duration.of(nanos, ChronoUnit.NANOS).toMillis();


        return String.format("%02d:%02d:%02d.%3d", hours, minutes, seconds, millis);
    }

    public String prettyRecord() {
        String date = dateOfRun.format( DateTimeFormatter.ofPattern("mm/dd/yyyy"));
        return String.format(
                "Player:      %-15s\n"
                        + "Place:       %-15d\n"
                        + "Date Of Run: %-15s\n"
                        + "Points:      %-15f\n"
                        + "Steam_ID:    %-15s\n"
                , playerName,  place, date, points, steamID);


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

    public Duration getTime() {
        return time;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
