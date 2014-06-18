/**
 * 
 */
package com.thomasww.tools.xml.rss;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.thomasww.tools.xml.Attribute;
import com.thomasww.tools.xml.Feed;
import com.thomasww.tools.xml.Item;
import com.thomasww.tools.xml.Tag;

/**
 * @author Paul Thomas <paul@thomasww.com>
 * This class describes and implements the way to handle and parse a RSS feed. It 
 * implements the <code>org.xml.sax.ContentHandler</code> interface.
 * @see org.xml.sax.ContentHandler 
 */
public class Handler implements ContentHandler {
	
	/**
	 * The logger.
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Log log = new Log4JLogger(this.getClass().getPackage().getName());
	
	/**
	 * The feed
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Feed feed;

	/**
	 * The current item
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Item item;

	/**
	 * The current tag
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private Tag tag;

	/**
	 * The current value
	 *
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	private String curValue = "";

	/**
	 * The default empty constructor.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public Handler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 * 
	 * @param uri The Namespace URI, or the empty string if the element has no Namespace 
	 * URI or if Namespace processing is not being performed.
	 * @param localName The local name (without prefix), or the empty string if Namespace 
	 * processing is not being performed.
	 * @param qName The qualified name (with prefix), or the empty string if qualified 
	 * names are not available.
	 * @param atts The attributes attached to the element. If there are no attributes, it 
	 * shall be an empty Attributes object. The value of this object after startElement 
	 * returns is undefined.
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		if ("channel".equalsIgnoreCase(localName))
			feed = new Feed();
		else if ("item".equalsIgnoreCase(localName)){
			item = new Item();
		} else if (item != null) {
			tag = new Tag();
			tag.setName(localName);
			for (int i = 0; i < atts.getLength(); i++) {
				Attribute att = new Attribute();
				att.setName(atts.getLocalName(i));
				att.setValue(atts.getValue(i));
				tag.addAttribute(att);
			}
		}
		if (log.isDebugEnabled())
			log.debug("startElement(uri: "+uri+", localName: "+localName+", qName: "+qName+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 * @param uri
	 * @param localName
	 * @param qName
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equalsIgnoreCase(localName)) {
			feed.addItem(item);
			item = null;
		} else if (item != null) {
			tag.setValue(curValue);
			item.addTag(tag);
			tag = null;
			curValue = "";
		}
		if (log.isDebugEnabled())
			log.debug("endElement(uri: "+uri+", localName: "+localName+", qName: "+qName+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
	 * 
	 * @param prefix The prefix that was being mapped. This is the empty string when a 
	 * default mapping scope ends.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
		if (log.isDebugEnabled())
			log.debug("endPrefixMapping(prefix: "+prefix+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	 * 
	 * @param name The name of the skipped entity. If it is a parameter entity, the name will 
	 * begin with '%', and if it is the external DTD subset, it will be the string "[dtd]".
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void skippedEntity(String name) throws SAXException {
		if (log.isInfoEnabled())
			log.info("skippedEntity(name: "+name+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
	 * 
	 * @param prefix The Namespace prefix being declared. An empty string is used for the 
	 * default element namespace, which has no prefix.
	 * @param uri The Namespace URI the prefix is mapped to.
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		if (log.isDebugEnabled())
			log.debug("startPrefixMapping(prefix: "+prefix+", uri: "+uri+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 * @param ch The characters from the XML document.
	 * @param start The start position in the array.
	 * @param length The number of characters to read from the array
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		String validChars = "";
		for (int i = start; i < start+length; i++)
			validChars += ch[i];
		if (tag != null)
			curValue += validChars;
		if (log.isDebugEnabled())
			log.debug("characters(characters array length: "+ch.length+", start: "+start+", length: "+length+", used characters: "+validChars+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#startDocument()
	 * @throws SAXException
	 */
	public void startDocument() throws SAXException {
		if (log.isDebugEnabled())
			log.debug("startDocument()");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#endDocument()
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void endDocument() throws SAXException {
		if (log.isDebugEnabled())
			log.debug("endDocument()");
		//System.out.println(feed!=null?feed.toString():"feed is empty or malformed");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
	 * @param ch The characters from the XML document.
	 * @param start The start position in the array.
	 * @param length The number of characters to read from the array
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		String chars = "";
		for (int i = 0; i < ch.length; i++)
			chars += ch[i];
		String validChars = "";
		for (int i = start; i < start+length; i++)
			validChars += ch[i];
		if (log.isDebugEnabled())
			log.debug("ignorableWhitespace(characters array: "+chars+", start: "+start+", length: "+length+", valid characters: "+validChars+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
	 * @param target The processing instruction target
	 * @param data The processing instruction data, or null if none was supplied. The data does not include any whitespace separating it from the target
	 * @throws SAXException
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void processingInstruction(String target, String data) throws SAXException {
		if (log.isInfoEnabled())
			log.info("processingInstruction(target: "+target+", data: "+data+")");
	}

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
	 * @param locator An object that can return the location of any SAX document event.
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public void setDocumentLocator(Locator locator) {
		if (log.isDebugEnabled())
			log.debug("setDocumentLocator(locator public ID: "+locator.getPublicId()+")");
	}

	/**
	 * @return
	 * 
	 * @author Paul Thomas <paul@thomasww.com>
	 */
	public Feed getFeed() {
		return this.feed;
	}

}
