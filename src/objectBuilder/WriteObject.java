package objectBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maps.MapList;
import menus.MenuHelpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class WriteObject {
	public static BufferedWriter writer;


	/**
	 * Open file for writing. If the file already exists, prompt the user if they inteded to overwrite it.
	 * @param filename file
	 * @return true to write, false to cancel
	 * @throws IOException IO
	 */
	public static boolean InitWriter(String filename) throws IOException {
		File file = new File(filename);
		String response = "";
		//Ask if they want to overwrite
		if(file.exists()) {
			response = MenuHelpers.promptWithOptions(Arrays.asList("y","n"),"File: " + filename + " already exists, overwrite?");
		}

		if(response.compareTo("y") == 0 || response.compareTo("") == 0) {
			writer = new BufferedWriter(new FileWriter(filename));
			return true;
		}
		return false;
	}

	/**
	 * Write the MapList object to a file we open with a BufferedWriter
	 * @param filename
	 * @param maps
	 */
	public static void writeObject(String filename, MapList maps) {

		try {
			boolean result = InitWriter(filename);
			if(!result)
				return;
		}
		catch (IOException e) {
			System.out.println(e.toString());

		}
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		//builder.setFieldNamingStrategy(new FieldNamingStrategy();

		Gson gson;
		try {
			gson = builder.create();
		}
		catch(Exception e) {
			System.out.println("Unhandled exception when building the Gson object");
			System.out.println(e.toString());
			return;
		}

		//System.out.println(gson.toJson(maps));

		//Write Json to file
		try {
			writer.write(gson.toJson(maps));
			writer.close();
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
