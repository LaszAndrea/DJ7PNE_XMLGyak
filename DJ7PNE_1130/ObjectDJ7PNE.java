package dj7pne;

import java.io.File;

import org.json.*;  

public class ObjectDJ7PNE {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("N�v", "LAndrea");
		obj.put("Neptunk�d", "DJ7PNE");
		obj.put("Szak", "M�rn�kinformatikus");
		
		System.out.println(obj);
		
		JSONArray a = new JSONArray();
		
		a.put(0, "nev");
		a.put(1, "LAndrea");
		a.put(2, "M�rn�kinformatika");
		
		System.out.println(a);
		
		
				
		
		
	}

}
