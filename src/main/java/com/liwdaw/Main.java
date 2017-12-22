package com.liwdaw;

import java.nio.file.FileSystems;

import com.liwdaw.util.XMLFile;

public class Main {

	public static void main(String[] args) {
		try {
			XMLFile xmlFile = new XMLFile(FileSystems.getDefault().getPath("config.xml"));
			xmlFile.setNodeList("source");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

}
