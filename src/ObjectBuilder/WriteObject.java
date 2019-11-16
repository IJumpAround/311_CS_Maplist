package ObjectBuilder;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maps.MapList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Scanner;

public class WriteObject {
	public static BufferedWriter writer;

	public static void InitWriter(String filename) throws IOException {
		filename = "ObjectGson.gson";
		writer = new BufferedWriter(new FileWriter(filename));
	}
	public static void test(MapList maps) {
		try {
			InitWriter("");
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting().serializeNulls();
		//builder.setFieldNamingStrategy(new FieldNamingStrategy();

		Gson gson = builder.create();

		//System.out.println(gson.toJson(maps));

		try {
			writer.write(gson.toJson(maps));
			writer.close();
		}
		catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
