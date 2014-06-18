package com.cpumedia.hmafp;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.IOException;

import com.cpumedia.tools.ProcResult;

//
// Hide My Ass free proxies
//
public class HMAHarness3 {

    public static void main(String[] args) throws IOException, InterruptedException {

		String result = ProcResult.consumeAsString("curl -x 46.188.10.224:3128 https://play.google.com/store/apps/category/FINANCE?feature=category-nav");
//    	System.out.println(result);
		
		Document doc = Jsoup.parse(result);
    
    }

}
