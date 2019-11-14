package Menus.MenuClasses;

/**
 * Abstract base class that implements some menu selection functionality
 * NOTE: This is class is actually a container for maps, zones or records.
 * It allows selection of the items it contains in a way that makes sense for a menu.
 * IE: Next() Previous() Current()
 * It has a cursor that points to the currently selected item in the arrayList
 * implemented by the child class.
 */
public abstract class SelectionList {
    protected int cursor;
    protected int count;
    protected String type;

    public SelectionList() {
        cursor = 0;
        count = 0;
        initializeType();
    }


    public SelectionList(int count) {
        cursor = 0;
        this.count = count;
        initializeType();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }
    public abstract String prettyCurrentItem();

    protected abstract void initializeType();

}

