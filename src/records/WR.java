package records;

public class WR extends Top10{
    private static int replayIDGen = 0;
    private int replayID;


    public WR() {
        super();
        obj = "WR";
        this.replayID = replayIDGen;
        replayIDGen++;
    }

    public WR(float pointBonus) {
        super(pointBonus);
        obj = "WR";
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
        return super.prettyRecord() + "\n"
                + String.format("Replay ID:      %-15s",replayID);
    }

    public int getReplayID() {
        return replayID;
    }

    public void setReplayID(int replayID) {
        this.replayID = replayID;
    }

}


