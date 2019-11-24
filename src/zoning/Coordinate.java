package zoning;

/**
 * Holds coordinates for a position in a 3D plane.
 */
public class Coordinate {
    private long x; // x coord
    private long y; // y coord
    private long z; // z coord

    /**
     * Parameter constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Coordinate(long x, long y, long z) {
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
    long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    long getZ() {
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
