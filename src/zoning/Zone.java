package zoning;

import Exceptions.InvalidDimensions;


/**
 * Defines a zone object and implements methods to create one.
 * A zone is always a rectangular cuboid. This means we only need two corners and a height to define a zone.
 */
public class Zone {
    protected Coordinate[] corners;     //4 corners for the zone box
    protected long zHeight;             //Height of the zone
    protected String ID;                //identifier of the zone

    /**
     * Default constructor
     */
    Zone() {
        corners = new Coordinate[4];
        zHeight = 0;
        ID = "-1";
    }

    /**
     * Parameter Constructor
     * @param id identifier for this zone
     * @param c1 First corner
     * @param c2 Opposite corner
     * @param zh Height of zone
     */
    Zone(String id, Coordinate c1, Coordinate c2, long zh) {
        this.ID = id;
        this.zHeight = zh;

        try {
            SetCorners(c1, c2);
        }
        catch (InvalidDimensions e) {
            System.out.println("Exception while creating zone " + id + " " + e.toString());
            corners = new Coordinate[4]; //use default constructor on exception
        }
    }

    /**
     * Take in two Coordinates representing opposite corners of the base of the cuboid
     * @param c1 first corner coordinate
     * @param c3 opposite corner coordinate
     * @throws InvalidDimensions thrown if c1 and c3 do not have the same z value
     */
    protected void SetCorners(Coordinate c1, Coordinate c3) throws InvalidDimensions {

        if(c1.getZ() != c3.getZ())
            throw new InvalidDimensions("Coord1: " + c1.toString() + "\tCoord2: " + c3.toString());

        this.corners[0] = new Coordinate(c1);
        this.corners[1] = new Coordinate(c1.getX(), c3.getY(), c1.getZ());
        this.corners[2] = new Coordinate(c3);
        this.corners[3] = new Coordinate(c3.getX(), c1.getY(), c1.getZ());
    }

    public Coordinate[] getCorners() {
        return corners;
    }

    public long getzHeight() {
        return zHeight;
    }

    public void setzHeight(long zHeight) {
        this.zHeight = zHeight;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Prettify zone information for display
     * @return
     */
    public String prettyZone() {
        return "Zone ID: " + ID + "\n"
                +    "Corners: " + corners[0].toString() + "\n"
                +    "         " + corners[1].toString() + "\n"
                +    "         " + corners[2].toString() + "\n"
                +    "         " + corners[3].toString() + "\n"
                +    "Height: " + zHeight;
    }
}
