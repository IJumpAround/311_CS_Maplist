package zoning;

/**
 * Holds coordinates for a position in a 3D plane.
 */
public class Coordinate {
    private long x;
    private long y;
    private long z;

    /**
     * Default constructor, initialize everything to 0
     */
    Coordinate(){
        x = 0;
        y = 0;
        z = 0;
    }

    Coordinate(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor
     * @param c A coordinate to copy into this one
     */
    Coordinate(Coordinate c) {
        this.x = c.x;
        this.y = c.y;
        this.z = c.z;
    }

    /**
     * Getters and setters
     */
    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public long getZ() {
        return z;
    }

    public void setZ(long z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y +  "," + this.z + ")";
    }
}
