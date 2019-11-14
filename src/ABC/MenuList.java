package ABC;

/**
 * Abstract base clase that implements some menu selection functionality
 */
public abstract class MenuList {
    protected int cursor;
    protected int count;

    public MenuList() {
        cursor = 0;
        count = 0;
    }

    public MenuList(int count) {
        cursor = 0;
        this.count = count;
    }

    int getCursor() {
        return cursor;
    }

    void next() throws IndexOutOfBoundsException {
        if(cursor < count)
            cursor++;
        else
            throw new IndexOutOfBoundsException("End of list");
    }

    void prev() throws IndexOutOfBoundsException {
        if(cursor > 0)
            cursor--;
        else
            throw new IndexOutOfBoundsException("Start of list");
    }

    void start() {
        cursor = 0;
    }

    void end() {
        if(count == 0)
            cursor = 0;
        else
            cursor = count-1;
    }

}

