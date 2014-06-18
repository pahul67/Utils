package com.cpumedia.hmafp;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * "Hide My Ass" free proxy reader
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class HMAFreeProxiesReader {

	private Document doc = null;
	private String strUrl = null;
	private HMAFreeProxiesParser hmafpp = null;
	private HMAFreeProxyList proxyList = null;
	private int index = 1;
	
	/**
	 * HMAFreeProxiesReader constructor
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public HMAFreeProxiesReader()	{

		proxyList = new HMAFreeProxyList();
		strUrl = "http://www.hidemyass.com/proxy-list/";

	}

	/**
	 * HMAFreeProxiesReader constructor
	 *
	 * @param nb	Number of pages to be read starting at "http://www.hidemyass.com/proxy-list/"
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public HMAFreeProxiesReader(int nb)	{
	
		index = nb;
		proxyList = new HMAFreeProxyList();
		strUrl = "http://www.hidemyass.com/proxy-list/";

	}

	/**
	 * Start reader
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
	public void read() 	{

		for(int i = 0; i < index; i++)	{
			if(i + 1 > 1)	{
				strUrl = "http://www.hidemyass.com/proxy-list/" + Integer.toString(i + 1);
			}
			
		try	{
			doc = Jsoup.connect(strUrl).timeout(10 * 1000).ignoreHttpErrors(true).get();
			hmafpp = new HMAFreeProxiesParser(doc);
			hmafpp.parse();
			proxyList.addAll(hmafpp.getProxyList());
			}
		catch(IOException ioe)	{
			ioe.printStackTrace();
			}

		}

	}

	/**
	 * Return HMAFreeProxyList read and parsed by this instance.
	 *
	 * @return HMAFreeProxyList
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    public HMAFreeProxyList getProxyList() {
    
    	return proxyList;

    }

}
