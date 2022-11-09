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
			
			//DocumentBuilder létrehozása
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentDJ7PNE.xml");
			
			document.getDocumentElement().normalize();
			
			//az XPath készítése
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//meg kell adni az elérési út kifejezést és a csomópont listát
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
			
			
			//Készítsünk egy listát, majd az xPath kifejezést le kell fordítani és ki kell értékelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			//A for ciklus segítségével a NodeList csomópontjain végig kell iterálni
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				
				//Meg kell vizsgálni a csomópontot, tesztelni kell a subelementet
				
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					
					Element element = (Element) node;
					
					//az id attribútumot ad vissza
					System.out.println("Hallgató ID: " + element.getAttribute("id"));
					
					//keresztnevet ad vissza
					System.out.println("Keresztnév: " + element.getElementsByTagName("lastname").item(0).getTextContent());
					
					//vezetéknevet ad vissza
					System.out.println("Vezetéknév: " + element.getElementsByTagName("firstname").item(0).getTextContent());
					
					//becenevet ad vissza
					System.out.println("Becenév: " + element.getElementsByTagName("nickname").item(0).getTextContent());
					
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
