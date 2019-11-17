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
            str =  curr.formattedTime();
        }
        return str;
    }

    /**
     * Add a record to the list and determine its point value.
     * Downgrade records that get booted out of WR or Top10 by this new record
     * @param name
     * @param id
     * @param dateOfRun
     * @param time
     */
    public void addRecord(String name, String id, LocalDateTime dateOfRun, Duration time, int mapTier) {
        int indexToInsert = findSpot(time);
        Record record = null;
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

        if(indexToInsert < 10 && count > 10) {
            downgradeRank10();
            if(indexToInsert == 0)
                downgradeOldWR();
        }

        updateAllRecords(mapTier);







        count++;
    }

    /**
     * Find the next slowest time and get the index
     * @param time
     * @return
     */
    public int findSpot(Duration time) {
        int index = count-1;
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
     * @param place
     * @param tier
     * @return
     */
    private float calculateScore(int place, int tier) {
        return (float)(tier * count) / place;
    }

    /**
     * Called after inserting a new WR
     * Assumes the new WR has been inserted
     */
    private void downgradeOldWR() {
        Record rec = new Top10((Top10) records.get(1));
        records.set(1,rec);
    }

    /**
     * Called when there are at least 10 completions, and someone in top10 gets bumped out
     */
    private void downgradeRank10() {
        Record rec = new Record(records.get(10));
        records.set(10,rec);
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
            float points = calculateScore(i,tier);
            curr.setPoints(points);
            curr.setPlace(i+1);
        }
    }


    /**
     * Retrieve a record by SteamID of the completing player.
     * @param id
     * @return either the record is returned or null is returned if not found
     */

    public Record GetRecordByPlayer(String id) {
        for(Record rec: this.records) {
            if(rec.getSteamID().compareTo(id) == 0)
                return rec;
        }
        return null;
    }

    /**
     * Retrieve a record by place
     * @param place
     * @return either the record is returned or null is returned if not found
     */
    public Record GetRecordByPlace(int place) {
        for(Record rec: this.records) {
            if(rec.getPlace() == place)
                return rec;
        }
        return null;
    }


}
