package br.edu.ifsp.pds.shadowstruggles.tools.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

// Based on a Java Logging API tutorial by Lars Vogel, version 1.3:
// http://www.vogella.com/articles/Logging/article.html.

public class MyLogger {
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;

	static private FileHandler fileHTML;
	static private Formatter formatterHTML;

	static public void setup() throws IOException {

		// Get the global logger to configure it
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		logger.setLevel(Level.WARNING);
		fileTxt = new FileHandler("Logging.txt");
		fileHTML = new FileHandler("Logging.html");
		// Create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);

		// Create HTML Formatter
		formatterHTML = new MyHtmlFormatter();
		fileHTML.setFormatter(formatterHTML);
		logger.addHandler(fileHTML);
	}
}