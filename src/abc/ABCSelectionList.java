package abc;

/**
 * Abstract base class that implements some menu selection functionality
 * NOTE: This is class is actually a container for maps, zones or records.
 * It allows selection of the items it contains in a way that makes sense for a menu.
 * IE: Next() Previous() Current()
 * It has a cursor that points to the currently selected item in the arrayList
 * implemented by the child class.
 */
public abstract class ABCSelectionList {
    protected int cursor;
    protected int count;
    protected String type;

    public ABCSelectionList() {
        cursor = 0;
        count = 0;
        initializeType();
    }


    public ABCSelectionList(int count) {
        cursor = 0;
        this.count = count;
        initializeType();
    }

    /**
     * Copy constructor
     * @param list
     */
    public ABCSelectionList(ABCSelectionList list) {
        this.cursor = list.cursor;
        this.count = list.count;
        this.type = list.type;
    }

    public int getCursor() {
        return cursor;
    }
    public void setCursor(int val) {
        cursor = val;
        clampCursor();
    }

    /**
     * Step cursor forwards by one element.
     * @throws IndexOutOfBoundsException
     */
    public void next() throws IndexOutOfBoundsException {
        if(cursor < (count -1))
            cursor++;
//        else
//            throw new IndexOutOfBoundsException("End of list");
    }

    /**
     * Step cursor backwards by one element
     * @throws IndexOutOfBoundsException
     */
    public void prev() throws IndexOutOfBoundsException {
        if(cursor > 0)
            cursor--;
//        else
//            throw new IndexOutOfBoundsException("Start of list");
    }

    /**
     * Set cursor to first element
     */
    public void start() {
        cursor = 0;
    }

    /**
     * Set cursor to last element
     */
    public void end() {
        if(count == 0)
            cursor = 0;
        else
            cursor = count-1;
    }

    /**
     * Cursor can end up outside of bounds when deleting
     */
    public void clampCursor() {
        if(cursor >= count && count != 0 ) {
            cursor = count -1;

        }
        else if(cursor > 0 && count <= 1) {
            cursor = 0;
        }
        else if(cursor < 0) {
            cursor = 0;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCursorInbounds() {
        if(cursor >= 0 && cursor < count)
            return true;
        else
            return false;
    }

    public int getCount() {
        return count;
    }
    public abstract String prettyCurrentItem();

    protected abstract void initializeType();

}

