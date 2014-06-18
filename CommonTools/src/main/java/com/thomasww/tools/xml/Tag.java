/**
 * 
 */
package com.thomasww.tools.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 *
 *
 * @author Paul Thomas <paul@thomasww.com> 8 sept. 2011
 */
public class Tag {

	/**
	 * The logger.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Log log = new Log4JLogger("com.thomasww.tools");
	
	/**
	 * The tag name.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String name;
	
	/**
	 * The tag value.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String value;
	
	/**
	 * The tag attributes.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private List<Attribute> atts = new ArrayList<Attribute>();
	
	/**
	 * The subtags of this tag.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private List<Tag> subtags = new ArrayList<Tag>(); 
	
	/**
	 * The name getter
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
	
	/**
	 * The attributes getter.
	 * @return The list of attributes.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public List<Attribute> getAttributes() {
		return atts;
	}
	
	/**
	 * The attributes setter.
	 * @param atts The list of attributes to set.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setAttributes(List<Attribute> atts) {
		this.atts = atts;
	}
	
	/**
	 * Adds an attribute to the list of attributes.
	 * @param att The attribute to add.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void addAttribute(Attribute att) {
		this.atts.add(att);
	}
	
	/**
	 * Remove the att <code>Attribute</code> from the list of attributes.
	 * @param att The attribute to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeAttribute(Attribute att) {
		this.atts.remove(att);
	}
	
	/**
	 * Removes the attribute at index i from the list of attributes.
	 * @param i The index of the attribute to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeAttribute(int i) {
		if (i >= 0 && i < this.atts.size())
			this.atts.remove(i);
	}

	/**
	 * The subtags getter.
	 * @return The subtags.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public List<Tag> getSubtags() {
		return subtags;
	}

	/**
	 * The subtags setter.
	 * @param subtags The subtags to set.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setSubtags(List<Tag> subtags) {
		this.subtags = subtags;
	}

	/**
	 * Adds a tag to the subtags list.
	 * @param tag The tag to add.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void addSubtag(Tag tag) {
		this.subtags.add(tag);
	}
	
	/**
	 * Removes the tag <code>Tag</code> from the subtags list.
	 * @param tag The subtag to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeSubtag(Tag tag) {
		this.subtags.remove(tag);
	}
	
	/**
	 * Removes the tag at index i from the subtag list.
	 * @param i The index of the tag to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeSubtag(int i) {
		if (i >= 0 && i < this.atts.size())
			this.subtags.remove(i);
	}
	
	/**
	 * Returns the String representation of the item.
	 * @see java.lang.Object#toString()
	 * @return The String representation of the item.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	@Override
	public String toString() {
		StringBuilder strValue = new StringBuilder("tag: name: ").append(name).append("|value: ").append(value).append("|attributes: ").append(atts.toString());
		return strValue.toString();
	}
}
