package com.cpumedia.hmafp;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;

//
// Hide My Ass free proxies
//
public class HMAHarness2 {

    public static void main(String[] args)  {
    
    	HMAFreeProxiesReader hmafpr = new HMAFreeProxiesReader(20);
    	hmafpr.read();
		HMAFreeProxyList proxyList = hmafpr.getProxyList();
		HMAFreeProxyList proxyListDE = proxyList.getAllCountry("fr");
//		HMAFreeProxyList proxyListDE = proxyList.getMostRecentByCriteria("de", 30, 90);
//		proxyListDE.write();
		proxyListDE.write("fr.txt");

    }

}
