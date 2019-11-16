package zoning;

public class TimerZone extends Zone {
    private timerType type;    //Bonus or main
    private boolean start;  //true if start zone, false if end zone

    public TimerZone() {
        super();
        type = timerType.MAIN;
        start = true;

    }

    public TimerZone(String id, Coordinate c1, Coordinate c2, long zh,timerType type, boolean start) {
        super(id, c1, c2, zh);
        this.type = type;
        this.start = start;
    }



    public timerType GetType() {
        return type;
    }
    public void setType(timerType type) {
        this.type = type;
    }


    public boolean IsStart() {
        return start;
    }

    public boolean IsEnd() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
