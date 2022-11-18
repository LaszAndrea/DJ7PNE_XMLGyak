package hu.domparse.DJ7PNE;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyDJ7PNE {

	public static void main(String argv[]) {

		try {

			File inputFile = new File("XMLDJ7PNE2.xml");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(inputFile);

			// nyomtatógép attribútumának módosítása
			Node nyomtatog = doc.getElementsByTagName("nyomtatogep").item(0);

			NamedNodeMap attr = nyomtatog.getAttributes();
			Node nodeAttr = attr.getNamedItem("termeknev");
			nodeAttr.setTextContent("1kg Nagyi Titka");

			// nyomtatógép folyóméter attribútiuumjának módosítása
			NodeList list = nyomtatog.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if ("folyometer".equals(eElement.getNodeName())) {
						if ("2500".equals(eElement.getTextContent())) {
							eElement.setTextContent("3200");
						}
					}
				}
			}

			// festék attribútumának módosítása
			NodeList festek = doc.getElementsByTagName("festek");

			for (int i = 0; i < festek.getLength(); i++) {
				Node node = festek.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if ("1kg Gyermelyi finomliszt".equals(eElement.getAttribute("felhasznalas"))) {
						NamedNodeMap attr2 = eElement.getAttributes();
						Node nodeAttr2 = attr2.getNamedItem("felhasznalas");
						nodeAttr2.setTextContent("1kg Nagyi Titka");
					}
				}
			}

			// Tasakrendelésben való módosítás
			Node trr = doc.getElementsByTagName("tasakrendeles").item(0);
			NodeList tr = trr.getChildNodes();

			for (int i = 0; i < tr.getLength(); i++) {
				Node node = tr.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if ("termeknev".equals(eElement.getNodeName())) {
						if ("1kg Gyermelyi finomliszt".equals(eElement.getTextContent())) {
							eElement.setTextContent("1kg Nagyi Titka");
						}
					}
					if ("mennyiseg".equals(eElement.getNodeName())) {
						if ("150000".equals(eElement.getTextContent())) {
							eElement.setTextContent("180000");
						}
					}
				}
			}

			// Szerzõdés kapcsolatban való módosítás

			NodeList sz = doc.getElementsByTagName("szerzodes");

			for (int i = 0; i < sz.getLength(); i++) {
				Node node = sz.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if ("1kg Gyermelyi finomliszt".equals(eElement.getAttribute("tNev"))) {
						NamedNodeMap attr2 = eElement.getAttributes();
						Node nodeAttr2 = attr2.getNamedItem("tNev");
						nodeAttr2.setTextContent("1kg Nagyi Titka");
					}
				}
			}

			// Tartalom konzolra, illetve fájlba való írása

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			System.out.println("-----Módosított fájl-----");

			StreamResult consoleResult = new StreamResult(System.out);
			StreamResult file = new StreamResult(inputFile);

			transformer.transform(source, consoleResult);
			transformer.transform(source, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
