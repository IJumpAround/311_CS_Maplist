package Exceptions;

public class InvalidDimensions extends Exception{
    public InvalidDimensions(String msg) {
        super(String.valueOf(msg));
    }
}
