package records;

public class WR extends Top10{
    private int replayID;
    private static int replayIDGen = 0;


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

    public int getReplayID() {
        return replayID;
    }

    public void setReplayID(int replayID) {
        this.replayID = replayID;
    }

}


