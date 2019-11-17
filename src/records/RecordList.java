package records;
import abc.ABCSelectionList;

import java.time.Duration;
import java.time.LocalDateTime;
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
            str =  String.format("%-13s","Time:") + curr.formattedTime() + "\n" + curr.prettyRecord();
        }
        return str;
    }

    /**
     * Add a record to the list and determine its point value.
     * Downgrade records that get booted out of WR or Top10 by this new record
     * Increment count and update point values for all records
     * @param name playername
     * @param id steamid
     * @param dateOfRun date
     * @param time run time
     */
    public void addRecord(String name, String id, LocalDateTime dateOfRun, Duration time, int mapTier) {
        int indexToInsert = findSpot(time);
        Record record;

        //TODO double check wr point bonus is working on initial entry, First record had zero points.
        //TODO try deleteing all records and adding new one, or add record to new map
        if(indexToInsert == 0) {
            record = new WR(100);
        }
        else if(indexToInsert < 10) {
            record = new Top10(25);
        }
        else
            record = new Record();

        //Set relevant properties
        record.setPlayerName(name);
        record.setSteamID(id);
        record.setPlace(indexToInsert + 1);
        record.setDateOfRun(dateOfRun);
        record.setTime(time);

        records.add(indexToInsert, record);
        count++;
        updateAllRecords(mapTier);
    }

    /**
     * Find the next slowest time and get the index
     * @param time time to insert
     * @return index of slot
     */
    public int findSpot(Duration time) {
        int index = count;
        if(count == 0)
            return 0;

        for(int i = 0; i < count; i++) {
            //T
            if(records.get(i).getTime().compareTo(time) > 0) {
                return i;
            }
        }
        return index;
    }

    public String getWRFormatted() {
        if(count > 0)
            return records.get(0).formattedTime();
        else
            return null;
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

    /**
     * Run this against every record each time one is updated.
     * @param place record rank
     * @param tier map tier
     * @return point value
     */
    private float calculateScore(int place, int tier) {
        int p = (place > 0) ? place : 1;
        return (float)(tier * count) / p;
    }

    /**
     * Called after inserting a new WR
     * Assumes the new WR has been inserted
     */
    private void downgradeOldWR(int index) {
        Record rec = new Top10((Top10) records.get(index));
        records.set(index,rec);
    }

    /**
     * Called when there are at least 10 completions, and someone in top10 gets bumped out
     */
    private void downgradeRank10(int index) {
        Record rec = new Record(records.get(index));
        records.set(index,rec);
    }

    /**
     * Adding a new record affects the points of all other records in the list.
     * It also changes the place of every record after the spot it was inserted.
     * So we need to increment the place of these records, and update their points.
     * @param tier
     */
    private void updateAllRecords(int tier) {

        Record curr;
        for(int i = 0; i < count; i++) {
            curr = records.get(i);
            if(curr instanceof WR && i != 0)
                downgradeOldWR(i);
            else if(curr instanceof Top10 && i >9) {
                downgradeRank10(i);
            }
            float points = calculateScore(i,tier);
            curr.setPoints(points);
            curr.setPlace(i+1);
        }
    }


    /**
     * Retrieve a record by SteamID of the completing player.
     * @param id of player to search for
     * @return either the record is returned or null is returned if not found
     */

    public boolean findRecordBySteamID(String id) {
        int c = cursor;
        for(int i = 0; i < count; i++) {
            if(records.get(i).getSteamID().equals(id)) {
                c = i;
                return true;
            }
        }
        cursor = c;
        return false;
    }

    /**
     * Retrieve a record by place
     * @param place
     * @return either the record is returned or null is returned if not found
     */
    public boolean findRecordByPlace(int place) {
        if (place < count && place > 0) {
            cursor = place - 1;
            return true;
        }
        return false;
    }

    /**
     * Delete the record currently selected by the cursor
     */
    public void deleteRecord() {
        if(count > 0) {
            records.remove(cursor);
            count--;
            clampCursor();
        }
    }


}
