/**
 * Created on Jul 12, 2012.
 * Copyright ©DecodeApps.
 */
package com.thomasww.tools.language;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.thomasww.tools.http.Client;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public class LanguageUtils {


	/**
	 * The logger.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private static Log log = new Log4JLogger(LanguageUtils.class.getPackage().getName());

	/**
	 * @param html
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public static String getLanguageForText(String text, String key) {
		String language = null;
		Client client = Client.getInstance();
		try {
		log.warn("getLanguageForText(https://www.googleapis.com/language/translate/v2/detect?q=" + URLEncoder.encode(text,"utf-8") + "&key=" + key + ")");
		InputStream in = client.getFeed("https://www.googleapis.com/language/translate/v2/detect?q=" + URLEncoder.encode(text,"utf-8") + "&key=" + key);
		ObjectMapper mapper = new ObjectMapper().configure(Feature.ALLOW_NON_NUMERIC_NUMBERS, true);
		try {
			JsonNode tree = mapper.readTree(in);
			if (tree != null && tree.findValuesAsText("language") != null && tree.findValuesAsText("language").size() > 0) {
				language = tree.findValuesAsText("language").get(0);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch(UnsupportedEncodingException e) {
			log.error("getLanguageForText(Encoding not supported)",e);
		}
		return language;
	}

}
