package records;
import ABC.ABCSelectionList;
import java.util.ArrayList;


/**
 * Inherits from MenuList to create a recordList
 */
public class RecordList extends ABCSelectionList {
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

    @Override
    protected void initializeType() {
        type = "Record";
    }

    public RecordList(ArrayList<Record> records) {
        this.records = records;
    }

    public Record getSelectedItem() {
        return records.get(cursor);
    }
}
