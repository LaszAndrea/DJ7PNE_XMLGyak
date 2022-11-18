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

			// DocumentBuilder létrehozása
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document document = documentBuilder.parse("XMLDJ7PNE.xml");

			document.getDocumentElement().normalize();

			// az XPath készítése
			XPath xPath = XPathFactory.newInstance().newXPath();

			
			
			// meg kell adni az elérési út kifejezést és a csomópont listát
			
			

			// a tasakgyartas root elem nyomtatogep gyerekelemeinek lekérdezése
			 String expression = "tasakgyartas / nyomtatogep";

			// 2-es ID-jû tasakrendelés lekérdezése
			// String expression = "//tasakrendeles[@tasakID='2']";

			// a második vevõ kiválasztása
			// String expression = "tasakgyartas/vevo[2]";

			// a logók, amikben van zöld szín felhasználva
			// String expression = "//logo[szinek='Zöld']";

			// a logóknak, amelyekben van zöld szín, azoknak az elkészítési ideje
			// String expression = "//logo[szinek='Zöld']/elkeszites";

			// Logo elkészítési idõpontja, és tervezõ kiíratása
			// String expression = "//elkeszites | //tervezo";

			// Szerdõdés kapcsolat elsõ két eleme
			// String expression = "//szerzodes[position()<3]";

			// Tasakrendelés papírminõségei, ahol a mennyiség több, mint 80000db
			//String expression = "//tasakrendeles[mennyiseg>80000]/papirmin";
			
			//Vevõk akik rendelkeznek bármilyen attribútummal
			//String expression = "//vevo[@*]";
			
			
			
			// Készítünk egy listát, majd az xPath kifejezést le kell fordítani és ki kell értékelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

			// A for ciklus segítségével a NodeList csomópontjain végig kell iterálni
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				System.out.println("\nAktuális elem: " + node.getNodeName());

				// Meg kell vizsgálni a csomópontot, tesztelni kell a subelementet, jelen esetben a nyomtatógép elemet
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("nyomtatogep")) {

					Element element = (Element) node;

					System.out.println("Terméknév: " + element.getAttribute("termeknev"));

					System.out.println(
							"Folyóméter: " + element.getElementsByTagName("folyometer").item(0).getTextContent());

					System.out.println("Típus: " + element.getElementsByTagName("tipus").item(0).getTextContent());

					System.out.println("Festékfelhasználás: "
							+ element.getElementsByTagName("festekfelhasznalas").item(0).getTextContent());

					if (nodeList.item(i).getChildNodes().getLength() > 5) {
						int db = 0;
						Node node4 = element.getElementsByTagName("raszterhenger").item(0);
						while (node4 != null) {
							node4 = element.getElementsByTagName("raszterhenger").item(db);
							if (node4 != null) {
								String rh = node4.getTextContent();
								System.out.println("Nyomtatáshoz felhasznált raszterhenger: " + rh);
							}
							db++;
						}

					}

				}
				
				// Logo elkészítés kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("elkeszites")) {

					Element element = (Element) node;

					System.out.println("Idopont: " + element.getTextContent());

				}

				// Logo tervezõjének kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tervezo")) {

					Element element = (Element) node;

					System.out.println("Tervezo: " + element.getTextContent());

				}

				// Tasakrendelés papírminõségének kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("papirmin")) {

					Element element = (Element) node;

					System.out.println("Papírminõség: " + element.getTextContent());

				}

				//Tasakrendelés kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tasakrendeles")) {

					Element element = (Element) node;

					System.out.println("tasakID: " + element.getAttribute("tasakID"));

					System.out.println(
							"Termék név: " + element.getElementsByTagName("termeknev").item(0).getTextContent());

					System.out.println(
							"Mennyiség: " + element.getElementsByTagName("mennyiseg").item(0).getTextContent());

					System.out.println(
							"Papírminõség: " + element.getElementsByTagName("papirmin").item(0).getTextContent());

				}

				//Vevõ kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vevo")) {

					Element element = (Element) node;

					System.out.println("Adószám: " + element.getAttribute("adoszam"));

					System.out.println("Név: " + element.getElementsByTagName("nev").item(0).getTextContent());

					System.out.println(
							"Származás: " + element.getElementsByTagName("szarmazas").item(0).getTextContent());

					if (nodeList.item(i).getChildNodes().getLength() > 3) {
						int db = 0;
						Node node3 = element.getElementsByTagName("telephely").item(0);
						while (node3 != null) {
							node3 = element.getElementsByTagName("telephely").item(db);
							if (node3 != null) {
								Node n = element.getElementsByTagName("isz").item(db);
								String isz = n.getTextContent();
								System.out.println("Telephely irányítószáma: " + isz);
								Node n2 = element.getElementsByTagName("utca").item(db);
								String u = n2.getTextContent();
								System.out.println("Telephely utcája: " + u);
							}
							db++;
						}

					}

				}

				//Logo kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("logo")) {

					Element element = (Element) node;

					System.out.println("logID: " + element.getAttribute("logoId"));

					System.out.println("Gyár adószáma, akié a logo: " + element.getAttribute("gyL"));

					System.out.println(
							"Elkészítés: " + element.getElementsByTagName("elkeszites").item(0).getTextContent());

					if (nodeList.item(i).getChildNodes().getLength() > 2) {
						int db = 0;
						Node node2 = element.getElementsByTagName("szinek").item(0);
						while (node2 != null) {
							node2 = element.getElementsByTagName("szinek").item(db);
							if (node2 != null) {
								String szin = node2.getTextContent();
								System.out.println("A logohoz felhasznált szín: " + szin);
							}
							db++;
						}

					}

					if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szinek")) {
						System.out.println("A logohoz felhasznált szín: "
								+ element.getElementsByTagName("szinek").item(0).getTextContent());
					}

				}

				//Szerzõdés kiíratása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("szerzodes")) {

					Element element = (Element) node;

					System.out.println("tNev: " + element.getAttribute("tNev"));

					System.out.println("tasakID: " + element.getAttribute("tasakID"));

					System.out.println("Adószám: " + element.getAttribute("adoszam"));

					System.out
							.println("Rendelés: " + element.getElementsByTagName("rendeles").item(0).getTextContent());

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
