package exceptions;

/**
 * Thrown when adding a duplicate zone, map, or record.
 */
public class DuplicateEntryException extends Exception {
    public DuplicateEntryException(String msg) {super(msg);}
}
