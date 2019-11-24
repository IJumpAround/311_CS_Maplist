package zoning;

import zoning.enums.timerType;

/**
 * Inherits from Zone
 * TimerZones start or stop the timer depending on if they are a start or end zone.
 * Each map has a timer start and end.
 * Each map optionally can have a bonus start and end
 */
public class TimerZone extends Zone {
    private timerType timeType;    //Bonus or main
    private boolean start;         //true if start zone, false if end zone

    /**
     * Param constructor
     * @param id string identifier for this zone
     * @param c1 first corner of base
     * @param c2 opposite corner of base
     * @param zh height of zone
     * @param timeType bonus or main
     * @param start start or end
     */
    public TimerZone(String id, Coordinate c1, Coordinate c2, long zh, timerType timeType, boolean start) {
        super(id, c1, c2, zh);
        this.timeType = timeType;
        this.start = start;
    }

    /**
     * Append timerzone information to the prettified string
     * @return formatted string
     */
    @Override
    public String prettyZone() {
        String t = this.timeType == timerType.MAIN ? "Main" : "Bonus";
        String start = this.start ? "Start" : "End";
        return super.prettyZone() + "\n"
                +"Zone is a " + t + " " + start + " zone";
    }

}
