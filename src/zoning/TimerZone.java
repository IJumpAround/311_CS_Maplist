package zoning;

public class TimerZone extends Zone {
    private String type;    //Bonus or main
    private boolean start;  //true if start zone, false if end zone

    TimerZone() {
        super();
        type = "main";
        start = true;

    }

    /**
     * Paremeter Constructor
     * @param id Zone Identifier
     * @param c1 first corner
     * @param c2 opposite corner
     * @param zh height
     * @param t  type (bonus or main)
     * @param s  start zone or end zone
     */
    TimerZone(String id, Coordinate c1, Coordinate c2, long zh, String t, boolean s) {
        super(id,c1,c2,zh);
        this.type = t;
        this.start = s;
    }

    public String GetType() {
        return type;
    }
    public void setType(String type) {
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
