package zoning;

import zoning.enums.timerType;

public class TimerZone extends Zone {
    private timerType timeType;    //Bonus or main
    private boolean start;  //true if start zone, false if end zone

    public TimerZone() {
        super();
        timeType = timerType.MAIN;
        start = true;

    }

    public TimerZone(String id, Coordinate c1, Coordinate c2, long zh, timerType timeType, boolean start) {
        super(id, c1, c2, zh);
        this.timeType = timeType;
        this.start = start;
    }

    @Override
    public String prettyZone() {
        String t = this.timeType == timerType.MAIN ? "Main" : "Bonus";
        String start = this.start ? "Start" : "End";
        return super.prettyZone() + "\n"
                +"Zone is a " + t + " " + start + " zone";
    }

    public timerType GetType() {
        return timeType;
    }
    public void setTimeType(timerType timeType) {
        this.timeType = timeType;
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
