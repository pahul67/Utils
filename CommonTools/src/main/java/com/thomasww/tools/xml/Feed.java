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
public class Feed {

	/**
	 * The logger.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Log log = new Log4JLogger("com.thomasww.tools");
	
	/**
	 * The uri.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String uri;
	
	/**
	 * The name
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String name;
	
	/**
	 * The items
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private List<Item> items = new ArrayList<Item>();

	/**
	 * The uri getter.
	 * @return The uri.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public String getUri() {
		if (log.isInfoEnabled())
			log.info("getUri(returning the uri: "+uri+")");
		return uri;
	}

	/**
	 * The uri setter.
	 * @param uri The uri to set.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setUri(String uri) {
		if (log.isInfoEnabled())
			log.info("setUri(setting the uri to: "+uri+")");
		this.uri = uri;
	}

	/**
	 * The name getter.
	 * @return The name.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public String getName() {
		if (log.isInfoEnabled())
			log.info("getName(returning the feed name: "+name+")");
		return name;
	}

	/**
	 * The name setter.
	 * @param name The name to set.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setName(String name) {
		if (log.isInfoEnabled())
			log.info("setName(setting the name of the feed to: "+name+")");
		this.name = name;
	}

	/**
	 * The items getter.
	 * @return The items.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public List<Item> getItems() {
		if (log.isInfoEnabled())
			log.info("getItems(returning the list of items: "+items.size()+")");
		return items;
	}

	/**
	 * The items setter.
	 * @param items The items to set.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setItems(List<Item> items) {
		if (log.isInfoEnabled())
			log.info("setItems(setting the list of items: "+items.size()+")");
		this.items = items;
	}
	
	/**
	 * Adds an item to the list of items.
	 * @param item The item to add.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void addItem(Item item) {
		this.items.add(item);
		if (log.isInfoEnabled())
			log.info("addItem(added 1 item to the list of items, final size of the list: "+items.size()+")");
	}
	
	/**
	 * Removes the item <code>Item</code> from the list of items.
	 * @param item The item to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeItem(Item item) {
		this.items.remove(item);
		if (log.isInfoEnabled())
			log.info("removeItem(removed 1 item to the list of items, final size of the list: "+items.size()+")");
	}
	
	/**
	 * Removes the item at index i from the list of items.
	 * @param i The index of the item to remove.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void removeItem(int i) {
		if (i >= 0 && i < items.size())
			this.items.remove(i);
		if (log.isInfoEnabled())
			log.info("removeItem(removed 1 item to the list of items at index "+i+", final size of the list: "+items.size()+")");
	}
	
	/**
	 * Returns the String representation of the feed.
	 * @see java.lang.Object#toString()
	 * @return The <code>String</code> representation of the feed.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	@Override
	public String toString() {
		StringBuilder strValue = new StringBuilder("Feed:\nuri: ").append(uri).append("\nname: ").append("\nitems: ").append(items.toString());
		return strValue.toString();
	}
}
