package xpathDJ7PNE;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathDJ7PNE {

	public static void main(String[] args) {
		
		try {
			
			//File xmlFile = new File("studentDJ7PNE.xml");
			
			//DocumentBuilder l�trehoz�sa
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentDJ7PNE.xml");
			
			document.getDocumentElement().normalize();
			
			//az XPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//meg kell adni az el�r�si �t kifejez�st �s a csom�pont list�t
			//String expression = "class";
			//String expression = "class / student";
			//String expression = "//student[@id='2']";
			//String expression = "//student";
			//String expression = "class/student[2]";
			//String expression = "class/student[last()]";
			//String expression = "class/student[last()-1]";
			//String expression = "class/student[position()<3]";
			//String expression = "/class/*";
			//String expression = "//student[@*]";
			//String expression = "//*";
			//String expression = "/class/student[age>20]";
			String expression = "//student/firstname | //student/lastname";
			
			
			//K�sz�ts�nk egy list�t, majd az xPath kifejez�st le kell ford�tani �s ki kell �rt�kelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//A for ciklus seg�ts�g�vel a NodeList csom�pontjain v�gig kell iter�lni
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktu�lis elem: " + node.getNodeName());
				
				//Meg kell vizsg�lni a csom�pontot, tesztelni kell a subelementet
				
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					
					Element element = (Element) node;
					
					//az id attrib�tumot ad vissza
					System.out.println("Hallgat� ID: " + element.getAttribute("id"));
					
					//keresztnevet ad vissza
					System.out.println("Keresztn�v: " + element.getElementsByTagName("lastname").item(0).getTextContent());
					
					//vezet�knevet ad vissza
					System.out.println("Vezet�kn�v: " + element.getElementsByTagName("firstname").item(0).getTextContent());
					
					//becenevet ad vissza
					System.out.println("Becen�v: " + element.getElementsByTagName("nickname").item(0).getTextContent());
					
					//kort ad vissza
					System.out.println("Kor: " + element.getElementsByTagName("age").item(0).getTextContent());
					
				}
				
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch(SAXException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}
	
}
