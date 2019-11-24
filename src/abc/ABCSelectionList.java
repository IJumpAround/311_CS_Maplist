package abc;

/**
 * Abstract base class that implements some menu selection functionality and templates other functions.
 * Implementers of this class will act as containers in that they hold arraylists of polymorphic types.
 * They allow selection of the items contained in a way that makes sense for a menu.
 * IE: Next() Previous() & retrieve currently selected item.
 * It has a cursor that points to the currently selected item in the arrayList
 */
public abstract class ABCSelectionList {
    protected int cursor;   //Points to the currently "selected" item in the list
    protected int count;    //Number of objects held by this class
    protected String typeName;  //Used for display purposes

    /**
     * Default Constructor
     */
    protected ABCSelectionList() {
        cursor = 0;
        count = 0;

    }

    /**
     * Getter for the cursor
     * @return cursor index
     */
    public int getCursor() {
        return cursor;
    }

    /**
     * Setter for cursor
     * @param val index to set
     */
    public void setCursor(int val) {
        cursor = val;
        clampCursor();
    }

    /**
     * Step cursor forwards by one element.
     * Will not decrement the cursor out of bounds
     */
    public void next() {
        if (cursor < (count - 1))
            cursor++;
    }

    /**
     * Step cursor backwards by one element.
     * Will not increment the cursor out of bounds
     */
    public void prev()  {
        if (cursor > 0)
            cursor--;
    }

    /**
     * Set cursor to first element
     * Already had too many menu options, didn't end up using
     */
    public void start() {
        cursor = 0;
    }

    /**
     * Set cursor to last element
     * same as above
     */
    public void end() {
        if (count == 0)
            cursor = 0;
        else
            cursor = count - 1;
    }

    /**
     * Cursor can end up outside the valid range when deleting.
     * Reset cursor to be inbounds
     */
    protected void clampCursor() {
        if (cursor >= count && count != 0) {
            cursor = count - 1;

        } else if (cursor > 0 && count <= 1) {
            cursor = 0;
        } else if (cursor < 0) {
            cursor = 0;
        }
    }

    /**
     * Getter for class type
     * @return name of class
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Check if the cursor is inbounds
     * @return true only if the current index has an item
     */
    protected boolean isCursorInbounds() {
        return cursor >= 0 && cursor < count;
    }

    public int getCount() {
        return count;
    }

    /**
     * Should be implemented by each base class for formatted output in the menu
     * Formats all information about the currently selected item for printing to the user
     * @return string
     */
    public abstract String prettyCurrentItem();
    protected abstract void initializeType();
}

