import Records.Record;
import maps.MapList;

import java.time.LocalTime;

public class Driver {

    public static void main(String[] args) {
        MapList maps;
        if(args.length == 0) {
            maps = new MapList();
        }
        else {
            //load from input file
        }

        recordTests();

    }

    public static void recordTests() {
        Record test = new Record();
        test.setTime(LocalTime.now());

        System.out.println(test.getTime().toString());
    }

    public static int MapsMenu(MapList maps) {
        int choice;
        System.out.println(" ");
        return -1;

    }

    /**
     * @author Satish
     * https://stackoverflow.com/questions/2979383/java-clear-the-console
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}


