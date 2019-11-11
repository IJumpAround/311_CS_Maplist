package zoning;

public class TimerZone extends Zone {
    private String type;    //Bonus or main
    private boolean start;  //true if start zone, false if end zone

    TimerZone() {
        super();
        type = "main";
        start = true;

    }

    TimerZone(String id, Coordinate c1, Coordinate c2, long zh, String t, boolean s) {
        super(id,c1,c2,zh);
        this.type = t;
        this.start = s;
    }

    public String GetType() {
        return type;
    }
}
