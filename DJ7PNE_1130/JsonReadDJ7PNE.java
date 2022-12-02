package dj7pne;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReadDJ7PNE {

	public static void main(String[] args) {

		JSONParser parser = new JSONParser();

		try {
			
			Object obj = parser.parse(new FileReader("JsonDJ7PNE.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			JSONArray students = (JSONArray) jsonObject.get("students");
			for (int i = 0; i < students.size(); i++) {

				System.out.println(i+1 + ". hallgató:\n");
				
				JSONObject a = (JSONObject) students.get(i);
				System.out.println("Név: " + a.get("nev"));
				System.out.println("Neptunkód: " + a.get("neptunkod"));
				System.out.println("Szak: " + a.get("szak"));
				
				System.out.println("\n");
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
