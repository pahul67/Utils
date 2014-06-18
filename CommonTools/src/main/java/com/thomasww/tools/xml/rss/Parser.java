/**
 * 
 */
package com.thomasww.tools.xml.rss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.thomasww.tools.xml.Feed;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public class Parser implements com.thomasww.tools.xml.Parser {

	/**
	 * 
	 */
	public Parser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see com.thomasww.tools.xml.Parser#parse(java.io.InputStream)
	 */
	public Feed parse(InputStream in) {
		Handler handler = new Handler();
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
			reader.parse(new InputSource(in));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handler.getFeed(); 
	}
	public static void main(String[] args) {
		Parser parser = new Parser();
		try {
			parser.parse(new FileInputStream("/Users/paul/Documents/workspace/Aggregator/src/main/resources/xml.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
