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

			// nyomtat�g�p attrib�tum�nak m�dos�t�sa
			Node nyomtatog = doc.getElementsByTagName("nyomtatogep").item(0);

			NamedNodeMap attr = nyomtatog.getAttributes();
			Node nodeAttr = attr.getNamedItem("termeknev");
			nodeAttr.setTextContent("1kg Nagyi Titka");

			// nyomtat�g�p foly�m�ter attrib�tiuumj�nak m�dos�t�sa
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

			// fest�k attrib�tum�nak m�dos�t�sa
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

			// Tasakrendel�sben val� m�dos�t�s
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

			// Szerz�d�s kapcsolatban val� m�dos�t�s

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

			// Tartalom konzolra, illetve f�jlba val� �r�sa

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			System.out.println("-----M�dos�tott f�jl-----");

			StreamResult consoleResult = new StreamResult(System.out);
			StreamResult file = new StreamResult(inputFile);

			transformer.transform(source, consoleResult);
			transformer.transform(source, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
