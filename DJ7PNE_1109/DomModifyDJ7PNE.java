package domDJ7PNE;

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

public class DomModifyDJ7PNE {

	public static void main(String argv[]) {

		try {

			File inputFile = new File("carsDJ7PNE.xml");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(inputFile);

			Node cars = doc.getFirstChild();
			Node supercar = doc.getElementsByTagName("supercars").item(0);

			// szuper auto attribútumának módosítása
			NamedNodeMap attr = supercar.getAttributes();
			Node nodeAttr = attr.getNamedItem("company");
			nodeAttr.setTextContent("Lamborghini");

			// loop the supercar child node
			NodeList list = supercar.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					if ("carname".equals(eElement.getNodeName())) {
						if ("Ferrari 01".equals(eElement.getTextContent())) {
							eElement.setTextContent("Lamborghini 01");
						}
						if ("Ferrari 02".equals(eElement.getTextContent())) {
							eElement.setTextContent("lamborghini 02");
						}
					}
				}
			}
			NodeList childNodes = cars.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				Node node = childNodes.item(j);

				if ("luxurycars".equals(node.getNodeName())) {
					cars.removeChild(node);
				}
			}

			// Tartalom konzolra írása

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			DOMSource source = new DOMSource(doc);

			System.out.println("-----Módosított fájl-----");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
