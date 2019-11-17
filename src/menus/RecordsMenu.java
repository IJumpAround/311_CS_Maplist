package menus;

import menus.menuclasses.optionsEnum;
import records.Record;
import records.RecordList;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import static menus.MenuHelpers.clearScreen;
import static menus.MenuHelpers.outputCurrentItem;


public class RecordsMenu {
    public static int recordsMenu(RecordList records, int maptier) {
        optionsEnum choice;
        clearScreen();

        //Leaving via exit then re-entering leave choice set to EXIT. The loop exits immediately in that case
        Menus.recordMenu.clearChoice();

        /**
         * Main loop for menu options
         */
        while (Menus.recordMenu.getMenuChoice() != optionsEnum.EXIT) {
            Menus.recordMenu.DisplayMenu();
            outputCurrentItem(records);
            choice = Menus.recordMenu.promptForMenuChoice();
            recordsExecute(records, choice, maptier);

            //Clear screen before end of loop, print any error messages here so they show at the top of screen
            clearScreen();

            //Write status messages to screen
            if(Menus.status.compareTo("") != 0) {
                System.out.println(Menus.status);
                Menus.status = "";
            }

        }
        return 0;
        //retur n choice;
    }

    public static int recordsExecute(RecordList records, optionsEnum choice, int maptier) {

        switch (choice){

            case ADD_RECORD:
                addRecordPrompt(records, maptier);
                break;
            case DELETE_RECORD:
                deleteRecordPrompt(records);
                break;
            case SEARCH_RECORDS:
                searchRecordPrompt(records);
                break;
            case NEXT:
                records.next();
                break;
            case PREVIOUS:
                records.prev();
                break;
            case EXIT:
                break;
            case NONE:
                break;
        }
        return -1;
    }

    public static void addRecordPrompt(RecordList records, int tier) {
        String input = "";
        String steamID = "";
        String playername = "";
        LocalDateTime dateOfRun = LocalDateTime.now();
        Duration time;
        float points = 0;
        int place;

        playername = MenuHelpers.promptForString("Player Name");
        steamID = MenuHelpers.promptForString("Player's SteamID:");
        time = MenuHelpers.promptForTime();

        //TODO no duplicates based on steamID
        records.addRecord(playername,steamID,dateOfRun,time,tier);
    }

    /**
     * Delete the currently selected record.
     * Prompts the user to confirm deletion first
     * @param records RecordList
     */
    public static void deleteRecordPrompt(RecordList records) {
        boolean delete;
        Record curr = records.getSelectedItem();

        delete = MenuHelpers.promptForComfirmation("Are you sure you want to delete " + curr.getPlayerName() + "'s record?");
        if(delete) {
            records.deleteRecord();
        }
    }

    /**
     * Prompt the user for a search value, set cursor equal to that value if it's found. Otherwise cursor stays the same
     * @param records RecordList
     */
    public static void searchRecordPrompt(RecordList records) {
        String searchOn,searchValue,param;
        boolean found = false;

        searchOn = MenuHelpers.promptWithOptions(Arrays.asList("steamID","place"),"Search on which value?");

        if(searchOn.equals("steamID")) {
            searchValue = MenuHelpers.promptForString("Enter the " + searchOn + " to search for");
            found = records.findRecordBySteamID(searchValue);
            param = searchValue;
        }
        else {
            int val = MenuHelpers.promptForNumber("Enter the record number you wish to see");
            found = records.findRecordByPlace(val);
            param = String.valueOf(val);
        }


        if(!found) {
            Menus.status = "[Message]: " + searchOn + " " + param + " was not found";
        }

    }

}
