package records;

public class WR extends Top10{
    private static int replayIDGen;
    private int replayID;


    public WR() {
        super();
        this.replayID = replayIDGen;
        replayIDGen++;
    }

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


