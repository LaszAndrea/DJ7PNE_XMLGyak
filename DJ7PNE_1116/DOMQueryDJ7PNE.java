import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMQueryDJ7PNE {

	public static void main(String argv[]) {

		try {

			File inputFile = new File("carsDJ7PNE.xml");

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
			System.out.println("------------------------------");

			NodeList nList = doc.getElementsByTagName("supercars");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);
				System.out.println("\nCurrent element: " + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String company = elem.getAttribute("company");
					
					System.out.println("Company: " + company);
					
					Node node1 = elem.getElementsByTagName("carname").item(i);
					int j = 0;
					
					
					while(elem.getElementsByTagName("carname").item(j)!=null) {
						node1 = elem.getElementsByTagName("carname").item(j);
						String carname = node1.getTextContent();
						System.out.println("carname: " + carname);
						j++;
					}
					
					
					/*NodeList nnList = doc.getElementsByTagName("carname");
					
					for (int j = 0; j < nnList.getLength(); j++) {
						
						Node nnNode = nnList.item(i);
						Element Eelem = (Element) nnNode;
						
						Node Node1 = Eelem.getElementsByTagName("carname").item(0);
						String carname = Node1.getTextContent();
						
						System.out.println("carname: " + carname);
						

					}*/

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
