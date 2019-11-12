package zoning;

import Interfaces.MenuList;

import java.util.ArrayList;
import java.util.Collection;

public class ZoneList extends ArrayList<Zone> implements MenuList {
    private int cursor;

    public ZoneList() {
        cursor = 0;
    }

    public ZoneList(int cursor) {
        this.cursor = cursor;
    }

    public ZoneList(Collection<? extends Zone> c, int cursor) {
        super(c);
        this.cursor = cursor;
    }

    @Override
    public int getCursor() {
        return cursor;
    }

    @Override
    public void next() throws IndexOutOfBoundsException {
        if(cursor < this.size())
            cursor++;
        else
            throw new IndexOutOfBoundsException("End of list");
    }

    @Override
    public void prev() throws IndexOutOfBoundsException {
        if(cursor > 0)
            cursor--;
        else
            throw new IndexOutOfBoundsException("Beginning of list");
    }

    @Override
    public void start() {
        cursor = 0;
    }

    @Override
    public void end() {
        if(this.size() == 0)
            cursor = 0;
        else
            cursor = this.size()-1;
    }
}
