package Interfaces;

public interface MenuList {

    int getCursor();

    void next() throws IndexOutOfBoundsException;

    void prev() throws IndexOutOfBoundsException;

    void start();


    void end();

}

