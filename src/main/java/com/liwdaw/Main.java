package com.liwdaw;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import com.liwdaw.util.Directory;
import com.liwdaw.util.Email;
import com.liwdaw.util.XMLFile;

public class Main {

	public static void main(String[] args) {
		try {
			Path xmlFilePath = FileSystems.getDefault().getPath("config.xml");
			XMLFile xmlFile = new XMLFile(xmlFilePath);
			xmlFile.setNodeList("source");
			Path directoryPath = FileSystems.getDefault().getPath(
					xmlFile.getElementByTagName("directoryAbsolutePath")
			);
			Directory directory = new Directory(directoryPath);
			String filesExtension = xmlFile.getElementByTagName("filesExtension");
			List<String> filesNames = directory.getFilesNames(filesExtension);
			xmlFile.setNodeList("sender");
			Email email = new Email();
			email.setProperties(xmlFile.getElementByTagName("smtpHost"), xmlFile.getElementByTagName("smtpPort"));
			email.createSession(xmlFile.getElementByTagName("email"), xmlFile.getElementByTagName("password"));
			xmlFile.setNodeList("receiver");
			email.setReceiver(xmlFile.getElementByTagName("email"));
			email.setTitle("BACKUP");
			email.addAtachments(directoryPath, filesNames, filesExtension);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
