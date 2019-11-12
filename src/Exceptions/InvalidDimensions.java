package Exceptions;

/**
 * Thrown when creating a zone of invalid proportions.
 * When the base of the cuboid has different z values
 */
public class InvalidDimensions extends Exception{
    public InvalidDimensions(String msg) {
        super(String.valueOf(msg));
    }
}
