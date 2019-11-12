package maps;
import Interfaces.MenuList;

import java.util.ArrayList;
import java.util.Collection;

public class MapList extends ArrayList<Map> implements MenuList {
    private int cursor;

    public MapList() {
        this.cursor = 0;
    }

    public MapList(int cursor) {
        this.cursor = cursor;
    }

    public MapList(Collection<? extends Map> c, int cursor) {
        super(c);
        this.cursor = cursor;
    }

    public int getCursor() {
        return cursor;
    }

    /**
     * Return the map the cursor is currently pointing at.
     * @return Map
     */
    public Map getMap() {
        return this.get(cursor);
    }

    /**
     * Set cursor to next item in the list
     * @throws IndexOutOfBoundsException at end of list
     */
    public void next() throws IndexOutOfBoundsException {
        if(cursor < this.size())
            cursor++;
        else
            throw new IndexOutOfBoundsException("End of list");
    }

    /**
     * Set cursor to previous item in the list
     * @throws IndexOutOfBoundsException already at start of list
     */
    public void prev() throws IndexOutOfBoundsException {
        if(cursor > 0)
            cursor--;
        else
            throw new IndexOutOfBoundsException("Beginning of list");
    }

    /**
     * Point cursor to the first element in the list
     */
    public void start() {
        cursor = 0;
    }

    /**
     * Point cursor to the last element in the list
     */
    public void end() {
        if(this.size() == 0)
            cursor = 0;
        else
            cursor = this.size()-1;
    }


}
