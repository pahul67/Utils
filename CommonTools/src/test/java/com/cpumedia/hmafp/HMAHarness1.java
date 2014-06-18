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
public class HMAHarness1 {

    public static void main(String[] args)  {
    	System.out.println("Instanciating HMAFreeProxiesReader");
    	HMAFreeProxiesReader hmafpr = new HMAFreeProxiesReader();
    	System.out.println("Instanciated HMAFreeProxiesReader");
    	hmafpr.read();
    	System.out.println("Read proxy list from HMA");
		HMAFreeProxyList proxyList = hmafpr.getProxyList();
		proxyList.sortByDate(true);
		proxyList.write();

    }


}
