/**
 * 
 */
package com.thomasww.tools.xml;

import java.io.InputStream;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public interface Parser {
	public Feed parse(InputStream in);
}
