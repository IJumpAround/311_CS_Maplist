package zoning;

import zoning.enums.teleportType;

;

public class TeleportZone extends Zone {
    private teleportType teleType;        //type of teleport zone (Teleporter or Destination)
    private String target;      //ID of Destination teleporter if this is a teleporter

    /**
     * Default Constructor
     */
    TeleportZone() {
        super();
        teleType = teleportType.SOURCE;
        target = "";
    }

    public TeleportZone(String id, Coordinate c1, Coordinate c2, long zh) {
        super(id, c1, c2, zh);
        teleType = teleportType.SOURCE;
        target = "";
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
    public TeleportZone(String id, Coordinate c1, Coordinate c2, long zh, teleportType _type, String _target) {
        super(id,c1,c2,zh);
        this.teleType = _type;
//        if(_type.compareTo("source") == 0)
//            type = teleportType.SOURCE;
//        else
//            type = teleportType.DESTINATION;
        target = _target;
    }

    @Override
    public String prettyZone() {
        String t = this.teleType == teleportType.SOURCE ? "Source" : "Destination";
        String targ = !this.target.equals("") ? this.target : "None";
        targ = this.teleType == teleportType.SOURCE ? targ : "N/A";

        return super.prettyZone() + "\n"
                + "Teleport type: " + t + "\n"
                + "Target: " + targ;
    }

    /*
            Getters and setters
         */
    public teleportType getTeleType() {
        return teleType;
    }

    public void setTeleType(teleportType teleType) {
        this.teleType = teleType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
