package menus;

import menus.menuclasses.optionsEnum;
import records.RecordList;

import java.time.Duration;
import java.time.LocalDateTime;

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
                //TODO implement this
                //deleteRecordPrompt(records);
                break;
            case VIEW:
                //recordsSubMenu(records.getSelectedMap());
                break;
            case SEARCH_RECORDS:
               // searchRecords(records);
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

}
