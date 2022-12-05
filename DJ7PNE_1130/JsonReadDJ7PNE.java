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
			
			Object obj = parser.parse(new FileReader("vizsgakDJ7PNE.json"));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject o = (JSONObject) jsonObject.get("vizsgak");

			JSONArray kurzus = (JSONArray) o.get("vizsga");
			for (int i = 0; i < kurzus.size(); i++) {

				System.out.println(i+1 + ". kurzus:\n");
				
				JSONObject a = (JSONObject) kurzus.get(i);
				System.out.println("Kurzus: " + a.get("kurzus"));
				System.out.println("Helysz�n: " + a.get("helyszin"));
				
				JSONObject object = (JSONObject) a.get("idopont");
				
				System.out.println("Nap: " + object.get("nap"));
				System.out.println("H�nyt�l: " + object.get("tol"));
				System.out.println("Meddig: " + object.get("ig"));
				
				System.out.println("Oktat�: " + a.get("oktato"));
				System.out.println("Jegy: " + a.get("jegy"));
				
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
