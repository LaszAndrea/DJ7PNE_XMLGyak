package dj7pne;

import java.io.File;

import org.json.*;  

public class ObjectDJ7PNE {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("Név", "LAndrea");
		obj.put("Neptunkód", "DJ7PNE");
		obj.put("Szak", "Mérnökinformatikus");
		
		System.out.println(obj);
		
		JSONArray a = new JSONArray();
		
		a.put(0, "nev");
		a.put(1, "LAndrea");
		a.put(2, "Mérnökinformatika");
		
		System.out.println(a);
		
		
				
		
		
	}

}
