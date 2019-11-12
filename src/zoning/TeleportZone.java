package zoning;

public class TeleportZone extends Zone {
    private String type;        //type of teleport zone (Teleporter or Destination)
    private String target;      //ID of Destination teleporter if this is a teleporter

    /**
     * Default Constructor
     */
    TeleportZone() {
        super();
        type = "source";
        target = null;
    }

    /**
     * Parameter Constructor
     * @param id identifier for this zone
     * @param c1 first corner
     * @param c2 second corner
     * @param zh zone height
     * @param _type Source or Destination
     * @param _target target destination
     */
    TeleportZone(String id, Coordinate c1, Coordinate c2, long zh, String _type, String _target) {
        super(id,c1,c2,zh);
        type = _type;
        target = _target;
    }

    /*
        Getters and setters
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
