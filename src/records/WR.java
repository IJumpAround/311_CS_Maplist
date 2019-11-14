package records;

public class WR extends Top10{
    private int replayID;

    public WR() {
    }

    /**
     * Create WR object
     * @param replayID
     */
    public WR(int replayID) {
        this.replayID = replayID;
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

