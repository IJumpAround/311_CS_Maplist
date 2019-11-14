package Records;
import ABC.MenuList;
import java.util.ArrayList;


public class RecordList extends MenuList {
    private ArrayList<Record> records;

    public RecordList() {
        records = new ArrayList<>();
    }

    public RecordList(ArrayList<Record> records) {
        this.records = records;
    }

    public Record getRecord() {
        return records.get(cursor);
    }
}
