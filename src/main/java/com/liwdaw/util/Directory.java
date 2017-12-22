package com.liwdaw.util;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Directory {
	private List<String> filesNames = new ArrayList<String>();
	private File directory;
	public Directory(Path path) {
		directory = new File(path.toString());
	}
	
	public List<String> getFilesNames(String filesExtension) {
	  	for (File file : directory.listFiles()) {
	  		if (file.getName().endsWith((filesExtension)) && !file.getName().startsWith("~$")) {
	  			filesNames.add(file.getName());
	  		}
	  	}
	  	return filesNames;
	}
}
