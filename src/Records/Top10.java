package Records;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Top10 extends Record {
    protected float pointBonus;

    /**
     *
     */
    Top10() {
        super();
        pointBonus = 0;
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
                 LocalTime time, int place, float pointBonus) {

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

    public float getPointBonus() {
        return pointBonus;
    }

    public void setPointBonus(float pointBonus) {
        this.pointBonus = pointBonus;
    }
}
