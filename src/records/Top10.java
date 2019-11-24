package records;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Inherits functionality from record and adds a point bonus for being in the top 10
 */
public class Top10 extends Record {
    float pointBonus;   //bonus points for being in the top 10

    /**
     * Default constructor
     */
    Top10() {
        super();
        pointBonus = 0;
    }

    /**
     * Constructor to set bonus points
     * @param pts set bonus points to this
     */
    Top10(float pts) {
        super();
        this.pointBonus = pts;
    }

    /**
     * Copy constructor
     * @param target target Top10
     */
    Top10(Top10 target) {
        this.steamID = target.steamID;
        this.playerName = target.playerName;
        this.dateOfRun = target.dateOfRun;
        this.setTime(target.getTime());
        this.points = target.points;
        this.pointBonus = target.pointBonus;
        this.setPlace(target.getPlace());
    }

    @SuppressWarnings("EmptyMethod")
    @Override
    public String prettyRecord() {
        return super.prettyRecord();
    }

    /**
     * Creates a top 10 record entry
     * @param playerName playername
     * @param steamID player's steamid
     * @param dateOfRun date
     * @param points points
     * @param time time
     * @param place rank
     * @param pointBonus bonus points
     */
    public Top10(String playerName, String steamID, LocalDateTime dateOfRun, float points,
                 Duration time, int place, float pointBonus) {

        super(playerName, steamID, dateOfRun, points, time, place);
        this.pointBonus = pointBonus;
    }

    @Override
    public String toString() {
        return "Top10{" +
                "playerName='" + playerName + '\'' +
                ", steamID='" + steamID + '\'' +
                ", dateOfRun=" + dateOfRun +
                ", points=" + points +
                ", pointBonus=" + pointBonus +
                '}';
    }

    float getPointBonus() {
        return pointBonus;
    }

    void setPointBonus(float pointBonus) {
        this.pointBonus = pointBonus;
    }

    @Override
    public void setPoints(float points) {
        this.points = points + this.pointBonus;
    }
}
