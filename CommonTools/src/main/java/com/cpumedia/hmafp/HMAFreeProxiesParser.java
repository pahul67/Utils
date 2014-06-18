package com.cpumedia.hmafp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Integer;
import java.io.IOException;

/**
 * "Hide My Ass" free proxy HTML page parser
 * @author William Hill <william.hill@gmail.com>
 *
 */
public class HMAFreeProxiesParser {

	private Calendar cal = null;
	private Document doc = null;
	private HMAFreeProxyList proxyList = null;

	/**
	 * HMAFreeProxiesParser constructor
	 *
	 * @param d	JSoup document to be parsed
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    public HMAFreeProxiesParser(Document d) {

		cal = Calendar.getInstance();
		doc = d;
		proxyList = new HMAFreeProxyList();

    }

	/**
	 * Parse JSoup document
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    public void parse() {
    
    	HMAFreeProxy hmafp = null;

	    Elements trs = doc.getElementsByTag("tr");
        for (Element tr : trs) {
        	if((tr.attr("class").equals("") || tr.attr("class").equals("altshade")) && !tr.attr("rel").equals("")) {
				hmafp = doRow(tr, cal.getTimeInMillis());
				if(hmafp != null) {
					proxyList.add(hmafp);
		        }
	        }
        }

    }

	/**
	 * Return HMAFreeProxyList parsed by this instance.
	 *
	 * @return HMAFreeProxyList
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    public HMAFreeProxyList getProxyList() {
    
    	return proxyList;

    }

	/**
	 *
	 * @param row	HTML table row to be parsed
	 * @param ts	current time
	 * @return HMAFreeProxy
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    private HMAFreeProxy doRow(Element row, long ts) {
        
        String strUpdateTS = null;
        String strCountry = null;
        String strSpeed = null;
        String strConnectivity = null;
        String strType = null;
        String strAnonymity = null;
        
        final String format = "yyyyMMddHHmm";
        final SimpleDateFormat sdf = new SimpleDateFormat(format);

        Elements divs = row.getElementsByTag("div");
        Element child = null;
        for (Element div : divs) {
            if (div.attr("class").equals("speedbar response_time")) {
	            child = div.child(0);
	            if (child != null && child.tagName().equals("div") && child.attr("style") != null) {
	            	strSpeed = child.attr("style").substring(6);
			        if (strSpeed.endsWith("%")) {
			        	strSpeed = strSpeed.substring(0, strSpeed.length() - 1);
			        }
		        }
	        }
            if (div.attr("class").equals("speedbar connection_time")) {
	            child = div.child(0);
	            if (child != null && child.tagName().equals("div") && child.attr("style") != null) {
	            	strConnectivity = child.attr("style").substring(6);
			        if (strConnectivity.endsWith("%")) {
			        	strConnectivity = strConnectivity.substring(0, strConnectivity.length() - 1);
			        }
		        }
	        }
        }

        Elements spans = row.getElementsByTag("span");
        for (Element span : spans) {
            if (span.attr("class").equals("updatets ")) {
                strUpdateTS = span.text();
	        }
            if (span.attr("class").equals("country")) {
                strCountry = span.text();
	        }
        }

        Elements tds = row.getElementsByTag("td");
        strType = tds.get(tds.size() - 2).text();
        Element ipEle = null;
        Element portEle = null;
        for (int i = 0; i < tds.size(); i++) {
            if (tds.get(i).attr("class").equals("leftborder timestamp")) {
            	ipEle = tds.get(i + 1);
            	portEle = tds.get(i + 2);
	        }
            if (tds.get(i).attr("class").equals("rightborder")) {
                strAnonymity = tds.get(i).text();
	        }
        }

        Element styleEle = row.getElementsByTag("style").get(0);

        String strTS = makeTS(makeTS(ts, strUpdateTS));
        String strISO = XlatCountryName2ISO.name2Iso(strCountry);
        if (strISO == null) {
            strISO = strCountry;
	    }

        HMAFreeProxy hmafp = new HMAFreeProxy();
        hmafp.strTS = strTS;
        hmafp.strISO = strISO;
        hmafp.speed = Integer.parseInt(strSpeed);
        hmafp.connectivity = Integer.parseInt(strConnectivity);
        hmafp.strType = strType;
        hmafp.strAnonymity = strAnonymity;
        hmafp.strIP = doIP(ipEle, styleEle);
        hmafp.strPort = portEle.text();
        
        return hmafp;
    }

	/**
	 * Returns "hidden" IP address
	 *
	 * @param ip	JSoup Element that contains the "hidden" IP address
	 * @param style	JSoup Element that contains the CSS stylesheet instructions to hide the IP address
	 * @return String
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    private String doIP(Element ip, Element style)  {
    
    	String ret = "";
    	
    	HashMap<String, String> xInlines = getNoDisplayInlines(style.toString());
		Element e = null;
		TextNode t = null;
		
		Node child = ip.childNode(0);
		Node grandChild = child.childNode(0);
		Node sibling = null;
		
		sibling = grandChild.nextSibling();
		while(sibling != null) {
		    if (sibling instanceof Element) {
		    	e = (Element)sibling;
	            if (e.hasAttr("class") && !xInlines.containsKey(e.attr("class"))) {
	                ret += e.text();
	    	    }
            	else if (e.hasAttr("style") && e.attr("style").equals("display: inline")) {
	                ret += e.text();
	        	}
            	else	{
	            	;
	        	}
    		}
		    else if (sibling instanceof TextNode) {
		    	t = (TextNode)sibling;
	                ret += t.text();
    		}
		    else {
		    	;
    		}
    		
			sibling = sibling.nextSibling();
		}

    	return ret;

    }

	/**
	 * Returns HashMap of CSS stylesheet identifiers that are used to hide text on the HTML page
	 *
	 * @param para	String that contains the CSS stylesheet instructions that define text to NOT be displayed by the HTML page
	 * @return HashMap<String, String>
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    private HashMap<String, String> getNoDisplayInlines(String para)	{
    
    	HashMap<String, String> hm = new HashMap<String, String>();
    
		String[] s = para.split("\n");
        for (int i = 0; i < s.length; i++) {
	        if (s[i].contains("display:none")) {
	        	hm.put(s[i].substring(1, 5), "1");
	        }
        }

		return hm;

    }

	/**
	 * Returns timestamp of when the free proxy was last tested
	 *
	 * @param ts	current time
	 * @param webTS	delay displayed on HTML page
	 * @return long
	 *
	 * @author William Hill <william.hill@gmail.com>
	 */
    private long makeTS(long ts, String webTs)	{
    	long ret;
    	long delay;
    	
    	String duration = null;
    	String units = null;
    	
    	int idx = webTs.indexOf(" ");
	    if (idx != -1) {
	    	duration = webTs.substring(0, idx);
	    	units = webTs.substring(idx + 1);

			Integer i = new Integer(duration);
			delay = i.longValue();    	
		    if (units.equals("secs")) {
	    		ret = ts - (delay * 1000);
	    	}
	    	else if (units.equals("minutes")) {
	    		ret = ts - (delay * 1000 * 60);
	    	}
	    	else	{
	    		ret = ts;
	    	}

	    }
	    else	{
	    	ret = ts;
	    }
    	
    	return ret;
    }

    private String makeTS(long ts)	{
		String format = "yyyyMMddHHmm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(ts));
    }

}
