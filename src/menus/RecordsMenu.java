package menus;

import menus.menuclasses.optionsEnum;
import records.RecordList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static menus.MenuHelpers.*;


public class RecordsMenu {
    public static int zonesMenu(RecordList records) {
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
            recordsExecute(records, choice);

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

    public static int recordsExecute(RecordList records, optionsEnum choice) {

        switch (choice){

            case ADD_ZONE:
                addRecordPrompt(records);
                break;
            case DELETE_RECORD:
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

    public static void addRecordPrompt(RecordList records) {
        String input = "";
        /*
        need playername
            String steamID
            LocalDateTime dateOfRun
            float points
            LocalTime time
            int place
         */

        String steamID = "";
        LocalDateTime dateOfRun = LocalDateTime.now();
        LocalTime time;
        float points = 0;
        int place;

        //ArrayList options = new ArrayList(Arrays.asList(
        steamID = MenuHelpers.promptForString();
    }

}
