/**
 * 
 */
package com.thomasww.tools.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 *
 *
 * @author Paul Thomas <paul@thomasww.com> 8 sept. 2011
 */
public class Attribute {

	/**
	 * The logger.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Log log = new Log4JLogger("com.thomasww.tools");
	
	/**
	 * The name of the attribute.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String name;
	
	/**
	 * The value of the attribute.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String value;
	
	/**
	 * The name getter.
	 * @return The name.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public String getName() {
		log.debug("Returning name: "+name);
		return name;
	}
	
	/**
	 * The name setter.
	 * @param name The name to set.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The value getter.
	 * @return The value.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * The value setter.
	 * @param value The value to set.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
