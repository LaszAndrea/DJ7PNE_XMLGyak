package hu.domparse.DJ7PNE;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMWriteDJ7PNE {

	public static void main(String argv[]) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.newDocument();

		Element root = doc.createElementNS("XMLDJ7PNE", "tasakgyartas");
		doc.appendChild(root);

		// Nyomtat�g�pek

		String[] rh = { "180/10", "120/8" };
		root.appendChild(createNyomtatogep(doc, "1kg Gyermelyi finomliszt", "2500", "VF001", "5kg", rh));
		String[] rh2 = { "180/10" };
		root.appendChild(createNyomtatogep(doc, "2kg Hajd� b�zadara", "12000", "VF001", "8kg", rh2));
		String[] rh3 = { "120/8", "110/2" };
		root.appendChild(createNyomtatogep(doc, "5kg Auschan Optimum", "20000", "VF002", "14kg", rh3));

		Element element = (Element) doc.getElementsByTagName("nyomtatogep").item(0);
		Comment comment = doc.createComment("Nyomtat�g�pek");
		element.getParentNode().insertBefore(comment, element);

		// Fest�kek

		String[] gy = { "Colorprint" };
		root.appendChild(createFestek(doc, "P1495", "1kg Gyermelyi finomliszt", "2030.10.15", "150kg", gy));
		String[] gy2 = { "Colorprint", "Poli-Farbe" };
		root.appendChild(createFestek(doc, "P706", "1kg Gyermelyi finomliszt", "2032.08.01", "450kg", gy2));
		String[] gy3 = { "Colorprint" };
		root.appendChild(createFestek(doc, "P1400", "2kg Hajd� b�zadara", "2028.02.22", "150kg", gy3));
		String[] gy4 = { "Colorprint" };
		root.appendChild(createFestek(doc, "P632", "5kg Auschan Optimum", "2024.07.19", "100kg", gy4));
		String[] gy5 = { "Colorprint" };
		root.appendChild(createFestek(doc, "P935", "5kg Auschan Optimum", "2026.12.31", "50kg", gy5));
		String[] gy6 = { "Colorprint" };
		root.appendChild(createFestek(doc, "P1250", "5kg Auschan Optimum", "2032.12.15", "80kg", gy6));

		element = (Element) doc.getElementsByTagName("festek").item(0);
		comment = doc.createComment("Fest�kek");
		element.getParentNode().insertBefore(comment, element);

		// Tasakrendel�sek

		root.appendChild(createTRendeles(doc, "1", "1kg Gyermelyi finomliszt", "150000", "80gr"));
		root.appendChild(createTRendeles(doc, "2", "2kg Hajd� b�zadara", "100000", "70gr"));
		root.appendChild(createTRendeles(doc, "3", "5kg Auschan Optimum", "50000", "100gr"));

		element = (Element) doc.getElementsByTagName("tasakrendeles").item(0);
		comment = doc.createComment("Tasakrendel�s");
		element.getParentNode().insertBefore(comment, element);

		// Vev�k

		String[] isz = { "4090", "4090" };
		String[] utca = { "Kossuth", "Bethlen" };
		root.appendChild(createVevo(doc, "77777777-77-777777", "Egyes c�g", "Magyarorsz�g", isz, utca));
		String[] isz2 = { "3515" };
		String[] utca2 = { "Di�sgy�ri" };
		root.appendChild(createVevo(doc, "22222222-22-222222", "Kettes c�g", "Magyarorsz�g", isz2, utca2));
		String[] isz3 = { "2080" };
		String[] utca3 = { "Pet�fi" };
		root.appendChild(createVevo(doc, "88888888-22-787878", "H�rmas c�g", "Magyarorsz�g", isz3, utca3));

		element = (Element) doc.getElementsByTagName("vevo").item(0);
		comment = doc.createComment("Vev�k");
		element.getParentNode().insertBefore(comment, element);

		// Gy�rak

		root.appendChild(
				createGyar(doc, "12345678-22-654321", "77777777-77-777777", "Egyes gy�r", "Fels�sima", "2002"));
		root.appendChild(createGyar(doc, "13151614-32-956348", "77777777-77-777777", "Kettes gy�r", "Miskolc", "2008"));
		root.appendChild(
				createGyar(doc, "32945817-68-164872", "22222222-22-222222", "H�rmas gy�r", "Debrecen", "2016"));
		root.appendChild(
				createGyar(doc, "62599875-77-121212", "88888888-22-787878", "N�gyes gy�r", "Debrecen", "2018"));

		element = (Element) doc.getElementsByTagName("gyar").item(0);
		comment = doc.createComment("Gy�rak");
		element.getParentNode().insertBefore(comment, element);

		// Logok

		String[] szin = { "Piros", "K�k", "Z�ld" };
		root.appendChild(createLogo(doc, "1", "12345678-22-654321", "2001.12.15", szin, "Kiss P�ter"));
		String[] szin2 = { "Feh�r", "Z�ld" };
		root.appendChild(createLogo(doc, "2", "13151614-32-956348", "2007.12.20", szin2, "Nagy Zolt�n"));
		String[] szin3 = { "V�r�s", "Feh�r", "Fekete" };
		root.appendChild(createLogo(doc, "3", "32945817-68-164872", "2016.02.08", szin3, "Bogy� Annam�ria"));
		String[] szin4 = { "Fekete", "Feh�r" };
		root.appendChild(createLogo(doc, "4", "62599875-77-121212", "2018.06.26", szin4, "Varga Krisztina"));

		element = (Element) doc.getElementsByTagName("logo").item(0);
		comment = doc.createComment("Log�k");
		element.getParentNode().insertBefore(comment, element);

		// Tulajdonosok

		root.appendChild(createTulajdonos(doc, "119075100", "12345678-22-654321", "Terpl�n Z�n�", "1982", "2002"));
		root.appendChild(createTulajdonos(doc, "117080205", "13151614-32-956348", "T�th L�szl�", "1970", "1993"));
		root.appendChild(createTulajdonos(doc, "120154203", "32945817-68-164872", "Reszegi Ivett", "1969", "2006"));
		root.appendChild(createTulajdonos(doc, "120005203", "62599875-77-121212", "Balogh Panna", "1993", "2012"));

		element = (Element) doc.getElementsByTagName("tulajdonos").item(0);
		comment = doc.createComment("Tulajdonosok");
		element.getParentNode().insertBefore(comment, element);

		// Szerz�d�sek

		root.appendChild(createSzerzodes(doc, "1kg Gyermelyi finomliszt", "1", "77777777-77-777777", "2022.09.15"));
		root.appendChild(createSzerzodes(doc, "2kg Hajd� b�zadara", "2", "77777777-77-777777", "2022.09.18"));
		root.appendChild(createSzerzodes(doc, "5kg Auschan Optimum", "3", "88888888-22-787878", "2022.10.06"));
		root.appendChild(createSzerzodes(doc, "5kg Auschan Optimum", "3", "22222222-22-222222", "2022.10.03"));

		element = (Element) doc.getElementsByTagName("szerzodes").item(0);
		comment = doc.createComment("Szerz�d�s kapcsolat");
		element.getParentNode().insertBefore(comment, element);

		// Transform

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

		// File l�trehoz�sa

		DOMSource source = new DOMSource(doc);
		File myFile = new File("XMLDJ7PNE2.xml");

		// Konzolra val� ki�rat�s

		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);

		transf.transform(source, console);
		transf.transform(source, file);

	}

	private static Node createNyomtatogep(Document doc, String termeknev, String folyometer, String tipus,
			String festekfelhasznalas, String[] raszterhenger) {

		Element ny = doc.createElement("nyomtatogep");

		ny.setAttribute("termeknev", termeknev);
		ny.appendChild(createElement(doc, "folyometer", folyometer));
		ny.appendChild(createElement(doc, "tipus", tipus));
		ny.appendChild(createElement(doc, "festekfelhasznalas", festekfelhasznalas));

		Node[] node = appendArray(doc, "raszterhenger", raszterhenger);

		for (int i = 0; i < raszterhenger.length; i++) {
			ny.appendChild(node[i]);
		}

		return ny;

	}

	private static Node createFestek(Document doc, String pantonszam, String felhasznalas, String lejarat,
			String raktaron, String[] gyarto) {

		Element f = doc.createElement("festek");

		f.setAttribute("pantonszam", pantonszam);
		f.setAttribute("felhasznalas", felhasznalas);
		f.appendChild(createElement(doc, "lejarat", lejarat));
		f.appendChild(createElement(doc, "raktaron", raktaron));

		Node[] node = appendArray(doc, "gyarto", gyarto);

		for (int i = 0; i < gyarto.length; i++) {
			f.appendChild(node[i]);
		}

		return f;

	}

	private static Node createTRendeles(Document doc, String tasakID, String termeknev, String mennyiseg,
			String papirmin) {

		Element tr = doc.createElement("tasakrendeles");

		tr.setAttribute("tasakID", tasakID);
		tr.appendChild(createElement(doc, "termeknev", termeknev));
		tr.appendChild(createElement(doc, "mennyiseg", mennyiseg));
		tr.appendChild(createElement(doc, "papirmin", papirmin));

		return tr;

	}

	private static Node createVevo(Document doc, String adoszam, String nev, String szarmazas, String[] isz,
			String[] utca) {

		Element v = doc.createElement("vevo");

		v.setAttribute("adoszam", adoszam);
		v.appendChild(createElement(doc, "nev", nev));
		v.appendChild(createElement(doc, "szarmazas", szarmazas));

		Node[] node1 = appendArray(doc, "isz", isz);
		Node[] node2 = appendArray(doc, "utca", utca);

		for (int i = 0; i < isz.length; i++) {
			Element th = doc.createElement("telephely");
			th.appendChild(node1[i]);
			th.appendChild(node2[i]);
			v.appendChild(th);
		}

		return v;

	}

	private static Node createGyar(Document doc, String gyAdoszam, String vGy, String nev, String telephely,
			String alapitas) {

		Element gy = doc.createElement("gyar");

		gy.setAttribute("gyAdoszam", gyAdoszam);
		gy.setAttribute("vGy", vGy);
		gy.appendChild(createElement(doc, "nev", nev));
		gy.appendChild(createElement(doc, "telephely", telephely));
		gy.appendChild(createElement(doc, "alapitas", alapitas));

		return gy;

	}

	private static Node createLogo(Document doc, String logoId, String gyL, String elkeszites, String[] szinek,
			String tervezo) {

		Element gy = doc.createElement("logo");

		gy.setAttribute("logoId", logoId);
		gy.setAttribute("gyL", gyL);
		gy.appendChild(createElement(doc, "elkeszites", elkeszites));

		Node[] node = appendArray(doc, "szinek", szinek);

		for (int i = 0; i < szinek.length; i++) {
			gy.appendChild(node[i]);
		}

		gy.appendChild(createElement(doc, "tervezo", tervezo));

		return gy;

	}

	private static Node createTulajdonos(Document doc, String tajszam, String tGy, String nev, String szuletes,
			String szerzodese) {

		Element t = doc.createElement("tulajdonos");

		t.setAttribute("tajszam", tajszam);
		t.setAttribute("tGy", tGy);
		t.appendChild(createElement(doc, "nev", nev));
		t.appendChild(createElement(doc, "szuletes", szuletes));
		t.appendChild(createElement(doc, "szerzodese", szerzodese));

		return t;

	}

	private static Node createSzerzodes(Document doc, String tajszam, String tasakID, String adoszam, String rendeles) {

		Element szer = doc.createElement("szerzodes");

		szer.setAttribute("tNev", tajszam);
		szer.setAttribute("tasakID", tasakID);
		szer.setAttribute("adoszam", adoszam);
		szer.appendChild(createElement(doc, "rendeles", rendeles));

		return szer;

	}

	private static Node createElement(Document doc, String name, String value) {

		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;

	}

	private static Node[] appendArray(Document doc, String name, String[] value) {

		Element nodes[] = new Element[value.length];

		for (int i = 0; i < value.length; i++) {

			nodes[i] = doc.createElement(name);
			nodes[i].appendChild(doc.createTextNode(value[i]));

		}

		return nodes;

	}

}
