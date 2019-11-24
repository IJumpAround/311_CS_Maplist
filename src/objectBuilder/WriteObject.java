package objectBuilder;

import com.google.gson.Gson;
import maps.MapList;
import menus.MenuHelpers;
import menus.Menus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


/**
 * Serializes the all objects contained in the current maplist and writes them to a file
 */
public class WriteObject {
	private static BufferedWriter writer;


	/**
	 * Open file for writing. If the file already exists, prompt the user if they inteded to overwrite it.
	 * @param filename file
	 * @throws IOException IO
	 */
	private static void InitWriter(String filename) throws IOException {
		File file = new File(filename);
		String response = "";
		//Ask if they want to overwrite
		if(file.exists()) {
			response = MenuHelpers.promptWithOptions(Arrays.asList("y","n"),"File: " + filename + " already exists, overwrite?");
		}

		if(response.compareTo("y") == 0 || response.compareTo("") == 0) {
			writer = new BufferedWriter(new FileWriter(filename));
		}
	}

	/**
	 * Write the MapList object to a file we open with a BufferedWriter
	 * @param filename
	 * @param maps
	 */
	public static void writeObject(String filename, MapList maps) {
		Gson gson = GsonHelpers.getGson();

		//Write Json to file
		try {
			//Convert to GSON
			String result = gson.toJson(maps);

			//open the file after serialization has succeeded
			InitWriter(filename);
			writer.write(result);
			writer.close();
			Menus.status = "[Info]: Wrote to " + filename;
		}
		catch (IOException e) {
			System.out.println("ERROR when reading from input file " + filename);
			System.out.println(e.toString());

		}
		catch(Exception e) {
			Menus.status = e.toString();
			System.out.println(e.toString());
			System.out.println("ERROR DID NOT WRITE TO FILE");
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
