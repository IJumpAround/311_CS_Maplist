package objectBuilder;

import maps.MapList;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadObject {
    private static Scanner objReader;

    /**
     * Load maplist information from a gson file
     * @param filename file to load
     * @return MapList generated from file
     */
    public static MapList loadObject(String filename) {

        StringBuilder builder = new StringBuilder();
        File file = new File(filename);
        //Open file
        try {
            objReader = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println(f.toString());
            return null;
        }

        //Read into a string builder
        while(objReader.hasNext()) {
            builder.append(objReader.nextLine());
        }

        //Convert file to Maplist
        Gson gson = new Gson();
        MapList maps = gson.fromJson(builder.toString(),MapList.class);

        objReader.close();
        return maps;
    }
}
