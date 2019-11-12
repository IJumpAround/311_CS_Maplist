package Records;

import Interfaces.MenuList;

import java.util.ArrayList;
import java.util.Collection;

public class RecordList extends ArrayList<Record> implements MenuList {
    private int cursor;

    public RecordList() {
        this.cursor = 0;
    }
    public RecordList(int cursor) {
        this.cursor = cursor;
    }

    public RecordList(Collection<? extends Record> c, int cursor) {
        super(c);
        this.cursor = cursor;
    }

    public Record getRecord() {
        return this.get(cursor);
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
            throw new IndexOutOfBoundsException("Start of list");
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
