package zoning;

;

public class TeleportZone extends Zone {
    private teleportType type;        //type of teleport zone (Teleporter or Destination)
    private String target;      //ID of Destination teleporter if this is a teleporter

    /**
     * Default Constructor
     */
    TeleportZone() {
        super();
        type = teleportType.SOURCE;
        target = null;
    }

    public TeleportZone(String id, Coordinate c1, Coordinate c2, long zh) {
        super(id, c1, c2, zh);
        type = teleportType.SOURCE;
        target = null;
    }

    /**
     * Parameter Constructor
     * assumes the _type input is either source or destination
     * @param id identifier for this zone
     * @param c1 first corner
     * @param c2 second corner
     * @param zh zone height
     * @param _type Source or Destination
     * @param _target target destination
     */
    TeleportZone(String id, Coordinate c1, Coordinate c2, long zh, String _type, String _target) {
        super(id,c1,c2,zh);

        if(_type.compareTo("source") == 0)
            type = teleportType.SOURCE;
        else
            type = teleportType.DESTINATION;
        target = _target;
    }

    /*
        Getters and setters
     */
    public teleportType getType() {
        return type;
    }

    public void setType(teleportType type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
