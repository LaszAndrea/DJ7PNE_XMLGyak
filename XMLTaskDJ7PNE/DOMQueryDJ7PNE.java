package hu.domparse.DJ7PNE;

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

public class DOMQueryDJ7PNE {

	public static void main(String[] args) {

		try {

			// DocumentBuilder l�trehoz�sa
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("XMLDJ7PNE.xml");

			document.getDocumentElement().normalize();

			// az XPath k�sz�t�se
			XPath xPath = XPathFactory.newInstance().newXPath();

			
			
			// meg kell adni az el�r�si �t kifejez�st �s a csom�pont list�t
			
			

			// a tasakgyartas root elem nyomtatogep gyerekelemeinek lek�rdez�se
			 String expression = "tasakgyartas / nyomtatogep";

			// 2-es ID-j� tasakrendel�s lek�rdez�se
			// String expression = "//tasakrendeles[@tasakID='2']";

			// a m�sodik vev� kiv�laszt�sa
			// String expression = "tasakgyartas/vevo[2]";

			// a log�k, amikben van z�ld sz�n felhaszn�lva
			// String expression = "//logo[szinek='Z�ld']";

			// a log�knak, amelyekben van z�ld sz�n, azoknak az elk�sz�t�si ideje
			// String expression = "//logo[szinek='Z�ld']/elkeszites";

			// Logo elk�sz�t�si id�pontja, �s tervez� ki�rat�sa
			// String expression = "//elkeszites | //tervezo";

			// Szerd�d�s kapcsolat els� k�t eleme
			// String expression = "//szerzodes[position()<3]";

			// Tasakrendel�s pap�rmin�s�gei, ahol a mennyis�g t�bb, mint 80000db
			//String expression = "//tasakrendeles[mennyiseg>80000]/papirmin";
			
			//Vev�k akik rendelkeznek b�rmilyen attrib�tummal
			//String expression = "//vevo[@*]";
			
			
			
			// K�sz�t�nk egy list�t, majd az xPath kifejez�st le kell ford�tani �s ki kell �rt�kelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			// A for ciklus seg�ts�g�vel a NodeList csom�pontjain v�gig kell iter�lni
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				System.out.println("\nAktu�lis elem: " + node.getNodeName());

				// Meg kell vizsg�lni a csom�pontot, tesztelni kell a subelementet, jelen esetben a nyomtat�g�p elemet
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("nyomtatogep")) {

					Element element = (Element) node;

					System.out.println("Term�kn�v: " + element.getAttribute("termeknev"));

					System.out.println(
							"Foly�m�ter: " + element.getElementsByTagName("folyometer").item(0).getTextContent());

					System.out.println("T�pus: " + element.getElementsByTagName("tipus").item(0).getTextContent());

					System.out.println("Fest�kfelhaszn�l�s: "
							+ element.getElementsByTagName("festekfelhasznalas").item(0).getTextContent());

					if (nodeList.item(i).getChildNodes().getLength() > 5) {
						int db = 0;
						Node node4 = element.getElementsByTagName("raszterhenger").item(0);
						while (node4 != null) {
							node4 = element.getElementsByTagName("raszterhenger").item(db);
							if (node4 != null) {
								String rh = node4.getTextContent();
								System.out.println("Nyomtat�shoz felhaszn�lt raszterhenger: " + rh);
							}
							db++;
						}

					}

				}
				
				// Logo elk�sz�t�s ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("elkeszites")) {

					Element element = (Element) node;

					System.out.println("Idopont: " + element.getTextContent());

				}

				// Logo tervez�j�nek ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tervezo")) {

					Element element = (Element) node;

					System.out.println("Tervezo: " + element.getTextContent());

				}

				// Tasakrendel�s pap�rmin�s�g�nek ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("papirmin")) {

					Element element = (Element) node;

					System.out.println("Pap�rmin�s�g: " + element.getTextContent());

				}

				//Tasakrendel�s ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tasakrendeles")) {

					Element element = (Element) node;

					System.out.println("tasakID: " + element.getAttribute("tasakID"));

					System.out.println(
							"Term�k n�v: " + element.getElementsByTagName("termeknev").item(0).getTextContent());

					System.out.println(
							"Mennyis�g: " + element.getElementsByTagName("mennyiseg").item(0).getTextContent());

					System.out.println(
							"Pap�rmin�s�g: " + element.getElementsByTagName("papirmin").item(0).getTextContent());

				}

				//Vev� ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vevo")) {

					Element element = (Element) node;

					System.out.println("Ad�sz�m: " + element.getAttribute("adoszam"));

					System.out.println("N�v: " + element.getElementsByTagName("nev").item(0).getTextContent());

					System.out.println(
							"Sz�rmaz�s: " + element.getElementsByTagName("szarmazas").item(0).getTextContent());

					if (nodeList.item(i).getChildNodes().getLength() > 3) {
						int db = 0;
						Node node3 = element.getElementsByTagName("telephely").item(0);
						while (node3 != null) {
							node3 = element.getElementsByTagName("telephely").item(db);
							if (node3 != null) {
								Node n = element.getElementsByTagName("isz").item(db);
								String isz = n.getTextContent();
								System.out.println("Telephely ir�ny�t�sz�ma: " + isz);
								Node n2 = element.getElementsByTagName("utca").item(db);
								String u = n2.getTextContent();
								System.out.println("Telephely utc�ja: " + u);
							}
							db++;
						}

					}

				}

				//Logo ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("logo")) {

					Element element = (Element) node;

					System.out.println("logID: " + element.getAttribute("logoId"));

					System.out.println("Gy�r ad�sz�ma, aki� a logo: " + element.getAttribute("gyL"));

					System.out.println(
							"Elk�sz�t�s: " + element.getElementsByTagName("elkeszites").item(0).getTextContent());

					if (nodeList.item(i).getChildNodes().getLength() > 2) {
						int db = 0;
						Node node2 = element.getElementsByTagName("szinek").item(0);
						while (node2 != null) {
							node2 = element.getElementsByTagName("szinek").item(db);
							if (node2 != null) {
								String szin = node2.getTextContent();
								System.out.println("A logohoz felhaszn�lt sz�n: " + szin);
							}
							db++;
						}

					}

					if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szinek")) {
						System.out.println("A logohoz felhaszn�lt sz�n: "
								+ element.getElementsByTagName("szinek").item(0).getTextContent());
					}

				}

				//Szerz�d�s ki�rat�sa
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szerzodes")) {

					Element element = (Element) node;

					System.out.println("tNev: " + element.getAttribute("tNev"));

					System.out.println("tasakID: " + element.getAttribute("tasakID"));

					System.out.println("Ad�sz�m: " + element.getAttribute("adoszam"));

					System.out
							.println("Rendel�s: " + element.getElementsByTagName("rendeles").item(0).getTextContent());

				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

}
