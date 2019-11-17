package records;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Top10 extends Record {
    protected float pointBonus;


    /**
     *
     */
    Top10() {
        super();
        obj = "Top10";
        pointBonus = 0;
    }

    Top10(float pts) {
        super();
        obj = "Top10";
        this.pointBonus = pts;
    }

    Top10(Top10 target) {
        obj = "Top10";
        this.steamID = target.steamID;
        this.playerName = target.playerName;
        this.dateOfRun = target.dateOfRun;
        this.setTime(target.getTime());
        this.points = target.points;
        this.pointBonus = target.pointBonus;
        this.setPlace(target.getPlace());
    }

    @Override
    public String prettyRecord() {
        return super.prettyRecord();
    }

    /**
     * Creates a top 10 record entry
     * @param playerName
     * @param steamID
     * @param dateOfRun
     * @param points
     * @param time
     * @param place
     * @param pointBonus
     */
    public Top10(String playerName, String steamID, LocalDateTime dateOfRun, float points,
                 Duration time, int place, float pointBonus) {

        super(playerName, steamID, dateOfRun, points, time, place);
        obj = "top";
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

    public float getPointBonus() {
        return pointBonus;
    }

    public void setPointBonus(float pointBonus) {
        this.pointBonus = pointBonus;
    }

    @Override
    public void setPoints(float points) {
        this.points = points + this.pointBonus;
    }
}
