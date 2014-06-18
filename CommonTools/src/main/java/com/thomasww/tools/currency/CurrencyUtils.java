/**
 * Created on Jul 27, 2012.
 * Copyright ©Decode Consulting.
 */
package com.thomasww.tools.currency;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.thomasww.tools.http.Client;

/**
 * @author Paul Thomas <paul@thomasww.com>
 *
 */
public class CurrencyUtils {

	private static final String URL_BASE = "http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate";
	private static final String PARAM_FROM = "FromCurrency";
	private static final String PARAM_TO = "ToCurrency";

	public double getRateToEuro(String currency) {
		double rate = 1.0;
		Client client = Client.getInstance();
		InputStream in = client.getFeed(URL_BASE + "?" + PARAM_FROM + "=" + currency + "&" + PARAM_TO + "=EUR");
		Parser parser = new Parser();
		return parser.parse(in);
	}

	private class Parser {

		public double parse(InputStream in) {
			double rate = -1.0;

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
			return handler.getRate();
		}
	}
	
	private class Handler implements ContentHandler {
		
		private double rate = -1.0;

		/**
		 * @param ch
		 * @param start
		 * @param length
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if (rate >= 0) {
				String strRate = "";
				for (int i = start; i < start + length; i++) {
					strRate += ch[i];
				}
				System.out.println(strRate);
				rate = Double.parseDouble(strRate);
			}
		}

		/**
		 * @return
		 * 
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		public double getRate() {
			System.out.println("Rate: " + this.rate);
			return this.rate;
		}

		/**
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#endDocument()
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void endDocument() throws SAXException {
			// TODO Auto-generated method stub

		}

		/**
		 * @param uri
		 * @param localName
		 * @param qName
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			
		}

		/**
		 * @param prefix
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void endPrefixMapping(String prefix) throws SAXException {
			// TODO Auto-generated method stub

		}

		/**
		 * @param ch
		 * @param start
		 * @param length
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void ignorableWhitespace(char[] ch, int start, int length)
				throws SAXException {
			// TODO Auto-generated method stub

		}

		/**
		 * @param target
		 * @param data
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String, java.lang.String)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void processingInstruction(String target, String data)
				throws SAXException {
			// TODO Auto-generated method stub

		}

		/**
		 * @param locator
		 * @see org.xml.sax.ContentHandler#setDocumentLocator(org.xml.sax.Locator)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void setDocumentLocator(Locator locator) {
			// TODO Auto-generated method stub

		}

		/**
		 * @param name
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void skippedEntity(String name) throws SAXException {
			// TODO Auto-generated method stub

		}

		/**
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#startDocument()
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void startDocument() throws SAXException {
			// TODO Auto-generated method stub

		}

		/**
		 * @param uri
		 * @param localName
		 * @param qName
		 * @param atts
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			if ("double".equals(qName)) {
				this.rate = 0.0;
			}
		}

		/**
		 * @param prefix
		 * @param uri
		 * @throws SAXException
		 * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String, java.lang.String)
		 *
		 * @author Paul Thomas <paul@thomasww.com>
		 */
		@Override
		public void startPrefixMapping(String prefix, String uri)
				throws SAXException {
			// TODO Auto-generated method stub

		}

	}
}
