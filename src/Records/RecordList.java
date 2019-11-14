package Records;
import Menus.MenuList;
import java.util.ArrayList;


/**
 * Inherits from MenuList to create a recordList
 */
public class RecordList extends MenuList {
    private ArrayList<Record> records;

    public RecordList() {
        records = new ArrayList<>();
    }

    @Override
    public String prettyCurrentItem() {
        String str = "";

        if(records.size() > 0) {
            Record curr = getSelectedItem();
            str =  curr.formattedTime();
        }
        return str;
    }

    public RecordList(ArrayList<Record> records) {
        this.records = records;
    }

    public Record getSelectedItem() {
        return records.get(cursor);
    }
}
