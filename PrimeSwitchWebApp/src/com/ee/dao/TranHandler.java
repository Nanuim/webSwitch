package com.ee.dao;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ee.entity.Mfb;

public class TranHandler {

	// Initializing Logger
	final static String mfb_XML = "C:\\ps_things/mfbconfig/test/";
	private static Logger log = Logger.getLogger(TranHandler.class.getName());

	public static void save(Mfb mb) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// Creating root element
			Element rootElement = doc.createElement("customer");
			doc.appendChild(rootElement);
			log.info("Successfully created root Element");

			// mfb elements
			Element staff = doc.createElement("mfb");
			rootElement.appendChild(staff);
			log.info("Successfully created child element");

			// set attribute to id element
			Attr attr = doc.createAttribute("id");
			attr.setValue(mb.getId());
			staff.setAttributeNode(attr);
			log.info("Successfully appended id as: " + mb.getId());

			// charge_account elements
			Element charge_account = doc.createElement("charge_account");
			charge_account.appendChild(doc.createTextNode(mb.getCharge_account()));
			staff.appendChild(charge_account);
			log.info("Successfully appended charge_account as: " + mb.getCharge_account());

			// suspense_account elements
			Element suspense_account = doc.createElement("suspense_account");
			suspense_account.appendChild(doc.createTextNode(mb.getSuspense_account()));
			staff.appendChild(suspense_account);
			log.info("Successfully appended suspense_account as: " + mb.getSuspense_account());

			// mfi_branch_id elements
			Element mfi_branch_id = doc.createElement("mfi_branch_id");
			mfi_branch_id.appendChild(doc.createTextNode(mb.getMfi_branch_id()));
			staff.appendChild(mfi_branch_id);
			log.info("Successfully appended Mfi_branch_id as: " + mb.getMfi_branch_id());

			// name elements
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(mb.getName()));
			staff.appendChild(name);
			log.info("Successfully Appended name as: " + mb.getName());

			// wsdl_location elements
			Element wsdl_location = doc.createElement("wsdl_location");
			wsdl_location.appendChild(doc.createTextNode(mb.getWsdl_location()));
			staff.appendChild(wsdl_location);
			log.trace("Successfully Appended wsdl_location as: " + mb.getWsdl_location());

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(mfb_XML + mb.getId() + ".xml"));

			// Output to console for testing
			StreamResult consresult = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public static void deletebyid(String id) {

		try {
			File file = new File(mfb_XML + id + ".xml");
			if (file.delete()) {
				log.info(file.getName() + " is deleted!");
			} else {
				log.info("Delete operation is failed.");
			}
		} catch (Exception pce) {
			pce.printStackTrace();
		}
	}

	public static void update(Mfb mb) {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(mfb_XML + mb.getId() + ".xml");
			log.info("\nRoot element :" + doc.getDocumentElement().getNodeName());

			// get the id attribute, and update the field value
			log.info("Checking that the id attribute is :" + mb.getId());

			// get the mfi_branch_id element, and update the value
			Node name = doc.getElementsByTagName("name").item(0);
			name.setTextContent(mb.getName());
			log.info("Successfully udated name as: " + mb.getName());

			// get the mfi_branch_id element, and update the value
			Node mfi_branch_id = doc.getElementsByTagName("mfi_branch_id").item(0);
			mfi_branch_id.setTextContent(mb.getMfi_branch_id());
			log.info("Successfully udated mfi_branch_id as: " + mb.getMfi_branch_id());

			// get the suspense_account element, and update the value
			Node suspense_account = doc.getElementsByTagName("suspense_account").item(0);
			suspense_account.setTextContent(mb.getSuspense_account());
			log.info("Successfully udated suspense_account as: " + mb.getSuspense_account());

			// get the charge_account element, and update the value
			Node charge_account = doc.getElementsByTagName("charge_account").item(0);
			charge_account.setTextContent(mb.getCharge_account());
			log.info("Successfully udated charge_account as: " + mb.getCharge_account());

			// get the wsdl_location element, and update the value
			Node wsdl_location = doc.getElementsByTagName("wsdl_location").item(0);
			wsdl_location.setTextContent(mb.getWsdl_location());
			log.info("Successfully udated wsdl_location as: " + mb.getWsdl_location());

			// write the content into former xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(mfb_XML + mb.getId() + ".xml"));

			// Output to console for testing
			StreamResult consoleresult = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File Updated!");

//			 		 
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	public static void read() {

		try {

			String files;
			File folder = new File(mfb_XML);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {

				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					if (files.endsWith(".xml") || files.endsWith(".XML")) {
						log.info(mfb_XML + files);

						DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
						Document doc = docBuilder.parse(mfb_XML + files);
						doc.getDocumentElement().normalize();
						log.info("\nRoot element :" + doc.getDocumentElement().getNodeName());

						NodeList nList = doc.getElementsByTagName("mfb");

						for (int temp = 0; temp < nList.getLength(); temp++) {
							Node nNode = nList.item(temp);
							log.info("\nCurrent Element :" + nNode.getNodeName());

							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;

								log.info("ID : " + nNode.getAttributes());
								log.info("Name : " + getValue("name", eElement));
								log.info("Charge Account : " + getValue("charge_account", eElement));
								log.info("Supense Account : " + getValue("suspense_account", eElement));
								log.info("Branch ID : " + getValue("mfi_branch_id", eElement));
								log.info("Wsdl Location : " + getValue("wsdl_location", eElement));
							}

							// Optional
//						log.info("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
//						log.info("Suspense Account : " + eElement.getElementsByTagName("suspense_account").item(0).getTextContent());
//						log.info("Branch ID : " + eElement.getElementsByTagName("mfi_branch_id").item(0).getTextContent());
//						log.info("Wsdl location : " + eElement.getElementsByTagName("wsdl_location").item(0).getTextContent());

//						JAXBContext jaxbContext = JAXBContext.newInstance(Mfb.class);
//						 
//						Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//						String file = mfb_XML+mb.getId()+".xml";
//						Mfb customer = (Mfb) jaxbUnmarshaller.unmarshal(new File(file));
//						System.out.println(customer);

						}
					}
				}
			}
		} catch (Exception pce) {
			pce.printStackTrace();
		}

	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}
