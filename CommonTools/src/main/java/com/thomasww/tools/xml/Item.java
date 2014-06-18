/**
 * 
 */
package com.thomasww.tools.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public class Item {

	/**
	 * The logger.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Log log = new Log4JLogger("com.thomasww.tools");
	
	/**
	 * The tags
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private List<Tag> tags = new ArrayList<Tag>();

	/**
	 * The tags getter.
	 * @return The tags.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public List<Tag> getTags() {
		log.debug("Returning tags: "+tags);
		return tags;
	}

	/**
	 * The tags setter.
	 * @param tags The tags to set.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	/**
	 * Adds a tag to the list of tags.
	 * @param tag The tag to add.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void addTag(Tag tag) {
		this.tags.add(tag);
	}
	
	/**
	 * Removes the <code>tag</code> tag from the list of tags.
	 * @param tag The tag to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeTag(Tag tag) {
		this.tags.remove(tag);
	}
	
	/**
	 * Remove the tag at index i from the list of tags.
	 * @param i The index of the tag to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeTag(int i) {
		if (i >= 0 && i < this.tags.size())
			this.tags.remove(i);
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
		StringBuilder strValue = new StringBuilder("item:\ntags: \n").append(tags.toString());
		return strValue.toString();
	}
}
