package com.liwdaw.util;

import java.io.File;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLFile {
	
	private Document document;
	private NodeList nodeList;
	private Element element;
	
	public XMLFile(Path path) throws Exception {
		File xmlFile = new File(path.toString());
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(xmlFile);
		document.getDocumentElement().normalize();
	}
	
	public void setNodeList(String tagName) {
		nodeList = document.getElementsByTagName(tagName);
		Node node = nodeList.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			element = (Element) node;
		}
	}
	
	public String getElementByTagName(String tagName) {
		return element.getElementsByTagName(tagName).item(0).getTextContent();
	}

}
