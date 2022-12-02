package dj7pne;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

public class JsonWriteDJ7PNE {

	public static void main(String[] args) {

		JSONObject hallgato_1 = new JSONObject();

		hallgato_1.put("nev", "Hallgato");
		hallgato_1.put("neptunkod", "kkklll");
		hallgato_1.put("szak", "PTI");
		
		JSONObject hallgato_2 = new JSONObject();
		
		hallgato_2.put("nev", "LAndrea");
		hallgato_2.put("neptunkod", "DJ7PNE");
		hallgato_2.put("szak", "MI");
		
		JSONObject json = new JSONObject();
		
		json.put("students", List.of(hallgato_1, hallgato_2));

		try (FileWriter file = new FileWriter("students_1.json")) {
			file.write(json.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
