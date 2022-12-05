package dj7pne;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

public class JsonWriteDJ7PNE {

	public static void main(String[] args) {

		JSONObject vizsgak = new JSONObject();
		
		JSONObject kurzus_1 = new JSONObject();

		kurzus_1.put("kurzus", "Valszám");
		kurzus_1.put("helyszin", "303");
		kurzus_1.put("oktato", "Dr. Fegyverneki Sándor");
		kurzus_1.put("jegy", "3");
		
		JSONObject idopont = new JSONObject();
		
		idopont.put("nap", "hetfo");
		idopont.put("tol", "8:00");
		idopont.put("ig", "10:00");
		
		kurzus_1.put("idopont", idopont);
		
		JSONObject kurzus_2 = new JSONObject();
		
		kurzus_2.put("kurzus", "OOP");
		kurzus_2.put("helyszin", "103");
		kurzus_2.put("oktato", "Dr. Baksáné Varga Erika");
		kurzus_2.put("jegy", "4");
		
		JSONObject idopont_2 = new JSONObject();
		
		idopont_2.put("nap", "kedd");
		idopont_2.put("tol", "10:00");
		idopont_2.put("ig", "12:00");
		
		kurzus_2.put("idopont", idopont_2);
		
		JSONObject json = new JSONObject();
		
		json.put("vizsga", List.of(kurzus_1, kurzus_2));
		
		vizsgak.put("_id", "DJ7PNE");
		vizsgak.put("vizsgak", json);

		try (FileWriter file = new FileWriter("vizsgak_1.json")) {
			file.write(vizsgak.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
