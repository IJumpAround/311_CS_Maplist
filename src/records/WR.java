package records;

/**
 * Inherits from Record and Top10
 * A wr run has a replay so other players can see how the player completed the map
 */
public class WR extends Top10{
    private static int replayIDGen; //Static value that prevents multiple instances of WR's from having the same replay
    private int replayID;           //Actual replay id


    /**
     * Default constructor
     */
    public WR() {
        super();
        this.replayID = replayIDGen;
        replayIDGen++;
    }

    /**
     * param constructor
     * @param pointBonus bonus points for wr
     */
    public WR(float pointBonus) {
        super(pointBonus);
        this.replayID = replayIDGen;
        replayIDGen++;
    }

    @Override
    public String toString() {
        return "WR{" +
                "playerName='" + playerName + '\'' +
                ", steamID='" + steamID + '\'' +
                ", dateOfRun=" + dateOfRun +
                ", points=" + points +
                ", replayID=" + replayID +
                ", pointBonus=" + pointBonus +
                '}';
    }

    /**
     * Append replay ID to this pretty string
     * @return formatted string
     */
    @Override
    public String prettyRecord() {
        return super.prettyRecord()
                + String.format("Replay ID:      %-15s",replayID);
    }

    public static void setReplayIDGen(int replayIDGen) {
        WR.replayIDGen = replayIDGen;
    }

    public static int getReplayIDGen() {
        return replayIDGen;
    }

    public int getReplayID() {
        return replayID;
    }

    public void setReplayID(int replayID) {
        this.replayID = replayID;
    }

}


