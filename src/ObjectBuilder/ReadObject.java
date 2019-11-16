package ObjectBuilder;

import maps.MapList;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadObject {
    private static Scanner objReader;

    public static MapList loadObject(String filename) {

        StringBuilder builder = new StringBuilder();
        File file = new File(filename);
        try {
            objReader = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println(f.toString());
        }
        while(objReader.hasNext()) {
            builder.append(objReader.nextLine());
        }

        Gson gson = new Gson();
        MapList maps = gson.fromJson(builder.toString(),MapList.class);

        objReader.close();
        return maps;
    }
}
